package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Classe Proxy que encapsula a lógica de controle e validação antes de delegar a execução ao serviço real.
 *
 * Padrão Estrutural:
 * - Permite controle adicional sobre o acesso ao serviço real (`RealAnalyticsService`).
 * - Pode ser usado para adicionar lógica de cache, segurança, ou controle de acesso.
 */
@Service
@Primary
public class AnalyticsServiceProxy implements AnalyticsService {

    private final RealAnalyticsService realAnalyticsService;

    @Autowired
    public AnalyticsServiceProxy(RealAnalyticsService realAnalyticsService) {
        this.realAnalyticsService = realAnalyticsService;
    }

    @Override
    public String getAnalytics(String activityID) {
        // Lógica de controle ou validação
        if (activityID == null || activityID.isEmpty()) {
            return "Invalid activityID!";
        }

        // Delegação para o serviço real
        return realAnalyticsService.getAnalytics(activityID);
    }
}

