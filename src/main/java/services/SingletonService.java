package com.empathuman.EmpatHumanAPI.services;

import org.springframework.stereotype.Service;

@Service // Define esta classe como um Bean Singleton gerido pelo Spring
public class SingletonService {

    private String value;

    // Construtor - Inicializa o valor padrão
    public SingletonService() {
        this.value = "Valor inicial do SingletonService";
    }

    // Método para obter o valor atual
    public String getValue() {
        return value;
    }

    // Método para atualizar o valor
    public void setValue(String value) {
        this.value = value;
    }
}
