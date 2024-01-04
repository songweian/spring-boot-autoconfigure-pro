package org.opengear.springboot.springbootautoconfigurepro.data.redis;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(ProRedisProperties.class)
public class ProRedisAutoConfiguration {

}
