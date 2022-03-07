package pcd.lab02.lost_updates;


public class Worker extends Thread {

	private Object lock;
	private UnsafeCounter counter;
	private int ntimes;
	
	public Worker(UnsafeCounter c, int ntimes, Object lock ){
		counter = c;
		this.ntimes = ntimes;
		this.lock = lock;
	}
	
	public void run(){
		for (int i = 0; i < ntimes; i++){
			synchronized (lock) {
				counter.inc();
			}
		}
	}
}
