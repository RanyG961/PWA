package pwa.projet.wintter.controllers;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pwa.projet.wintter.models.User;
import pwa.projet.wintter.services.UserService;

import javax.servlet.http.HttpServletRequest;

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
        return "redirect:/";
    }

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
    @RequestMapping("/userPage")
    public String connexionUser()
    {
        System.out.println("Here ! ");
        return "userPage";
    }

    @GetMapping ("/login_error")
    public String failedLogin()
    {
        System.out.println("Failed login !");
        return "login_error";
    }

}
