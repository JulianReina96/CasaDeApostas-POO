package DTOs;

import java.time.LocalDate;

import Model.Aposta;
import Model.Evento;
import Model.StatusAposta;
import Model.TipoAposta;
import Model.Usuario;

public class ApostaDto {

	public ApostaDto(Aposta aposta) {
		super();
		NomeUsuario = aposta.getUsuario().getNome();
		DescricaoEvento = aposta.getEvento().getNome();
		OddApostada = aposta.getOddApostada();
		DataEvento = aposta.getEvento().getDataEvento();
		this.tipoAposta = aposta.getTipoAposta().getDescricao();
		this.statusAposta = aposta.getStatusAposta().getDescricao();
		this.valor = aposta.getValor();
		this.dataAposta = aposta.getDataAposta();
	}
	//Usuario
	private String NomeUsuario;
	
	//Evento
	private String DescricaoEvento;
	private Double OddApostada;	
	private LocalDate DataEvento;
	private String TimeApostado;
	
	//Aposta
	private String tipoAposta;
	private String statusAposta;
	private double valor;
	private LocalDate dataAposta;
	
	public String getNomeUsuario() {
		return NomeUsuario;
	}
	public String getDescricaoEvento() {
		return DescricaoEvento;
	}
	public Double getOddApostada() {
		return OddApostada;
	}
	public LocalDate getDataEvento() {
		return DataEvento;
	}
	public String getTimeApostado() {
		return TimeApostado;
	}
	public String getTipoAposta() {
		return tipoAposta;
	}
	public String getStatusAposta() {
		return statusAposta;
	}
	public double getValor() {
		return valor;
	}
	public LocalDate getDataAposta() {
		return dataAposta;
	}

	
	
	
	
	
}
