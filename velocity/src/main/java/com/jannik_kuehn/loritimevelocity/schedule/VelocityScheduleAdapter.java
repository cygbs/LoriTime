package com.jannik_kuehn.loritimevelocity.schedule;

import com.jannik_kuehn.common.api.scheduler.PluginScheduler;
import com.jannik_kuehn.common.api.scheduler.PluginTask;
import com.jannik_kuehn.loritimevelocity.LoriTimeVelocity;
import com.velocitypowered.api.scheduler.Scheduler;

import java.util.concurrent.TimeUnit;

public class VelocityScheduleAdapter implements PluginScheduler {
    private final LoriTimeVelocity plugin;

    private final Scheduler scheduler;

    public VelocityScheduleAdapter(final LoriTimeVelocity plugin, final Scheduler scheduler) {
        this.plugin = plugin;
        this.scheduler = scheduler;
    }

    @Override
    public PluginTask runAsyncOnce(final Runnable task) {
        return new VelocityTask(
                scheduler.buildTask(plugin, task)
                        .schedule()
        );
    }

    @Override
    public PluginTask runAsyncOnceLater(final long delay, final Runnable task) {
        return new VelocityTask(
                scheduler.buildTask(plugin, task)
                        .delay(delay, TimeUnit.SECONDS)
                        .schedule()
        );
    }

    @Override
    public PluginTask scheduleAsync(final long delay, final long interval, final Runnable task) {
        return new VelocityTask(
                scheduler.buildTask(plugin, task)
                        .delay(delay, TimeUnit.SECONDS)
                        .repeat(interval, TimeUnit.SECONDS)
                        .schedule()
        );
    }

    @Override
    public PluginTask scheduleSync(final Runnable task) {
        return new VelocityTask(
                scheduler.buildTask(plugin, task)
                        .schedule()
        );
    }
}
