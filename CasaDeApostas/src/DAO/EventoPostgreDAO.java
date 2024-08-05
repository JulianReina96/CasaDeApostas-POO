package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Evento;

public class EventoPostgreDAO implements EventoDAO{

	@Override
	public Evento getEvento(int eventoID) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("SELECT * FROM EVENTO WHERE ID = ?");
		ps.setInt(1, eventoID);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return new Evento(rs.getInt("ID"), 
					rs.getString("NOME"), 
					rs.getDate("DATA").toLocalDate(), 
					rs.getString("timeCasa"), 
					rs.getString("timeVisitante"), 
					rs.getDouble("oddCasa"), 
					rs.getDouble("oddVisitante"),
					rs.getDouble("oddEmpate"), 
					rs.getBoolean("aberta"));
		}
		
		return null;
	}

	@Override
	public List<Evento> listarEventos() throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("SELECT * FROM EVENTO");
		ResultSet rs = ps.executeQuery();
		List<Evento> eventos = new ArrayList<Evento>();
		while(rs.next()) {
			Evento e = new Evento(rs.getInt("ID"), 
					rs.getString("NOME"), 
					rs.getDate("DATA").toLocalDate(), 
					rs.getString("timeCasa"), 
					rs.getString("timeVisitante"), 
					rs.getDouble("oddCasa"), 
					rs.getDouble("oddVisitante"),
					rs.getDouble("oddEmpate"), 
					rs.getBoolean("aberta"));
			
			eventos.add(e);
			
		}
		return eventos;
	}

	@Override
	public boolean cadastrarEvento(Evento evento) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao()
				.prepareStatement("INSERT INTO EVENTO(NOME, DATA, TIMECASA, TIMEVISITANTE, ODDCASA, ODDVISITANTE, ODDEMPATE, ABERTA)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?");
		
		ps.setString(1, evento.getNome());
		ps.setDate(2, Date.valueOf(evento.getDataEvento()));
		ps.setString(3, evento.getTimeCasa());
		ps.setString(4, evento.getTimeVisitante());
		ps.setDouble(5, evento.getOddVitoria());
		ps.setDouble(6, evento.getOddDerrota());
		ps.setDouble(7, evento.getOddEmpate());
		ps.setBoolean(8, evento.getAberta());
		
		if(ps.executeUpdate() > 0)
			return true;
		return false;
	}

	@Override
	public Evento editarEvento(Evento evento) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao()
				.prepareStatement("UPDATE EVENTO SET (NOME = ?, DATA = ?, TIMECASA = ?, TIMEVISITANTE = ?, ODDCASA = ?, ODDVISITANTE = ?, ODDEMPATE = ?, ABERTA = ?) "
						+ "WHERE ID = ?");
		
		ps.setString(1, evento.getNome());
		ps.setDate(2, Date.valueOf(evento.getDataEvento()));
		ps.setString(3, evento.getTimeCasa());
		ps.setString(4, evento.getTimeVisitante());
		ps.setDouble(5, evento.getOddVitoria());
		ps.setDouble(6, evento.getOddDerrota());
		ps.setDouble(7, evento.getOddEmpate());
		ps.setBoolean(8, evento.getAberta());
		ps.setInt(9, evento.getID());
		
		if(ps.executeUpdate() == 1)
			return evento;
		return null;
	}

	@Override
	public boolean deletarEvento(Evento evento) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("DELETE FROM EVENTO WHERE ID = ?");
		ps.setInt(1, evento.getID());
		if(ps.executeUpdate() > 0)
			return true;
		return false;
	}

}
