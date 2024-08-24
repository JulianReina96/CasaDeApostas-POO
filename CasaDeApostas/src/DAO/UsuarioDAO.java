package DAO;

import java.sql.SQLException;

import Model.Usuario;

public interface UsuarioDAO {
	public Usuario getUsuario(Usuario user) throws SQLException;
	public boolean CadastrarUsuario(Usuario user) throws SQLException;
	public Usuario EditarUsuario(Usuario user) throws SQLException;
	public boolean DeletarUsuario(String email) throws SQLException;
}
