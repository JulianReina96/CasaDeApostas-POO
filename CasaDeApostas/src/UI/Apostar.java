package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Evento;
import Model.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Apostar {

	private JFrame frame;
//	private JFrame home;
	private final ButtonGroup oddApostada = new ButtonGroup();
	private Evento evento;
	private Usuario user;
	private JTextField txtValorApostado;
	private JLabel lblGanhos;
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public Apostar(Evento evento, Usuario user) {
		this.user = user;
		this.evento = evento;
		
		
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
        frame.setBounds(100, 100, 521, 230);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        // Set a thin black border for the frame
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		JButton btnNewButton = new JButton("Apostar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showConfirmDialog(btnNewButton, "Tem certeza que deseja apostar?");
//				Home novaTela = new Home(user);
//				novaTela.getFrame().setVisible(true);
//				home.setVisible(false);
//				frame.setVisible(false);
				
				
				
			}
		});
		btnNewButton.setBounds(207, 170, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblTimeCasa = new JLabel(evento.getTimeCasa());
		lblTimeCasa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeCasa.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTimeCasa.setBounds(56, 53, 118, 14);
		frame.getContentPane().add(lblTimeCasa);
		
		JLabel lblTimeFora = new JLabel(evento.getTimeVisitante());
		lblTimeFora.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTimeFora.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeFora.setBounds(330, 53, 118, 14);
		frame.getContentPane().add(lblTimeFora);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(229, 53, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JRadioButton rdOddCasa = new JRadioButton(Double.toString(evento.getOddVitoria()));
		oddApostada.add(rdOddCasa);
		rdOddCasa.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddCasa.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddCasa.setBounds(48, 74, 135, 23);
		frame.getContentPane().add(rdOddCasa);
		
		JRadioButton rdOddEmpate = new JRadioButton(Double.toString(evento.getOddEmpate()));
		oddApostada.add(rdOddEmpate);
		rdOddEmpate.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddEmpate.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddEmpate.setBounds(220, 74, 65, 23);
		frame.getContentPane().add(rdOddEmpate);
		
		JRadioButton rdOddFora = new JRadioButton(Double.toString(evento.getOddDerrota()));
		oddApostada.add(rdOddFora);
		rdOddFora.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddFora.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddFora.setBounds(322, 74, 135, 23);
		frame.getContentPane().add(rdOddFora);
		
		JLabel lblEvento = new JLabel(evento.getNome());
		lblEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblEvento.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEvento.setBounds(94, 20, 316, 23);
		frame.getContentPane().add(lblEvento);
		
		lblGanhos = new JLabel("R$ 0,00");
		lblGanhos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanhos.setBounds(245, 134, 127, 14);
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
					if(getOddSelected(Collections.list(oddApostada.getElements())) != null) {
						valorD = valorD * Double.parseDouble(getOddSelected(Collections.list(oddApostada.getElements())).getLabel());
					}
					lblGanhos.setText("R$ " + String.format("%.2f", valorD).replace('.', ','));
				}
			}
		});
		
		txtValorApostado.setBounds(149, 134, 86, 20);
		frame.getContentPane().add(txtValorApostado);
		txtValorApostado.setColumns(10);
		
		JLabel lblValorApostado = new JLabel("Valor");
		lblValorApostado.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorApostado.setLabelFor(txtValorApostado);
		lblValorApostado.setBounds(169, 120, 46, 14);
		frame.getContentPane().add(lblValorApostado);
		
		JLabel lblGanhosPontenciais = new JLabel("Ganhos potenciais:");
		lblGanhosPontenciais.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanhosPontenciais.setBounds(249, 120, 123, 14);
		frame.getContentPane().add(lblGanhosPontenciais);
		
		// Create and configure "Cancelar" button
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(414, 196, 97, 23);
        btnCancelar.addActionListener(e -> frame.setVisible(false));
        frame.getContentPane().add(btnCancelar);
	}
	
	private JRadioButton getOddSelected(ArrayList<AbstractButton> arrayList) {
	    for (AbstractButton botao : arrayList) {
	        if (botao.isSelected()) {
	            return (JRadioButton)botao;
	        }
	    }
	    return null;
	}
}
