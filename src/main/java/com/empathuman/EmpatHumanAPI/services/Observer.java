package com.empathuman.EmpatHumanAPI.services;

/**
 * Interface para os Observadores que desejam ser notificados de mudanças no estado.
 */
public interface Observer {
    void update(String newValue);
}
