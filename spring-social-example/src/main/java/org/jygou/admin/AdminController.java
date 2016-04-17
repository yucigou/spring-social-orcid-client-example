package org.jygou.admin;

import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String welcome(HttpServletResponse response, Locale locale, Model model) {
        
        return "admin/clients";
    } 

}
