package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
