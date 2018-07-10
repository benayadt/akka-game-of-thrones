package com.benayad.funwithakka.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.benayad.funwithakka.messages.Raven;

public class TyrionLannister extends AbstractLoggingActor {

    public static Props props() {
        return Props.create(TyrionLannister.class, () -> new TyrionLannister());
    }


    {
        receive(ReceiveBuilder
                .match(Raven.class, this::onRavenReceived)
                .build());
    }

    private void onRavenReceived(Raven raven) {
        log().info("Received a Raven");
    }

}
