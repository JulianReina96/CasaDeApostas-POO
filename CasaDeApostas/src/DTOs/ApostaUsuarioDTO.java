package DTOs;

public class ApostaUsuarioDTO {
	private int usuarioID;
	private double valor;
	private double odd;
	
	public ApostaUsuarioDTO(int usuarioID, double valor, double odd) {
		this.usuarioID = usuarioID;
		this.valor = valor;
		this.odd = odd;
	}
	
	public int getUsuarioID() {
		return usuarioID;
	}
	public double getValor() {
		return valor;
	}
	public double getOdd() {
		return odd;
	}
	
	
}
