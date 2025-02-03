package com.empathuman.EmpatHumanAPI.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Observer que registra alterações no SingletonService.
 *
 * Padrão Comportamental:
 * - Define uma dependência de "um-para-muitos" entre objetos.
 * - Permite que o `SingletonService` notifique automaticamente os observadores sobre mudanças no seu estado.
 */
@Component
public class LoggerObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(LoggerObserver.class);

    @Override
    public void update(String newValue) {
        logger.info("LoggerObserver: O valor do SingletonService foi alterado para: {}", newValue);
    }
}

