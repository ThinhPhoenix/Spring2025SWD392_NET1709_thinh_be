package com.fpt.swd392.cvsts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.fpt.swd392.cvsts.config.EnvConfig;

@SpringBootApplication
public class CvstsApplication {

	public static void main(String[] args) {
		// EnvConfig.init();
		SpringApplication.run(CvstsApplication.class, args);
	}

}
