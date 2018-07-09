package com.fabHotels.IplSchedulerProject;

import com.fabHotels.IplSchedulerProject.Security.ApplicationSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableWebSecurity
public class IplSchedulerProjectApplication {

	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter(){
		return new ApplicationSecurity();
	}

	public static void main(String[] args) {
		SpringApplication.run(IplSchedulerProjectApplication.class, args);
	}
}
