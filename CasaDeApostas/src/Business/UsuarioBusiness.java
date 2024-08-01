package Business;

import java.sql.SQLException;

import DAO.UsuarioDAO;
import DAO.UsuarioPostgreDAO;
import Model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioBusiness {
	private UsuarioDAO usuarioDAO = new UsuarioPostgreDAO();
	
	public boolean cadastrarUsuario(Usuario user) throws SQLException{
		user.setSenha(criptografarSenha(user)); 
		return usuarioDAO.CadastrarUsuario(user);
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
	
	private String criptografarSenha(Usuario user) {
	    return BCrypt.hashpw(user.getSenha(), BCrypt.gensalt());
	}
}
