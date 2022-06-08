package br.com.brunorodrigo.adotai.security;

import br.com.brunorodrigo.adotai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static br.com.brunorodrigo.adotai.security.SecurityConstants.LOGIN_URL;
import static br.com.brunorodrigo.adotai.security.SecurityConstants.PETS_URL;
import static br.com.brunorodrigo.adotai.security.SecurityConstants.USERS_URL;
import static br.com.brunorodrigo.adotai.security.SecurityConstants.USER_ROLE;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationFilter customFilter = new AuthenticationFilter(authenticationManagerBean());
        customFilter.setFilterProcessesUrl(LOGIN_URL);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(LOGIN_URL.concat("/**")).permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, PETS_URL.concat("/**")).permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, USERS_URL.concat("/**")).permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, PETS_URL.concat("/**")).hasAnyAuthority(USER_ROLE);
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customFilter);
        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new UserDetailServiceImpl(userService);
    }


}
