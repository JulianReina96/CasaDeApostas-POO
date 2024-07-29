package Business;

import java.sql.SQLException;
import java.util.List;

import DAO.ApostaDAO;
import DAO.ApostaPostgreDAO;
import Model.Aposta;

public class ApostaBusiness {
	private ApostaDAO apostaDAO = new ApostaPostgreDAO();
	
	public Aposta getAposta(int apostaID) throws SQLException{
		return apostaDAO.getAposta(apostaID);
	}
	
	public List<Aposta> listarApostas(int usuarioID) throws SQLException{
		return apostaDAO.listaApostas(usuarioID);
	}
	
	public Aposta reaizarAposta(Aposta aposta) throws SQLException{
		return apostaDAO.realizarAposta(aposta);
	}
	
	public Aposta atualizarStatus(Aposta aposta) throws SQLException{
		return apostaDAO.atualizarStatus(aposta);
	}
}
