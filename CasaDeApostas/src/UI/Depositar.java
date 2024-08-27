package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Collections;

import javax.lang.model.element.Element;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Model.Aposta;
import Model.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Controller.UsuarioController;

import java.awt.Font;

public class Depositar {

	private JFrame frame;
	private JTextField txtValor;
	private Usuario user;
	private JFrame oldHome;
	
	public JFrame getFrame() {
		return this.frame;
	}

	public Depositar(Usuario user, JFrame oldHome) {
		this.user = user;
		this.oldHome = oldHome;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 128, 192));
        frame.setBounds(100, 100, 285, 218);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != ',') {
					e.consume();
				} else if (c == ',' && (txtValor.getText().contains(",") || txtValor.getText().length() < 1)) {
					e.consume();
				} else if (txtValor.getText().contains(",")) {
					if (txtValor.getText().length() - txtValor.getText().indexOf(',') - 1 >= 2) {
						e.consume();
					}
				}
			}
		});
		txtValor.setBounds(99, 41, 86, 20);
		frame.getContentPane().add(txtValor);
		
		JLabel lblNewLabel = new JLabel("Valor do Desposito");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(85, 15, 114, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(94, 170, 97, 23);
        btnCancelar.addActionListener(e -> frame.setVisible(false));
        frame.getContentPane().add(btnCancelar);
        
        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setForeground(new Color(255, 255, 255));
        btnDepositar.setBackground(new Color(0, 0, 160));
        btnDepositar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				user.setSaldo(user.getSaldo() + Double.valueOf(txtValor.getText().replace(',', '.')));
				UsuarioController ctr = new UsuarioController();
				var newUser = ctr.EditarUsuario(user);
				Home newHome = new Home(newUser);
				oldHome.setVisible(false);
				newHome.getFrame().setVisible(true);
				frame.setVisible(false);
				JOptionPane.showMessageDialog(null, "Deposito Realizado com sucesso!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(frame, "Ocorreu um erro: " + e1.getMessage());
				}
			}
		});
		btnDepositar.setBounds(80, 93, 125, 43);
		frame.getContentPane().add(btnDepositar);
	}
}
