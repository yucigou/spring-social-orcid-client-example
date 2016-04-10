package org.jygou.orcid.api;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.social.connect.Connection;
import org.springframework.social.orcid.api.MessageOperations;
import org.springframework.social.orcid.api.OrcidApi;
import org.springframework.social.orcid.jaxb.beans.OrcidProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class OrcidController {
    
}
/*
@Controller
@RequestMapping("api")
public class OrcidController {
    @Inject
    @Qualifier("orcid")
    Connection<OrcidApi> orcid;

    @RequestMapping(value = "/orcid/profile", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody OrcidProfile getProfile(HttpServletRequest request) {
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
        
        OrcidProfile orcidProfile = messageOperations.getOrcidProfile();
        return orcidProfile;
    }    
}
*/