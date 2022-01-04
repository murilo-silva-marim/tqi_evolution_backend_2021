# Prezada TQI,

---

#### Essa proposta de solução visa atender o seguinte cenário proposto:
> Dado o cenário abaixo, elabore uma sugestão para o back-end necessária para resolver o problema.
Use a criatividade! Você pode explorar aqui todos os seus conhecimentos, demonstrando fluxos, telas, protótipos e/ou codificação.
É importante sabermos por que você decidiu fazer a solução, portanto deixe clara a motivação das suas escolhas.
>
> Uma empresa de empréstimo precisa criar um sistema de análise de crédito para fornecer aos seus clientes as seguintes funcionalidades:
> 
> i. Cadastro de clientes
> 
> O cliente pode cadastrar: nome, e-mail, CPF, RG, endereço completo, renda e senha.
> 
> ii. Login 
> 
> A autenticação será realizada por e-mail e senha.
> 
> iii. Solicitação de empréstimo
> 
> Para solicitar um empréstimo, precisamos do valor do empréstimo, data da primeira parcela e quantidade de parcelas.
> 
> O máximo de parcelas será 60 e a data da primeira parcela deve ser no máximo 3 meses após o dia atual.
> 
> iv. Acompanhamento das solicitações de empréstimo
> 
> O cliente pode visualizar a lista de empréstimos solicitados por ele mesmo e também os detalhes de um de seus empréstimos.
> 
> Na listagem, devemos retornar no mínimo o código do empréstimo, o valor e a quantidade de parcelas.
> 
> No detalhe do empréstimo, devemos retornar: código do empréstimo, valor, quantidade de parcelas, data da primeira parcela, e-mail do cliente e renda do cliente.
>
> Restrições
>1. A implementação deve utilizar linguagem Java ou Kotlin.
>1. Use todos os seus conhecimentos adquiridos no bootcamp para explorar bem a solução. Não se preocupe, porque não existe certo ou errado. Só queremos conhecer um pouco mais sobre você.
>2. Utilize o GitHub para repositório de código.
>
>O prazo máximo para finalizar esta implementação é 10/01/2022. Portanto, explore bem este tempo!
>
>Quando finalizar, você deve criar uma tag no GitHub chamada tqi_evolution_avaliacao. Desta forma, nosso time já irá avaliar o material final.
>
>Bora pra este desafio?

---

## O Conceito
A ideia inicial do projeto era apresentar uma solução completa, concisa e autossuficiente. E para atingir esse objetivo foi escolhido um modelo de arquitetura monolito.
Esse modelo permite o controle da aplicação de forma centralizada, uma ótima abordagem para uma construção feita somente por uma pessoa que possui todo o controle.

Como a meta é autossuficiência e demonstração de conhecimento, foi pensado em uma solução front-end já acoplada ao back-end. Onde
teria uma API Rest para cadastro de clientes junto de um portal de acesso do cliente. Nesse portal ele teria as seguintes ações:
- Solicitar Empréstimo
- Checar seus empréstimos já solicitados

E nesse portal teria uma interface de acesso ao usuário, utilizando Bootstrap para construção do design das páginas.

Assim foi idealizado e então produzido, espero que gostem da minha solução!

---
### Neste projeto foi utilizado as seguintes tecnologias:
- #### Spring Framework
  - Para ter a simplicidade, poder e produtividade na criação e desenvolvimento de aplicações Java.
- #### Spring Security
  - Para trazer a segurança para dentro da aplicação, interceptando as requisições e barrando o acesso quando for necessário.  
- #### Hibernate Validator
  - Dependência utilizada para validar os dados da aplicação REST de Clientes e o formulário de Solicitação de empréstimo. 
- #### Lombok
  - Dependência utilizada para aumentar a qualidade do código, diminuindo a quantidade de código repetitivo. 
- #### PostgreSQL
  - Banco de dados relacional muito versátil, ótima opção para trabalhar com dados estruturados. Foi escolhido pela praticidade do SQL e pelo modelo do projeto ser parecido com sistemas CRM.  
- #### Swagger
  - Foi implementado como um auxílio à TQI para descoberta das rotas da aplicação.
- #### Thymeleaf
  - Motor de renderização de páginas server-side, utilizado para carregamento do HTML de forma dinâmica.  
- #### Bootstrap 5
  - Biblioteca utilizada para estilização e responsividade das páginas. 
- #### Input Mask
  - Biblioteca para formatação dos campos do formulário, aplicando máscaras nos inputs. 

---
