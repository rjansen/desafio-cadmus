# desafio-cadmus

##Desafio Técnico
  Este projeto tem como objetivo implementar uma proposta de desafio. Esta proposta é composta por 3 desafios:
  - Subcadeia de soma máxima
  - Conjectura de Collatz
  - Sistema Robô

##Dependências
==============
- [JDK 1.8+](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3+](http://maven.apache.org/download.cgi)

##Informações Gerais
====================
> Para a execução dos comandos de compilação e execução dos desafios é necessário estar posicionado no diretório raiz do projeto

##Compilação do Projeto
========================
###Utilizando o Apache Maven
```shell
mvn clean install
```

###Utilizando o Javac
**Desafio Subcadeia de soma máxima:**
**Mac, Linux, SO Baseado em Unix**
```shell
javac -sourcepath src/main/java -d target/classes src/main/java/br/com/rjansen/desafios/SubcadeiaSomaMaxima.java
```
**Windows**
```shell
javac -sourcepath src\main\java -d target\classes src\main\java\br\com\rjansen\desafios\SubcadeiaSomaMaxima.java
```

**Desafio Conjectura de Collatz**
```shell
javac -sourcepath src/main/java -d target/classes src/main/java/br/com/rjansen/desafios/Collatz.java
```
**Desafio Sistema Robô**
```shell
javac -sourcepath src/main/java -d target/classes src/main/java/br/com/rjansen/desafios/SistemaRobo.java
```

##Execução dos Desafios
========================
**Desafio Subcadeia de soma máxima:**
```shell
java -cp target/classes/ br.com.rjansen.desafios.SubcadeiaSomaMaxima [cadeia]
```
**Onde:**
- [cadeia]=1,20,-3,4000,n
- n precisa ser um numero inteiro valido

**Desafio Conjectura de Collatz**
```shell
java -cp target/classes/ br.com.rjansen.desafios.Collatz [numero_inicial_collatz]
```
**Onde:**
- [numero_inicial_collatz]=n
- n precisa ser um numero inteiro longo valido

**Desafio Sistema Robô**
```shell
java -cp target/classes/ br.com.rjansen.desafios.SistemaRobo [arquivo_comandos]
```
**Onde:**
- [arquivo_comandos]=/caminho_arquivo/comandos
