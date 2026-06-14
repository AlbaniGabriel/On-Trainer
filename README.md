# On Trainer

> Um trabalho feito por **Gabriel Albani** e **Miguel A. Guedes**

Sistema web para registro e acompanhamento de treinos de academia.

---

## Domínio do Problema

Muitos frequentadores de academia não possuem uma forma simples e organizada de registrar seus treinos e acompanhar sua frequência. Normalmente utilizam anotações em papel ou aplicativos complexos.

O sistema proposto tem como objetivo permitir:

- Cadastro de usuários
- Login no sistema
- Criação de rotinas de treino (ex: "Treino de Perna")
- Associação de exercícios a uma rotina
- Edição e exclusão de rotinas
- Registro de check-in na academia
- Visualização do histórico de check-ins

---

## Requisitos Funcionais (RF)

| Código | Descrição |
|--------|-----------|
| RF01 | O sistema deve permitir cadastro de usuário. |
| RF02 | O sistema deve permitir login com e-mail e senha. |
| RF03 | O usuário deve poder criar uma rotina de treino. |
| RF04 | O usuário deve poder associar exercícios à rotina. |
| RF05 | O usuário deve poder registrar check-in. |
| RF06 | O sistema deve permitir visualizar histórico de check-ins. |

---

## Requisitos Não Funcionais (RNF)

| Código | Descrição |
|--------|-----------|
| RNF01 | O sistema deve ser acessível via navegador. |
| RNF02 | O tempo de resposta deve ser inferior a 3 segundos. |
| RNF03 | As senhas devem ser armazenadas de forma segura. |
| RNF04 | A interface deve ser simples e intuitiva. |

---

## Principais Tecnologias e Justificativas

### Backend

- **Java** — Linguagem robusta, orientada a objetos e amplamente utilizada no mercado.
- **Spring Boot** — Framework que facilita a criação de APIs REST com Java, reduzindo configuração e aumentando produtividade.

### Banco de Dados

- **PostgreSQL** — Banco de dados relacional confiável, gratuito e adequado para modelagem com relacionamentos entre usuários, rotinas, exercícios e check-ins.

### Frontend

- **HTML, CSS e JavaScript** — Tecnologias leves e suficientes para desenvolver uma interface web simples para o sistema.

### Controle de Versão

- **Git e GitHub** — Permitem versionamento do código e colaboração entre os integrantes da dupla.

---

## Organização Simples de Tarefas (Dupla)

**Integrante 1**
- Desenvolvimento do backend (Java e Spring Boot)
- Modelagem do banco de dados
- Implementação do sistema de check-in
- Criação das APIs

**Integrante 2**
- Desenvolvimento do frontend
- Criação das telas (cadastro, login, rotinas e check-in)
- Integração com a API
- Documentação do projeto

---

## Figma

[Protótipo no Figma — On Trainer](https://www.figma.com/design/iwhrNspDMjd0KwXAa3tgmk/On-Trainer?node-id=0-1&t=z4CJK0AmPcUYQEhI-1)

## Nível 1 — Diagrama de Contexto

```mermaid
    flowchart LR
    usuario["<b>Usuário da Academia</b>
    <i>[Pessoa]</i>

    Frequentador que cadastra rotinas,
    exercícios e registra check-ins."]

    sistema["<b>Sistema de Gerenciamento de Academia</b>
    <i>[Sistema de Software]</i>

    Cadastro de usuários, login, criação de
    rotinas de treino, associação de exercícios
    e registro de check-ins."]

    usuario -->|"Cadastra-se, gerencia rotinas
    e registra check-ins
    <i>[HTTPS]</i>"| sistema

    classDef pessoa fill:#08427b,stroke:#052e56,stroke-width:2px,color:#ffffff
    classDef sistema fill:#1168bd,stroke:#0b4884,stroke-width:2px,color:#ffffff

    class usuario pessoa
    class sistema sistema
```

## Nível 2: - Diagrama de Contêineres

```mermaid
flowchart TB
    usuario["<b>Usuário da Academia</b>
    <i>[Pessoa]</i>

    Frequentador da academia."]

    subgraph sistema [Sistema de Gerenciamento de Academia]
        direction TB
        frontend["<b>Frontend Web</b>
        <i>[HTML, CSS, JavaScript]</i>

        Interface do usuário
        acessível via navegador."]

        backend["<b>Backend API REST</b>
        <i>[Java 17, Spring Boot]</i>

        Processa as regras de negócio
        e expõe endpoints REST."]

        bancoDados[("<b>Banco de Dados</b>
        <i>[PostgreSQL 15]</i>

        Armazena usuários, rotinas,
        exercícios e check-ins.")]
    end

    usuario -->|"Acessa pelo navegador
    [HTTPS]"| frontend
    frontend -->|"Consome endpoints REST
    [JSON / HTTPS]"| backend
    backend -->|"Lê e persiste dados
    [JDBC / JPA]"| bancoDados

    classDef pessoa fill:#08427b,stroke:#052e56,stroke-width:2px,color:#ffffff
    classDef container fill:#438dd5,stroke:#2e6295,stroke-width:2px,color:#ffffff

    class usuario pessoa
    class frontend,backend,bancoDados container

    style sistema fill:none,stroke:#888888,stroke-width:2px,stroke-dasharray:5 5
```
## Nível 3: - Diagrama de Componentes

```mermaid
flowchart TB
    usuario["<b>Usuário da Academia</b>
    <i>[Pessoa]</i>"]

    frontend["<b>Frontend Web</b>
    <i>[HTML, CSS, JavaScript]</i>"]

    bancoDados[("<b>Banco de Dados</b>
    <i>[PostgreSQL 15]</i>")]

    subgraph backend [Backend API REST - Java 17 / Spring Boot]
        direction TB

        subgraph seg [Segurança]
            direction TB
            securityConfig["<b>Security Config</b>
            <i>[Spring Security]</i>"]
            jwtUtil["<b>JWT Util</b>
            <i>[JJWT]</i>"]
            passwordEncoder["<b>Password Encoder</b>
            <i>[BCrypt]</i>"]
        end

        subgraph auth [Autenticação]
            direction TB
            authController["<b>Auth Controller</b>
            <i>[REST Controller]</i>"]
            authService["<b>Auth Service</b>
            <i>[Spring Service]</i>"]
        end

        subgraph usr [Usuário]
            direction TB
            usuarioController["<b>Usuario Controller</b>
            <i>[REST Controller]</i>"]
            usuarioService["<b>Usuario Service</b>
            <i>[Spring Service]</i>"]
            usuarioRepository["<b>Usuario Repository</b>
            <i>[Spring Data JPA]</i>"]
        end

        subgraph rot [Rotina]
            direction TB
            rotinaController["<b>Rotina Controller</b>
            <i>[REST Controller]</i>"]
            rotinaService["<b>Rotina Service</b>
            <i>[Spring Service]</i>"]
            rotinaRepository["<b>Rotina Repository</b>
            <i>[Spring Data JPA]</i>"]
        end

        subgraph exe [Exercício]
            direction TB
            exercicioController["<b>Exercicio Controller</b>
            <i>[REST Controller]</i>"]
            exercicioService["<b>Exercicio Service</b>
            <i>[Spring Service]</i>"]
            exercicioRepository["<b>Exercicio Repository</b>
            <i>[Spring Data JPA]</i>"]
        end

        subgraph chk [Check-in]
            direction TB
            checkinController["<b>Checkin Controller</b>
            <i>[REST Controller]</i>"]
            checkinService["<b>Checkin Service</b>
            <i>[Spring Service]</i>"]
            checkinRepository["<b>Checkin Repository</b>
            <i>[Spring Data JPA]</i>"]
        end
    end

    usuario -->|"[HTTPS]"| frontend
    frontend -->|"/auth/register, /auth/login"| authController
    frontend -->|"GET /usuarios/me"| usuarioController
    frontend -->|"CRUD /rotinas"| rotinaController
    frontend -->|"CRUD /exercicios"| exercicioController
    frontend -->|"POST e GET /checkins"| checkinController

    securityConfig -->|"valida token"| jwtUtil

    authController --> authService
    authService -->|"hash de senha"| passwordEncoder
    authService -->|"gera token"| jwtUtil
    authService --> usuarioRepository

    usuarioController --> usuarioService
    usuarioService --> usuarioRepository

    rotinaController --> rotinaService
    rotinaService --> rotinaRepository
    rotinaService -.->|"verifica dono"| usuarioRepository

    exercicioController --> exercicioService
    exercicioService --> exercicioRepository
    exercicioService -.->|"verifica rotina"| rotinaRepository

    checkinController --> checkinService
    checkinService --> checkinRepository
    checkinService -.->|"associa usuário"| usuarioRepository

    usuarioRepository -->|"JPA/JDBC"| bancoDados
    rotinaRepository -->|"JPA/JDBC"| bancoDados
    exercicioRepository -->|"JPA/JDBC"| bancoDados
    checkinRepository -->|"JPA/JDBC"| bancoDados

    classDef pessoa fill:#08427b,stroke:#052e56,stroke-width:2px,color:#ffffff
    classDef container fill:#438dd5,stroke:#2e6295,stroke-width:2px,color:#ffffff
    classDef component fill:#85bbf0,stroke:#5d82a8,stroke-width:1px,color:#000000

    class usuario pessoa
    class frontend,bancoDados container
    class securityConfig,jwtUtil,passwordEncoder,authController,authService,usuarioController,usuarioService,usuarioRepository,rotinaController,rotinaService,rotinaRepository,exercicioController,exercicioService,exercicioRepository,checkinController,checkinService,checkinRepository component

    style backend fill:none,stroke:#888888,stroke-width:2px,stroke-dasharray:5 5
    style seg fill:none,stroke:#bbbbbb,stroke-width:1px
    style auth fill:none,stroke:#bbbbbb,stroke-width:1px
    style usr fill:none,stroke:#bbbbbb,stroke-width:1px
    style rot fill:none,stroke:#bbbbbb,stroke-width:1px
    style exe fill:none,stroke:#bbbbbb,stroke-width:1px
    style chk fill:none,stroke:#bbbbbb,stroke-width:1px
```
## UML Banco de Dados 
<img width="1408" height="768" alt="image" src="https://github.com/user-attachments/assets/207749ce-2f93-4d40-bbc3-7932b95f70de" />
