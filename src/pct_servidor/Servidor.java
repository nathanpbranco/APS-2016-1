package pct_servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Servidor extends javax.swing.JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor frame = new Servidor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/*Sessão do console*/
		try {
			/*Libera uma porta para que seja possivel uma conexão*/
			ServerSocket servidor = new ServerSocket(5000);
			System.out.println("A porta 5000 foi aberta...");
			/*A partir daqui, o processo é enterrompido pelo método accept().
			 * O programa está esperando um usuario se conectar*/
			Socket cliente = servidor.accept();
			System.out.println("Nova conexão");
			System.out.println("Nome: " + cliente.getInetAddress().getHostName());
			System.out.println("IP: " + cliente.getInetAddress().getHostAddress());
			
			/*Recebe as informações enviadas pelo usuario*/
			Scanner leitor = new Scanner(cliente.getInputStream());
			while (leitor.hasNextLine()) {
				System.out.println(leitor.nextLine());
			}
			
			/*Encerra as conexões*/
			leitor.close();
			servidor.close();
			cliente.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public Servidor() {
		setTitle("Servidor");
		setDefaultCloseOperation(Servidor.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 472);
		
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
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(66, 8, 107, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(10, 36, 46, 14);
		contentPane.add(lblIp);
		
		textField_1 = new JTextField();
		textField_1.setBounds(66, 33, 107, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNome_1 = new JLabel("Nome:");
		lblNome_1.setBounds(459, 11, 46, 14);
		contentPane.add(lblNome_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(515, 8, 103, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblIp_1 = new JLabel("IP:");
		lblIp_1.setBounds(459, 36, 46, 14);
		contentPane.add(lblIp_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(515, 33, 103, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblConectadoCom = new JLabel("Conectado com...");
		lblConectadoCom.setBounds(258, 22, 113, 14);
		contentPane.add(lblConectadoCom);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(10, 61, 608, 309);
		contentPane.add(textArea);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(10, 380, 77, 23);
		contentPane.add(btnEnviar);
		
		textField_4 = new JTextField();
		textField_4.setBounds(97, 381, 521, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	}
}
