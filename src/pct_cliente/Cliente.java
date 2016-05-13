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
	
	/*Sessão de objetos globais*/
	
	private JPanel contentPane;
	public JTextField tfChat;
	
	static Funcoes funcao = new Funcoes();
	
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
		
		/*Sessão do console*/
			
			/*Conecta com o servidor*/
			funcao.setIP(); //Precisa ser implementado.
			funcao.setGateway(); //Precisa ser Implementado.
			funcao.conectar();
			System.out.println("Conectado ao servidor!");
			JOptionPane.showMessageDialog(null, "Conectado com o servidor !");
			
			/*Lê e envia os dados do cliente para o servidor*/
			funcao.enviarMensagemViaConsole();
			
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
		
		/*Sessão de Action Handlers*/
		
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
	}
}
