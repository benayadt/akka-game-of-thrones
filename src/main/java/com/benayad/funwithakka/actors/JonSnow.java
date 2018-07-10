package com.benayad.funwithakka.actors;

import akka.actor.AbstractActor;
import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.benayad.funwithakka.messages.Raven;

public class JonSnow extends AbstractLoggingActor{

    public static Props props() {
        return Props.create(JonSnow.class, () -> new JonSnow());
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
