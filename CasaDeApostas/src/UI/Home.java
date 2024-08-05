package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Business.EventoBusiness;
import Model.Evento;
import Model.Usuario;

public class Home {

	private JFrame frame;
	private JTable tbEventos;
	private JTable tbApostas;
	private Usuario userSession;

	public Home(Usuario user) {
		userSession = user;
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

		SwingUtilities.invokeLater(() -> {
			EventoBusiness ctr = new EventoBusiness();
			try {
				List<Evento> eventos = ctr.listarEventos();
				Object[][] data = new Object[eventos.size()][6];
				
			    for (int i = 0; i < eventos.size(); i++) {
			        Evento evento = eventos.get(i);
			        data[i] = new Object[]{
			                evento.getNome(),
			                evento.getTimeCasa() + " - " + evento.getOddVitoria(),
			                evento.getOddEmpate(),
			                evento.getTimeVisitante() + " - " + evento.getOddDerrota(),
			                "Apostar",
			                evento
			        };
			    }
			String[] columnNames = { "Evento", "Time de Casa", "Empate", "Time de Fora", "Apostar", ""};

			// Modelo da tabela
			DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return column == 4; // Torna a coluna dos botões editável
			    }
			};

			// Criação da tabela
			JTable table = new JTable(model);
			table.setRowHeight(30); // Ajusta a altura da linha para o botão

			// Ocultar a última coluna
			TableColumn lastColumn = table.getColumnModel().getColumn(table.getColumnCount() - 1);
			lastColumn.setMinWidth(0);
			lastColumn.setMaxWidth(0);
			lastColumn.setPreferredWidth(0);
			
			// Centraliza os dados em todas as colunas
		    for (int i = 0; i < table.getColumnCount(); i++) {
		        table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
		            @Override
		            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		                    boolean hasFocus, int row, int column) {
		                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
		                return c;
		            }
		        });
		    }

			// Configuração do renderizador e editor para a coluna do botão
			table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
			table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

			JPanel panel = new JPanel(new BorderLayout());
			panel.setSize(866, 564);
			panel.setLocation(20, 106);

			panel.add(new JScrollPane(table), BorderLayout.CENTER);

			frame.getContentPane().add(panel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		

		tbApostas = new JTable();
		tbApostas.setBounds(916, 106, 338, 564);
		frame.getContentPane().add(tbApostas);

		JLabel lblNewLabel = new JLabel("Cat's Bet");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 28));
		lblNewLabel.setBounds(487, 14, 313, 40);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Eventos");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setLabelFor(tbEventos);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setBounds(20, 67, 866, 40);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Apostas");
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setLabelFor(tbApostas);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setBounds(1008, 71, 165, 36);
		frame.getContentPane().add(lblNewLabel_2);

		JButton btnDeposito = new JButton("Depositar");
		btnDeposito.setForeground(new Color(0, 0, 0));
		btnDeposito.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnDeposito.setBounds(1110, 19, 132, 36);
		frame.getContentPane().add(btnDeposito);

	}

	// Renderizador de célula para o botão
	class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
			setOpaque(true);
			setText("Apostar");
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return this;
		}
	}

	// Editor de célula para o botão
	class ButtonEditor extends DefaultCellEditor {

		private final JButton button;
		private JTable table;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);

			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.convertRowIndexToModel(table.getEditingRow());
					Evento evento = (Evento) table.getValueAt(row, 5); 
					Apostar apostar = new Apostar(evento, userSession);
					apostar.getFrame().setVisible(true);
					fireEditingStopped(); // Para parar a edição
				}
			});

			table = null; // Inicialize em null, será definido mais tarde
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			this.table = table;
			button.setText("Apostar");
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			return button.getText();
		}
	}
}
