package pct_servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FuncoesServidor extends Servidor{

	private static final long serialVersionUID = 1L;
	
	//<INICIO> Objetos globais
	
		ServerSocket servidor;
		Socket cliente;
		Scanner leitor;
		
		//<FIM> Objetos globais
		
	public FuncoesServidor() {} 

	//<INICIO> Variaveis globais
	
	int gateway = 5000;
	
	//<FIM> Variaveis globais
	
	//<INICIO> Funções
	
	public void openGateway() {
		try {
			servidor = new ServerSocket(gateway); // Instancia uma porta para que posteriormente seja usada para conexão.
			System.out.println("A porta " + servidor.getLocalPort() + " foi aberta. \nAguardando conexão...");
			JOptionPane.showMessageDialog(null, "A porta " + servidor.getLocalPort() + " foi aberta.\nAguardando conexão do usuário.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listenConnection() {
		try {
			cliente = servidor.accept(); // O metodo .accept() "pausa" o processamento do programa até que receba uma conexão nova.
			
			System.out.println("Nova conexão");
			System.out.println("Nome: " + cliente.getInetAddress().getLocalHost().getHostName());
			System.out.println("IP: " + cliente.getInetAddress().getHostAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getHostName() {
		String name = null;
			try {
				name = cliente.getInetAddress().getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		return name ;
	}

	public String getHostIp() {
		String ip = null;
		ip = cliente.getInetAddress().getHostAddress();
		return ip;
	}

	public String receveMessage() throws IOException {
		String msg = null;
				leitor = new Scanner(cliente.getInputStream());
				while (leitor.hasNextLine()) {
					msg = leitor.nextLine();
					System.out.println(cliente.getInetAddress().getHostName() + ": " + msg);
					JOptionPane.showMessageDialog(null, "Acabou de chegar um novo aviso !\n Enviada por " + cliente.getInetAddress().getLocalHost());
					return msg;
				}
		return null;
	}

	public void exit() {
		try {
			leitor.close();
			servidor.close();
			cliente.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//<FIM> Funções
}
