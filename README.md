# desafio-cadmus

##Desafio Técnico
  Projeto com objetivo de implementar uma proposta de 3 desafios:
  - Subcadeia de soma máxima
    ```
    Dado um conjunto de números, descobrir o subconjunto em que a soma dos elementos são de máxima soma. 
    
    Exemplo: dado o conjunto de elementos 
    [2, -4, 6, 8, -10, 100, -6, 5]
    O subconjunto de soma máxima é:
    [2, -4, 6, 8, -10, 100, -6, 5]
    Assim, o programa deve retornar a posição do primeiro e do último elemento da subcadeia. 
    Neste exemplo, as posições 2 e 5, considerando a primeira posição com índice 0.
    ```

  - Conjectura de Collatz
    ```
    A seguinte sequência iterativa é definida pelo conjunto de inteiros positivos onde:

    n -> n/2 (se n é par) 
    n -> 3n + 1 (se n é impar)

    Usando as regras acima e começando pelo número 13, nós geraríamos a seguinte sequência:

    13 40 20 10 5 16 8 4 2 1

    O que pode ser observado dessa sequência (começando no 13 e terminando no 1) é que ela contém 10 itens. 
    Embora ainda não esteja matematicamente provado, é esperando que, dado um numero inteiro positivo qualquer, 
    a sequencia sempre chegará em 1.

    Qual inteiro positivo abaixo de 1 milhão, produz a sequência com mais itens?

    OBS: seu código precisa executar em menos de 5 segundos para o caso de 1 milhão.
    ```
    
  - Sistema Robô
    ```
    Suponha que você precisa implementar um robô controlado pelos seguintes comandos:
    
    L - Virar 90 graus para a esquerda
    R - Virar 90 graus para a direita
    M - Mover um passo para frente
    T - Se tele-transportar para uma determinada célula 
    
    O robô anda em um plano cartesiano, em um espaço de tamanho especifico, com quatro direções Norte(N), Sul (S), Leste (E), Oeste (W). 
    Ele não pode se mover ou tele transportar para fora desse espaço. 
    O input do problema vem de um arquivo com o seguinte formato:
    
    1a linha: o tamanho do espaço que o robô pode usar - X<espaço>Y
    2a linha: localização inicial do robô e sua direção - X<espaço>Y<espaço>D
    3a linha em diante: comandos do robô sem separação. A exceção é o comando de teletransporte, que deve ficar em sua própria linha com o seguinte formato - T<espaço>X<espaço>Y
    
    Um exemplo (sem os comentários):
    
    10 10              # o tamanho do espaço é 10 por 10
    2 5 N              # sua localização inicial é (2,5) e a sua direção é Norte
    LLRRMMMRLRMMM      # um conjunto de comandos
    T 1 3              # o robô se tele transporta para o ponto (1,3)
    LLRRMMRMMRM        # um outro conjunto de comandos
    
    O resultado final deve ser dado pelo robô para indicar sua posição e para onde ele está apontando, por exemplo:
    
    2 4 E              # na posição 2 4, virado para Leste
    
    Assuma que a célula imediatamente ao norte de (x, y) é (x, y + 1) e a leste é (x + 1, y)
    ```

##Dependências
- [JDK 1.8+](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3+](http://maven.apache.org/download.cgi) Opicional

##Informações Gerais
> Para a execução dos comandos de compilação e execução dos desafios é necessário estar posicionado no diretório raiz do projeto

##Compilação do Projeto
###Utilizando o Apache Maven
```shell
mvn clean install
```

###Utilizando o Javac
**Desafio Subcadeia de soma máxima:**
```shell
#Unix Like
javac -sourcepath src/main/java -d target/classes src/main/java/br/com/rjansen/desafios/SubcadeiaSomaMaxima.java

#Windows
javac -sourcepath src\main\java -d target\classes src\main\java\br\com\rjansen\desafios\SubcadeiaSomaMaxima.java
```

**Desafio Conjectura de Collatz**
```shell
#Unix Like
javac -sourcepath src/main/java -d target/classes src/main/java/br/com/rjansen/desafios/Collatz.java

#Windows
javac -sourcepath src\main\java -d target\classes src\main\java\br\com\rjansen\desafios\Collatz.java
```

**Desafio Sistema Robô**
```shell
#Unix Like
javac -sourcepath src/main/java -d target/classes src/main/java/br/com/rjansen/desafios/SistemaRobo.java

#Windows
javac -sourcepath src\main\java -d target\classes src\main\java\br\com\rjansen\desafios\SistemaRobo.java
```

##Execução dos Desafios
**Desafio Subcadeia de soma máxima:**
```shell
#Unix Like
java -cp target/classes/ br.com.rjansen.desafios.SubcadeiaSomaMaxima [cadeia]

#Windows
java -cp target\classes\ br.com.rjansen.desafios.SubcadeiaSomaMaxima [cadeia]
```
**Onde:**
- [cadeia]=1,20,-3,4000,n
- n precisa ser um numero inteiro valido

**Desafio Conjectura de Collatz**
```shell
#Unix Like
java -cp target/classes/ br.com.rjansen.desafios.Collatz [numero_inicial_collatz]

#Windows
java -cp target\classes\ br.com.rjansen.desafios.Collatz [numero_inicial_collatz]
```
**Onde:**
- [numero_inicial_collatz]=n
- n precisa ser um numero inteiro longo valido

**Desafio Sistema Robô**
```shell
#Unix Like
java -cp target/classes/ br.com.rjansen.desafios.SistemaRobo [arquivo_comandos]

#Windows
java -cp target\classes\ br.com.rjansen.desafios.SistemaRobo [arquivo_comandos]
```
**Onde:**
- [arquivo_comandos]=/caminho_arquivo/comandos
