package com.benayad.funwithakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.benayad.funwithakka.actors.JonSnow;
import com.benayad.funwithakka.actors.TyrionLannister;
import com.benayad.funwithakka.messages.Raven;
import com.benayad.funwithakka.messages.WhiteRaven;

import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) throws IOException {
        ActorSystem actorSystem = ActorSystem.create("Fun-With-Akka");
        try {
            ActorRef tyrionLannister = actorSystem.actorOf(TyrionLannister.props());
            ActorRef jonSnow = actorSystem.actorOf(JonSnow.props(tyrionLannister));

            jonSnow.tell(new WhiteRaven(), ActorRef.noSender());

            System.out.println("Press enter to terminate");
            System.in.read();
        } finally {
            actorSystem.terminate();
        }
    }

}
