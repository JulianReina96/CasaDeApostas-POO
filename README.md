<h1 align="center">Projeto Casa de Apostas - INF008</h1>

<p align="center">Projeto avaliativo utilizando Java com Spring Framework para demonstração na disciplina INF008 - Programação Orientada a Objetos.</p>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-000?style=for-the-badge&logo=postgresql)

Este projeto executa utilizando [Java](https://www.java.com/pt-BR/)

=======
## Tecnologias

As tecnologias que serão usadas nesse projeto são:

- [Java](https://www.java.com/pt-BR/) - Com o framework [Spring](https://spring.io/) para renderização das telas.
- [Postgres SQL](https://www.postgresql.org/) - Como nossa database, executando localmente.
- [jbcrypt-0.4](https://www.mindrot.org/projects/jBCrypt/) Para criptografia de senhas do sistema.


=======
## Instalação

[Documentação](https://docs.google.com/document/d/13A02iXFY9bCg7rfXiEJ7scn2iDqL5qeK8xdFnEhhGig/edit?usp=sharing)

Depois de clonar este repositório, você precisa executar os seguintes passos para executar a aplicação:

-Instalação do Java e IDE de sua escolha.(Para o desenvolvimento, optamos por utilizar a IDE [Eclipse](https://www.eclipse.org/downloads/)

-Instalação e configuração do Postgres SQL.

Após configuração de ambiente, abrir IDE instalada e referenciar as seguintes bibliotecas:

[jbcrypt-0.4](https://www.mindrot.org/projects/jBCrypt/) para criptografia.

[postgresql-42.7.3](https://jdbc.postgresql.org/changelogs/2024-03-14-42.7.3-release/) driver JDBC do Postgres para comunicação com o banco de dados.

Após as referências é preciso configurar a conexão com o banco de dados. Mantemos a configuração padrão para o projeto, mas caso a instalação tenha sido feita fora do padrão, é necessario realizar as devidas alterações na classe ConexaoSingleton.java:

```
    private ConexaoSingleton() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        this.conexao = DriverManager.getConnection(url, "postgres", "123");
    }
```

O próximo passo é executar o script para criação e povoação das tabelas do sistema.

```
scripts SQL.sql
```
Em seguida executa-se o projeto pela IDE escolhida, através do seguinte arquivo:
 ```
Login.Java
```


Por padrão, um usuario administrador é cadastrado ao criar as tabelas do sistema:

**Login:** admin@catsbet.com

**Senha:** admin01

Com isso o projeto está pronto para ser executado!

![image](https://github.com/user-attachments/assets/46ea1d3a-1fa7-4c7f-a228-d6507cb0cf0e)
