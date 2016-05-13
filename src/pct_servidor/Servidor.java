package pct_servidor;

import java.awt.EventQueue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Caret;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class Servidor extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
	/*Sessão de objetos globais*/

	private static JPanel contentPane;
	private static JTextField tfNome;
	private static JTextField tfIP;
	
	static ServerSocket servidor;
	static Socket cliente;
	static Scanner leitor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		/*Sessão do console*/
		
		try {
			/*Libera uma porta para que seja possivel uma conexão*/
			servidor = new ServerSocket(5000);
			System.out.println("A porta 5000 foi aberta. \nAguardando conexão...");
			JOptionPane.showMessageDialog(null, "A porta 5000 foi aberta.\nAguardando conexão do usuário.");
			
			/*A partir daqui, o processo é enterrompido pelo método accept().
			 * O programa está esperando um usuario se conectar*/
			cliente = servidor.accept();
			System.out.println("Nova conexão");
			System.out.println("Nome: " + cliente.getInetAddress().getLocalHost().getHostName());
			System.out.println("IP: " + cliente.getInetAddress().getHostAddress());
			
			tfNome.setText(cliente.getInetAddress().getLocalHost().getHostName());
			tfIP.setText(cliente.getInetAddress().getHostAddress());
			
			/*Recebe as informações enviadas pelo usuario*/
			leitor = new Scanner(cliente.getInputStream());
			while (leitor.hasNextLine()) {
				System.out.println(cliente.getInetAddress().getHostName() + ": " + leitor.nextLine());
				JOptionPane.showMessageDialog(null, "Acabou de chegar um novo aviso !\n Enviada por " + cliente.getInetAddress().getLocalHost() + ".\n Confira no console.");
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public Servidor() {
		setTitle("Servidor");
		setDefaultCloseOperation(Servidor.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 428);
		
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
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 35, 46, 14);
		contentPane.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setBounds(66, 32, 107, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblIP = new JLabel("IP:");
		lblIP.setBounds(10, 60, 46, 14);
		contentPane.add(lblIP);
		
		tfIP = new JTextField();
		tfIP.setEditable(false);
		tfIP.setBounds(66, 57, 107, 20);
		contentPane.add(tfIP);
		tfIP.setColumns(10);
		
		JLabel lblConectadoCom = new JLabel("Conectado com...");
		lblConectadoCom.setBounds(10, 11, 113, 14);
		contentPane.add(lblConectadoCom);
		
		JTextArea taChat = new JTextArea();
		taChat.setLineWrap(true);
		taChat.setBounds(10, 85, 608, 269);
		contentPane.add(taChat);
		taChat.setText("Mensagens aparecerão aqui...");
		
		/*Sessão de Action Handlers*/
		
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Encerra as conexões*/
				try {
					leitor.close();
					servidor.close();
					cliente.close();
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
