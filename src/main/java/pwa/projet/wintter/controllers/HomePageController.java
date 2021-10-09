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
import org.springframework.security.crypto.password.PasswordEncoder;


@RequestMapping("/")
@Controller
@AllArgsConstructor
public class HomePageController
{
    private final UserService userService;

    @PostMapping("/Register")
    public String registerUser(@ModelAttribute("user") User user,Model model)
    {
        User compa = new User();
         compa = userService.getUser(user.getNickName());
        
         if(compa == null){
             //if nickname is not used
            userService.addUser(user);
            return "redirect:/";
         }else{
             //if nickname is used 
             model.addAttribute("erreur", user.getNickName()+"already exist please use another nickname");
             return "redirect:/Error";

         }
        
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



    @PostMapping("/Connexion")
    public String connexionUser(@ModelAttribute("user") User user,Model model)
        {
            User compa = new User();
            compa = userService.getUser(user.getNickName());
            if(compa != null){
                //if user is existing 
                //String test = passwordEncoder.encode(user.getPassword());
                

                model.addAttribute("user", compa);
                return "userPage";
    
            }else{

                model.addAttribute("erreur", user.getNickName()+"NOT EXISTING");

                return "redirect:/Error";
            }
           
        }

}
