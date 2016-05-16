package pct_servidor;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FuncoesServidor extends Servidor{

	private static final long serialVersionUID = 1L;
	
	public FuncoesServidor() {}

	//<INICIO> Objetos globais
	
	Scanner leitor;
	ServerSocket servidor;
	Socket cliente;
	
	//<FIM> Objetos globais
	
	//<INICIO> Variaveis globais
	
	int gateway = 5000;
	
	//<FIM> Variaveis globais
	
	//<INICIO> Funções
	
	public void openGateway() {
		try {
			servidor = new ServerSocket(gateway); // Instancia uma porta para que posteriormente seja usada para conexão.
			System.out.println("A porta " + gateway + " foi aberta. \nAguardando conexão...");
			JOptionPane.showMessageDialog(null, "A porta " + gateway + " foi aberta.\nAguardando conexão do usuário.");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return name ;
	}

	public String getHostIp() {
		String ip = null;
		ip = cliente.getInetAddress().getHostAddress();
		return ip;
	}

	public void receveMessageOnConsole() {
		leitor = new Scanner(System.in);
		while (leitor.hasNextLine()) {
			System.out.println(cliente.getInetAddress().getHostName() + ": " + leitor.nextLine());
			try {
				JOptionPane.showMessageDialog(null, "Acabou de chegar um novo aviso !\n Enviada por " + cliente.getInetAddress().getLocalHost() + "\n Confira no console.");
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}

	public void exit() {
		try {
			leitor.close();
			servidor.close();
			cliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	//<FIM> Funções
}
