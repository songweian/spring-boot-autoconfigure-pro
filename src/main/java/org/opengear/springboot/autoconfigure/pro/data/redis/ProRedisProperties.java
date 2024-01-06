package org.opengear.springboot.autoconfigure.pro.data.redis;


import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.SslOptions;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.models.partitions.RedisClusterNode;
import io.lettuce.core.protocol.DecodeBufferPolicy;
import io.lettuce.core.protocol.ProtocolVersion;
import io.lettuce.core.protocol.ReadOnlyCommands;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Set;
import java.util.function.Predicate;

@ConfigurationProperties(prefix = "spring.data.redis")
public class ProRedisProperties {
    private Lettuce lettuce;

    @Data
    public static class Lettuce {

        private Boolean autoReconnect;

        private Boolean cancelCommandsOnReconnectFailure;

        private DecodeBufferPolicy decodeBufferPolicy;

        private ClientOptions.DisconnectedBehavior disconnectedBehavior;

        private Boolean pingBeforeActivateConnection;

        private ProtocolVersion protocolVersion;

        private Boolean publishOnScheduler;

        private ReadOnlyCommands.ReadOnlyPredicate readOnlyCommands;

        private Integer requestQueueSize;

        private Charset scriptCharset;

        private SocketOptions socketOptions;

        private SslOptions sslOptions;

        private Boolean suspendReconnectOnProtocolFailure;

        private TimeoutOptions timeoutOptions;

        private Cluster cluster;

        @Data
        public static class Cluster {
            private Boolean closeStaleConnections;

            private Integer maxRedirects;

            private Boolean validateClusterNodeMembership;

            private Predicate<RedisClusterNode> nodeFilter;

            private ClusterTopologyRefreshOptions topologyRefreshOptions;
        }

        @Data
        public static class ClusterTopologyRefreshOptions {

            private Set<io.lettuce.core.cluster.ClusterTopologyRefreshOptions.RefreshTrigger> adaptiveRefreshTriggers;

            private Duration adaptiveRefreshTimeout;

            private Boolean closeStaleConnections;

            private Boolean dynamicRefreshSources;

            private Boolean periodicRefreshEnabled;

            private Duration refreshPeriod;

            private Integer refreshTriggersReconnectAttempts;
        }
    }

    public Lettuce getLettuce() {
        return lettuce;
    }

    public void setLettuce(Lettuce lettuce) {
        this.lettuce = lettuce;
    }
}
