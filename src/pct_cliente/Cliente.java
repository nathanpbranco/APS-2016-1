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
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

	private static final long serialVersionUID = 1L;
	
	static FuncoesCliente funcao = new FuncoesCliente();
	
	static JPanel contentPane;
	static JTextField tfChat;
	static JLabel lblStatus;
	static JTextField tfIP;
	static JMenuBar menuBar;
	static JMenu mnArquivo;
	static JTextArea taChat;
	static JButton btnEnviar;
	static JLabel lblIP ;
	static JButton btnConectar;
	static JButton btnDesconectar;
	
	public static void main(String[] args) throws IOException {
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
		boolean infiniteLoop = true;
		while (infiniteLoop == true) {
			taChat.append("Servidor: " + funcao.receveTextMessage() + "\n");
			taChat.append("-----------------------------------------------------------\n");
		}
	}

	public Cliente() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 600);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 136, 184, 14);
		contentPane.add(lblStatus);
		lblStatus.setText("Status: Desconectado");
		
		taChat = new JTextArea();
		taChat.setEditable(false);
		taChat.setBounds(10, 161, 334, 333);
		contentPane.add(taChat);
		taChat.append("Mensagens aparecerão aqui.\n");
		
		btnEnviar = new JButton(">");
		btnEnviar.setBounds(298, 505, 46, 23);
		contentPane.add(btnEnviar);
		btnEnviar.setEnabled(false);
		
		tfChat = new JTextField();
		tfChat.setBounds(10, 506, 278, 20);
		contentPane.add(tfChat);
		tfChat.setColumns(10);
		
		lblIP = new JLabel("IP:");
		lblIP.setBounds(10, 11, 46, 14);
		contentPane.add(lblIP);
		
		tfIP = new JTextField();
		tfIP.setBounds(46, 8, 86, 20);
		contentPane.add(tfIP);
		tfIP.setColumns(10);
		tfIP.setText("192.168.56.1");
		tfIP.setEnabled(true);
		
		btnConectar = new JButton("Conectar");
		btnConectar.setBounds(10, 36, 122, 39);
		contentPane.add(btnConectar);
		btnConectar.setEnabled(true);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(10, 86, 122, 39);
		contentPane.add(btnDesconectar);
		btnDesconectar.setEnabled(false);
		
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					funcao.exit();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
		
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				funcao.setIP(tfIP.getText());
				try {
					funcao.connect();
				} catch (UnknownHostException e) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro desconhecido.");
					e.printStackTrace();
					System.exit(0);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Não foi possivel se conectar com o servidor.\nVerifique a rede local ou as configurações de IP/Gateway.");
					e.printStackTrace();
					System.exit(0);
				}
				lblStatus.setText("Status: Conectado");
				btnDesconectar.setEnabled(true);
				btnConectar.setEnabled(false);
				btnEnviar.setEnabled(true);
				tfIP.setEnabled(false);
				
			}
		});
		
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					funcao.desconnect();
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Um erro ocorreu ao se desconectar.\nEncerrando a aplicação.");
					System.exit(0);
				}
				btnDesconectar.setEnabled(false);
				btnConectar.setEnabled(true);
				btnEnviar.setEnabled(false);
				lblStatus.setText("Status: Desconectado");
				tfIP.setEnabled(true);
			}
		});
	}
}