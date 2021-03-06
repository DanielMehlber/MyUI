package com.danielmehlber.myui;

public class MySyncTask extends MyTask {

	private int ups;
	private Thread thread;
	private long delay_time;
	private boolean running;

	public MySyncTask(Runnable _action) {
		super(_action);
		setUps(1);
		thread = new Thread(() -> {

			long last_time = System.nanoTime();
			while (running) {
				if (System.nanoTime() - last_time > delay_time) {
					last_time = System.nanoTime();
					runnable.run();

				}
			}
		});
	}

	public static void sync(int _ups) {
		long start_time = System.nanoTime();
		while (System.nanoTime() - start_time < 1000000000L / _ups) {
			try {
				Thread.currentThread();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void sleep(long millis) {
		try {
			Thread.currentThread();
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		running = true;
		thread.start();
	}

	public void stop() {
		running = false;
	}

	public Runnable getRunnable() {
		return runnable;
	}

	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}

	public int getUps() {
		return ups;
	}

	public void setUps(int ups) {
		this.ups = ups;
		this.delay_time = 1000000000L / ups;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	@Override
	public void fire() {
		start();

	}

}
