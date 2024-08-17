package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.ApostaUsuarioDTO;
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

	
	public boolean finalizarEvento(int eventoID, int tipoVencendorID) throws SQLException {
	    Connection conn = ConexaoSingleton.getInstance().getConexao();
	    conn.setAutoCommit(false); // Desabilita o autocommit

	    try {
	        PreparedStatement ps1 = conn.prepareStatement("update evento set aberta = false where id = ?");
	        ps1.setInt(1, eventoID);
	        ps1.executeUpdate();

	        PreparedStatement ps2 = conn.prepareStatement("update aposta set statusapostaid = 2 where eventoid = ? and tipoapostaid = ?");
	        ps2.setInt(1, eventoID);
	        ps2.setInt(2, tipoVencendorID);
	        ps2.executeUpdate();
	        
	        PreparedStatement ps3 = conn.prepareStatement("update aposta set statusapostaid = 3 where eventoid = ? and tipoapostaid <> ?");
	        ps3.setInt(1, eventoID);
	        ps3.setInt(2, tipoVencendorID);
	        ps3.executeUpdate();
	        	        
	        PreparedStatement ps4 = conn.prepareStatement("select usuarioID, valor, "
	        		+ "	case when tipoapostaid = 1 then e.oddcasa "
	        		+ "	when tipoapostaid = 2 then e.oddempate "
	        		+ "	else e.oddvisitante "
	        		+ "	end "
	        		+ "	from aposta as a "
	        		+ "	inner join evento as e "
	        		+ "	on e.id = a.eventoid "
	        		+ "	where eventoid = ? and tipoapostaid = ?");
	        ps4.setInt(1, eventoID);
	        ps4.setInt(2, tipoVencendorID);
	        var rs =  ps4.executeQuery();
	        
	        List<ApostaUsuarioDTO> usuarios = new ArrayList<ApostaUsuarioDTO>();
	        while(rs.next()) {
	        	usuarios.add(new ApostaUsuarioDTO(rs.getInt(1), rs.getDouble(2), rs.getDouble(3)));
	        }
	        rs.close();
	        usuarios.forEach(x -> {
	        	try {
					PreparedStatement ps5 = conn.prepareStatement("update usuario set saldo = saldo + (? * ?) where id = ?");
					ps5.setDouble(1, x.getValor());
					ps5.setDouble(2, x.getOdd());
					ps5.setInt(3, x.getUsuarioID());
					ps5.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        });
	        
	        
	        conn.commit();
	        return true;
	    } catch (SQLException e) {
	    	conn.rollback();
	    	throw e;
	    } finally {
	    	conn.setAutoCommit(true); // Reativa o autocommit
	    }
	}
}
