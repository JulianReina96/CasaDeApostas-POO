package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Aposta;
import Model.Evento;
import Model.StatusAposta;
import Model.TipoAposta;
import Model.Usuario;

public class ApostaPostgreDAO implements ApostaDAO {

	@Override
	public List<Aposta> listaApostas(int usuarioID) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("SELECT "
	    		+ "U.ID AS USUARIOID, U.NOME AS NOMEUSER, U.EMAIL, U.SENHA, U.ADMINISTRADOR, "
	    		+ "E.ID AS EVENTOID, E.NOME AS NOMEEVENTO, E.DATAEVENTO, E.DESCRICAO AS DESCRICAOEVENTO, E.TIMECASA, E.TIMEVISITANTE, E.ODDVITORIA, E.ODDDERROTA, E.ODDEMPATE, "
	    		+ "TA.ID AS TIPOAPOSTAID, TA.DESCRICAO AS TIPODESCRICAO, "
	    		+ "SA.ID AS STATUSAPOSTAID, SA.DESCRICAO AS STATUSDESCRICAO, "
	    		+ "A.ID AS APOSTAID, A.VALOR, A.DATAAPOSTA"
	    		+ "FROM APOSTAS AS A "
	    		+ "INNER JOIN TIPOAPOSTA AS TA ON TA.ID = A.TIPOAPOSTAID"
	    		+ "INNER JOIN STATUSAPOSTA AS SA ON SA.ID = A.STATUSAPOSTAID"
	    		+ "INNER JOIN USUARIO AS U ON U.ID = A.USUARIOID"
	    		+ "WHERE A.USUARIOID = ?");
		ps.setInt(1, usuarioID);
	    ResultSet rs = ps.executeQuery();
	    List<Aposta> apostas = new ArrayList<Aposta>();
	    while (rs.next()) {
	    	Usuario user = new Usuario(
					rs.getInt("USUARIOID"), 
					rs.getString("NOMEUSER"),  
					rs.getString("EMAIL"), 
					rs.getString("SENHA"), 
					rs.getDouble("saldo"),
					rs.getBoolean("ADMINISTRADOR"));
	    	
	    	
	    	Evento evento = new Evento(rs.getInt("EVENTOID"), 
					rs.getString("NOMEEVENTO"), 
					rs.getDate("DATAEVENTO").toLocalDate(), 
					rs.getString("DESCRICAOEVENTO"), 
					rs.getString("TIMECASA"), 
					rs.getString("TIMEVISITANTE"), 
					rs.getDouble("ODDVITORIA"), 
					rs.getDouble("ODDDERROTA"), 
					rs.getDouble("ODDEMPATE"));
	    	
	    	
	    	TipoAposta tipo = new TipoAposta(
	    			rs.getInt("TIPOAPOSTAID"),
	    			rs.getString("TIPODESCRICAO"));
	    	
	    	
	    	StatusAposta status = new StatusAposta(
	    			rs.getInt("STATUSAPOSTAID"),
	    			rs.getString("STATUSDESCRICAO"));

	        
	        Aposta aposta = new Aposta(
	        		rs.getInt("APOSTAID"),
	        		user,
	        		evento,
	        		tipo,
	        		status,
	        		rs.getDouble("VALOR"),
	        		rs.getDate("DataAposta").toLocalDate()
	        		);

	        apostas.add(aposta);
	    }
	    
	    return apostas;
	}

	@Override
	public Aposta getAposta(int apostaID) throws SQLException {
	    PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("SELECT "
	    		+ "U.ID AS USUARIOID, U.NOME AS NOMEUSER, U.EMAIL, U.SENHA, U.ADMINISTRADOR, "
	    		+ "E.ID AS EVENTOID, E.NOME AS NOMEEVENTO, E.DATAEVENTO, E.DESCRICAO AS DESCRICAOEVENTO, E.TIMECASA, E.TIMEVISITANTE, E.ODDVITORIA, E.ODDDERROTA, E.ODDEMPATE, "
	    		+ "TA.ID AS TIPOAPOSTAID, TA.DESCRICAO AS TIPODESCRICAO, "
	    		+ "SA.ID AS STATUSAPOSTAID, SA.DESCRICAO AS STATUSDESCRICAO, "
	    		+ "A.ID AS APOSTAID, A.VALOR, A.DATAAPOSTA"
	    		+ "FROM APOSTAS AS A "
	    		+ "INNER JOIN TIPOAPOSTA AS TA ON TA.ID = A.TIPOAPOSTAID"
	    		+ "INNER JOIN STATUSAPOSTA AS SA ON SA.ID = A.STATUSAPOSTAID"
	    		+ "INNER JOIN USUARIO AS U ON U.ID = A.USUARIOID"
	    		+ "WHERE A.ID = ?");
	    ps.setInt(1, apostaID);
	    ResultSet rs = ps.executeQuery();

	    if (rs.next()) {
	    	Usuario user = new Usuario(
					rs.getInt("USUARIOID"), 
					rs.getString("NOMEUSER"),  
					rs.getString("EMAIL"), 
					rs.getString("SENHA"), 
					rs.getDouble("saldo"),
					rs.getBoolean("ADMINISTRADOR"));
	    	
	    	
	    	Evento evento = new Evento(rs.getInt("EVENTOID"), 
					rs.getString("NOMEEVENTO"), 
					rs.getDate("DATAEVENTO").toLocalDate(), 
					rs.getString("DESCRICAOEVENTO"), 
					rs.getString("TIMECASA"), 
					rs.getString("TIMEVISITANTE"), 
					rs.getDouble("ODDVITORIA"), 
					rs.getDouble("ODDDERROTA"), 
					rs.getDouble("ODDEMPATE"));
	    	
	    	
	    	TipoAposta tipo = new TipoAposta(
	    			rs.getInt("TIPOAPOSTAID"),
	    			rs.getString("TIPODESCRICAO"));
	    	
	    	
	    	StatusAposta status = new StatusAposta(
	    			rs.getInt("STATUSAPOSTAID"),
	    			rs.getString("STATUSDESCRICAO"));

	        
	        Aposta aposta = new Aposta(
	        		rs.getInt("APOSTAID"),
	        		user,
	        		evento,
	        		tipo,
	        		status,
	        		rs.getDouble("VALOR"),
	        		rs.getDate("DataAposta").toLocalDate()
	        		);


	        return aposta;
	    }

	    return null;
	}


	@Override
	public Aposta realizarAposta(Aposta aposta) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("INSERT INTO APOSTA (USUARIOID, EVENTOID, TIPOAPOSTAID, STATUSAPOSTAID, VALOR, DATAAPOSTA) "
				+ "VALUES(?, ?, ?, ?, ?, ?)");
		
	    
	    ps.setInt(1, aposta.getUsuario().getID());
	    ps.setInt(2, aposta.getEvento().getID());
	    ps.setInt(3, aposta.getTipoAposta().getID());
		ps.setInt(4, aposta.getStatusAposta().getID());
		ps.setDouble(5, aposta.getValor());
		ps.setDate(6, Date.valueOf(aposta.getDataAposta()));

	    if(ps.executeUpdate() > 0)
	    	return aposta;
	    return null;
	}

	@Override
	public Aposta atualizarStatus(Aposta aposta) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("UPDATE APOSTA SET STATUSAPOSTAID = ? WHERE ID = ?");
		ps.setInt(1, aposta.getStatusAposta().getID());
	    ps.setInt(2, aposta.getID());
	    if(ps.executeUpdate() > 0)
	    	return aposta;
	    return null;
	}

}
