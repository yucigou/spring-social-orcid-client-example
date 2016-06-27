package org.jyougo.orcid.api;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.dom.DOMSource;

import org.jyougo.utils.Utils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.social.connect.Connection;
import org.springframework.social.orcid.api.MessageOperations;
import org.springframework.social.orcid.api.OrcidApi;
import org.springframework.social.orcid.jaxb.beans.OrcidProfile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

/**
 * Handles requests for API.
 * 
 * @author Yuci
 *
 */
@RestController
@RequestMapping("api")
public class OrcidController {

    @Inject
    @Qualifier("orcid")
    Connection<OrcidApi> orcid;

    @RequestMapping(value = "/orcid/profile", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public OrcidProfile getProfile(HttpServletRequest request) {
        System.out.println("Entering getProfile()");
        
        if (orcid == null) {
            System.out.println("ORCID connection null");
            return null;
        }

        OrcidApi orcidApi = orcid.getApi();
        if (orcidApi == null) {
            System.out.println("orcidApi null");
            return null;
        }
        
        MessageOperations messageOperations = orcidApi.messageOperations();
        if (messageOperations == null) {
            System.out.println("messageOperations null");
            return null;
        }
        
        OrcidProfile orcidProfile = messageOperations.getOrcidProfile(orcid.getKey().getProviderUserId(), true);
        return orcidProfile;
    }    

    @RequestMapping(value = "/orcid/add/works", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Boolean addWorks(HttpServletRequest request) {
        System.out.println("Entering addWorks()");
        
        if (orcid == null) {
            System.out.println("ORCID connection null");
            return false;
        }

        OrcidApi orcidApi = orcid.getApi();
        if (orcidApi == null) {
            System.out.println("orcidApi null");
            return false;
        }
        
        MessageOperations messageOperations = orcidApi.messageOperations();
        if (messageOperations == null) {
            System.out.println("messageOperations null");
            return false;
        }
        
        String s = Utils._getWorksXml();
        Document doc;
        DOMSource domSource;
        try {
            doc = Utils.stringToDom(s);
            domSource = Utils.getDomSource(doc);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        
        boolean ret = messageOperations.addWorks(orcid.getKey().getProviderUserId(), domSource);
        return ret;
    }
}
