package pwa.projet.wintter.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.services.UserService;

@Controller
public class HomePageController
{
    private final UserService userService;

    public HomePageController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping({ "/" })
    public String baseRedirect(Model model)
    {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/Register")
    public String registerUser(User user)
    {
        userService.addUser(user);
        return "redirect:/";
    }

    @PostMapping("/Connexion")
    public void connexionUser(User user)
    {
        userService.findUserById(user.getUserId());
    }
    
}
