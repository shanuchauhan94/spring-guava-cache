package org.cache.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(24, TimeUnit.HOURS));
        cacheManager.setCacheNames(List.of("myEmployeeCache"));
        return cacheManager;
    }

    /*@Bean
    @Override
    public CacheManager cacheManager() {

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        //  GuavaCache myOrgCache = new GuavaCache("myOrgCache", CacheBuilder.newBuilder().build());
   //     GuavaCache myEmployeeCache = new GuavaCache("myEmployeeCache", CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).build());
        GuavaCache myEmployeeCache = new GuavaCache("myEmployeeCache", CacheBuilder.newBuilder().build());

        // cacheManager.setCaches(Arrays.asList(myOrgCache, myEmployeeCache));
        cacheManager.setCaches(List.of(myEmployeeCache));
        return cacheManager;
    }*/

    @Override
    public KeyGenerator keyGenerator() {
        return new CacheKeyGenerator();
    }


}
