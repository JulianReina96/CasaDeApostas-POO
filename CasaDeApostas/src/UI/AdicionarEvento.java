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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Business.EventoBusiness;
import Business.UsuarioBusiness;
import DTOs.EventoDto;
import Model.Evento;
import Model.Usuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.Icon;


public class AdicionarEvento {

	private JFrame frame;
	private JFrame home;
	private JTextField txtOddCasa;
	private JTextField txtOddEmpate;
	private JTextField txtOddVisitante;
	private JTextField txtTimeCasa;
	private JTextField txtTimeVisitante;
	private JTextField txtNomeEvento;
	private JTextField txtDataEvento;
	
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarEvento window = new AdicionarEvento();
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
	public AdicionarEvento() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(70, 163, 255));
		frame.setBounds(100, 100, 551, 353);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		frame.getContentPane().setLayout(null);
		frame.setTitle("AdicionarEvento");
		
		JLabel lblEditarEvento = new JLabel("Adicionar Evento");
		lblEditarEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarEvento.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEditarEvento.setBounds(188, 16, 180, 32);
		frame.getContentPane().add(lblEditarEvento);

		txtOddCasa = new JTextField();
		txtOddCasa.setToolTipText("Digite a odd do time da casa");
		txtOddCasa.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != ',') {
					e.consume();
				} else if (c == ',' && (txtOddCasa.getText().contains(",") || txtOddCasa.getText().length() < 1)) {
					e.consume();
				} else if (txtOddCasa.getText().contains(",")) {
					if (txtOddCasa.getText().length() - txtOddCasa.getText().indexOf(',') - 1 >= 2) {
						e.consume();
					}
				}
			}
		});
		txtOddCasa.setBounds(108, 181, 58, 20);
		frame.getContentPane().add(txtOddCasa);
		txtOddCasa.setColumns(10);

		txtOddEmpate = new JTextField();
		txtOddEmpate.setToolTipText("Digite a odd do empate");
		txtOddEmpate.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != ',') {
					e.consume();
				} else if (c == ',' && (txtOddEmpate.getText().contains(",") || txtOddEmpate.getText().length() < 1)) {
					e.consume();
				} else if (txtOddEmpate.getText().contains(",")) {
					if (txtOddEmpate.getText().length() - txtOddEmpate.getText().indexOf(',') - 1 >= 2) {
						e.consume();
					}
				}
			}
		});
		txtOddEmpate.setColumns(10);
		txtOddEmpate.setBounds(254, 181, 58, 20);
		frame.getContentPane().add(txtOddEmpate);

		txtOddVisitante = new JTextField();
		txtOddVisitante.setToolTipText("Digite a odd do time visitante");
		txtOddVisitante.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != ',') {
					e.consume();
				} else if (c == ','
						&& (txtOddVisitante.getText().contains(",") || txtOddVisitante.getText().length() < 1)) {
					e.consume();
				} else if (txtOddVisitante.getText().contains(",")) {
					if (txtOddVisitante.getText().length() - txtOddVisitante.getText().indexOf(',') - 1 >= 2) {
						e.consume();
					}
				}
			}
		});
		txtOddVisitante.setColumns(10);
		txtOddVisitante.setBounds(396, 181, 58, 20);
		frame.getContentPane().add(txtOddVisitante);

		txtTimeCasa = new JTextField();
		txtTimeCasa.setToolTipText("Digite o time da casa");
		txtTimeCasa.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeCasa.setBounds(78, 135, 114, 20);
		frame.getContentPane().add(txtTimeCasa);
		txtTimeCasa.setColumns(10);

		JLabel lblEmpateEdit = new JLabel("Empate");
		lblEmpateEdit.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEmpateEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpateEdit.setBounds(257, 135, 55, 16);
		frame.getContentPane().add(lblEmpateEdit);

		txtTimeVisitante = new JTextField();
		txtTimeVisitante.setToolTipText("Digite o time visitante");
		txtTimeVisitante.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeVisitante.setColumns(10);
		txtTimeVisitante.setBounds(370, 135, 114, 20);
		frame.getContentPane().add(txtTimeVisitante);

		txtNomeEvento = new JTextField();
		txtNomeEvento.setToolTipText("Digite o nome do evento");
		txtNomeEvento.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeEvento.setColumns(10);
		txtNomeEvento.setBounds(62, 78, 305, 20);
		frame.getContentPane().add(txtNomeEvento);

		txtDataEvento = new JTextField();
		txtDataEvento.setToolTipText("Digite a data do evento");
		txtDataEvento.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataEvento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || txtDataEvento.getText().length() >= 10) {
					e.consume();
				} else {
					var data = txtDataEvento.getText();
					if (data.length() == 1 || data.length() == 4) {
						txtDataEvento.setText(data + c + "/");
						e.consume();
					}
				}
			}
		});
		txtDataEvento.setBounds(379, 78, 114, 20);
		frame.getContentPane().add(txtDataEvento);
		txtDataEvento.setColumns(10);

		
		JButton btnCadastrar = new JButton("Adicionar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = txtNomeEvento.getText();
					LocalDate dataEvento = LocalDate.parse(txtDataEvento.getText(),
							DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					String timeCasa = txtTimeCasa.getText();
					String timeVisitante = txtTimeVisitante.getText();
					Double oddCasa = Double.valueOf(txtOddCasa.getText().replace(',', '.'));
					Double oddEmpate = Double.valueOf(txtOddEmpate.getText().replace(',', '.'));
					Double oddDerrota = Double.valueOf(txtOddVisitante.getText().replace(',', '.'));
					Evento newEvent = new Evento(nome, dataEvento, timeCasa, timeVisitante, oddCasa,
							oddDerrota, oddEmpate, true);
					EventoBusiness ctr = new EventoBusiness();
					ctr.cadastrarEvento(newEvent);
					JOptionPane.showMessageDialog(frame, "Evento cadastrado com sucesso!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}

			}
		});
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setMnemonic('E');
		btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCadastrar.setBackground(new Color(0, 64, 128));
		btnCadastrar.setBounds(201, 223, 159, 39);
		frame.getContentPane().add(btnCadastrar);
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(201, 291, 159, 39);
		btnCancelar.addActionListener(e -> frame.setVisible(false));
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblTimeEmCasa = new JLabel("Time em Casa");
		lblTimeEmCasa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeEmCasa.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTimeEmCasa.setBounds(64, 117, 140, 16);
		frame.getContentPane().add(lblTimeEmCasa);
		
		JLabel lblTimeVisitante = new JLabel("Time Visitante");
		lblTimeVisitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeVisitante.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTimeVisitante.setBounds(356, 117, 140, 16);
		frame.getContentPane().add(lblTimeVisitante);
		
		JLabel lblNomeDoEvento = new JLabel("Nome do Evento");
		lblNomeDoEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeDoEvento.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNomeDoEvento.setBounds(146, 59, 140, 16);
		frame.getContentPane().add(lblNomeDoEvento);
		
		JLabel lblDataDoEvento = new JLabel("Data do Evento");
		lblDataDoEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDoEvento.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDataDoEvento.setBounds(368, 59, 140, 16);
		frame.getContentPane().add(lblDataDoEvento);
		
		JLabel lblOdd = new JLabel("ODD");
		lblOdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblOdd.setFont(new Font("Dialog", Font.BOLD, 14));
		lblOdd.setBounds(111, 162, 55, 16);
		frame.getContentPane().add(lblOdd);
		
		JLabel lblOdd_1 = new JLabel("ODD");
		lblOdd_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOdd_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblOdd_1.setBounds(256, 162, 55, 16);
		frame.getContentPane().add(lblOdd_1);
		
		JLabel lblOdd_1_1 = new JLabel("ODD");
		lblOdd_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOdd_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblOdd_1_1.setBounds(398, 162, 55, 16);
		frame.getContentPane().add(lblOdd_1_1);
	}
}
