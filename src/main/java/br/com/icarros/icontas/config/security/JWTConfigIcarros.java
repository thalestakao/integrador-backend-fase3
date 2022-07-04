package br.com.icarros.icontas.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.icarros.icontas.config.security.data.SecurityConstsICarros;
import br.com.icarros.icontas.config.security.filter.CorrentistaJWTAuthenticationFilterProcess;
import br.com.icarros.icontas.config.security.filter.TokenValidatorFilterProcess;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JWTConfigIcarros {

	
    private UserDetailsService userDetailsService;
	
	private PasswordEncoder encoder;
	
	
	public JWTConfigIcarros(UserDetailsService userDetailsService, PasswordEncoder encoder) {
		this.userDetailsService = userDetailsService;
		this.encoder = encoder;
	}
	
	/**
	 * Confiruação de segurança, especificando as URLs de logins e os respectivos filtros
	 * para a gestão dos tokens.
	 * @return
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        
		http.csrf().disable().authorizeHttpRequests()
		.antMatchers(HttpMethod.POST, SecurityConstsICarros.SIGN_UP_CORRENTISTA_URL).permitAll()
		.antMatchers(SecurityConstsICarros.ALLOWED_URL).permitAll()
		.anyRequest().authenticated()
		.and()
		.authenticationManager(authenticationManager)
		.addFilter(new CorrentistaJWTAuthenticationFilterProcess(authenticationManager))
		.addFilter(new TokenValidatorFilterProcess(authenticationManager))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
		
	}
	
	/**
	 * Configuração do CORS, liberada qualquer origem para testes.
	 * @return
	 */
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }
	
	
}
