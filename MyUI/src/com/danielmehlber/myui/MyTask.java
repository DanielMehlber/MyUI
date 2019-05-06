package com.danielmehlber.myui;

public class MyTask{

	protected Runnable runnable;
	
	public MyTask(Runnable _runnable) {
		runnable = _runnable;
	}
	
	
	void fire() {
		runnable.run();
	}
	
}
