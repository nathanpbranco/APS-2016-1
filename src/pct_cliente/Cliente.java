package pct_cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Cliente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/*Sessão do console*/
		try {
			/*Conecta com o servidor*/
			Socket cliente = new Socket("192.168.25.194", 5000);
			System.out.println("Conectado ao servidor!");
			
			/*Lê e envia os dados do cliente para o servidor*/
			PrintStream saída = new PrintStream(cliente.getOutputStream());
			Scanner leitor = new Scanner(System.in);
			while (leitor.hasNextLine()) {
				saída.println(leitor.nextLine());
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Cliente() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
