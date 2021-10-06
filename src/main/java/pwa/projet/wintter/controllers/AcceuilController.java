package pwa.projet.wintter.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AcceuilController {

    @RequestMapping({ "/" })
    public String BaseRedirect() {
    
        return "Acceuil";
    }
    
}
