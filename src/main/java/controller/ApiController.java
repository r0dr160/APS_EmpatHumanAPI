package com.empathuman.EmpatHumanAPI;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    // Define a URL base pública gerada pelo ngrok
    private static final String BASE_URL = "https://meiw.duckdns.org";

    @GetMapping("/config")
    public String getConfigPage() {
        return """
            <html>
            <body>
                <h1>Configuração da Atividade</h1>
                <form>
                    <label for='difficulty'>Nível de Dificuldade:</label>
                    <input type='text' id='difficulty' name='difficulty' value='medium'>
                    
                    <label for='timeLimit'>Tempo Limite (minutos):</label>
                    <input type='number' id='timeLimit' name='timeLimit' value='30'>
                    
                    <input type='hidden' id='activityID' name='activityID' value='12345'>
                </form>
            </body>
            </html>
            """;
    }

    @GetMapping("/params")
    public List<Map<String, String>> getConfigParams() {
        return List.of(
                Map.of("name", "difficulty", "type", "text"),
                Map.of("name", "timeLimit", "type", "integer"),
                Map.of("name", "activityID", "type", "text")
        );
    }

    @GetMapping("/deploy")
    public Map<String, String> deployActivity(@RequestParam String activityID) {
        String accessUrl = BASE_URL + "/activity/start?activityID=" + activityID;
        return Map.of("message", "Activity deployed successfully!", "accessUrl", accessUrl);
    }

    @PostMapping("/analytics")
    public List<Map<String, Object>> getActivityAnalytics(@RequestBody Map<String, String> request) {
        String activityID = request.get("activityID");

        return List.of(
                Map.of(
                        "inveniraStdID", "1001",
                        "quantAnalytics", List.of(
                                Map.of("name", "Acedeu à atividade", "value", true),
                                Map.of("name", "Evolução pela atividade (%)", "value", 33.3)
                        ),
                        "qualAnalytics", List.of(
                                Map.of("Student activity profile", BASE_URL + "/analytics/1001/profile")
                        )
                )
        );
    }

    @GetMapping("/analytics/list")
    public Map<String, List<Map<String, String>>> getAnalyticsList() {
        return Map.of(
                "qualAnalytics", List.of(
                        Map.of("name", "Student activity profile", "type", "URL")
                ),
                "quantAnalytics", List.of(
                        Map.of("name", "Acedeu à atividade", "type", "boolean"),
                        Map.of("name", "Evolução pela atividade (%)", "type", "integer")
                )
        );
    }
}
