package pwa.projet.wintter.controllers;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.services.UserService;

@RequestMapping("/")
@Controller
@AllArgsConstructor
public class HomePageController
{
    private final UserService userService;

    @PostMapping("/Register")
    public String registerUser(@ModelAttribute("user") User user)
    {
        userService.addUser(user);
        System.out.println(user.getPassword());
        return "redirect:/";
    }

//
//    public HomePageController(UserService userService) {
//        this.userService = userService;
//    
//
//
    @RequestMapping("/" )
    public String baseRedirect(Model model)
    {
        model.addAttribute("user", new User());
        return "index";
    }
//
//    @PostMapping("/Register")
//    public String registerUser(User user)
//    {
//        userService.addUser(user);
//        return "redirect:/";
//    }
//
//    @PostMapping("/Connexion")
//    public void connexionUser(User user)
//    {
//        userService.findUserById(user.getUserId());
//    }
//
}
