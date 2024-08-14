package UI;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
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

import Business.ApostaBusiness;
import Model.Aposta;
import Model.Evento;
import Model.Usuario;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HistoricoApostas {

	private JFrame frame;
	private JTable tbEventos;
	private Usuario userSession;

	public HistoricoApostas(Usuario user) {
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
			ApostaBusiness ctr = new ApostaBusiness();
			try {
				List<Aposta> apostas = ctr.listarApostas(userSession.getID());
				Object[][] data = new Object[apostas.size()][6];
			    for (int i = 0; i < apostas.size(); i++) {
			        Aposta aposta = apostas.get(i);

			        	data[i] = new Object[]{
				                aposta.getEvento().getNome(),
				                aposta.getEvento().getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				                aposta.getTipoAposta().getDescricao(),
				                "R$ " + aposta.getValor(),
				                aposta.getStatusAposta().getDescricao()
				        };
			    }

			String[] columnNames = { "Evento", "Data do Evento", "Tipo de Aposta", "Valor Apostado","Status"};

			// Modelo da tabela
			DefaultTableModel model = new DefaultTableModel(data, columnNames);

			// Criação da tabela
			JTable table = new JTable(model);
			table.setRowHeight(30); // Ajusta a altura da linha

			// Ocultar a última coluna
			/*
			TableColumn lastColumn = table.getColumnModel().getColumn(table.getColumnCount() - 1);
			lastColumn.setMinWidth(0);
			lastColumn.setMaxWidth(0);
			lastColumn.setPreferredWidth(0);
			*/
			
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

			JPanel panel = new JPanel(new BorderLayout());
			panel.setSize(866, 564);
			panel.setLocation(20, 106);

			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setLocation(2, 0);
			panel.add(scrollPane, BorderLayout.CENTER);

			frame.getContentPane().add(panel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		JLabel lblNewLabel = new JLabel("Cat's Bet");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Home home = new Home(userSession);
				frame.setVisible(false);
				home.getFrame().setVisible(true);
			}
		});
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 28));
		lblNewLabel.setBounds(487, 14, 313, 40);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Histórico de Apostas");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setLabelFor(tbEventos);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setBounds(199, 67, 866, 40);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBackground(new Color(0, 0, 0));

			
		JButton btnDeposito = new JButton("");
		btnDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeposito.setIcon(new ImageIcon(HistoricoApostas.class.getResource("/Icons/user_icon.png")));
		btnDeposito.setForeground(new Color(0, 0, 0));
		btnDeposito.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnDeposito.setBounds(1210, 18, 36, 36);
		frame.getContentPane().add(btnDeposito);
		
		JLabel lblUserName = new JLabel("Olá, " + userSession.getNome());
		lblUserName.setForeground(new Color(255, 255, 255));
		lblUserName.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setFont(new Font("Dialog", Font.BOLD, 24));
		lblUserName.setBounds(935, 16, 267, 36);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblSaldo = new JLabel("Saldo R$ " + String.format("%.2f", userSession.getSaldo()).replace('.', ','));
		lblSaldo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSaldo.setForeground(new Color(255, 255, 255));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBounds(1081, 60, 165, 14);
		frame.getContentPane().add(lblSaldo);

	}

	
}