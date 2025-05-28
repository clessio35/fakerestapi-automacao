🧪 Automação de Testes - FakeRESTApi
Projeto em andamento para automação dos testes da API pública FakeRESTApi, utilizando Java 17 com Cucumber, RestAssured e Hamcrest. Inclui geração de evidências em PDF, integração contínua com GitHub Actions e Jenkins, e execução em ambientes isolados via Docker.

🚀 Objetivo
Automatizar testes da API FakeRESTApi com foco em:

✅ Testes automatizados para métodos HTTP (GET, POST, PUT, DELETE)

✅ Validações utilizando Hamcrest

✅ Escrita de cenários de teste com Cucumber (BDD)

✅ Geração de evidências em PDF

✅ Execução automatizada com GitHub Actions e Jenkins

✅ Ambiente de execução isolado usando Docker

🛠️ Tecnologias Utilizadas
Tecnologia	Finalidade
Java 17	Linguagem base
Maven	Gerenciador de dependências
Cucumber 6	Testes BDD com escrita de cenários em Gherkin
JUnit 4	Framework de execução dos testes
REST-assured 5.2.0	Testes de APIs REST
Hamcrest 2.2	Validações mais expressivas
iText 9	Geração de evidências em PDF
Java Faker 1.0.2	Geração de dados dinâmicos para testes
org.json	Manipulação de objetos JSON


📁 Estrutura do Projeto

fakerestapi-automacao/                                                                                                      
├── src/                                                                                                                      
│   ├── main/                                                                                                   
│   │   └── java/                                                                                                               
│   │       └── fakerest/                                                                                                          
│   │           ├── service/            # Lógica de consumo da API                                                              
│   │           └── utils/              # Geração de evidências e Hooks                                                          
│   └── test/                                                                                                                      
│       ├── java/                                                                                                         
│       │   └── fakerest/                                                                                                         
│       │       ├── runner/             # RunnerTest para execução com Cucumber                                                  
│       │       └── steps/              # Definição dos passos dos testes                                                         
│       └── resources/
│           ├── features/               # Arquivos .feature (BDD)                                                  
│           └── evidences/              # Evidências geradas (PDFs)                                                  
├── Dockerfile                          # Configuração de build Docker                                                  
├── pom.xml                             # Arquivo de configuração do Maven                                                  



▶️ Como Executar Localmente
Pré-requisitos
Java 17 instalado

Maven 3.8+ instalado

Comando de execução:
mvn clean test

🐳 Execução com Docker

Este projeto inclui um Dockerfile com Maven 3.9.6 e Java 17 (Temurin).

🔨 Construir a imagem

docker build -t fakerestapi-tests .

▶️ Executar os testes

docker run --rm fakerestapi-tests

📝 Dockerfile utilizado

Dockerfile
FROM maven:3.9.6-eclipse-temurin-17
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
CMD ["mvn", "clean", "test"]


⚙️ Integração Contínua
GitHub Actions: Automatização via workflows YAML

Jenkins: Pipeline definido no Jenkinsfile para execução contínua e agendada

🧾 Evidências e Relatórios
📄 Evidências geradas em PDF com detalhes da execução dos testes

💼 Utiliza a biblioteca iText 9 para geração programática dos arquivos

📌 Status do Projeto
🚧 Em desenvolvimento – Estruturação de cenários, métodos da API e geração de evidências em andamento.
