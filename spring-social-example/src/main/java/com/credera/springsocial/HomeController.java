package com.credera.springsocial;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.Connection;
import org.springframework.social.orcid.api.OrcidApi;
import org.springframework.social.orcid.jaxb.beans.OrcidProfile;
import org.springframework.social.orcid.utils.StringUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
    @Qualifier("orcid")
    Connection<OrcidApi> orcid;
		
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Locale locale, Model model) {
	    OrcidApi orcidApi = orcid.getApi();
	    OrcidProfile orcidProfile = orcidApi.messageOperations().getOrcidProfile();
	    orcidProfile.getOrcidBio().getPersonalDetails();
	    
	    String orcidId = orcidProfile.getOrcidIdentifier().getPath();
        
        String givenName = orcidProfile.getOrcidBio().getPersonalDetails().getGivenNames();
        String familyName = orcidProfile.getOrcidBio().getPersonalDetails().getFamilyName();
        String displayName = givenName;
        if (StringUtility.hasContent(familyName)) {
            if (StringUtility.hasContent(displayName)) {
                displayName += " ";
                displayName += familyName;
            } else {
                displayName = familyName;
            }
        }       
		
		model.addAttribute("orcidId", orcidId);
		model.addAttribute("name", displayName);
		
		return "welcome_orcid";
	}
	
}
