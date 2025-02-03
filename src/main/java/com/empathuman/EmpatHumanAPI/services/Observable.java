package com.empathuman.EmpatHumanAPI.services;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Interface para objetos que podem ser observados por Observadores.
 */
public abstract class Observable {
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String newValue) {
        for (Observer observer : observers) {
            observer.update(newValue);
        }
    }
}
