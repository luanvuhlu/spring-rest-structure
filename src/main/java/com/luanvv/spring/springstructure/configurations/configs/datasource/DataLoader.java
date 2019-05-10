package com.luanvv.spring.springstructure.configurations.configs.datasource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value = { "design" })
public class DataLoader implements ApplicationRunner {
	private static final Logger log = LogManager.getLogger(DataLoader.class);
	
	@Override
	public void run(ApplicationArguments args) {
		// empty
	}

}
