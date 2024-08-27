package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import DAO.UsuarioDAO;
import Model.Usuario;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

import Controller.UsuarioController;

public class ContaUsuario {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmarSenha;
	private JButton btnMostrarSenha;
	private Usuario userSession;

	public JFrame getFrame() {
		return this.frame;
	}

	public ContaUsuario(Usuario usuario) {
		this.userSession = usuario;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(70, 163, 255));
		frame.setBounds(100, 100, 550, 601);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Editar Usuario");
			frame.setUndecorated(true);
			frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		

		txtNome = new JTextField();
		txtNome.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtNome.setText(userSession.getNome());
		txtNome.setBounds(107, 97, 319, 25);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNome.setBounds(107, 70, 124, 20);
		
		frame.getContentPane().add(lblNome);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEmail.setBounds(107, 150, 124, 20);
		frame.getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setText(userSession.getEmail());
		txtEmail.setEditable(false);
		txtEmail.setEnabled(false);
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(107, 177, 319, 25);
		frame.getContentPane().add(txtEmail);

		JLabel lblSenha = new JLabel("Nova Senha");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSenha.setBounds(107, 226, 124, 20);
		frame.getContentPane().add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setToolTipText("Insira uma nova senha");
		txtSenha.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtSenha.setColumns(10);
		txtSenha.setBounds(107, 256, 319, 25);
		txtSenha.setEchoChar('*');
		frame.getContentPane().add(txtSenha);

		JLabel lblConfirmeSuaSenha = new JLabel("Confirmar Senha");
		lblConfirmeSuaSenha.setForeground(new Color(255, 255, 255));
		lblConfirmeSuaSenha.setFont(new Font("Dialog", Font.BOLD, 18));
		lblConfirmeSuaSenha.setBounds(105, 291, 200, 20);
		frame.getContentPane().add(lblConfirmeSuaSenha);

		txtConfirmarSenha = new JPasswordField();
		txtConfirmarSenha.setToolTipText("Confirme a nova senha");
		txtConfirmarSenha.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtConfirmarSenha.setColumns(10);
		txtConfirmarSenha.setBounds(107, 321, 319, 25);
		txtConfirmarSenha.setEchoChar('*');
		frame.getContentPane().add(txtConfirmarSenha);

		JButton btnEditar = new JButton("Editar Cadastro");
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setMnemonic('C');
		btnEditar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnEditar.setBackground(new Color(0, 128, 192));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController ctr = new UsuarioController();
				if ((txtConfirmarSenha.getText().equals(txtSenha.getText())) && !txtNome.getText().isBlank()) 
				{
					
					JPasswordField campoSenha = new JPasswordField();
					int resposta =  JOptionPane.showConfirmDialog(null, campoSenha, "Digite sua senha", JOptionPane.PLAIN_MESSAGE);
					if (resposta == JOptionPane.OK_OPTION) {
					
						if(ctr.verificarSenha(campoSenha.getText(), userSession.getSenha())) {
							
							String senhaEditada = txtSenha.getText().isBlank() ? userSession.getSenha() : ctr.criptografarSenha(txtSenha.getText());
							Usuario usuarioEditado = new Usuario(
									txtNome.getText(),
									userSession.getEmail(),
									senhaEditada,
									userSession.getSaldo(),
									userSession.isAdministrador()									
									);
							try {
								userSession = ctr.EditarUsuario(usuarioEditado);
								JOptionPane.showMessageDialog(frame, "Usuario editado com sucesso");
								Home home = new Home(userSession);

								frame.setVisible(false);
								home.getFrame().setVisible(true);

								
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(frame, "Erro ao editar usuario!");
								e1.printStackTrace();
							}
											
						}
						else {
							JOptionPane.showMessageDialog(frame, "Senha incorreta!");
						}
					}
				}
				else {				
					JOptionPane.showMessageDialog(frame, "Atenção: verifique os dados informados para edição!");
				}
			}
		});
		
		
		btnEditar.setBounds(181, 387, 171, 39);
		frame.getContentPane().add(btnEditar);

		btnMostrarSenha = new JButton(new ImageIcon(ContaUsuario.class.getResource("/Icons/eye_icon.png")));
		btnMostrarSenha.setBounds(430, 256, 26, 26);
		frame.getContentPane().add(btnMostrarSenha);

		// Evento do botão para alternar a exibição da senha
		btnMostrarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleExibirSenha(txtSenha, btnMostrarSenha);
			}
		});

		// Evento do botão para alternar a exibição da senha de confirmação
		JButton btnMostrarConfirmarSenha = new JButton(
				new ImageIcon(ContaUsuario.class.getResource("/Icons/eye_icon.png")));
		btnMostrarConfirmarSenha.setBounds(430, 321, 26, 26);
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
				
					Home home = new Home(userSession);
					frame.setVisible(false);
					home.getFrame().setVisible(true);


			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVoltar.setBounds(200, 454, 137, 31);
		btnVoltar.setBackground(new Color(0, 128, 192));
		frame.getContentPane().add(btnVoltar);
		
		JLabel lblEditarUsurio = new JLabel("Editar Usuário");
		lblEditarUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarUsurio.setForeground(Color.WHITE);
		lblEditarUsurio.setFont(new Font("Dialog", Font.BOLD, 22));
		lblEditarUsurio.setBounds(181, 10, 191, 50);
		frame.getContentPane().add(lblEditarUsurio);
		
		ImageIcon iconTrash = new ImageIcon();
		if(ContaUsuario.class.getResource("/Icons/trash.png") != null) {
			iconTrash = new ImageIcon(ContaUsuario.class.getResource("/Icons/trash.png"));
		}
		
		JButton btnExcluirUsuario = new JButton("", iconTrash);
		btnExcluirUsuario.setBounds(453, 493, 47, 43);
		
		
		btnExcluirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPasswordField campoSenha = new JPasswordField();
				JPanel panel = new JPanel();
				JTextArea textArea = new JTextArea("Atenção! Essa ação irá excluir todos"
						+ " os dados do usuario. Deseja continuar?");
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setSize(300,400);
				textArea.disable();
				textArea.setDisabledTextColor(Color.red);
			    panel.add(textArea);
			    

				int resposta = JOptionPane.showConfirmDialog(null, panel, "Excluir Conta", JOptionPane.YES_NO_OPTION);	
				if(resposta == JOptionPane.OK_OPTION){
					int respostaSenha = JOptionPane.showConfirmDialog(null, campoSenha, "Digite sua senha", JOptionPane.OK_CANCEL_OPTION);
				
				if (respostaSenha == JOptionPane.OK_OPTION) {
					UsuarioController ctr = new UsuarioController();
					if(ctr.verificarSenha(campoSenha.getText(), userSession.getSenha())) {
						try {
							ctr.DeletarUsuario(userSession.getEmail());
							Login login = new Login();
							frame.setVisible(false);
							login.getFrame().setVisible(true);
						    
						    JOptionPane.showMessageDialog(frame, "Que pena não termos mais você aqui conosco. Sinta-se livre para voltar quando desejar!");
						    
							
							
							
						}catch(SQLException ex) {
							JOptionPane.showMessageDialog(frame, "Erro ao editar usuario!");
							ex.printStackTrace();
						}

					}
					else {
						JOptionPane.showMessageDialog(frame, "Senha incorreta!");
						
					}
					}
					}

			}
		});
		frame.getContentPane().add(btnExcluirUsuario);
		
		JLabel lblNewLabel = new JLabel("Excluir Conta");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(430, 538, 94, 13);
		frame.getContentPane().add(lblNewLabel);
	}

	private void toggleExibirSenha(JPasswordField campoSenha, JButton botao) {
		char echoChar = campoSenha.getEchoChar();
		if (echoChar == '*') {
			campoSenha.setEchoChar((char) 0); // Mostrar o texto real
			botao.setIcon(new ImageIcon(ContaUsuario.class.getResource("/Icons/eye_slash_icon.png"))); // Ícone de olho com
																									// barra
		} else {
			campoSenha.setEchoChar('*'); // Ocultar o texto com asteriscos
			botao.setIcon(new ImageIcon(ContaUsuario.class.getResource("/Icons/eye_icon.png"))); // Ícone de olho
		}
	}
}
