package fr.vdelll.onlinebank.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import fr.vdelll.onlinebank.OnlinebankApplication;

public class WebInitializer extends SpringBootServletInitializer {

	/**
	 * Permet de lancer le projet WAR dans tomcat
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(OnlinebankApplication.class);
	}
	
}
