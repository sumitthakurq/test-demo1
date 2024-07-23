package com.example.demo;

import java.time.Duration;

import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class DemoApplication extends SpringBootServletInitializer{
//	http://localhost:9099/manage/health/readiness
// http://localhost:9099/manage/health/liveness	
// http://localhost:9099/manage    -- Actuator path
	// http://localhost:8085/hospital/getData
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}

  	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(DemoApplication.class);
	    }  
  	  
  	@Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
      RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);

      return new LettuceConnectionFactory(configuration);
    }
  	  
  	  @Bean
  	  public RedisCacheManager cacheManager() {
  	    RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10)).disableCachingNullValues();

  	    return RedisCacheManager.builder(redisConnectionFactory())
  	        .cacheDefaults(cacheConfig)
  	        .withCacheConfiguration("Hospitals", myDefaultCacheConfig(Duration.ofMinutes(5)))
  	        .withCacheConfiguration("Hospital", myDefaultCacheConfig(Duration.ofMinutes(1)))
  	        .build();
  	  }

  	  private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
  	    return RedisCacheConfiguration
  	        .defaultCacheConfig()
  	        .entryTtl(duration)
  	        .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  	  }
	
}
