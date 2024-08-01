package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class Home {

	private JFrame frame;
	private JTable tbEventos;
	private JTable tbApostas;

	public Home() {
		initialize();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(70, 163, 255));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null); 
		frame.setTitle("Cat's Bet");
		
		tbEventos = new JTable();
		tbEventos.setModel(new DefaultTableModel(
			new Object[][] {
				{"Bahia e Vitoria", "2.00", "2.15", "1.75", "<html><button>Aposte</button><html>"},
			},
			new String[] {
				"Evento", "ODD Vitoria Casa", "ODD Empate", "ODD Derrota Casa", "Apostar"
			}
		));
		tbEventos.setToolTipText("Eventos em aberto\r\n");
		tbEventos.setBounds(10, 106, 1069, 564);
		frame.getContentPane().add(tbEventos);
		
		tbApostas = new JTable();
		tbApostas.setBounds(1089, 106, 165, 519);
		frame.getContentPane().add(tbApostas);
		
		JLabel lblNewLabel = new JLabel("Cat's Bet");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 28));
		lblNewLabel.setBounds(487, 14, 313, 40);
		frame.getContentPane().add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("Nova Aposta");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnNewButton.setBounds(1089, 630, 165, 40);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Eventos");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setLabelFor(tbEventos);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setBounds(10, 67, 1069, 40);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apostas");
		lblNewLabel_2.setLabelFor(tbApostas);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setBounds(1089, 71, 165, 36);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnDeposito = new JButton("Depositar");
		btnDeposito.setForeground(new Color(0, 0, 0));
		btnDeposito.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnDeposito.setBounds(1110, 19, 132, 36);
		frame.getContentPane().add(btnDeposito);
		
	}
}
