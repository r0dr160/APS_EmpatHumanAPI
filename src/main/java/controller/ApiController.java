package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.AnalyticsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final AnalyticsService analyticsService;

    @Autowired
    public ApiController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PostMapping("/analytics")
    public String getActivityAnalytics(@RequestBody Map<String, String> request) {
        String activityID = request.get("activityID");
        return analyticsService.getAnalytics(activityID);
    }

    // Outras rotas já existentes
}
