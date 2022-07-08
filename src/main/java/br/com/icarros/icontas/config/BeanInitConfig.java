package br.com.icarros.icontas.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.icarros.icontas.config.security.data.SecurityConstsICarros;

@Configuration
public class BeanInitConfig {

	/**
	 * Utilizado em UserServiceDetailsImpl. Como BCryptPassword não é um @Component, foi 
	 * inicializado como bean.
	 * @return
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Permite fazer a injeção desse conjunto de constantes em Beans.
	 * @return
	 */
	@Bean
	public SecurityConstsICarros getSecConsts() {
		return new SecurityConstsICarros();
	}
	
	/**
	 * So SpringApplicationContext.getBean pode ser utilizado para obter um bean em uma classe não
	 * gerenciada pelo spring, por exemplo:
	 * 1. Obter AppProperties em SecurityConstsIcarros. SecurityConstsIcarros não é um bean.
	 * @return
	 */
	@Bean 
	public SpringApplicationContext springApplicationContext()
	{
		return new SpringApplicationContext();
	}

	/**
	 * Utilizado para obter o AppProperties em SecurityConstsIcarros, pois SecurityConsts não
	 * tem anotação @Service ou @Component. É utilizado o SpringApplicationContext para obter
	 * esse bean.
	 * Nota: SpringApplicationContext é uma classe personalizada nesste projeto.
	 * @return
	 */
	@Bean(name="AppProperties")
	public AppProperties getAppProperties()
	{
		return new AppProperties();
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
