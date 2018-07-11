package com.benayad.funwithakka.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.benayad.funwithakka.messages.BendTheKnee;
import com.benayad.funwithakka.messages.NightKingIsComing;
import com.benayad.funwithakka.messages.Raven;
import com.benayad.funwithakka.messages.WeAreComing;

public class TyrionLannister extends AbstractLoggingActor {

    private int counter = 3;

    public static Props props() {
        return Props.create(TyrionLannister.class, () -> new TyrionLannister());
    }


    {
        receive(ReceiveBuilder
                .match(Raven.class, this::onRavenReceived)
                .match(NightKingIsComing.class, this::onNightKingIsComing)
                .build());
    }

    private void onNightKingIsComing(NightKingIsComing nightKingIsComing) {
        if(counter-- > 0) {
            log().info("Bend the knee! And we'll help!!");
            sender().tell(new BendTheKnee(), self());
        } else {
            log().info("Okay okay, we are coming");
            sender().tell(new WeAreComing(), self());
        }
    }

    private void onRavenReceived(Raven raven) {
        log().info("Received a Raven: " + raven);
    }

}
