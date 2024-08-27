package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Business.ApostaBusiness;
import Business.EventoBusiness;
import Model.Aposta;
import Model.Evento;
import Model.Usuario;
import javax.swing.JSeparator;
import java.awt.Choice;
import java.awt.Component;
import javax.swing.Box;

public class EditarEvento {

	private JFrame frame;
	private JFrame home;
	private final ButtonGroup oddApostada = new ButtonGroup();
	private Evento evento;
	private Usuario user;
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

	public EditarEvento(Evento evento, JFrame home, Usuario user) {
		this.evento = evento;
		this.home = home;
		this.user = user;

		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 325);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		// Set a thin black border for the frame
		frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		JRadioButton rdOddCasa = new JRadioButton(evento.getTimeCasa());
		rdOddCasa.setToolTipText("1");
		oddApostada.add(rdOddCasa);
		rdOddCasa.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddCasa.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddCasa.setBounds(522, 134, 135, 23);
		rdOddCasa.setName("1");
		frame.getContentPane().add(rdOddCasa);

		JRadioButton rdOddEmpate = new JRadioButton("Empate");
		oddApostada.add(rdOddEmpate);
		rdOddEmpate.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddEmpate.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddEmpate.setBounds(612, 160, 102, 23);
		rdOddEmpate.setName("2");
		frame.getContentPane().add(rdOddEmpate);

		JRadioButton rdOddFora = new JRadioButton(evento.getTimeVisitante());
		oddApostada.add(rdOddFora);
		rdOddFora.setHorizontalAlignment(SwingConstants.CENTER);
		rdOddFora.setFont(new Font("Dialog", Font.BOLD, 14));
		rdOddFora.setBounds(659, 134, 135, 23);
		rdOddFora.setName("3");
		frame.getContentPane().add(rdOddFora);

		// Create and configure "Cancelar" button
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(693, 291, 97, 23);
		btnCancelar.addActionListener(e -> frame.setVisible(false));
		frame.getContentPane().add(btnCancelar);

		JLabel lblFinalizarEvento = new JLabel("Finalizar Evento");
		lblFinalizarEvento.setFont(new Font("Dialog", Font.BOLD, 18));
		lblFinalizarEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalizarEvento.setBounds(567, 35, 180, 32);
		frame.getContentPane().add(lblFinalizarEvento);

		JLabel lblSelecioneOResultado = new JLabel("Selecione o Resultado:");
		lblSelecioneOResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneOResultado.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSelecioneOResultado.setBounds(577, 98, 180, 32);
		frame.getContentPane().add(lblSelecioneOResultado);

		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EventoBusiness ctr = new EventoBusiness();
					if (rdOddCasa.isSelected()) {
						if (ctr.finalizarEvento(evento.getID(), 1)) {
							JOptionPane.showMessageDialog(frame, "Evento finalizado com sucesso!");
						} else {
							JOptionPane.showMessageDialog(frame, "Ocorreu um erro ao finalizar o Evento!");
						}
					} else if (rdOddEmpate.isSelected()) {
						if (ctr.finalizarEvento(evento.getID(), 2)) {
							JOptionPane.showMessageDialog(frame, "Evento finalizado com sucesso!");
						} else {
							JOptionPane.showMessageDialog(frame, "Ocorreu um erro ao finalizar o Evento!");
						}
					} else if (rdOddFora.isSelected()) {
						if (ctr.finalizarEvento(evento.getID(), 3)) {
							JOptionPane.showMessageDialog(frame, "Evento finalizado com sucesso!");
						} else {
							JOptionPane.showMessageDialog(frame, "Ocorreu um erro ao finalizar o Evento!");
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Selecione o resultado do evento para finalizar!");
						return;
					}
					Home newHome = new Home(user);
					home.setVisible(false);
					newHome.getFrame().setVisible(true);
					frame.setVisible(false);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setMnemonic('F');
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBounds(588, 205, 159, 39);
		frame.getContentPane().add(btnNewButton);

		JLabel lblEditarEvento = new JLabel("Editar Evento");
		lblEditarEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarEvento.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEditarEvento.setBounds(149, 35, 180, 32);
		frame.getContentPane().add(lblEditarEvento);

		txtOddCasa = new JTextField(Double.toString(evento.getOddVitoria()).replace('.', ','));
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
		txtOddCasa.setBounds(63, 163, 58, 20);
		frame.getContentPane().add(txtOddCasa);
		txtOddCasa.setColumns(10);

		txtOddEmpate = new JTextField(Double.toString(evento.getOddEmpate()).replace('.', ','));
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
		txtOddEmpate.setBounds(219, 163, 58, 20);
		frame.getContentPane().add(txtOddEmpate);

		txtOddVisitante = new JTextField(Double.toString(evento.getOddDerrota()).replace('.', ','));
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
		txtOddVisitante.setBounds(361, 163, 58, 20);
		frame.getContentPane().add(txtOddVisitante);

		txtTimeCasa = new JTextField(evento.getTimeCasa());
		txtTimeCasa.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeCasa.setBounds(42, 136, 114, 20);
		frame.getContentPane().add(txtTimeCasa);
		txtTimeCasa.setColumns(10);

		JLabel lblEmpateEdit = new JLabel("Empate");
		lblEmpateEdit.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEmpateEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpateEdit.setBounds(219, 138, 55, 16);
		frame.getContentPane().add(lblEmpateEdit);

		txtTimeVisitante = new JTextField(evento.getTimeVisitante());
		txtTimeVisitante.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeVisitante.setColumns(10);
		txtTimeVisitante.setBounds(334, 136, 114, 20);
		frame.getContentPane().add(txtTimeVisitante);

		txtNomeEvento = new JTextField(evento.getNome());
		txtNomeEvento.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeEvento.setColumns(10);
		txtNomeEvento.setBounds(26, 79, 305, 20);
		frame.getContentPane().add(txtNomeEvento);

		txtDataEvento = new JTextField(evento.getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
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
		txtDataEvento.setBounds(343, 79, 114, 20);
		frame.getContentPane().add(txtDataEvento);
		txtDataEvento.setColumns(10);

		JPanel linha = new JPanel();
		linha.setBounds(507, -1, 5, 332);
		linha.setBackground(Color.BLACK);
		frame.getContentPane().add(linha);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
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
					Evento editEvent = new Evento(evento.getID(), nome, dataEvento, timeCasa, timeVisitante, oddCasa,
							oddDerrota, oddEmpate, true);
					EventoBusiness ctr = new EventoBusiness();
					ctr.editarEvento(editEvent);
					JOptionPane.showMessageDialog(frame, "Evento editado com sucesso!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}

			}
		});
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setMnemonic('E');
		btnEditar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnEditar.setBackground(new Color(0, 64, 128));
		btnEditar.setBounds(171, 234, 159, 39);
		frame.getContentPane().add(btnEditar);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int resposta = JOptionPane.showConfirmDialog(frame,
							"<html><b style='text-align: center;'>Tem Certeza que deseja excluir o evento</b></br><p>Não será possivel excluir eventos que ja foram apostados</p></html>",
							"Excluir Evento?", JOptionPane.YES_NO_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {
						EventoBusiness ctr = new EventoBusiness();
						ctr.deletarEvento(evento);
					}
				} catch (SQLException ex) {
				    if (ex.getSQLState().equals("23503")) {
				        JOptionPane.showMessageDialog(frame, "O evento ja possui apostas, logo não pode ser deletado!");
				    } else {
				        // Tratamento para outros tipos de exceções
				        JOptionPane.showMessageDialog(frame, "Ocorreu um erro inesperado: " + ex.getMessage());
				    }
					
				}
			}
		});
		btnDeletar.setMnemonic('D');
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnDeletar.setBackground(new Color(255, 0, 0));
		btnDeletar.setBounds(12, 287, 102, 27);
		frame.getContentPane().add(btnDeletar);

	}
}
