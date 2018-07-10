package com.benayad.funwithakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.benayad.funwithakka.actors.JonSnow;
import com.benayad.funwithakka.actors.TyrionLannister;
import com.benayad.funwithakka.messages.Raven;

public class MainApplication {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("Fun-With-Akka");
        try {
            ActorRef jonSnow = actorSystem.actorOf(JonSnow.props());
            jonSnow.tell(new Raven("You know nothing"), ActorRef.noSender());

            ActorRef tyrionLannister = actorSystem.actorOf(TyrionLannister.props());
            tyrionLannister.tell(new Raven("Will you pay your debt?"), ActorRef.noSender());
        } finally {
            actorSystem.terminate();
        }
    }

}
