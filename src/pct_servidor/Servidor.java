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

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Servidor extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
	/*Sessão de objetos globais*/

	private static JPanel contentPane;
	private static JTextField tfNome;
	private static JTextField tfIP;
	private static JTextArea taChat;
	
	static FuncoesServidor funcao = new FuncoesServidor();

	/**
	 * Launch the application.
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
		
		/*Sessão do console*/
		
		/*Libera uma porta para que seja possivel uma conexão*/
		funcao.openGateway();
		
		/*A partir daqui, o processo é enterrompido pelo método accept().
		 * O programa está esperando um usuario se conectar*/
		funcao.listenConnection();
		
		tfNome.setText(funcao.getHostName());
		tfIP.setText(funcao.getHostIp());
		
		/*Mensagens do chat serão processadas abaixo*/
		taChat.append("Novas mensagens aparecerão aqui...\n");
		boolean infiniteLoop = true;
		while (infiniteLoop == true) {
			taChat.append(funcao.getHostName() + ": " + funcao.receveMessage() + "\n");
		}
	}

	public Servidor() { // Objetos gerados pelo swing
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
		
		taChat = new JTextArea();
		taChat.setBounds(10, 85, 608, 272);
		contentPane.add(taChat);

		//<INICIO> Action Handlers
		
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Encerra as conexões e o programa*/
				funcao.exit();
			}
		});
		
		//<FIM> Action Handlers
	}
}
