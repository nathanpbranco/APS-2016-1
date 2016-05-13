package pct_cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

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
	
	/*Sess�o de objetos globais*/
	
	private JPanel contentPane;
	private JTextField tfChat;
	
	static Socket cliente;
	static PrintStream sa�da;
	static Scanner leitor;
	
	JLabel lblStatus = new JLabel("Status:");
	
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
		
		/*Sess�o do console*/
		
		try {
			/*Conecta com o servidor*/
			cliente = new Socket("192.168.56.1", 5000);
			System.out.println("Conectado ao servidor!");
			JOptionPane.showMessageDialog(null, "Conectado com o servidor !");
			
			/*L� e envia os dados do cliente para o servidor*/
			sa�da = new PrintStream(cliente.getOutputStream());
			leitor = new Scanner(System.in);
			while (leitor.hasNextLine()) {
				sa�da.println(leitor.nextLine());
			}
			
			/*Termina a conex�o*/
			sa�da.close();
			leitor.close();
			cliente.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "N�o foi possivel realizar a conex�o com o servidor.\nVerifique se o servidor est� conectado ou se o IP escolhido est� correto.");
			System.out.println("N�o foi possivel realizar a conex�o com o servidor.\nVerifique se o servidor est� conectado ou se o IP escolhido est� correto.");
		}
	}

	/**
	 * Create the frame.
	 */
	public Cliente() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 379);
		
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
		
		lblStatus.setBounds(10, 11, 184, 14);
		contentPane.add(lblStatus);
		
		final JTextArea taChat = new JTextArea();
		taChat.setBounds(10, 36, 506, 241);
		contentPane.add(taChat);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(10, 287, 89, 23);
		contentPane.add(btnEnviar);
		
		tfChat = new JTextField();
		tfChat.setBounds(109, 288, 407, 20);
		contentPane.add(tfChat);
		tfChat.setColumns(10);
		
		/*Sess�o de Action Handlers*/
		
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cliente.close();
					sa�da.close();
					leitor.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sa�da.println(tfChat.getText());
				taChat.append("Voc�: " + tfChat.getText() + "\n");
				taChat.append("(Enviada �s " + (new Date()) + ")\n");
				taChat.append("------------------------------------------------------------------------------------------------------------------\n");
			}
		});
	}
}
