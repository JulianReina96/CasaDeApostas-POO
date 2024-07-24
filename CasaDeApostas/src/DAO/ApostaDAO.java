package DAO;

import java.sql.SQLException;
import java.util.List;
import Model.Aposta;


public interface ApostaDAO {
	public List<Aposta> listaApostas(int usuarioID) throws SQLException;
	public Aposta getAposta(int apostaID) throws SQLException;
	public Aposta realizarAposta(Aposta aposta) throws SQLException;
	public Aposta atualizarStatus(Aposta aposta) throws SQLException;
}
