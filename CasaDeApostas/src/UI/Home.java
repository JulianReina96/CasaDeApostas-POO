package UI;

import java.awt.EventQueue;

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
import Business.EventoBusiness;
import Model.Aposta;
import Model.Evento;
import Model.Usuario;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

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
				Object[][] data = new Object[(int) eventos.stream().filter(x -> x.getAberta()).count()][6];
				String nomeBotao = "";
				if(userSession.isAdministrador()) {
					nomeBotao = "Editar";
				}
				else {
					nomeBotao = "Apostar";
				}
				 
				int count = 0;
			    for (int i = 0; i < eventos.size(); i++) {
			        Evento evento = eventos.get(i);
			        if(evento.getAberta()) {
			        	data[count] = new Object[]{
				                evento.getNome(),
				                evento.getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				                evento.getTimeCasa() + " - " + evento.getOddVitoria(),
				                evento.getOddEmpate(),
				                evento.getTimeVisitante() + " - " + evento.getOddDerrota(),
				                nomeBotao,
				                evento
				        };
			        	count++;
			        }
			    }

			String[] columnNames = { "Evento", "Data", "Time de Casa", "Empate", "Time de Fora", nomeBotao, ""};

			// Modelo da tabela
			DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return column == 5; // Torna a coluna dos botões editável
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
			table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
			table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

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
		

		SwingUtilities.invokeLater(() -> { //TESTE DA TABELA DE APOSTAS PENDENTES PARA A HOME
			ApostaBusiness ctr = new ApostaBusiness();
				List<Aposta> apostas = userSession.getApostasPendentes();
				Object[][] data = new Object[apostas.size()][6];
				for (int i = 0; i < apostas.size(); i++) {
					Aposta aposta = apostas.get(i);

					data[i] = new Object[] { aposta.tipoApostaDescricao(), 
							"R$ " + aposta.getValor(), aposta
							 };
				}

				String[] columnNames = { "Aposta", "Valor Apostado", "Remover", ""};

				// Modelo da tabela
				DefaultTableModel model = new DefaultTableModel(data, columnNames) {
					 @Override
					    public boolean isCellEditable(int row, int column) {
					        return column == 2; // Torna a coluna dos botões editável
					    }
				};

				// Criação da tabela
				JTable table = new JTable(model);
				table.setRowHeight(30); // Ajusta a altura da linha
				
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
							Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
									column);
							((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
							return c;
						}
					});
				}
				
				// Configuração do renderizador e editor para a coluna do botão
				table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRendererRemove());
				table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditorRemove(new JCheckBox()));

				JPanel panel = new JPanel(new BorderLayout());
				panel.setSize(338, 564);
				panel.setLocation(916, 106);

				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setLocation(2, 0);
				panel.add(scrollPane, BorderLayout.CENTER);

				frame.getContentPane().add(panel);
		});

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(44, 36);
		menuBar.setLocation(1210, 18);

        JMenu menuOpcoes = new JMenu("");
        
        if(Home.class.getResource("/Icons/user_icon.png") != null) {
        	menuOpcoes.setIcon(new ImageIcon(Home.class.getResource("/Icons/user_icon.png")));
        }
        

        JMenuItem itemConta = new JMenuItem("Conta");
		itemConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaUsuario Conta =new ContaUsuario(userSession);
				frame.setVisible(false);
				
				Conta.getFrame().setVisible(true);
			}
		});

        menuOpcoes.add(itemConta);
        
        if(userSession.isAdministrador()) {
        	
        JMenuItem itemAdmin = new JMenuItem("Cadastrar Admin");
        
        itemAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro cadastro = new Cadastro(true);
				
				cadastro.getFrame().setVisible(true);
			}
		});
        
        menuOpcoes.add(itemAdmin);
        
        JMenuItem itemNovoEvento = new JMenuItem("Adicionar Evento");
        
       itemNovoEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarEvento evento = new AdicionarEvento();
				
				evento.getFrame().setVisible(true);
			}
		});
       
        menuOpcoes.add(itemNovoEvento);
        }
        else {
        JMenuItem itemApostas = new JMenuItem("Minhas Apostas");
        itemApostas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoricoApostas historicoapostas = new HistoricoApostas(userSession);
				frame.setVisible(false);
				historicoapostas.getFrame().setVisible(true);
			}
		});
        
        menuOpcoes.add(itemApostas);
        }

        
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
			frame.setVisible(false);
				
				login.getFrame().setVisible(true);
			}
		});
        menuOpcoes.add(itemSair);
        
        
        
      
        menuBar.add(menuOpcoes);

        frame.getContentPane().add(menuBar);
        //MENU OPÇÕES CADASTRAR EVENTOS 
		
		
		
		
		
		
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
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setLabelFor(tbApostas);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setBounds(1008, 71, 165, 36);
		frame.getContentPane().add(lblNewLabel_2);
		
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

	// Renderizador de célula para o botão
	class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
			setOpaque(true);
			if(userSession.isAdministrador()) {
				setText("Editar");
			}
			else {
				setText("Apostar");
			}
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
					Evento evento = (Evento) table.getValueAt(row, 6); 
					if(!userSession.isAdministrador()) {
						Apostar apostar = new Apostar(evento, userSession, frame);
						apostar.getFrame().setVisible(true);
						fireEditingStopped(); // Para parar a edição
					}
					else {
						EditarEvento editEvent = new EditarEvento(evento, frame, userSession);
						editEvent.getFrame().setVisible(true);
						fireEditingStopped();
					}
					
				}
			});

			table = null; // Inicialize em null, será definido mais tarde
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			this.table = table;
			if(userSession.isAdministrador()) {
				button.setText("Editar");
			}
			else {
				button.setText("Apostar");
			}
			
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			return button.getText();
		}
	}
	
	// Renderizador de célula para o botão REMOVER APOSTA
		class ButtonRendererRemove extends JButton implements TableCellRenderer {

			public ButtonRendererRemove() {
				setOpaque(true);

					setText("Remover");
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				return this;
			}
		}
		
		// Editor de célula para o botão REMOVER APOSTA
		class ButtonEditorRemove extends DefaultCellEditor {
			
			private final JButton button;
			private JTable table;

			public ButtonEditorRemove(JCheckBox checkBox) {
				super(checkBox);
				button = new JButton();
				button.setOpaque(true);

				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int row = table.convertRowIndexToModel(table.getEditingRow());
						Aposta aposta = (Aposta) table.getValueAt(row, 3); 
						userSession.removerApostaPendente(aposta);
						table.removeRowSelectionInterval(row, row);
					}
				});

				table = null; // Inicialize em null, será definido mais tarde
			}

			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				this.table = table;
				button.setText("Remover");
				return button;
			}

			@Override
			public Object getCellEditorValue() {
				return button.getText();
			}
		}
}
