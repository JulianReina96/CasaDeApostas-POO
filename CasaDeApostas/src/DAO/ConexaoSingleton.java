package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSingleton {
    private static ConexaoSingleton instance;
    private Connection conexao;

    private ConexaoSingleton() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        this.conexao = DriverManager.getConnection(url, "postgres", "123");
    }

    public Connection getConexao() {
        return this.conexao;
    }

    public static ConexaoSingleton getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConexaoSingleton();
        }
        return instance;
    }
}

