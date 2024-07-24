package Business;

import java.sql.SQLException;

import Model.Usuario;

public class testeUsuario {

	public static void main(String[] args) {
		Usuario user = new Usuario("andre@gmail.com", "senhaFoda");
		
		UsuarioBusiness userBusiness = new UsuarioBusiness();
		
		try {
			user = userBusiness.Login(user);
			System.out.println(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
