package services;

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

    @Override
    public void update(String newValue) {
        System.out.println("LoggerObserver: O valor do SingletonService foi alterado para: " + newValue);
    }
}

