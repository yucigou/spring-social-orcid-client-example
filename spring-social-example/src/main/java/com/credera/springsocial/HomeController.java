package com.credera.springsocial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
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

    @Inject
    @Qualifier("facebook")
    Connection<Facebook> facebook;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "home";
    }

    @RequestMapping(value = "/orcid", method = RequestMethod.GET)
    public String welcomeOrcid(HttpServletResponse response, Locale locale, Model model) {
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

        response.addCookie(new Cookie("REMEMBER_ME", "SDFDSFLK32K90JFSDFSF2P39FJDIO"));
        
        return "welcome_orcid";
    }

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String welcomeFacebook(Locale locale, Model model) {
        Facebook fb = facebook.getApi();

        List<Page> moviePages = fb.likeOperations().getMovies();
        
        List<String> movies = new ArrayList<String>();
        for (Page p : moviePages) {
            movies.add(p.getName());
        }
        
        // Uncomment the following line to see how easy posting to your timeline is.
        //fb.feedOperations().post(userId, "I just tried out the My Movies example site!");
        
        int numFriends = fb.friendOperations().getFriends().size();
        
        model.addAttribute("facebook", true);
        model.addAttribute("numFriends", numFriends);
        String name = facebook.getDisplayName();
        model.addAttribute("name", name);
        String imageUrl = facebook.getImageUrl();
        model.addAttribute("imgUrl", imageUrl);
        model.addAttribute("movies", movies);
        
        return "welcome_facebook";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(HttpServletResponse response, Locale locale, Model model) {
        
        try {
            OrcidApi orcidApi = orcid.getApi();
            return welcomeOrcid(response, locale, model);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            Facebook fb = facebook.getApi();
            return welcomeFacebook(locale, model);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "home";
    } 

}
