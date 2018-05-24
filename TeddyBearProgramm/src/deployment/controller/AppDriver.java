package deployment.controller;

import java.util.Timer;

import deployment.Observer;

public class AppDriver /* timer class */ {

	private static Timer timer;
	private static boolean initializ_flag; // deprecated
	private static AppController obj;
	private static Observer observer; // deprecated

	public static void main(String[] args) throws Exception {

		AppController obj = new AppController();

		TimerScheduler ts = new TimerScheduler(args);

		obj.addObsPayload(ts);

		Timer time = new Timer();
		time.schedule(ts, 0, 1000); // 15 sec (15000)

		try {
			// for demo only.
			while (true) {
				System.out.println("looping...");
				ts.update(obj); // update for an updater
								// we need to go deeper
				Thread.sleep(10000);
			}
		} catch (Exception e) {
			System.out.println("error. see exception \n" + e.getMessage());
			System.out.println("Application Terminated");
			System.exit(0);
		}

		// TRASH BELONGS IN THE TRASHBIN

		// obj = new AppController();
		//
		// timer = new Timer();
		//
		// StartupThread thread0 = new StartupThread(args);
		//
		// // SchedulerThread thread1 = new SchedulerThread();
		//
		// try {
		//
		// thread0.start();
		//
		// // thread1.start();
		// // time-out to give the user a chance to enter values
		// // thread1.sleep(14000);
		//
		// // while (true) {
		// //
		// // thread1.sleep(15000); // should sleep every 5 mins (300000)
		// // }
		//
		// } catch (/* MyException quit */ Exception e /* for now */) {
		// // if exception is myexception
		// thread0.interrupt();
		// // thread1.interrupt();
		// System.exit(0);
		// } // the code above would be necessary to kill all threads when closing the
		// // programm. It will receive a Custom Exception from above (AppController or
		// // MonitorController will throw it)
		//
		// /// to be continued

	}
}
