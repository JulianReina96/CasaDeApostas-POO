package Controller;

import java.sql.SQLException;
import java.util.List;

import DAO.EventoDAO;
import DAO.EventoPostgreDAO;
import Model.Evento;

public class EventoController {
	private EventoDAO eventoDAO = new EventoPostgreDAO();
	
	public boolean cadastrarEvento(Evento evento) throws SQLException {
		return eventoDAO.cadastrarEvento(evento);
	}
	
	public List<Evento> listarEventos() throws SQLException{
		return eventoDAO.listarEventos();
	}
	
	public Evento getEvento(int eventoID) throws SQLException {
		return eventoDAO.getEvento(eventoID);
	}
	
	public Evento editarEvento(Evento evento) throws SQLException {
		return eventoDAO.editarEvento(evento);
	}
	
	public boolean deletarEvento(Evento evento) throws SQLException{
		return eventoDAO.deletarEvento(evento);
	}
	
	public boolean finalizarEvento(int eventoID, int TipoVencededorID) throws SQLException{
		return eventoDAO.finalizarEvento(eventoID, TipoVencededorID);
	}
	
}
