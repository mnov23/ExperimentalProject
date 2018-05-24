package deployment.controller;

import java.util.Set;
import java.util.TimerTask;

import deployment.Observable;
import deployment.Observer;
import deployment.ObserverPayload;

public class TimerScheduler extends TimerTask implements ObserverPayload {
	private AppController app;
	private AppDriver main;
	private MonitorController monitor;
	private GraphController graph;
	private Set<Observer> observers;
	private String[] args;

	public TimerScheduler(String[] args) {
		this.args = args;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		StartupThread startup = new StartupThread(args);

		startup.run();
		
		if (app.hasMonitors()) // to prevent first crashes
			observers = app.getMonitors(); // crashed ofc
		System.out.println("pushing notification");
		app.pushNotification();
	}

	/*
	 * discouraged (non-Javadoc)
	 * 
	 * @see deployment.ObserverPayload#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setObservable(Observable subject) {
		if (subject /* local */ instanceof AppDriver /* global */) {
			/* global */main = (AppDriver) subject /* local */;
			/* local */subject.addObsPayload(this);
		}
	}

	/*
	 * this method should be used (non-Javadoc)
	 * 
	 * @see deployment.ObserverPayload#update(java.lang.Object)
	 */
	@Override
	public void update(Object payload) {
		if (payload instanceof AppController)
			app = (AppController) payload;
		// hot swap. I hope it doesn't crash
	}

	public class StartupThread extends Thread {

		private String[] args;

		public StartupThread(String[] args) {
			this.args = args;
		}

		@Override
		public void run() {
			app.startup(args); // blocks further code
		}
	}
}
