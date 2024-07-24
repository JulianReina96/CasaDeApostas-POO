package Business;

import java.sql.SQLException;

import DAO.UsuarioDAO;
import DAO.UsuarioPostgreDAO;
import Model.Usuario;

public class UsuarioBusiness {
	private UsuarioDAO usuarioDAO = new UsuarioPostgreDAO();
	
	public boolean cadastrarUsuario(Usuario user) throws SQLException{
		user.setSenha(criptografarSenha(user)); 
		return usuarioDAO.CadastrarUsuario(user);
	}
	
	public Usuario Login(Usuario user) throws SQLException {
		user.setSenha(criptografarSenha(user));
		return usuarioDAO.getUsuario(user);
	}
	
	private String criptografarSenha(Usuario user) {
		String msgCript = "";
		String msg = user.getSenha();
	    for (int i = 0; i < msg.length(); i++) {
	        msgCript += (char) (msg.charAt(i) + 1234);
	    }
	    return msgCript;
	}
}
