package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.AnalyticsService;
import services.LoggerObserver;
import services.SingletonService;

import java.util.List;
import java.util.Map;

/**
 * Controlador principal que gerencia os endpoints da aplicação.
 *
 * Integração dos Padrões:
 * - **Proxy**: Utiliza o `AnalyticsServiceProxy` para acessar os serviços de análise com controle adicional.
 * - **Singleton**: Gerencia valores compartilhados no `SingletonService`.
 * - **Observer**: Notifica automaticamente observadores sobre alterações no estado do Singleton.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    private final AnalyticsService analyticsService;
    private final SingletonService singletonService;

    @Autowired
    public ApiController(AnalyticsService analyticsService, SingletonService singletonService, LoggerObserver loggerObserver) {
        this.analyticsService = analyticsService;
        this.singletonService = singletonService;

        // Registra o LoggerObserver no SingletonService
        this.singletonService.addObserver(loggerObserver);
    }

    /**
     * Endpoint para exibir a página de configuração.
     * @return HTML contendo os parâmetros de configuração.
     */
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

    /**
     * Endpoint para listar os parâmetros de configuração disponíveis.
     * @return Lista de parâmetros com seus tipos.
     */
    @GetMapping("/params")
    public List<Map<String, String>> getConfigParams() {
        return List.of(
                Map.of("name", "difficulty", "type", "text"),
                Map.of("name", "timeLimit", "type", "integer"),
                Map.of("name", "activityID", "type", "text")
        );
    }

    /**
     * Endpoint para obter dados de analytics de uma atividade.
     * @param request JSON contendo o "activityID".
     * @return Dados analíticos relacionados à atividade.
     */
    @PostMapping("/analytics")
    public String getActivityAnalytics(@RequestBody Map<String, String> request) {
        String activityID = request.get("activityID");
        return analyticsService.getAnalytics(activityID);
    }

    /**
     * Endpoint para listar os tipos de analytics disponíveis.
     * @return Lista de tipos de analytics, divididos entre qualitativos e quantitativos.
     */
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

    /**
     * Endpoint para preparar o deploy de uma atividade.
     * @param activityID ID da atividade a ser preparada.
     * @return URL de acesso e confirmação de deploy.
     */
    @GetMapping("/deploy")
    public Map<String, String> deployActivity(@RequestParam String activityID) {
        singletonService.setValue(activityID);

        // Gera dinamicamente a URL base atual
        String accessUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/activity/start")
                .queryParam("activityID", activityID)
                .toUriString();

        return Map.of(
                "message", "Activity deployed successfully!",
                "accessUrl", accessUrl,
                "singletonValue", singletonService.getValue()
        );
    }

    /**
     * Endpoint para obter o valor atual armazenado no SingletonService.
     * @return Valor atual do SingletonService.
     */
    @GetMapping("/singleton/value")
    public Map<String, String> getSingletonValue() {
        return Map.of("singletonValue", singletonService.getValue());
    }

    /**
     * Endpoint para atualizar o valor armazenado no SingletonService.
     * @param request JSON contendo o novo valor a ser armazenado.
     * @return Mensagem de confirmação e o novo valor.
     */
    @PostMapping("/singleton/update")
    public Map<String, String> updateSingletonValue(@RequestBody Map<String, String> request) {
        String newValue = request.get("value");
        singletonService.setValue(newValue);
        return Map.of("message", "Valor atualizado com sucesso", "newValue", newValue);
    }
}
