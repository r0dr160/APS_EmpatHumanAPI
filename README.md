# EmpatHumanAPI

## Descrição do Projeto
O **EmpatHumanAPI** é uma aplicação desenvolvida para gerenciar e expor serviços analíticos e de configuração de atividades através de uma API REST. Foi implementado utilizando **Spring Boot**, empacotado em um contêiner **Docker** e está hospedado em uma infraestrutura pessoal baseada em uma **Raspberry Pi 3-B**.

---

## Justificativa dos Antipadrões
Foram analisados os antipadrões (como blob, continuous obsolescence, lava flow, ambiguous viewpoint, functional decomposition, poltergeists, boat anchor, golden hammer, dead end, spaghetti code, input kludge, walking through a minefield, cut-and-paste programming e mushroom management) e não foram localizados problemas críticos no código. Contudo, para aprimorar a qualidade do projeto e atender ao feedback do professor na última entrega (referente ao padrão Observer), foram realizadas as seguintes alterações:
- **Logging Aprimorado:** O `LoggerObserver` agora utiliza SLF4J, substituindo o uso de `System.out.println` para melhor gerenciamento dos logs.
- **Thread-Safety no Observer:** A classe `Observable` passou a utilizar `CopyOnWriteArrayList` para garantir que a notificação dos observadores ocorra de forma segura em ambientes concorrentes.
- **Diagrama do Padrão Observer Atualizado:** Nos diagramas de componentes e de sequência foi incluída a etapa de registro (attach) do `LoggerObserver` ao `SingletonService` via o método `addObserver()`, evidenciando o fluxo completo do padrão.
- **Reorganização de Pacotes:** A estrutura dos pacotes foi reorganizada sob o namespace `com.empathuman.EmpatHumanAPI` para melhorar a clareza e a manutenção do código.

---

## **Padrões de Design Implementados**

### 1. Singleton (Padrão de Criação)
- **Classe:** `SingletonService`
- **Finalidade:**
  - Gerenciar valores persistentes compartilhados entre componentes da aplicação.
  - Notificar automaticamente os observadores sobre alterações no estado.
- **Exemplo de Uso:**
  - Endpoint `/api/singleton/update`: Atualiza o valor do Singleton e dispara a notificação aos observadores.

### 2. Proxy (Padrão Estrutural)
- **Classe:** `AnalyticsServiceProxy`
- **Finalidade:**
  - Encapsular a lógica de controle e validação antes de delegar a execução ao serviço real.
  - Garantir a integridade dos dados analisados pelo `RealAnalyticsService`.
- **Exemplo de Uso:**
  - Endpoint `/api/analytics`: Valida o `activityID` antes de processar os dados.
- **Nota:** Nesta versão, o `AnalyticsServiceProxy` foi anotado com `@Primary` para priorizar seu uso nos endpoints que requerem o serviço de analytics.

### 3. Observer (Padrão Comportamental)
- **Classe:** `LoggerObserver`
- **Finalidade:**
  - Registrar mudanças no estado do `SingletonService`.
  - Implementar uma dependência "um-para-muitos", permitindo que múltiplos componentes sejam notificados automaticamente.
- **Melhorias Aplicadas:**
  - Logging aprimorado com SLF4J.
  - Uso de `CopyOnWriteArrayList` na classe `Observable` para garantir thread-safety.
  - Nos diagramas, foi incluída a etapa de registro (attach) do `LoggerObserver` ao `SingletonService` via o método `addObserver()`, refletindo o fluxo completo do padrão Observer.
- **Exemplo de Uso:**
  - Notificação ao alterar o valor do Singleton via `/api/singleton/update`.

---

## Arquitetura Implementada

### DNS Público
O domínio público [https://meiw.duckdns.org](https://meiw.duckdns.org) foi configurado para acesso à aplicação e protegido por HTTPS. O certificado SSL é gerenciado pelo **Certbot** e fornecido pela **Let's Encrypt**.

### Reverse Proxy com Nginx
Um proxy reverso (**Nginx**) redireciona as requisições recebidas pelo domínio público para o contêiner Docker que executa a aplicação.

### Servidor Aplicacional
A aplicação está hospedada em uma **Raspberry Pi 3-B**, configurada para operar como um servidor dedicado de baixo consumo energético.

### Gerenciamento com Docker
A aplicação foi empacotada em um contêiner Docker e é gerenciada com **Docker Compose**. Um script de automação via **Systemd** garante que o contêiner seja reiniciado automaticamente após falhas ou reinicializações.

---

## Acesso aos Serviços

### 1. Configuração da Atividade
- **Endpoint:** `GET /api/config`
- **Descrição:** Retorna uma página HTML com parâmetros de configuração da atividade.

Exemplo de acesso:  
``  
curl https://meiw.duckdns.org/api/config  
``

---

### 2. Lista de Parâmetros de Configuração
- **Endpoint:** `GET /api/params`
- **Descrição:** Retorna os parâmetros configuráveis disponíveis para a página de configuração.

Exemplo de acesso:  
``  
curl https://meiw.duckdns.org/api/params  
``

Exemplo de resposta esperada:  
``  
[ { "name": "difficulty", "type": "text/plain" }, { "name": "timeLimit", "type": "integer" }, { "name": "activityID", "type": "text/plain" } ]  
``

---

### 3. Analytics de uma Atividade
- **Endpoint:** `POST /api/analytics`
- **Descrição:** Retorna dados de analytics para uma atividade específica.

Body esperado:  
``  
{ "activityID": "12345" }  
``

Exemplo de acesso:  
``  
curl -X POST https://meiw.duckdns.org/api/analytics -H "Content-Type: application/json" -d '{"activityID": "12345"}'  
``

Exemplo de resposta esperada:  
``  
Analytics data for activityID: 12345  
``

---

### 4. Lista de Tipos de Analytics Disponíveis
- **Endpoint:** `GET /api/analytics/list`
- **Descrição:** Retorna os tipos de analytics qualitativos e quantitativos disponíveis.

Exemplo de acesso:  
``  
curl https://meiw.duckdns.org/api/analytics/list  
``

Exemplo de resposta esperada:  
``  
{ "qualAnalytics": [ { "name": "Student activity profile", "type": "URL" } ], "quantAnalytics": [ { "name": "Acedeu à atividade", "type": "boolean" }, { "name": "Evolução pela atividade (%)", "type": "integer" } ] }  
``

---

### 5. Preparar o Deploy de uma Atividade
- **Endpoint:** `GET /api/deploy`
- **Descrição:** Prepara o deploy de uma atividade e retorna a URL de acesso.

Parâmetros: `activityID` (string) (ex.: `?activityID=12345`)

Exemplo de acesso:  
``  
curl https://meiw.duckdns.org/api/deploy?activityID=12345  
``

Exemplo de resposta esperada:  
``  
{ "message": "Activity deployed successfully!", "accessUrl": "https://meiw.duckdns.org/activity/start?activityID=12345", "singletonValue": "12345" }  
``

---

### 6. Obter Valor Atual do Singleton
- **Endpoint:** `GET /api/singleton/value`
- **Descrição:** Retorna o valor atual armazenado no SingletonService.

Exemplo de acesso:  
``  
curl https://meiw.duckdns.org/api/singleton/value  
``

Exemplo de resposta esperada:  
``  
{ "singletonValue": "Valor inicial do SingletonService" }  
``

---

### 7. Atualizar Valor do Singleton
- **Endpoint:** `POST /api/singleton/update`
- **Descrição:** Atualiza o valor armazenado no SingletonService.

Body esperado:  
``  
{ "value": "Novo valor do Singleton" }  
``

Exemplo de acesso:  
``  
curl -X POST https://meiw.duckdns.org/api/singleton/update -H "Content-Type: application/json" -d '{"value": "Novo valor do Singleton"}'  
``

Exemplo de resposta esperada:  
``  
{ "message": "Valor atualizado com sucesso", "newValue": "Novo valor do Singleton" }  
``

---

## Diagramas

### Diagrama de Componentes
![Diagrama de Componentes](src/assets/component_diagram.png)  
*Observação: O diagrama ilustra explicitamente o registro (attach) do `LoggerObserver` ao `SingletonService` via o método `addObserver()`, e a sequência de notificações realizadas pelo padrão Observer.*

### Diagrama de Sequência
![Diagrama de Sequência](src/assets/sequence_diagram.png)  
*Observação: O diagrama de sequência inclui a etapa de registro do observador, seguida da atualização do valor e subsequente notificação ao `LoggerObserver`.*

---

## Autor
- **Rodrigo Costa**  
  Email: 2401857@estudante.uab.pt  
  Universidade Aberta

---

## Licença
Este projeto é de uso educacional e desenvolvido como parte do Mestrado em Engenharia Informática da Universidade Aberta.