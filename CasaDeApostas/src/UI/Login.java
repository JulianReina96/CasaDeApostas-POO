package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Business.UsuarioBusiness;
import Model.Usuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.Icon;


public class Login {

	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private JButton btnMostrarSenha;
	private Usuario userSession;
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(70, 163, 255));
		frame.setBounds(100, 100, 550, 601);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Login");
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBackground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEmail.setBounds(107, 104, 124, 20);
		frame.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(107, 131, 319, 25);
		frame.getContentPane().add(txtEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSenha.setBounds(107, 179, 124, 20);
		frame.getContentPane().add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtSenha.setColumns(10);
		txtSenha.setBounds(107, 206, 319, 25);
		txtSenha.setEchoChar('*');
		frame.getContentPane().add(txtSenha);
		
		JButton btnCadastro = new JButton("Cadastrar-se");
		btnCadastro.setMnemonic('C');
		btnCadastro.setFont(new Font("Dialog", Font.BOLD, 18));
		btnCadastro.setBackground(new Color(0, 128, 192));
		btnCadastro.setForeground(new Color(255, 255, 255));
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro cadastro = new Cadastro();
				frame.setVisible(false);
				cadastro.getFrame().setVisible(true);
			}
		});
		btnCadastro.setBounds(192, 368, 145, 39);
		frame.getContentPane().add(btnCadastro);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setBackground(new Color(0, 128, 192));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioBusiness ctr = new UsuarioBusiness();
				try {
					userSession = ctr.Login(new Usuario(txtEmail.getText(), txtSenha.getText()));
					if(userSession == null) {
						JOptionPane.showMessageDialog(frame, "Informações de login incorretas!");
					}
					else {
						JOptionPane.showMessageDialog(frame, "Bem vindo a Cat'sBet " + userSession.getNome() + "!");
						Home home = new Home();
						frame.setVisible(false);
						home.getFrame().setVisible(true);
					}
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(frame, "Erro ao realizar Login!");
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setMnemonic('E');
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 18));
		btnLogin.setBounds(192, 292, 145, 39);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblEsqueciSenha = new JLabel("<html><u>Esqueci minha senha</u></html>");
		lblEsqueciSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
			}
		});
		lblEsqueciSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsqueciSenha.setBounds(192, 438, 145, 16);
		frame.getContentPane().add(lblEsqueciSenha);
		
		btnMostrarSenha = new JButton(new ImageIcon(Login.class.getResource("/Icons/eye_icon.png")));
        btnMostrarSenha.setBounds(428, 205, 26, 26);
        frame.getContentPane().add(btnMostrarSenha);

        // Evento do botão para alternar a exibição da senha
        btnMostrarSenha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleExibirSenha(txtSenha);
            }
        });

	}
	
	private void toggleExibirSenha(JPasswordField campoSenha) {
        char echoChar = campoSenha.getEchoChar();
        if (echoChar == '*') {
            campoSenha.setEchoChar((char) 0); // Mostrar o texto real
            btnMostrarSenha.setIcon(new ImageIcon(Login.class.getResource("/Icons/eye_slash_icon.png"))); // Ícone de olho com barra
        } else {
            campoSenha.setEchoChar('*'); // Ocultar o texto com asteriscos
            btnMostrarSenha.setIcon(new ImageIcon(Login.class.getResource("/Icons/eye_icon.png"))); // Ícone de olho
        }
    }

}
