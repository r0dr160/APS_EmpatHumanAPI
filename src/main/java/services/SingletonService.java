package com.empathuman.EmpatHumanAPI.services;

import org.springframework.stereotype.Service;

@Service // Define esta classe como um Bean Singleton gerido pelo Spring
public class SingletonService {
    private String lastAccessedActivity;

    // Construtor - Inicializa o valor padrão
    public SingletonService() {
        this.lastAccessedActivity = "Nenhuma atividade acessada ainda.";
    }

    // Método para obter o valor atual
    public String getLastAccessedActivity() {
        return lastAccessedActivity;
    }

    // Método para atualizar o valor
    public void setLastAccessedActivity(String activityID) {
        this.lastAccessedActivity = activityID;
    }
}
