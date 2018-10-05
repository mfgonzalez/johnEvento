# john-evento-h1
Trabalho da disciplina de Introdução ao Desenvolvimento Ágil - Uniritter - 2018

[![Build Status](https://travis-ci.org/mfgonzalez/johnEvento.svg?branch=master)](https://travis-ci.org/mfgonzalez/johnEvento)
[![codecov](https://codecov.io/gh/mfgonzalez/johnEvento/branch/master/graph/badge.svg)](https://codecov.io/gh/mfgonzalez/johnEvento)
![Quality Gates](https://sonarcloud.io/api/project_badges/measure?project=mfgonzalez_johnEvento&metric=alert_status)

# US#1 - Criar evento

História:
Como gestor de eventos
Quero ter a possibilidade de criar eventos
Para que meus clientes tenham conhecimento de eventos e suas datas

Critérios

#1
Dado que quero criar um evento
Quando  carregar o formulário
Então deve apresentar os seguintes campos:
*Nome
*Data do evento


#2
Dado que quero criar um evento
Quando salvar o evento
Então os campos abaixo devem ser obrigatórios:
*Nome
*Data do evento

#3
Dado que quero criar um evento
Quando informar um name maior que 150 caracteres e salvar
Então não deve permitir salvar e informar a mensagem:
"O name permite no máximo 150 caracteres"

#4
Dado que quero criar um evento
Quando informar uma date inferior ao do dia atual e salvar
Então não deve permitir salvar e informar a mensagem:
"A date do evento deve ser igual ou maior que a de hoje"



# US#2 Ofertar Ingressos

História
Como gestor de eventos
Quero ter inúmeros tipos de ingresso
Para que eu possa atingir vários níveis de público

Critérios

#1
Dado que quero disponibilizar um ingresso VIP
Quando informar o valor
Então o mesmo deve ser 1000

#2
Dado que quero disponibilizar um ingresso BACKSTAGE
Quando informar o valor
Então o mesmo deve ser 800

#3
Dado que quero disponibilizar um ingresso PLATEIA VIP
Quando informar o valor
Então o mesmo deve ser 500

#4
Dado que quero disponibilizar um ingresso PLATEIA
Quando informar o valor
Então o mesmo deve ser 300

# US#3 Disponibilizar Vários Tipos de Ingressos

História
Como gestor de eventos
Quero  para cada evento disponibilizar variados tipos de ingressos com período de vendas
Para que eu possa atingir vários níveis de público

Critérios

#1
Dado que pretendo disponibilizar venda online
Quando cadastrar o evento
Então devo definir um período de venda obrigatório

#2
Dado que quero criar um evento
Quando informar a date de início de venda posterior a date fim de venda do evento
Então sistema não deve permitir salvar e informa a mensagem:
"A date de início de venda deve ser inferior a date de fim"

#3
Dado que quero vincular os tipos de ingresso disponíveis para o evento
Quando cadastrar o evento
Então quero definir quais tipos de ingressos estarão disponíveis

#4
Dado que quero criar um evento com ingressos
Quando salvar e ocorrer tipos de ingressos duplicados
Então sistema não deve permitir salvar
