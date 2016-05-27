package pct_servidor;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Servidor extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
    static JPanel contentPane;
	static JTextField tfNome;
	static JTextField tfIP;
	static JTextArea taChat;
	static JTextField tfChat;
	static JMenuBar menuBar;
	static JMenu mnArquivo;
	static JMenuItem mntmSair;
	static JLabel lblNome;
	static JLabel lblIP;
	static JLabel lblNotificacao;
	static JButton btnEnviar;
	
	static FuncoesServidor funcao = new FuncoesServidor();

	/**
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor frame = new Servidor();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		funcao.openGateway();
		
		funcao.listenConnection();
		
		lblNotificacao.setText("Conectado com...");
		tfNome.setText(funcao.getHostName());
		tfIP.setText(funcao.getHostIp());
		tfChat.setEditable(true);
		btnEnviar.setEnabled(true);
		
		funcao.errorFix();
		
		taChat.append("Mensagens aparecerão aqui...\n");
		taChat.append("-------------------------------------------------------\n");
		boolean infiniteLoop = true;
		while (infiniteLoop == true) {
			taChat.append(funcao.getHostName() + ": " + funcao.receveMessage() + "\n");
			taChat.append("-------------------------------------------------------\n");
		}
	}

	public Servidor() { 
		setTitle("Servidor");
		setDefaultCloseOperation(Servidor.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 600);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 35, 46, 14);
		contentPane.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setBounds(66, 32, 107, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		lblIP = new JLabel("IP:");
		lblIP.setBounds(10, 60, 46, 14);
		contentPane.add(lblIP);
		
		tfIP = new JTextField();
		tfIP.setEditable(false);
		tfIP.setBounds(66, 57, 107, 20);
		contentPane.add(tfIP);
		tfIP.setColumns(10);
		
		lblNotificacao = new JLabel("Aguardando usuário...");
		lblNotificacao.setBounds(10, 11, 163, 14);
		contentPane.add(lblNotificacao);
		
		taChat = new JTextArea();
		taChat.setBounds(10, 85, 334, 413);
		contentPane.add(taChat);
		taChat.setEditable(false);
		
		tfChat = new JTextField();
		tfChat.setBounds(10, 509, 278, 20);
		contentPane.add(tfChat);
		tfChat.setColumns(10);
		tfChat.setEditable(false);
		
		btnEnviar = new JButton(">");
		btnEnviar.setBounds(298, 508, 46, 23);
		contentPane.add(btnEnviar);
		btnEnviar.setEnabled(false);

		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					funcao.sendTextMessage(tfChat.getText());
				} catch (IOException e) {
					e.printStackTrace();
				}
				taChat.append("Você: " + tfChat.getText() + "\n");
				taChat.append("(Enviada às " + (new Date()) + ")\n");
				taChat.append("------------------------------------------------------------------------------------------------------------------\n");
				System.out.println("Você: " + tfChat.getText());
				System.out.println("(Enviada às " + (new Date()) + ")");
				System.out.println("------------------------------------------------------------------------------------------------------------------");
				tfChat.setText("");
			}
		});
	}
}
