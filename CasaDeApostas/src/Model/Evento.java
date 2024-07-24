package Model;

import java.time.LocalDate;

public class Evento {
	private int ID;
	private String nome;
	private LocalDate dataEvento;
	private String descricao;
	private String timeCasa;
	private String timeVisitante;
	private double oddVitoria;
	private double oddDerrota;
	private double oddEmpate;
	
	public Evento() {}
	
	public Evento(String nome, LocalDate data, String descricao, String timeCasa, String timeVisitante, double oddVitoria, double oddDerrota, double oddEmpate) {
		this.nome = nome;
		this.dataEvento = data;
		this.descricao = descricao;
		this.timeCasa = timeCasa;
		this.timeVisitante = timeVisitante;
		this.oddVitoria = oddVitoria;
		this.oddDerrota = oddDerrota;
		this.oddEmpate = oddEmpate;
	}
	
	public Evento(int ID, String nome, LocalDate data, String descricao, String timeCasa, String timeVisitante, double oddVitoria, double oddDerrota, double oddEmpate) {
		this.ID = ID;
		this.nome = nome;
		this.dataEvento = data;
		this.descricao = descricao;
		this.timeCasa = timeCasa;
		this.timeVisitante = timeVisitante;
		this.oddVitoria = oddVitoria;
		this.oddDerrota = oddDerrota;
		this.oddEmpate = oddEmpate;
	}

	public int getID() {
		return ID;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataEvento() {
		return dataEvento;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getTimeCasa() {
		return timeCasa;
	}

	public String getTimeVisitante() {
		return timeVisitante;
	}

	public double getOddVitoria() {
		return oddVitoria;
	}

	public double getOddDerrota() {
		return oddDerrota;
	}

	public double getOddEmpate() {
		return oddEmpate;
	}
	
}
