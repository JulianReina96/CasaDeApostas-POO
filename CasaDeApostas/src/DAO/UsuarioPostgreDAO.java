package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Usuario;

public class UsuarioPostgreDAO implements UsuarioDAO {

	
	public Usuario getUsuario(Usuario user) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("SELECT * FROM USUARIO WHERE EMAIL = ?;");
		ps.setString(1, user.getEmail());
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return new Usuario(
					rs.getInt("ID"), 
					rs.getString("NOME"),  
					rs.getString("EMAIL"), 
					rs.getString("SENHA"), 
					rs.getDouble("saldo"),
					rs.getBoolean("ADMINISTRADOR"));
		}
		
		return null;
	}

	@Override
	public boolean CadastrarUsuario(Usuario user) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("INSERT INTO USUARIO(NOME, EMAIL, SENHA, SALDO, ADMINISTRADOR) VALUES(?, ?, ?, ?, ?);");
		ps.setString(1, user.getNome());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getSenha());
		ps.setDouble(4, user.getSaldo());
		ps.setBoolean(5, user.isAdministrador());
		
		if(ps.executeUpdate() > 0)
			return true;
		return false;
	}

	@Override
	public Usuario EditarUsuario(Usuario user) throws SQLException{
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("UPDATE USUARIO SET NOME = ?, EMAIL = ?, SENHA = ?, SALDO = ?, ADMINISTRADOR = ? WHERE EMAIL = ?");
		ps.setString(1, user.getNome());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getSenha());
		ps.setDouble(4, user.getSaldo());
		ps.setBoolean(5, user.isAdministrador());
		ps.setString(6, user.getEmail());

		
		if(ps.executeUpdate() > 0)
			return user;
		return null;
	}

	@Override
	public boolean DeletarUsuario(Usuario user) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("DELETE FROM USUARIO WHERE ID = ?;");
		ps.setInt(1, user.getID());
		if(ps.executeUpdate() > 0)
			return true;
		return false;
	}

}
