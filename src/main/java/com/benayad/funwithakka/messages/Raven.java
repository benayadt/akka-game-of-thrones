package com.benayad.funwithakka.messages;

public class Raven {

    private final String message;

    public Raven(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
