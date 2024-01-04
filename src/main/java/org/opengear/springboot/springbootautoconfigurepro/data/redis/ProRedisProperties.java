package org.opengear.springboot.springbootautoconfigurepro.data.redis;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.data.redis")
public class ProRedisProperties {

    private RedisProperties.ClientType clientType;

    private RedisProperties.Sentinel sentinel;

    private RedisProperties.Cluster cluster;

    private final RedisProperties.Ssl ssl = new RedisProperties.Ssl();

    private final RedisProperties.Jedis jedis = new RedisProperties.Jedis();

    private final RedisProperties.Lettuce lettuce = new RedisProperties.Lettuce();
}
