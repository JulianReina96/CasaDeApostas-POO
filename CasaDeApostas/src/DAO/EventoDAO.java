package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Evento;

public interface EventoDAO {
	public Evento getEvento(int eventoID) throws SQLException;
	public List<Evento> listarEventos() throws SQLException;
	public boolean cadastrarEvento(Evento evento) throws SQLException;
	public Evento editarEvento(Evento evento) throws SQLException; 
	public boolean deletarEvento(Evento evento) throws SQLException;
	public boolean finalizarEvento(int eventoID, int tipoVencendorID) throws SQLException;
}
