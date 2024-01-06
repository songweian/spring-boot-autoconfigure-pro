package org.opengear.springboot.autoconfigure.pro.data.redis;

import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;

import java.time.Duration;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

public class LettuceClusterTopologyRefreshOptionsHelpBuilder {

    private final ClusterTopologyRefreshOptions.Builder builder;

    public LettuceClusterTopologyRefreshOptionsHelpBuilder(ClusterTopologyRefreshOptions originClusterTopologyRefreshOptions) {
        this.builder = ClusterTopologyRefreshOptions.builder();

        this.builder.enableAdaptiveRefreshTrigger(originClusterTopologyRefreshOptions.getAdaptiveRefreshTriggers().toArray(new ClusterTopologyRefreshOptions.RefreshTrigger[]{}));
        this.builder.refreshTriggersReconnectAttempts(originClusterTopologyRefreshOptions.getRefreshTriggersReconnectAttempts());
        this.builder.adaptiveRefreshTriggersTimeout(originClusterTopologyRefreshOptions.getAdaptiveRefreshTimeout());
        this.builder.refreshPeriod(originClusterTopologyRefreshOptions.getRefreshPeriod());
        this.builder.dynamicRefreshSources(originClusterTopologyRefreshOptions.useDynamicRefreshSources());
        this.builder.closeStaleConnections(originClusterTopologyRefreshOptions.isCloseStaleConnections());
        this.builder.refreshPeriod(originClusterTopologyRefreshOptions.getRefreshPeriod());
        this.builder.enablePeriodicRefresh(originClusterTopologyRefreshOptions.isPeriodicRefreshEnabled());
    }

    public static LettuceClusterTopologyRefreshOptionsHelpBuilder builder(ClusterTopologyRefreshOptions customTopologyRefreshOptions) {
        return new LettuceClusterTopologyRefreshOptionsHelpBuilder(customTopologyRefreshOptions);
    }

    public LettuceClusterTopologyRefreshOptionsHelpBuilder enableAdaptiveRefreshTrigger(ClusterTopologyRefreshOptions.RefreshTrigger... refreshTrigger) {
        builder.enableAdaptiveRefreshTrigger(refreshTrigger);
        return this;
    }

    public LettuceClusterTopologyRefreshOptionsHelpBuilder enableAllAdaptiveRefreshTriggers() {
        builder.enableAdaptiveRefreshTrigger(EnumSet.allOf(ClusterTopologyRefreshOptions.RefreshTrigger.class).toArray(new ClusterTopologyRefreshOptions.RefreshTrigger[]{}));
        return this;
    }

    public LettuceClusterTopologyRefreshOptionsHelpBuilder adaptiveRefreshTriggersTimeout(Duration timeout) {
        this.builder.adaptiveRefreshTriggersTimeout(timeout);
        return this;
    }

    @Deprecated
    public LettuceClusterTopologyRefreshOptionsHelpBuilder adaptiveRefreshTriggersTimeout(long timeout, TimeUnit unit) {
        return adaptiveRefreshTriggersTimeout(Duration.ofNanos(unit.toNanos(timeout)));
    }

    public LettuceClusterTopologyRefreshOptionsHelpBuilder closeStaleConnections(boolean closeStaleConnections) {
        this.builder.closeStaleConnections(closeStaleConnections);
        return this;
    }

    public LettuceClusterTopologyRefreshOptionsHelpBuilder dynamicRefreshSources(boolean dynamicRefreshSources) {
        this.builder.dynamicRefreshSources(dynamicRefreshSources);
        return this;
    }

    public LettuceClusterTopologyRefreshOptionsHelpBuilder enablePeriodicRefresh() {
        return enablePeriodicRefresh(true);
    }

    public LettuceClusterTopologyRefreshOptionsHelpBuilder enablePeriodicRefresh(boolean enabled) {
        this.enablePeriodicRefresh(enabled);
        return this;
    }

    public LettuceClusterTopologyRefreshOptionsHelpBuilder enablePeriodicRefresh(Duration refreshPeriod) {
        return refreshPeriod(refreshPeriod).enablePeriodicRefresh();
    }

    @Deprecated
    public LettuceClusterTopologyRefreshOptionsHelpBuilder enablePeriodicRefresh(long refreshPeriod, TimeUnit refreshPeriodUnit) {
        return refreshPeriod(refreshPeriod, refreshPeriodUnit).enablePeriodicRefresh();
    }

    public LettuceClusterTopologyRefreshOptionsHelpBuilder refreshPeriod(Duration refreshPeriod) {
        this.refreshPeriod(refreshPeriod);
        return this;
    }

    @Deprecated
    public LettuceClusterTopologyRefreshOptionsHelpBuilder refreshPeriod(long refreshPeriod, TimeUnit refreshPeriodUnit) {
        return refreshPeriod(Duration.ofNanos(refreshPeriodUnit.toNanos(refreshPeriod)));
    }

    public LettuceClusterTopologyRefreshOptionsHelpBuilder refreshTriggersReconnectAttempts(int refreshTriggersReconnectAttempts) {
        this.refreshTriggersReconnectAttempts(refreshTriggersReconnectAttempts);
        return this;
    }

    public ClusterTopologyRefreshOptions build() {
        return this.builder.build();
    }
}
