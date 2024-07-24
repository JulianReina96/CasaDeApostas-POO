package Model;

public class StatusAposta {
	private int ID;
	private String descricao;
	
	
	public StatusAposta(int ID, String decricao) {
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
