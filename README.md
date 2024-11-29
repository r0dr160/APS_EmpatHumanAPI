# EmpatHumanAPI

## Descrição do Projeto
O **EmpatHumanAPI** é uma aplicação desenvolvida para gerenciar e expor serviços analíticos e de configuração de atividades através de uma API REST. Foi implementado utilizando **Spring Boot**, empacotado em um contêiner **Docker**, e está hospedado em uma infraestrutura pessoal baseada em uma **Raspberry Pi 3-B**.

## Arquitetura Implementada

### DNS Público
O domínio público [`https://meiw.duckdns.org`](https://meiw.duckdns.org) foi configurado para acesso à aplicação e protegido por HTTPS. O certificado SSL é gerenciado pelo **Certbot** e fornecido pela **Let's Encrypt**.

### Reverse Proxy com Nginx
Um proxy reverso (**Nginx**) redireciona as requisições recebidas pelo domínio público para o contêiner Docker que executa a aplicação.

### Servidor Aplicacional
A aplicação está hospedada em uma **Raspberry Pi 3-B**, que foi configurada para operar como um servidor dedicado de baixo consumo energético.

### Gerenciamento com Docker
A aplicação foi empacotada em um contêiner Docker e é gerenciada com **Docker Compose**. Um script de automação via **Systemd** garante que o contêiner seja reiniciado automaticamente após falhas ou reinicializações.

---

## Acesso aos Serviços

### Configuração da Atividade
- **Endpoint:** `GET /api/config`  
- **Descrição:** Retorna uma página HTML com parâmetros de configuração da atividade.  

**Exemplo de acesso:**  
```bash
curl https://meiw.duckdns.org/api/config
