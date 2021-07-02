package br.edu.utfpr.cp.espjava.crudcidades;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

import net.minidev.json.JSONArray;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .authorizeRequests()
                .antMatchers("/")
                .hasAnyAuthority("listar", "admin")
                .antMatchers("/criar").hasAuthority("admin")
                .antMatchers("/excluir").hasAuthority("listar")
                .antMatchers("/preparaAlterar").hasAuthority("admin")
                .antMatchers("/alterar").hasAuthority("admin")
                .anyRequest().denyAll()
            .and()
            .oauth2Login(l -> {
                    l.userInfoEndpoint().userAuthoritiesMapper(userAuthoritiesMapper());
                }).logout().permitAll();
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            Optional<OidcUserAuthority> awsAuthority = (Optional<OidcUserAuthority>) authorities.stream()
                    .filter(grantedAuthority -> "ROLE_USER".equals(grantedAuthority.getAuthority())).findFirst();

            if (awsAuthority.isPresent()) {
                mappedAuthorities = ((JSONArray) awsAuthority.get().getAttributes().get("cognito:groups")).stream()
                        .map(role -> new SimpleGrantedAuthority("" + role)).collect(Collectors.toSet());
            }

            return mappedAuthorities;
        };
    }

}