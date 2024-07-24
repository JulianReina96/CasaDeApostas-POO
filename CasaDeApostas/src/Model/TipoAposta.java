package Model;

public class TipoAposta {
	private int ID;
	private String descricao;
	
	
	
	public TipoAposta(int ID, String decricao) {
		this.ID = ID;
		this.descricao = decricao;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
