package Model;

import java.time.LocalDate;

public class Aposta {
	private int ID;
	private Usuario usuario;
	private Evento evento;
	private TipoAposta tipoAposta = new TipoAposta();
	private StatusAposta statusAposta = new StatusAposta();
	private double valor;
	private LocalDate dataAposta;
	
	
	public Aposta() {}
	
	public Aposta(Usuario usuario, Evento evento, int tipoApostaID,  double valor) {
		this.usuario = usuario;
		this.evento = evento;
		this.tipoAposta.setID(tipoApostaID);
		this.statusAposta.setID(1);
		this.valor = valor;
		this.dataAposta = LocalDate.now();
	}
	
	public Aposta(int ID, Usuario user, Evento evento, TipoAposta tipoAposta, StatusAposta status, double valor, LocalDate data) {
		this.ID = ID;
		this.evento = evento;
		this.usuario = user;
		this.tipoAposta = tipoAposta;
		this.statusAposta = status;
		this.valor = valor;
		this.dataAposta = LocalDate.now();
	}

	public int getID() {
		return ID;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public Evento getEvento() {
		return this.evento;
	}

	public TipoAposta getTipoAposta() {
		return tipoAposta;
	}

	public StatusAposta getStatusAposta() {
		return statusAposta;
	}
	
	public void setStatus(int statusID) {
		this.statusAposta.setID(statusID);
	}

	public double getValor() {
		return valor;
	}

	public LocalDate getDataAposta() {
		return dataAposta;
	}
	
	public double getOddApostada() {
		
		switch(tipoAposta.getID()) {
		case 1:
			return this.evento.getOddVitoria();
		case 2:
			return this.evento.getOddEmpate();
		case 3:
			return this.evento.getOddDerrota();
			default:
				return 0;
				//verificar tratamento caso valor inserido seja incorreto
		}

		}
		
	}
	
	

