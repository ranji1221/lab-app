package com.ranji.lab.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import java.lang.reflect.Method;
import java.time.Duration;

/**
 * 配置Redis缓存机制的Key和Value的生产策略
 * 如果不需要改变，可以不需要写此配置类
 * @author RanJi
 *
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getSimpleName()+".");
                sb.append(method.getName());
                sb.append("[");
                for(Object obj:params){
                    sb.append(obj.toString());
                }
                sb.append("]");
                return sb.toString();
            }};
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //-- 1. 初始化RedisCacheWriter对象
        RedisCacheWriter redisCacheWriter =
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //-- 设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer =
                new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair =
                RedisSerializationContext.
                        SerializationPair.
                        fromSerializer(jsonSerializer);
        //-- 2. 初始化配置对象
        RedisCacheConfiguration defaultCacheConfig=
                RedisCacheConfiguration.
                        defaultCacheConfig().
                        entryTtl(Duration.ofSeconds(60*30)).   //-- 链式设置缓存过期时间30分钟
                        serializeValuesWith(pair);

        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }
}
