package pct_cliente;

import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/*Sessão de objetos globais*/
	
	private JPanel contentPane;
	public JTextField tfChat;
	
	static Funcoes funcao = new Funcoes();
	
	JLabel lblStatus = new JLabel("Status:");
	private JTextField tfGateway;
	private JTextField tfIP;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cliente() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 495);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblStatus.setBounds(10, 124, 184, 14);
		contentPane.add(lblStatus);
		
		final JTextArea taChat = new JTextArea();
		taChat.setBounds(10, 149, 506, 241);
		contentPane.add(taChat);
		
		final JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(10, 401, 89, 23);
		contentPane.add(btnEnviar);
		btnEnviar.setEnabled(false);
		
		tfChat = new JTextField();
		tfChat.setBounds(109, 402, 407, 20);
		contentPane.add(tfChat);
		tfChat.setColumns(10);
		
		JLabel lblIP = new JLabel("IP:");
		lblIP.setBounds(10, 11, 46, 14);
		contentPane.add(lblIP);
		
		JLabel lblGateway = new JLabel("Gateway:");
		lblGateway.setBounds(10, 36, 70, 14);
		contentPane.add(lblGateway);
		
		tfGateway = new JTextField();
		tfGateway.setBounds(90, 33, 86, 20);
		contentPane.add(tfGateway);
		tfGateway.setColumns(10);
		tfGateway.setText(String.valueOf(5000));
		
		tfIP = new JTextField();
		tfIP.setBounds(90, 8, 86, 20);
		contentPane.add(tfIP);
		tfIP.setColumns(10);
		tfIP.setText("192.168.56.1");
		
		final JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(186, 7, 89, 47);
		contentPane.add(btnConectar);
		
		final JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(285, 7, 93, 46);
		contentPane.add(btnDesconectar);
		
		//<INICIO> Action Handlers
		
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Termina a conexão*/
				funcao.encerrar();
			}
		});
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				funcao.enviarMensagemViaTextField(tfChat.getText());
				taChat.append("Você: " + tfChat.getText() + "\n");
				taChat.append("(Enviada às " + (new Date()) + ")\n");
				taChat.append("------------------------------------------------------------------------------------------------------------------\n");
			}
		});
		
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				funcao.setIP(tfIP.getText());
				funcao.setGateway(Integer.parseInt(tfGateway.getText()));
				funcao.conectar();
				lblStatus.setText("Status: Conectado");
				btnDesconectar.setEnabled(true);
				btnConectar.setEnabled(false);
				btnEnviar.setEnabled(true);
				tfIP.setEnabled(false);
				tfGateway.setEnabled(false);
				
			}
		});
		
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				funcao.desconectar();
				btnDesconectar.setEnabled(false);
				btnConectar.setEnabled(true);
				btnEnviar.setEnabled(false);
				tfIP.setEnabled(true);
				tfGateway.setEnabled(true);
			}
		});
	}
}
