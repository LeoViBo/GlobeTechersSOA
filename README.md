# Plataforma SOA – Sistema de Upskilling & Reskilling

API desenvolvida em Java 21 + Spring Boot 3.5.7 com autenticação JWT, banco H2, migrações Flyway e quatro CRUDs completos. 

---

## Grupo GlobeTechers:  
Gabriel Augusto Maciel Toledo - RM: 551654  
Leonardo Viotti Bonini - RM: 551716  
Vinicius Santos Yamashita de Farias - RM: 550908  

---

## 1. Descrição do Problema

Empresas e instituições educacionais precisam gerenciar trilhas de aprendizagem, competências e perfis profissionais para apoiar iniciativas de upskilling e reskilling. Nesse contexto, surge a necessidade de uma API robusta que permita:  

- Cadastro e autenticação de usuários.  
- Criação e gerenciamento de trilhas de aprendizagem.  
- Relação entre trilhas, competências e perfis profissionais.  

Além disso, o projeto deve fornecer endpoints limpos, seguros e documentados para facilitar testes e integração.  conectadno diretamende com as ODS 4 e 10:  

**ODS 4 – Educação de Qualidade**  
- O sistema promove acesso a trilhas de aprendizagem, cursos e conteúdos de qualificação.  
- Apoia processos de upskilling e reskilling.
- Fornece dados para ajudar empresas e profissionais a identificar lacunas de competências.  
**Conexão: aprender continuamente é essencial no futuro do trabalho com IA, automação e dados.**  
  
**ODS 10 – Redução das Desigualdades**  
- Democratiza o acesso à qualificação profissional.  
- Permite que pessoas de diferentes níveis socioeconômicos tenham as mesmas oportunidades.  
- Possibilita trilhas personalizadas que atendem perfis diversos.  
**Conexão: combate desigualdades no acesso à educação e oportunidades de trabalho.**  

Além de leves conexões com as ODS 8 e 9:  

**ODS 8 – Trabalho Decente e Crescimento Econômico** - Cria ambientes mais produtivos e inclusivos no mercado de trabalho.  
**ODS 9 – Indústria, Inovação e Infraestrutura** - Prepara trabalhadores e organizações para a economia digital.  

---

## 2. Solução Implementada

Esta API resolve o problema fornecendo um backend completo com:  

✔ Autenticação e Autorização  
- JWT com expiração configurável  
- Login com validação personalizada (email + senha)  
- Filtros de segurança configurados com Spring Security 6  

✔ Quatro CRUDs completos:  
1. Usuário   
- Cadastro e gerenciamento de alunos/profissionais.  

2. Trilha   
- Trilhas de aprendizagem com temas e módulos.  

3. Competência  
- Competências exigidas ou desenvolvidas nas trilhas.  

4. Matrícula   
- Registra que um usuário está matriculado em uma trilha.  

5. *Trilha–Competência    
- Relacionamento N:N entre trilhas e competências.  

Cada CRUD possui:

- GET /listar  
- GET /{id}  
- POST  
- PUT /{id}  
- DELETE /{id}  
- Validações  
- DTOs de request/response  
- Respostas padronizadas  

✔ Banco H2 com Flyway  
- Execução simples para desenvolvimento  
- Scripts versionados em /resources/db/migration  

✔ Documentação com Swagger UI  
Disponível automaticamente em:  
```
http://localhost:8080/swagger-ui.html  
```

---

## 3. Tecnologias Utilizadas  

| Tecnologia | Versão |
| ------------------- | ---- |
| Java	| 21 |
| Spring Boot	| 3.5.7 |
| Spring Web	| ✓ |
| Spring Security + JWT	| ✓ |
| Spring Data JPA	| ✓ |
| H2 Database	| ✓ |
| Flyway	| ✓ |
| Lombok	| ✓ |
| Swagger – Springdoc	| 2.5.0 |

---

## 4. Configuração do banco:

**Banco de dados H2 – Ambiente de Desenvolvimento**  
spring.datasource.url=jdbc:h2:file:./data/meu_banco  
spring.datasource.username=sa  
spring.datasource.password=  
spring.datasource.driverClassName=org.h2.Driver  

**Console Web do H2**  
spring.h2.console.enabled=true  
spring.h2.console.path=/h2-console  

**JPA / Hibernate**  
spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  
spring.jpa.properties.hibernate.format_sql=true  

---

## 4. Como Executar o Projeto

1. **Clone o repositório**
```
git clone https://github.com/SEU-USUARIO/SEU-REPO.git
cd SEU-REPO
```
2. **Rodar com Maven**
```
mvn spring-boot:run
```
3.1. **Acessar o H2 (opcional)**
```
http://localhost:8080/h2-console
```
3.2. **Acessar o postman**

---

## 5. Autenticação JWT

#### Login

**POST**
```
/auth/login
```
**Exemplo de requisição:**
```
{
  "email": "usuario@email.com",
  "senha": "123456"
}
```
**Resposta:**
```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
   ...
}
```
**No postman usar Authorization Bearer com o token.**

---

## 6. CRUD – Usuário
| Método	| Rota	| Descrição |
| ------ | --------------- | ------------ |
| GET	| /usuarios	| Lista todos |
| GET	| /usuarios/{id}	| Busca por ID |
| POST	| /usuarios	| Cria |
| PUT	| /usuarios/{id}	| Atualiza |
| DELETE	| /usuarios/{id}	| Remove |

**Exemplo POST – Criar Usuário**
```
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "123456",
  "areaAtuacao": "Tecnologia",
  "nivelCarreira": "Júnior"
}
```

---

## 7. CRUD – Trilha de Aprendizagem

| Método |	Rota |	Descrição |
| ------ | --------------- | ------------ |
| GET |	/trilhas |	Lista todas |
| GET |	/trilhas/{id} |	Busca por ID |
| POST |	/trilhas |	Cria |
| PUT |	/trilhas/{id} |	Atualiza |
| DELETE |	/trilhas/{id} |	Remove |

**Exemplo POST – Criar Trilha**
```
{
  "nome": "Trilha Front-End",
  "descricao": "Do básico ao avançado",
  "nivel": "INTERMEDIARIO",
  "cargaHoraria": 40,
  "focoPrincipal": "Desenvolvimento Web",
  "criadorId": 1,
  "objetivoCarreira": "Transição para desenvolvedor front-end"
}
```

---

## 8. Como Testar no Postman

1. Crie um usuário  
2. Faça login com email + senha  
3. Copie o token retornado  
4. Adicione em cada requisição: Authorization → Bearer Token → seu_token  
5. Teste as rotas de Usuários e Trilhas  
6. Verifique no H2 se os dados foram salvos  

---

## 9. Observações Importantes

- As senhas são sempre salvas com BCrypt graças ao SenhaVO.  
- Emails são tratados como Value Object para evitar inconsistências.  
- TrilhaModel possui relação com Competências e com o Usuário criador.  
- Como padrão, o usuário recebe a role ROLE_USER, para receber admin, para teste, basta ter um email com o arroba @globetechers.com.  
