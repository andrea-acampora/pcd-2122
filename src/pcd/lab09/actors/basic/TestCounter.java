package pcd.lab09.actors.basic;

import akka.actor.typed.ActorSystem;
import pcd.lab09.actors.basic.CounterUserActor.StartMsg;

public class TestCounter {

	// È SBAGLIATO PERCHE IN QUESTO MODO SI CREANO 2 ACTOR SYSTEM
	// IN REALTA BISOGNA SEGUIRE UNO SCHEMA GERARCHICO QUINDI CON UN SOLO ACTOR SYSTEM
	// NEL MAIN QUINDI SI CREA SOLO UN ROOT ACTOR CHE POI A SUA VOLTA CREERÀ GLI ATTORI NECESSARI

  public static void main(String[] args) throws Exception  {

	  final ActorSystem<CounterMsg> counter =
			    ActorSystem.create(CounterActor.create(), "myCounter");

	  final ActorSystem<CounterUserMsg> counterUser =
			    ActorSystem.create(CounterUserActor.create(), "myCounterUser");

	  counterUser.tell(new StartMsg(counter));
  }
}
