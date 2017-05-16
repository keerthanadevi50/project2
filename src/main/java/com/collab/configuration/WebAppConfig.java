package com.collab.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
	@Configuration
	@EnableWebMvc
	// to create beans for all the classes under the package com.****....
	@ComponentScan(basePackages="com.collab.*")
	public class WebAppConfig extends WebMvcConfigurerAdapter {

	/*@Bean
	public InternalResourceViewResolver getViewResolver(){
	InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
viewResolver.setPrefix("/WEB-INF/");
	viewResolver.setSuffix(".jsp");
	return viewResolver;

	} */

	public void addResourceHandlers(ResourceHandlerRegistry registry){
	registry.addResourceHandler("/resources/**")
	.addResourceLocations("/WEB-INF/resources/");
	}


	}

