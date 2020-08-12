package com.example.ehCachecacheprovider.config.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.cache.CacheManager;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ehCachecacheprovider.model.Student;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
@EnableCaching
public class HazelcastEmbeddedCacheConfig implements JCacheManagerCustomizer {

//	@Bean
//	Config config() {
//
//		Config config = new Config();
//		MapConfig mapConfig = new MapConfig();
//		mapConfig.setTimeToLiveSeconds(300);
//		config.getMapConfigs().put("cars", mapConfig);
//
//		return config;
//	}

	@Override
	public void customize(CacheManager cacheManager) {
		// TODO Auto-generated method stub
		CompleteConfiguration<Long, Student> cc = new MutableConfiguration<Long, Student>()
				.setTypes(Long.class, Student.class).setExpiryPolicyFactory(
						FactoryBuilder.factoryOf(new AccessedExpiryPolicy(new Duration(TimeUnit.SECONDS, 60))));
		cacheManager.createCache("students", cc);

		@SuppressWarnings("unchecked")
		CompleteConfiguration<Object, List<Student>> allStudentCacheConfig = new MutableConfiguration<Object, List<Student>>()
				.setTypes(Object.class, (Class<List<Student>>) (Class<?>) List.class).setExpiryPolicyFactory(
						FactoryBuilder.factoryOf(new CreatedExpiryPolicy(new Duration(TimeUnit.SECONDS, 30))));
		cacheManager.createCache("allStudents", allStudentCacheConfig);

	}

}
