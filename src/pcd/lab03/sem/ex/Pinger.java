package pcd.lab03.sem.ex;

import java.util.concurrent.Semaphore;

public class Pinger extends Thread {

	private Semaphore pingerNotifiedEvent;
	private Semaphore pongerNotifiedEvent;


	public Pinger(Semaphore pingerNotifiedEvent, Semaphore pongerNotifiedEvent) {
		this.pingerNotifiedEvent = pingerNotifiedEvent;
		this.pongerNotifiedEvent = pongerNotifiedEvent;
	}	
	
	public void run() {
		while (true) {
			try {
				pongerNotifiedEvent.acquire();
				System.out.println("ping!");
				pingerNotifiedEvent.release();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
