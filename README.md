# On-Trainer
Um Trabalho Feito Por Gabriel Albani e Miguel .A Guedes

Domínio do Problema

Muitos frequentadores de academia não possuem uma forma simples e organizada de registrar seus treinos e acompanhar sua frequência. Normalmente utilizam anotações em papel ou aplicativos complexos.
O sistema proposto tem como objetivo permitir:

Cadastro de usuários
Login no sistema
Criação de rotinas de treino (ex: “Treino de Perna”)
Associação de exercícios a uma rotina
Edição e exclusão de rotinas
Registro de check-in na academia
Visualização do histórico de check-ins
---------------------------------------------------------
Requisitos Funcionais (RF)

RF01 – O sistema deve permitir cadastro de usuário.
RF02 – O sistema deve permitir login com e-mail e senha.
RF03 – O usuário deve poder criar uma rotina de treino.
RF04 – O usuário deve poder associar exercícios à rotina.
RF05 – O usuário deve poder registrar check-in.
RF06 – O sistema deve permitir visualizar histórico de check-ins.
---------------------------------------------------------
Requisitos Não Funcionais (RNF)

RNF01 – O sistema deve ser acessível via navegador.
RNF02 – O tempo de resposta deve ser inferior a 3 segundos.
RNF03 – As senhas devem ser armazenadas de forma segura.
RNF04 – A interface deve ser simples e intuitiva.
---------------------------------------------------------
Principais Tecnologias e Justificativas

Backend

Java
Justificativa: Linguagem robusta, orientada a objetos e amplamente utilizada no mercado.
Spring Boot
Justificativa: Framework que facilita a criação de APIs REST com Java, reduzindo configuração e aumentando produtividade.

Banco de Dados

MySQL
Justificativa: Banco de dados relacional confiável, gratuito e adequado para modelagem com relacionamentos entre usuários, rotinas, exercícios e check-ins.

Frontend
HTML, CSS e JavaScript
Justificativa: Tecnologias leves e suficientes para desenvolver uma interface web simples para o sistema.

Controle de Versão
Git e GitHub
Justificativa: Permitem versionamento do código e colaboração entre os integrantes da dupla.
---------------------------------------------------------
Organização Simples de Tarefas (Dupla)

Integrante 1:

Desenvolvimento do backend (Java e Spring Boot)
Modelagem do banco de dados
Implementação do sistema de check-in
Criação das APIs

Integrante 2:

Desenvolvimento do frontend
Criação das telas (cadastro, login, rotinas e check-in)
Integração com a API
Documentação do projeto
