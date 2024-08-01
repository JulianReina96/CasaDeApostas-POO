package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Model.Evento;

import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.Choice;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Apostar {

	private JFrame frame;
	private final ButtonGroup oddApostada = new ButtonGroup();
	private Evento evento;
	private JTextField txtValorApostado;
	private JLabel lblGanhos;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apostar window = new Apostar(new Evento("Bahia vs Vitoria", LocalDate.now(), "Campeonato Brasileiro serie A", "Bahia", "Vitoria", 1.5, 2.7, 2.0));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Apostar(Evento evento) {
		this.evento = evento;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 521, 230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Apostar");
		btnNewButton.setBounds(206, 153, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblTimeCasa = new JLabel(evento.getTimeCasa());
		lblTimeCasa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeCasa.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTimeCasa.setBounds(56, 44, 118, 14);
		frame.getContentPane().add(lblTimeCasa);
		
		JLabel lblTimeFora = new JLabel(evento.getTimeVisitante());
		lblTimeFora.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTimeFora.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeFora.setBounds(330, 44, 118, 14);
		frame.getContentPane().add(lblTimeFora);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(229, 44, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JRadioButton rdOddCasa = new JRadioButton(Double.toString(evento.getOddVitoria()));
		oddApostada.add(rdOddCasa);
		rdOddCasa.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddCasa.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddCasa.setBounds(48, 65, 135, 23);
		frame.getContentPane().add(rdOddCasa);
		
		JRadioButton rdOddEmpate = new JRadioButton(Double.toString(evento.getOddEmpate()));
		oddApostada.add(rdOddEmpate);
		rdOddEmpate.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddEmpate.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddEmpate.setBounds(220, 65, 65, 23);
		frame.getContentPane().add(rdOddEmpate);
		
		JRadioButton rdOddFora = new JRadioButton(Double.toString(evento.getOddDerrota()));
		oddApostada.add(rdOddFora);
		rdOddFora.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddFora.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddFora.setBounds(322, 65, 135, 23);
		frame.getContentPane().add(rdOddFora);
		
		JLabel lblEvento = new JLabel(evento.getNome());
		lblEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblEvento.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEvento.setBounds(93, 11, 316, 23);
		frame.getContentPane().add(lblEvento);
		
		lblGanhos = new JLabel("");
		lblGanhos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanhos.setBounds(287, 128, 46, 14);
		frame.getContentPane().add(lblGanhos);
		
		txtValorApostado = new JTextField();
		txtValorApostado.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) && c != ',') {
					e.consume();
				}
				else if(c == ',' && (txtValorApostado.getText().contains(",") || txtValorApostado.getText().length() < 1)) {
					e.consume();
				}
				else {
					var valor = txtValorApostado.getText();
					if(c != ',') {
						valor = valor + c;
					}
					Double valorD = Double.valueOf(valor.replace(',', '.'));
					lblGanhos.setText(valorD.toString());
				}
			}
		});
		
		txtValorApostado.setBounds(169, 125, 86, 20);
		frame.getContentPane().add(txtValorApostado);
		txtValorApostado.setColumns(10);
		
		JLabel lblValorApostado = new JLabel("Valor");
		lblValorApostado.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorApostado.setLabelFor(txtValorApostado);
		lblValorApostado.setBounds(189, 107, 46, 14);
		frame.getContentPane().add(lblValorApostado);
	
	}
}
