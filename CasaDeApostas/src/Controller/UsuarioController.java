package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.UsuarioDAO;
import DAO.UsuarioPostgreDAO;
import Model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioController {
	private UsuarioDAO usuarioDAO = new UsuarioPostgreDAO();
	
	public boolean cadastrarUsuario(Usuario user) throws SQLException{
		user.setSenha(criptografarSenha(user)); 
		return usuarioDAO.CadastrarUsuario(user);
	}
	public Usuario EditarUsuario(Usuario user) throws SQLException {
		return usuarioDAO.EditarUsuario(user);
	}
	public boolean DeletarUsuario(String email) throws SQLException {
		return usuarioDAO.DeletarUsuario(email);
	}
	
	public Usuario Login(Usuario user) throws SQLException {
		
		var usuario = usuarioDAO.getUsuario(user);
		if(usuario == null)
			return null;
		else if(BCrypt.checkpw(user.getSenha(), usuario.getSenha()))
			return usuario;
		else
			return null;
	}
	
	public String criptografarSenha(Usuario user) {
	    return BCrypt.hashpw(user.getSenha(), BCrypt.gensalt());
	}
	
	public String criptografarSenha(String senha) {
	    return BCrypt.hashpw(senha, BCrypt.gensalt());
	}
	
	public boolean verificarSenha(String senhaUser, String senhaDigitada) {
		return (BCrypt.checkpw(senhaUser, senhaDigitada));
	}
}
