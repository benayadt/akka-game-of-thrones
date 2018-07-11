package com.benayad.funwithakka.actors;

import akka.actor.AbstractActor;
import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.benayad.funwithakka.messages.*;

public class JonSnow extends AbstractLoggingActor{

    private final ActorRef tyrionLannister;

    JonSnow(ActorRef tyrionLannister) {
        this.tyrionLannister = tyrionLannister;
    }

    public static Props props(ActorRef tyrionLannister) {
        return Props.create(JonSnow.class, () -> new JonSnow(tyrionLannister));
    }

    {
        receive(ReceiveBuilder
                .match(Raven.class, this::onRavenReceived)
                .match(WhiteRaven.class, this::onWhiteRavenReceived)
                .match(BendTheKnee.class, this::onBendTheKneeReceived)
                .match(WeAreComing.class, this::onWaAreComingReceived)
                .build());
    }

    private void onWaAreComingReceived(WeAreComing weAreComing) {
        log().info("The Great war is beginning");
    }

    private void onBendTheKneeReceived(BendTheKnee bendTheKnee) {
        log().info("I'm the King in the north, I won't bend the knee");
        sender().tell(new NightKingIsComing(), self());
    }

    private void onRavenReceived(Raven raven) {
        log().info("Received a Raven: " + raven);
    }
    private void onWhiteRavenReceived(WhiteRaven whiteRaven) {
        log().info("I received a white Raven, tell tyrion we need help");
        tyrionLannister.tell(new NightKingIsComing(), self());
    }
}
