package pcd.lab08.rx;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class Test02c_creation_fromCallable {

	public static void main(String[] args) throws Exception {

		System.out.println("\n=== TEST fromCallable | main thread ===\n");

		// ha senso quando la lamba che passiamo è un compito asincrono che genera un valore
		Flowable.fromCallable(() -> {
		    log("started.");
		    Thread.sleep(1000); 
		    log("completed.");
		    return "Done";
		})
		.subscribe(s -> {
			log("result: " + s);
		});

		Thread.sleep(2000); // <--- wait for the flow to finish
	}
	
	static private void log(String msg) {
		System.out.println("[ " + Thread.currentThread().getName() + "  ] " + msg);
	}

}
