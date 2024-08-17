package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
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

import Business.ApostaBusiness;
import Model.Aposta;
import Model.Evento;
import Model.Usuario;

public class Apostar {

	private JFrame frame;
	private JFrame home;
	private final ButtonGroup oddApostada = new ButtonGroup();
	private Evento evento;
	private Usuario user;
	private JTextField txtValorApostado;
	private JLabel lblGanhos;
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public Apostar(Evento evento, Usuario user, JFrame home) {
		this.user = user;
		this.evento = evento;
		this.home = home;
		
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
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Confirmação", JOptionPane.YES_NO_OPTION);
				if(resposta == JOptionPane.YES_OPTION) {
					JRadioButton botao = getOddSelected(Collections.list(oddApostada.getElements()));
					var valor = txtValorApostado.getText();
					if(botao == null) {
						JOptionPane.showMessageDialog(frame, "Selecione um odd para apostar!");
					}
					else if(valor.equals("")) {
						JOptionPane.showMessageDialog(frame, "Digite um valor para apostar!");
					}
					else if(Double.valueOf(valor.replace(',', '.')) > user.getSaldo()) {
						JOptionPane.showMessageDialog(frame, "O valor apostado excede o saldo!");
					}
					else {
						ApostaBusiness ctr = new ApostaBusiness();
						try {
							var aposta = ctr.reaizarAposta(new Aposta(user, evento, Integer.valueOf(botao.getName()), Double.valueOf(valor.replace(',', '.'))));
							JOptionPane.showMessageDialog(frame, "Aposta realizada com sucesso!");
							user.setSaldo(user.getSaldo() - aposta.getValor());
							Home novaTela = new Home(user);
							frame.setVisible(false);
							novaTela.getFrame().setVisible(true);
							home.setVisible(false);
							
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(frame, "Ocorreu um erro ao apostar!");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(frame, "Ocorreu um erro ao apostar!");
						}
					}
				}				
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
		rdOddCasa.setToolTipText("1");
		oddApostada.add(rdOddCasa);
		rdOddCasa.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddCasa.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddCasa.setBounds(48, 74, 135, 23);
		rdOddCasa.setName("1");
		frame.getContentPane().add(rdOddCasa);
		
		JRadioButton rdOddEmpate = new JRadioButton(Double.toString(evento.getOddEmpate()));
		oddApostada.add(rdOddEmpate);
		rdOddEmpate.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddEmpate.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddEmpate.setBounds(220, 74, 65, 23);
		rdOddEmpate.setName("2");
		frame.getContentPane().add(rdOddEmpate);
		
		JRadioButton rdOddFora = new JRadioButton(Double.toString(evento.getOddDerrota()));
		oddApostada.add(rdOddFora);
		rdOddFora.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddFora.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddFora.setBounds(322, 74, 135, 23);
		rdOddFora.setName("3");
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
