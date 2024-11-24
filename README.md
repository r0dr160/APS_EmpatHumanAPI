# EmpatHumanAPI

## Descrição
**EmpatHumanAPI** é uma API desenvolvida com Java e Spring Boot para fornecer serviços RESTful em conformidade com os requisitos da plataforma **Inven!RA**. O projeto implementa endpoints para configuração, parâmetros, deploy e analytics, conforme descrito na especificação da tarefa.
## Endpoints Disponíveis

A API fornece os seguintes endpoints RESTful:

### 1. Página de Configuração
- **Método:** `GET`
- **URL:** `/api/config`
- **Descrição:** Retorna uma página HTML simulada para configuração da atividade.
- **Exemplo de resposta:**
    ```html
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
    ```

### 2. Lista de Parâmetros
- **Método:** `GET`
- **URL:** `/api/params`
- **Descrição:** Retorna uma lista em formato JSON com os parâmetros disponíveis na página de configuração.
- **Exemplo de resposta:**
    ```json
    [
        {"name": "difficulty", "type": "text"},
        {"name": "timeLimit", "type": "integer"},
        {"name": "activityID", "type": "text"}
    ]
    ```

### 3. Deploy de Atividade
- **Método:** `GET`
- **URL:** `/api/deploy`
- **Parâmetro:** `activityID` (obrigatório)
- **Descrição:** Prepara a atividade para uso e retorna a URL para acesso.
- **Exemplo de resposta:**
    ```json
    {
        "message": "Activity deployed successfully!",
        "accessUrl": "http://localhost:8080/activity/start?activityID=12345"
    }
    ```

### 4. Analytics de Atividade
- **Método:** `POST`
- **URL:** `/api/analytics`
- **Descrição:** Retorna os dados analíticos de uma atividade, incluindo dados qualitativos e quantitativos.
- **Exemplo de body da requisição:**
    ```json
    {
        "activityID": "12345"
    }
    ```
- **Exemplo de resposta:**
    ```json
    [
        {
            "inveniraStdID": "1001",
            "quantAnalytics": [
                {"name": "Acedeu à atividade", "value": true},
                {"name": "Evolução pela atividade (%)", "value": 33.3}
            ],
            "qualAnalytics": [
                {"Student activity profile": "http://localhost:8080/analytics/1001/profile"}
            ]
        }
    ]
    ```

### 5. Lista de Analytics Disponíveis
- **Método:** `GET`
- **URL:** `/api/analytics/list`
- **Descrição:** Retorna uma lista em formato JSON com os tipos de analytics disponíveis para a atividade.
- **Exemplo de resposta:**
    ```json
    {
        "qualAnalytics": [
            {"name": "Student activity profile", "type": "URL"}
        ],
        "quantAnalytics": [
            {"name": "Acedeu à atividade", "type": "boolean"},
            {"name": "Evolução pela atividade (%)", "type": "integer"}
        ]
    }
    ```
## Instruções para Executar o Projeto

Siga os passos abaixo para executar o projeto localmente:

### 1. Pré-requisitos
- **Java Development Kit (JDK)** - versão 21 ou superior.
- **Gradle** - integrado ao projeto.
- **IntelliJ IDEA** ou qualquer IDE de sua preferência.
- **Postman** ou **HTTP Client** nativo do IntelliJ IDEA para testar os endpoints.

### 2. Clone o Repositório
Clone o repositório do projeto para sua máquina local:
```bash
git clone https://github.com/r0dr160/APS_EmpatHumanAPI.git
```

### Detalhes dos Diretórios e Arquivos

- **src/main/java**: Contém o código-fonte principal da aplicação.
    - `controller/`: Implementação dos endpoints REST.
    - `EmpatHumanApiApplication.java`: Classe principal responsável por iniciar o Spring Boot.

- **src/main/resources**: Contém arquivos de configuração e recursos estáticos.
    - `application.properties`: Configurações do Spring Boot, como porta do servidor.

- **src/test/java**: Contém testes unitários e de integração.
    - `EmpatHumanApiApplicationTests.java`: Classe para testes automatizados.

- **src/test/http-requests**: Contém os arquivos `.http` para testar os endpoints diretamente no IntelliJ IDEA.

- **build.gradle**: Arquivo de configuração do Gradle, define dependências e tarefas de build.

- **README.md**: Documentação do projeto.

#### Criado por Rodrigo Costa - 2401857 - MEIW
