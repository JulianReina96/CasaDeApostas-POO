package Model;

import java.time.LocalDate;

public class Evento {
	private int ID;
	private String nome;
	private LocalDate dataEvento;
	private String timeCasa;
	private String timeVisitante;
	private double oddVitoria;
	private double oddDerrota;
	private double oddEmpate;
	private boolean aberta;
	
	public Evento() {}
	
	public Evento(String nome, LocalDate data, String timeCasa, String timeVisitante, double oddVitoria, double oddDerrota, double oddEmpate, boolean aberta) {
		this.nome = nome;
		this.dataEvento = data;
		this.timeCasa = timeCasa;
		this.timeVisitante = timeVisitante;
		this.oddVitoria = oddVitoria;
		this.oddDerrota = oddDerrota;
		this.oddEmpate = oddEmpate;
		this.aberta = aberta;
	}
	
	public Evento(int ID, String nome, LocalDate data, String timeCasa, String timeVisitante, double oddVitoria, double oddDerrota, double oddEmpate, boolean aberta) {
		this.ID = ID;
		this.nome = nome;
		this.dataEvento = data;
		this.timeCasa = timeCasa;
		this.timeVisitante = timeVisitante;
		this.oddVitoria = oddVitoria;
		this.oddDerrota = oddDerrota;
		this.oddEmpate = oddEmpate;
		this.aberta = aberta;
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
	
	public boolean getAberta() {
		return this.aberta;
	}
}
