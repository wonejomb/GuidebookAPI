package de.mrbunny.guidebook.api.util;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

public class DeduplicatingRunner {
    private final Runnable runnable;
    private final Duration delay;
    private final String name;
    private Timer timer;
    private TimerTask task;

    public DeduplicatingRunner(Runnable runnable, Duration delay, String name) {
        this.runnable = runnable;
        this.delay = delay;
        this.name = name;
    }

    public synchronized void run() {
        if (task != null) {
            task.cancel();
        }
        task = new TimerTask() {
            @Override
            public void run() {
                doRun(this);
            }
        };
        if (timer == null) {
            timer = new Timer(name);
        }
        timer.schedule(task, delay.toMillis());
    }

    private synchronized void doRun(TimerTask fromTask) {
        if (task == fromTask) {
            runnable.run();
            task = null;
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
    }
}
