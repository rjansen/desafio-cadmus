# desafio-cadmus
Desafio Técnico
- Foi utilizada a linguagem de progamação Java

Dependências:
=============
- JDK 1.8+
- Maven 3+
- Baixe o maven em: http://maven.apache.org/download.cgi
- Para instalar basta descompactar e colocar a sua pasta bin no path do sistema operacional

Para compilar e instalar execute na pasta raiz: 
mvn clean install

Para executar cada desafio faça:
- utilize o shell de comandos e execute um cd para a pasta target na raiz do projeto.
- O diretório corrente deve ser o desafio-cadmus

Windows:
--------
- c:\desafio-cadmus>cd target\classes

Baseados em Unix:
-----------------
- desafio-cadmus$ cd target/classes/

Subcadeia de soma máxima:
- java br.com.rjansen.desafios.SubcadeiaSomaMaxima [cadeia]

Onde:
- [cadeia]=1,20,-3,4000,n
- n precisa ser um numero inteiro valido

Conjectura de Collatz:
- java br.com.rjansen.desafios.Collatz [numero_inicial_collatz]

Onde:
- [numero_inicial_collatz]=n
- n precisa ser um numero inteiro longo valido

Sistema Robô:
- java br.com.rjansen.desafios.SistemaRobo [arquivo_comandos]

Onde:
- [arquivo_comandos]=/caminho_arquivo/comandos
