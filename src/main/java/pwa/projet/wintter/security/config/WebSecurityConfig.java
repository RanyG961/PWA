package pwa.projet.wintter.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pwa.projet.wintter.component.*;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.*;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/", "/home", "/js/**", "/css/**")
//                .permitAll()
//                .antMatchers("/api/authentification/**")
//                .permitAll()
//                .antMatchers("/home")
//                .permitAll()
//                .antMatchers("/login_error")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .permitAll()
//                .loginPage("/MyLogin")
//                .successHandler(authSuccessHandler)
//                .failureHandler(authFailureHandler)
////                .defaultSuccessUrl("/test.html", true)
//                .and()
//                .logout()
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .permitAll();
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/", "/home", "/js/**", "/css/**", "/login/**", "/api/user/token/refresh/**", "/api/user/register/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/user/getUsers/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(GET,)
//        http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
}
