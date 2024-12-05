package model;

import observer.Observer;

public abstract class AbstractToy {
    protected String name;

    public AbstractToy(String name) {
        this.name = name;
    }
}
