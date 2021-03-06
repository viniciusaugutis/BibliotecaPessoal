package br.com.biblioteca.ui;

import java.util.Iterator;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.Timer;

import br.com.biblioteca.dominio.Pessoa;
import br.com.biblioteca.persistencia.PessoaDB;
import utili.AplicaLookAndFeel;
import utili.Limitador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class PessoaUI extends JInternalFrame {

	private JPanel contentPane;
	private JTable tbPessoa;
	private DefaultTableModel modelo = new DefaultTableModel();
	private List<Pessoa> lista;
	JButton btnRemover = new JButton();
	JButton btnnAlterar = new JButton();

	public PessoaUI() {

		AplicaLookAndFeel.pegaLookAndFeel();

		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);

		setTitle("Pessoa");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnRemover.setToolTipText("Remover");
		btnRemover.setIcon(new ImageIcon(AutorUI.class.getResource("/icones/delete2.png")));

		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tbPessoa.getSelectedRow() != -1) {

					if (JOptionPane.showConfirmDialog(null, "Confirma a exclus�o do registro?", "Exclus�o",
							JOptionPane.YES_NO_OPTION) == 0) {
						PessoaDB pessoadb = new PessoaDB();
						pessoadb.excluir(lista.get(tbPessoa.getSelectedRow()));
						atualizaTabela();
					}

				} else {
					JOptionPane meuJOPane = new JOptionPane("Selecione um registro na tabela",
							JOptionPane.ERROR_MESSAGE);//
					final JDialog dialog = meuJOPane.createDialog(null, "Aten��o!");

					dialog.setModal(true);

					Timer timer = new Timer(3 * 1000, new ActionListener() {

						public void actionPerformed(ActionEvent ev) {
							dialog.dispose();

						}

					});
					timer.start();
					dialog.setVisible(true);
					timer.stop();
				}
			}
		});

		btnnAlterar.setToolTipText("Alterar");
		btnnAlterar.setIcon(new ImageIcon(AutorUI.class.getResource("/icones/alterar2.png")));

		btnnAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if (tbPessoa.getSelectedRow() != -1) {

					CadastroPessoaUI cadastroPessoa = new CadastroPessoaUI(1, lista.get(tbPessoa.getSelectedRow()));

					BibliotecaPessoal b = BibliotecaPessoal.frame;
					b.desktopPane.add(cadastroPessoa);
					cadastroPessoa.setVisible(true);

					setVisible(false);

				} else {
					JOptionPane meuJOPane = new JOptionPane("Selecione um registro na tabela",
							JOptionPane.ERROR_MESSAGE);//
					final JDialog dialog = meuJOPane.createDialog(null, "Aten��o!");

					dialog.setModal(true);

					Timer timer = new Timer(3 * 1000, new ActionListener() {

						public void actionPerformed(ActionEvent ev) {
							dialog.dispose();

						}

					});
					timer.start();
					dialog.setVisible(true);
					timer.stop();
				}

			}
		});

		JButton btnNovo = new JButton("");
		btnNovo.setIcon(new ImageIcon(AutorUI.class.getResource("/icones/novo5.png")));
		btnNovo.setToolTipText("Novo");

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroPessoaUI cadastroPessoa = new CadastroPessoaUI(0, null);

				BibliotecaPessoal b = BibliotecaPessoal.frame;
				b.desktopPane.add(cadastroPessoa);
				cadastroPessoa.setVisible(true);

				setVisible(false);
			}
		});

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(AutorUI.class.getResource("/icones/voltar2.png")));
		btnVoltar.setToolTipText("Voltar");

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 51,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnnAlterar, GroupLayout.PREFERRED_SIZE, 53,
												GroupLayout.PREFERRED_SIZE).addGap(18)
								.addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)).addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(15)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 44,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(btnnAlterar, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addGap(24)));

		String[] nomesColuna = { "Nome", "Telefone", "Email" };
		modelo.setColumnIdentifiers(nomesColuna);
		tbPessoa = new JTable(modelo);

		tbPessoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnRemover.setEnabled(true);
				btnnAlterar.setEnabled(true);
			}
		});

		tbPessoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnRemover.setEnabled(true);
				btnnAlterar.setEnabled(true);
			}
		});

		scrollPane.setViewportView(tbPessoa);
		contentPane.setLayout(gl_contentPane);

		atualizaTabela();
	}

	private void atualizaTabela() {

		btnnAlterar.setEnabled(false);
		btnRemover.setEnabled(false);

		PessoaDB pessoadb = new PessoaDB();
		lista = pessoadb.buscarTodos();

		Iterator<Pessoa> it = lista.iterator();
		Pessoa a;

		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}

		while (it.hasNext()) {
			a = it.next();
			modelo.addRow(new Object[] { a.getNome(), a.getTelefone(), a.getEmail() });
		}
	}

}
