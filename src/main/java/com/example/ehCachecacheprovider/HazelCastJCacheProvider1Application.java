package com.example.ehCachecacheprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {HazelcastAutoConfiguration.class})
public class HazelCastJCacheProvider1Application {

	public static void main(String[] args) {
		SpringApplication.run(HazelCastJCacheProvider1Application.class, args);
	}

}
