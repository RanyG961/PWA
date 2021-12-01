package pwa.projet.wintter.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
    {
        String username = request.getParameter("username");
        String error = exception.getMessage();
        System.out.println("Login " + username + " incorrect ! Reason : " + error);

        super.setDefaultFailureUrl("/login_error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
