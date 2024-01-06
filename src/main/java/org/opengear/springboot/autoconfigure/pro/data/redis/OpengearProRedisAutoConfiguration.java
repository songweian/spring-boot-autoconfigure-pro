package org.opengear.springboot.autoconfigure.pro.data.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import org.opengear.springboot.autoconfigure.pro.support.ValueSetter;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;

import java.util.Optional;

@Configuration
@EnableConfigurationProperties(ProRedisProperties.class)
public class OpengearProRedisAutoConfiguration {

    @Bean
    public LettuceClientConfigurationBuilderCustomizer openGearProLettuceClientConfigurationBuilderCustomizer(ProRedisProperties proRedisProperties) {
        return (builder) -> {
            LettuceClientConfiguration originClientConfiguration = builder.build();
            Optional<ClientOptions> clientOptions = originClientConfiguration.getClientOptions();
            Optional<ClientOptions> customizedClientOptions = customizeClientOptions(clientOptions, proRedisProperties);
            customizedClientOptions.ifPresent(builder::clientOptions);
        };
    }

    private Optional<ClientOptions> customizeClientOptions(Optional<ClientOptions> originClientOptions, ProRedisProperties proRedisProperties) {
        if (originClientOptions.isEmpty()) {
            return Optional.empty();
        }
        ClientOptions clientOptions = originClientOptions.get();
        ClientOptions.Builder baseClientOptionsBuilder = ClientOptions.builder();
        // set commons client options

        // set cluster options
        if (clientOptions instanceof ClusterClientOptions && proRedisProperties.getLettuce().getCluster() != null) {
            ClusterClientOptions.Builder builder = ClusterClientOptions.builder(baseClientOptionsBuilder.build());
            ClusterClientOptions build = builder.build();

            LettuceClusterTopologyRefreshOptionsHelpBuilder topologyRefreshOptionsHelpBuilder = LettuceClusterTopologyRefreshOptionsHelpBuilder.builder(build.getTopologyRefreshOptions());

            ProRedisProperties.Lettuce.ClusterTopologyRefreshOptions topologyRefreshOptions = proRedisProperties.getLettuce().getCluster().getTopologyRefreshOptions();
            ValueSetter.of()
                    .setIfValueNotNull(topologyRefreshOptionsHelpBuilder::dynamicRefreshSources, topologyRefreshOptions.getDynamicRefreshSources());

            return Optional.of(builder.build());
        }
        return Optional.of(baseClientOptionsBuilder.build());
    }

}
