package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.ApostaUsuarioDTO;
import Model.Aposta;
import Model.Evento;
import Model.StatusAposta;
import Model.TipoAposta;
import Model.Usuario;

public class ApostaPostgreDAO implements ApostaDAO {

	@Override
	public List<Aposta> listaApostas(int usuarioID) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("SELECT "
				+ "U.ID AS USUARIOID, U.NOME AS NOMEUSER, U.EMAIL, U.SENHA, U.ADMINISTRADOR, U.SALDO, "
				+ "E.ID AS EVENTOID, E.NOME AS NOMEEVENTO, E.DATA, E.TIMECASA, E.TIMEVISITANTE, E.ODDCASA, E.ODDEMPATE, E.ODDVISITANTE, E.Aberta, "
				+ "TA.ID AS TIPOAPOSTAID, TA.DESCRICAO AS TIPODESCRICAO, "
				+ "SA.ID AS STATUSAPOSTAID, SA.DESCRICAO AS STATUSDESCRICAO, "
				+ "A.ID AS APOSTAID, A.VALOR, A.DATAAPOSTA " + "FROM APOSTA AS A "
				+ "INNER JOIN TIPOAPOSTA AS TA ON TA.ID = A.TIPOAPOSTAID "
				+ "INNER JOIN STATUSAPOSTA AS SA ON SA.ID = A.STATUSAPOSTAID "
				+ "INNER JOIN EVENTO AS E ON E.ID = A.EVENTOID " + "INNER JOIN USUARIO AS U ON U.ID = A.USUARIOID "
				+ "WHERE A.USUARIOID = ?");
		ps.setInt(1, usuarioID);
		ResultSet rs = ps.executeQuery();
		List<Aposta> apostas = new ArrayList<Aposta>();
		while (rs.next()) {
			Usuario user = new Usuario(rs.getInt("USUARIOID"), rs.getString("NOMEUSER"), rs.getString("EMAIL"),
					rs.getString("SENHA"), rs.getDouble("saldo"), rs.getBoolean("ADMINISTRADOR"));

			Evento evento = new Evento(rs.getInt("EVENTOID"), rs.getString("NOMEEVENTO"),
					rs.getDate("DATA").toLocalDate(), rs.getString("timeCasa"), rs.getString("timeVisitante"),
					rs.getDouble("oddCasa"), rs.getDouble("oddEmpate"), rs.getDouble("oddVisitante"),
					rs.getBoolean("aberta"));

			TipoAposta tipo = new TipoAposta(rs.getInt("TIPOAPOSTAID"), rs.getString("TIPODESCRICAO"));

			StatusAposta status = new StatusAposta(rs.getInt("STATUSAPOSTAID"), rs.getString("STATUSDESCRICAO"));

			Aposta aposta = new Aposta(rs.getInt("APOSTAID"), user, evento, tipo, status, rs.getDouble("VALOR"),
					rs.getDate("DataAposta").toLocalDate());

			apostas.add(aposta);
		}

		return apostas;
	}

	@Override
	public Aposta getAposta(int apostaID) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao().prepareStatement("SELECT "
				+ "U.ID AS USUARIOID, U.NOME AS NOMEUSER, U.EMAIL, U.SENHA, U.ADMINISTRADOR, "
				+ "E.ID AS EVENTOID, E.NOME AS NOMEEVENTO, E.DATA, E.TIMECASA, E.TIMEVISITANTE, E.ODDCASA, E.ODDEMPATE, E.ODDVISITANTE, E.Aberta, "
				+ "TA.ID AS TIPOAPOSTAID, TA.DESCRICAO AS TIPODESCRICAO, "
				+ "SA.ID AS STATUSAPOSTAID, SA.DESCRICAO AS STATUSDESCRICAO, "
				+ "A.ID AS APOSTAID, A.VALOR, A.DATAAPOSTA " + "FROM APOSTA AS A "
				+ "INNER JOIN TIPOAPOSTA AS TA ON TA.ID = A.TIPOAPOSTAID "
				+ "INNER JOIN STATUSAPOSTA AS SA ON SA.ID = A.STATUSAPOSTAID "
				+ "INNER JOIN EVENTO AS E ON E.ID = A.EVENTOID " + "INNER JOIN USUARIO AS U ON U.ID = A.USUARIOID "
				+ "WHERE A.ID = ?");
		ps.setInt(1, apostaID);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			Usuario user = new Usuario(rs.getInt("USUARIOID"), rs.getString("NOMEUSER"), rs.getString("EMAIL"),
					rs.getString("SENHA"), rs.getDouble("saldo"), rs.getBoolean("ADMINISTRADOR"));

			Evento evento = new Evento(rs.getInt("ID"), rs.getString("NOME"), rs.getDate("DATA").toLocalDate(),
					rs.getString("timeCasa"), rs.getString("timeVisitante"), rs.getDouble("oddCasa"),
					rs.getDouble("oddEmpate"), rs.getDouble("oddVisitante"), rs.getBoolean("aberta"));

			TipoAposta tipo = new TipoAposta(rs.getInt("TIPOAPOSTAID"), rs.getString("TIPODESCRICAO"));

			StatusAposta status = new StatusAposta(rs.getInt("STATUSAPOSTAID"), rs.getString("STATUSDESCRICAO"));

			Aposta aposta = new Aposta(rs.getInt("APOSTAID"), user, evento, tipo, status, rs.getDouble("VALOR"),
					rs.getDate("DataAposta").toLocalDate());

			return aposta;
		}

		return null;
	}

	@Override
	public Aposta realizarAposta(Aposta aposta) throws SQLException {
		Connection conn = ConexaoSingleton.getInstance().getConexao();
	    conn.setAutoCommit(false); // Desabilita o autocommit

	    try {
	    	PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO APOSTA (USUARIOID, EVENTOID, TIPOAPOSTAID, STATUSAPOSTAID, VALOR, DATAAPOSTA) "
							+ "VALUES(?, ?, ?, ?, ?, ?)");

			ps.setInt(1, aposta.getUsuario().getID());
			ps.setInt(2, aposta.getEvento().getID());
			ps.setInt(3, aposta.getTipoAposta().getID());
			ps.setInt(4, aposta.getStatusAposta().getID());
			ps.setDouble(5, aposta.getValor());
			ps.setDate(6, Date.valueOf(aposta.getDataAposta()));
			
			ps.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement("UPDATE USUARIO SET SALDO = SALDO - ? WHERE ID = ?");
			ps2.setDouble(1, aposta.getValor());
			ps2.setInt(2, aposta.getUsuario().getID());
			ps2.executeUpdate();
			
	        conn.commit();
	        return aposta;
	    } catch (SQLException e) {
	    	conn.rollback();
	    	throw e;
	    } finally {
	    	conn.setAutoCommit(true); // Reativa o autocommit
	    }
		
	}

	@Override
	public Aposta atualizarStatus(Aposta aposta) throws SQLException {
		PreparedStatement ps = ConexaoSingleton.getInstance().getConexao()
				.prepareStatement("UPDATE APOSTA SET STATUSAPOSTAID = ? WHERE ID = ?");
		ps.setInt(1, aposta.getStatusAposta().getID());
		ps.setInt(2, aposta.getID());
		if (ps.executeUpdate() > 0)
			return aposta;
		return null;
	}


}
