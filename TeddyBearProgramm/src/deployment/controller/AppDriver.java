package deployment.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import deployment.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AppDriver /* timer class */ {

	private static Timer timer;
	private static boolean initializ_flag; // deprecated
	private static AppController obj;
	private static Observer observer; // deprecated
	private static Set<Observer> observers;
	static AnchorPane root;

	@FXML
	private TextField txtFieldSymbol;

	public static void main(String[] args) throws Exception {

		observers = new HashSet<>();

		obj = new AppController();

		timer = new Timer();

		StartupThread thread0 = new StartupThread(args);

		//SchedulerThread thread1 = new SchedulerThread();

		try {

			thread0.start();

			//thread1.start();
			// time-out to give the user a chance to enter values
			// thread1.sleep(14000);

			// while (true) {
			//
			// thread1.sleep(15000); // should sleep every 5 mins (300000)
			// }

		} catch (/* MyException quit */ Exception e /* for now */) {
			// if exception is myexception
			thread0.interrupt();
			//thread1.interrupt();
			System.exit(0);
		} // the code above would be necessary to kill all threads when closing the
			// programm. It will receive a Custom Exception from above (AppController or
			// MonitorController will throw it)

		/// to be continued

	}

	public static class SchedulerThread extends Thread {
		@Override
		public void run() {

			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					// observers = obj.getMonitors();
					// for (Observer ob : observers)
					// ob.setObservable(obj /* object is subject, irony! */);

					// notify observers to update
					System.out.println("push notification!\n");
					obj.pushNotification();
				}

			}, 15000 /* 300000 real time (5 mins) */
			// for debugging purposes it is set to 15 seconds (15000)
			);
		}

		public Observer getMonitor() {
			return null;
		}
	}

	public static class StartupThread extends Thread {

		private String[] args;

		public StartupThread(String[] args) {
			this.args = args;
		}

		@Override
		public void run() {
			obj.startup(args); // blocks further code
		}
	}

}
