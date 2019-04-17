package myui.core;

public class MySyncTask extends MyTask{
	
	private int ups;
	private Thread thread;
	private long delay_time;
	private boolean running;
	
	
	public MySyncTask(Runnable _action) {
		super(_action);
		setUps(1);
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				long last_time = System.nanoTime();
				while(running) {
					if(System.nanoTime()-last_time > delay_time) {
						System.out.println("PING");
						last_time = System.nanoTime();
						runnable.run();
						
					}
				}
			}
		});
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
		this.delay_time = 1000000000l / ups;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	public static void sync(int _ups) {
		long start_time = System.nanoTime();
		while(System.nanoTime() - start_time < 1000000000l / _ups) {}
	}
	
	public static void sleep(long millis) {
		try {
			Thread.currentThread().sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void fire() {
		start();
		
	}
	
	
	
}
