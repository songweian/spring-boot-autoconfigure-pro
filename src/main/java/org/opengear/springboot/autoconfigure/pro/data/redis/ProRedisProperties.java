package org.opengear.springboot.autoconfigure.pro.data.redis;


import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.SslOptions;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.protocol.DecodeBufferPolicies;
import io.lettuce.core.protocol.DecodeBufferPolicy;
import io.lettuce.core.protocol.ProtocolVersion;
import io.lettuce.core.protocol.ReadOnlyCommands;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;

@ConfigurationProperties(prefix = "spring.data.redis")
public class ProRedisProperties {
    private Lettuce lettuce;

    @Data
    public static class Lettuce {

        private Boolean autoReconnect;

        private Boolean cancelCommandsOnReconnectFailure;

        private DecodeBufferPolicy decodeBufferPolicy;

        private ClientOptions.DisconnectedBehavior disconnectedBehavior;

        private Boolean pingBeforeActivateConnection ;

        private ProtocolVersion protocolVersion;

        private Boolean publishOnScheduler;

        private ReadOnlyCommands.ReadOnlyPredicate readOnlyCommands ;

        private Integer requestQueueSize ;

        private Charset scriptCharset ;

        private SocketOptions socketOptions;

        private SslOptions sslOptions;

        private Boolean suspendReconnectOnProtocolFailure;

        private TimeoutOptions timeoutOptions;

        private Cluster cluster;

        public static class Cluster {

        }
    }

    public Lettuce getLettuce() {
        return lettuce;
    }

    public void setLettuce(Lettuce lettuce) {
        this.lettuce = lettuce;
    }
}
