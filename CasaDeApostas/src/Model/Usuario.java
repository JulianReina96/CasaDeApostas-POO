package Model;
import java.util.ArrayList;


public class Usuario {
	private int ID;
	private String nome;
	private String email;
	private String senha;
	private double saldo;
	private boolean Administrador;
	private ArrayList<Aposta> apostasPendentes;
	
	public Usuario() {
		this.apostasPendentes = new ArrayList<>();
	}
	
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
		this.apostasPendentes = new ArrayList<>();
	}
	
	public Usuario(String nome, String email, String senha, double saldo, boolean admin) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.saldo = saldo;
		this.Administrador = admin;
		this.apostasPendentes = new ArrayList<>();
	}
	
	public Usuario(int ID, String nome, String email, String senha, double saldo ,boolean admin) {
		this.ID = ID;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.saldo = saldo;
		this.Administrador = admin;
		this.apostasPendentes = new ArrayList<>();
	}
	
	public int getID() {
		return ID;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public boolean isAdministrador() {
		return Administrador;
	}
	
	@Override
	public String toString() {
		return "Bem vindo " + this.getNome() + " Seus dados de login são\nEmail: " + this.getEmail() +"\nSenha: " + this.getSenha() + "\nSeu saldo atual é de "+ this.getSaldo();
	}
	
	public ArrayList<Aposta> getApostasPendentes () {
		return apostasPendentes;
	}
	
	public void adicionarApostaPendente (Aposta aposta) {
		apostasPendentes.add(aposta);
	}
	
	public void apostasPendentesClear () {
		apostasPendentes.clear();
	}
	
	public void removerApostaPendente (Aposta aposta) {
		apostasPendentes.remove(aposta);
	}
}
