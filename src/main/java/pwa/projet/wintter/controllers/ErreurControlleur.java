package pwa.projet.wintter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErreurControlleur {
    
    @RequestMapping("/Error" )
    public String baseRedirect(Model model){
        
        return "erreur";
    }

}
