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
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("SELECT * FROM EVENTOS WHERE ID = ?");
		ps.setInt(1, eventoID);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return new Evento(rs.getInt("ID"), 
					rs.getString("NOME"), 
					rs.getDate("DATAEVENTO").toLocalDate(), 
					rs.getString("DESCRICAO"), 
					rs.getString("TIMECASA"), 
					rs.getString("TIMEVISITANTE"), 
					rs.getDouble("ODDVITORIA"), 
					rs.getDouble("ODDDERROTA"), 
					rs.getDouble("ODDEMPATE"));
		}
		
		return null;
	}

	@Override
	public List<Evento> listarEventos() throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("SELECT * FROM EVENTOS");
		ResultSet rs = ps.executeQuery();
		List<Evento> eventos = new ArrayList<Evento>();
		while(rs.next()) {
			Evento e = new Evento(rs.getInt("ID"), 
					rs.getString("NOME"), 
					rs.getDate("DATAEVENTO").toLocalDate(), 
					rs.getString("DESCRICAO"), 
					rs.getString("TIMECASA"), 
					rs.getString("TIMEVISITANTE"), 
					rs.getDouble("ODDVITORIA"), 
					rs.getDouble("ODDDERROTA"), 
					rs.getDouble("ODDEMPATE"));
			
			eventos.add(e);
			
		}
		return eventos;
	}

	@Override
	public boolean cadastrarEvento(Evento evento) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao()
				.prepareStatement("INSERT INTO EVENTOS(NOME, DATAEVENTO, DESCRICAO, TIMECASA, TIMEVISITANTE, ODDVITORIA, ODDDERROTA, ODDEMPATE) "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?");
		
		ps.setString(1, evento.getNome());
		ps.setDate(2, Date.valueOf(evento.getDataEvento()));
		ps.setString(3, evento.getDescricao());
		ps.setString(4, evento.getTimeCasa());
		ps.setString(5, evento.getTimeVisitante());
		ps.setDouble(6, evento.getOddVitoria());
		ps.setDouble(7, evento.getOddDerrota());
		ps.setDouble(8, evento.getOddEmpate());
		
		if(ps.executeUpdate() > 0)
			return true;
		return false;
	}

	@Override
	public Evento editarEvento(Evento evento) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao()
				.prepareStatement("UPDATE EVENTOS SET (NOME = ?, DATAEVENTO = ?, DESCRICAO = ?, TIMECASA = ?, TIMEVISITANTE = ?, ODDVITORIA = ?, ODDDERROTA = ?, ODDEMPATE = ?) "
						+ "WHERE ID = ?");
		
		ps.setString(1, evento.getNome());
		ps.setDate(2, Date.valueOf(evento.getDataEvento()));
		ps.setString(3, evento.getDescricao());
		ps.setString(4, evento.getTimeCasa());
		ps.setString(5, evento.getTimeVisitante());
		ps.setDouble(6, evento.getOddVitoria());
		ps.setDouble(7, evento.getOddDerrota());
		ps.setDouble(8, evento.getOddEmpate());
		ps.setInt(9, evento.getID());
		
		if(ps.executeUpdate() == 1)
			return evento;
		return null;
	}

	@Override
	public boolean deletarEvento(Evento evento) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("DELETE FROM EVENTOS WHERE ID = ?");
		ps.setInt(1, evento.getID());
		if(ps.executeUpdate() > 0)
			return true;
		return false;
	}

}
