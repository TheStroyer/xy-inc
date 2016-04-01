# xy-inc

/*************************************
	TECNOLOGIAS UTILIZADAS
**************************************/

1) Versão da jdk
	2.1) jdk1.8.0_20

2) Linguagem de programação
	2.1) Java

3) Banco de dados
	3.1) MySQL

4) Servidor Web
	4.1) Apache Tomcat 8.0.33

5) Framework Java
	5.1) Spring Boot

6) Template Engine
	6.1) Thymeleaf

7) Framework front-end
	7.1) Bootstrap

8) Biblioteca JavaScript
	8.1) jQuery 1.12

/*************************************
	INSTRUÇÕES PARA CONFIGURAÇÃO
**************************************/

1) Configurações de banco de dados:
	1.1) As informações para conexão no banco estão disponíveis no arquivo "application.properties". O nome do schema utilizado foi 'zup' e os scripts para criação dos modelos de dados no banco estão disponíveis no arquivo "tabelas.sql"

2) Confoguração do Servidor
	2.1) O web container usado no desenvolvimento desta versao foi o tomcat na porta 8081. Caso queira utilizar outra porta, basta alterar a propriedade server.port no arquivo "application.properties"

/*************************************
	ARQUITETURA PROPOSTA
**************************************/

O projeto foi desenvolvido utilizando as últimas tecnologias do mercado, fazendo com que ele esteja o mais atualizado possivel com todas as melhorias disponíveis com a evolução das linguagens, servidores, frameworks, bibliotecas e etc. tornando-o mais robusto e confiável. 
A arquitetura proposta utiliza um framework, bastante conhecido no mercado, o spring, isso torna sua manutenção mais fácil uma vez que, por exemplo, a mudança de um banco de dados se torna uma tarefa simples, alterado apenas um arquivo de configuração, ficando o restante transparente para o cliente. Tratando da lógica de negócio a arquitetura foi proposta para permitir mudanças e adaptar se a elas, por exemplo, no conceito de tipos de dados de atributos basta configurar um enum e a tabela do banco, para todo a aplicação se adaptar a elas. 
A solução é simples contendo um controlador para as requisições, entidades bem definidas e classes simples para gerenciamento dos reposirórios.

/*************************************
	INSTRUÇÕES PARA EXECUÇÃO
**************************************/

1) Execução
	3.1) Uma vez realizado as configurações acima, basta executar o jar xy-inc-0.1.0.jar: java -jar xy-inc-0.1.0
	3.2) Caso esteja com o codigo fonte aberto, rodar a classe "Application.java"

/***************************************
	INSTRUÇÕES PARA TESTES DA APLICAÇÃO
****************************************/

1) Tabela de fluxos

FL01) Fluxo de sucesso
FL02) Fluxo de erro

2) Tabela de cenarios

CN01) Criar modelo 
CN02) Criar modelo com erro de nome
CN03) Criar modelo com erro de attributo
CN04) Excluir modelo
CN05) Listar modelos
CN06) Criar instância de modelo especifico com sucesso
CN07) Listar instâncias de um modelo especifico
CN08) Buscar instância de modelo especifico
CN09) Editar instância de um modelo especifico com sucesso
CN010) Excluir instância de um modelo especifico

3) Casos de Testes

	ID de Caso de Teste: 1

	ID Cenário:	CN01)

	ID do fluxo: FL01)

	Descrição:
		Acessar a home e clicar em criar modelo.

	Condição:
		Nome: "Products"
		Attributo 1 -> 
			Nome: "Nome". 
			Tipo: "Palavra".

	Resultado Esperado:
		Modelo criado com sucesso.

**************************************************

**************************************************

	ID de Caso de Teste: 2

	ID Cenário:	CN02)

	ID do fluxo: FL02)

	Descrição:
		Acessar a home e clicar em criar modelo.

	Condição:
		Nome: "Products"
		Attributo 1 -> 
			Nome: Deixar o campo vazio 
			Tipo: "Palavra".

	Resultado Esperado:
		Erro ao criar modelo!

**************************************************

**************************************************

	ID de Caso de Teste: 3

	ID Cenário:	CN03)

	ID do fluxo: FL02)

	Descrição:
		Acessar a home e clicar em criar modelo.

	Condição:
		Nome: Deixar o campo vazio 
		Attributo 1 -> 
			Nome: "Nome"
			Tipo: "Palavra".

	Resultado Esperado:
		Erro ao criar modelo!

**************************************************

**************************************************

	ID de Caso de Teste: 4

	ID Cenário:	CN04)

	ID do fluxo: FL01)

	Descrição:
		Acessar a home e clicar em listar modelos. Selecionar um modelo na listagem e clicar em "excluir"

	Condição:
		Existir pelo menos um modelo criado.

	Resultado Esperado:
		Recarregar a página sem o modelo.

**************************************************

**************************************************

	ID de Caso de Teste: 5

	ID Cenário:	CN05)

	ID do fluxo: FL01)

	Descrição:
		Acessar a home e clicar em listar modelos. 

	Condição:
		Existir pelo menos um modelo criado.

	Resultado Esperado:
		Listar todos os modelos cadastrados

**************************************************

**************************************************

	ID de Caso de Teste: 6

	ID Cenário:	CN06)

	ID do fluxo: FL01)

	Descrição:
		Acessar a home e clicar em listar modelos. Selecionar um modelo e clicar no botão "Criar".
		Na página que abrir, preencher os campos e clicar em salvar.


	Condição:
		Existir um modelo chamada Automoveis criado com dois atributos: marca e quantidade.
		marca: FIAT
		quantidade: 100 
		
	Resultado Esperado:
		Direcionar para a página de listagem com Sucesso ao criar modelo!

**************************************************

**************************************************

	ID de Caso de Teste: 7

	ID Cenário:	CN07)

	ID do fluxo: FL01)

	Descrição:
		Acessar a home e clicar em listar modelos. Selecionar um modelo e clicar no em seu link correspondente

	Condição:
		Existir pelo menos uma instancia cadastrada do modelo selecionado.
		
	Resultado Esperado:
		Direcionar para a página de listagem das instancias do modelo.

**************************************************

**************************************************

	ID de Caso de Teste: 8

	ID Cenário:	CN08)

	ID do fluxo: FL01)

	Descrição:
		Executar o caso de teste 7 e clicar em uma instancia

	Condição:
		Existir a instancia cadastrada.
		
	Resultado Esperado:
		JSon com as informações do modelo (attributos e valores dos attributos)

**************************************************

**************************************************

	ID de Caso de Teste: 9

	ID Cenário:	CN09)

	ID do fluxo: FL01)

	Descrição:
		Executar o caso de teste 7, selecionar uma instancia e clicar no botao Editar. Preencher os novos valores e clicar em "Salvar".

	Condição:
		Existir a instancia cadastrada.
		
	Resultado Esperado:
		Voltar para a página de listagem de modelos

**************************************************

**************************************************

	ID de Caso de Teste: 10

	ID Cenário:	CN09)

	ID do fluxo: FL01)

	Descrição:
		Executar o caso de teste 7, selecionar uma instancia e clicar em "excluir"

	Condição:
		Existir a instancia cadastrada.
		
	Resultado Esperado:
		Voltar para a página de listagem de modelos

**************************************************

**************************************************

