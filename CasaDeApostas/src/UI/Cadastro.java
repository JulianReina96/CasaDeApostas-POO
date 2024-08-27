package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Controller.UsuarioController;
import Model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Cadastro {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmarSenha;
	private JButton btnMostrarSenha;
	private boolean Adm = false;

	public JFrame getFrame() {
		return this.frame;
	}

	public Cadastro(boolean Admin) {
		this.Adm = Admin;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(70, 163, 255));
		frame.setBounds(100, 100, 550, 601);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Cadastre-se");
		if (this.Adm) {
			frame.setUndecorated(true);
			frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		}

		txtNome = new JTextField();
		txtNome.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtNome.setBounds(107, 97, 319, 25);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(107, 70, 124, 20);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEmail.setBounds(107, 150, 124, 20);
		frame.getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(107, 177, 319, 25);
		frame.getContentPane().add(txtEmail);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSenha.setBounds(107, 225, 124, 20);
		frame.getContentPane().add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtSenha.setColumns(10);
		txtSenha.setBounds(107, 252, 319, 25);
		txtSenha.setEchoChar('*');
		frame.getContentPane().add(txtSenha);

		JLabel lblConfirmeSuaSenha = new JLabel("Confirmar Senha");
		lblConfirmeSuaSenha.setForeground(new Color(255, 255, 255));
		lblConfirmeSuaSenha.setFont(new Font("Dialog", Font.BOLD, 18));
		lblConfirmeSuaSenha.setBounds(107, 295, 200, 20);
		frame.getContentPane().add(lblConfirmeSuaSenha);

		txtConfirmarSenha = new JPasswordField();
		txtConfirmarSenha.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtConfirmarSenha.setColumns(10);
		txtConfirmarSenha.setBounds(107, 322, 319, 25);
		txtConfirmarSenha.setEchoChar('*');
		frame.getContentPane().add(txtConfirmarSenha);

		JButton btnCadastrar = new JButton("Cadastrar-se");
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setMnemonic('C');
		btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 18));
		btnCadastrar.setBackground(new Color(0, 128, 192));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController ctr = new UsuarioController();
				if (txtSenha.getText().equals(txtConfirmarSenha.getText())) {
					Usuario newUser = new Usuario(txtNome.getText(), txtEmail.getText(), txtSenha.getText(), 0, Adm);
					try {
						if (ctr.cadastrarUsuario(newUser)) {
							JOptionPane.showMessageDialog(frame, "Usuario Cadastrado Com Sucesso!");
							if (!Adm) {
								Login login = new Login();
								frame.setVisible(false);
								login.getFrame().setVisible(true);
							}
							else {
								frame.setVisible(false);
							}
						} else {
							JOptionPane.showMessageDialog(frame, "Erro ao Cadastrar o Usuario!");
						}
					} catch (Exception e1) {
						if(e1.getMessage()
.contains("usuario_email_key"))
							JOptionPane.showMessageDialog(frame, "Erro ao Cadastrar o Usuario! Email já cadastrado");
						else
							JOptionPane.showMessageDialog(frame, "Erro ao Cadastrar o Usuario!");
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "As senhas não coincidem");
				}

			}
		});
		btnCadastrar.setBounds(191, 403, 145, 39);
		frame.getContentPane().add(btnCadastrar);

		btnMostrarSenha = new JButton(new ImageIcon(Cadastro.class.getResource("/Icons/eye_icon.png")));
		btnMostrarSenha.setBounds(430, 252, 26, 26);
		frame.getContentPane().add(btnMostrarSenha);

		// Evento do botão para alternar a exibição da senha
		btnMostrarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleExibirSenha(txtSenha, btnMostrarSenha);
			}
		});

		// Evento do botão para alternar a exibição da senha de confirmação
		JButton btnMostrarConfirmarSenha = new JButton(
				new ImageIcon(Cadastro.class.getResource("/Icons/eye_icon.png")));
		btnMostrarConfirmarSenha.setBounds(430, 322, 26, 26);
		frame.getContentPane().add(btnMostrarConfirmarSenha);

		btnMostrarConfirmarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleExibirSenha(txtConfirmarSenha, btnMostrarConfirmarSenha);
			}
		});

		frame.getContentPane().add(btnMostrarSenha);
		frame.getContentPane().add(btnMostrarConfirmarSenha);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(new Color(255, 255, 255));
		btnVoltar.setMnemonic('V');
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Adm) {
					frame.setVisible(false);
				} else {
					Login login = new Login();
					frame.setVisible(false);
					login.getFrame().setVisible(true);
				}

			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVoltar.setBounds(207, 469, 111, 31);
		btnVoltar.setBackground(new Color(0, 128, 192));
		frame.getContentPane().add(btnVoltar);
	}

	private void toggleExibirSenha(JPasswordField campoSenha, JButton botao) {
		char echoChar = campoSenha.getEchoChar();
		if (echoChar == '*') {
			campoSenha.setEchoChar((char) 0); // Mostrar o texto real
			botao.setIcon(new ImageIcon(Cadastro.class.getResource("/Icons/eye_slash_icon.png"))); // Ícone de olho com
																									// barra
		} else {
			campoSenha.setEchoChar('*'); // Ocultar o texto com asteriscos
			botao.setIcon(new ImageIcon(Cadastro.class.getResource("/Icons/eye_icon.png"))); // Ícone de olho
		}
	}
}
