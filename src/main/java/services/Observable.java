package services;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface para objetos que podem ser observados por Observadores.
 */
public abstract class Observable {
    private final List<Observer> observers = new ArrayList<>();

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
