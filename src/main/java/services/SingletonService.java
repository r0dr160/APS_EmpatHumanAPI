package services;

import org.springframework.stereotype.Service;

/**
 * Serviço Singleton que gerencia um estado compartilhado e notifica os observadores sobre mudanças.
 *
 * Padrão de Criação:
 * - Garante que apenas uma instância desta classe seja criada na aplicação.
 * - Fornece um ponto único para acessar e modificar o estado compartilhado.
 */
@Service
public class SingletonService extends Observable {
    private String value;

    public SingletonService() {
        this.value = "Valor inicial do SingletonService";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        notifyObservers(value); // Notifica os observadores sobre a mudança de valor.
    }
}

