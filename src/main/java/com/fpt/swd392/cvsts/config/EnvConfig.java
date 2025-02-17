// package com.fpt.swd392.cvsts.config;

// import org.springframework.context.annotation.Configuration;
// import io.github.cdimascio.dotenv.Dotenv;
// import jakarta.annotation.PostConstruct;

// @Configuration
// public class EnvConfig {
    
//     @PostConstruct
//     public static void init() {
//         Dotenv dotenv = Dotenv.load();
//         dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
//     }
// }
