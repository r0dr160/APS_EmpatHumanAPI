package com.empathuman.EmpatHumanAPI.services;

import org.springframework.stereotype.Service;

@Service
public class RealAnalyticsService implements AnalyticsService {

    @Override
    public String getAnalytics(String activityID) {
        // Simulação de lógica real para analytics
        return "Analytics data for activityID: " + activityID;
    }
}
