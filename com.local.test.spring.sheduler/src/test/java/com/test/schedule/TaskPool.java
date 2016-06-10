package com.test.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

public class TaskPool {
	
	protected volatile boolean running = false;
	protected volatile boolean paused = false;
	protected volatile boolean initialized = false;
	private ScheduledExecutorService scheduledExecutorService;

	private void init() {
		if (initialized)
			return;
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setName("redis读写线程");
				return thread;
			}
		});
		initialized = true;
	}

	public void start() {
		if (!initialized) {
			init();
		}
	}

	

}
