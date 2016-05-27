package pct_servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FuncoesServidor extends Servidor {

	private static final long serialVersionUID = 1L;
	
		ServerSocket servidor;
		Socket cliente;
		Scanner leitorInput;
		PrintStream saida;
		
		int gateway = 5000;
		
	public FuncoesServidor() {} 

	public void openGateway() throws IOException {
		servidor = new ServerSocket(gateway); // Instancia uma porta para que posteriormente seja usada para conexão.
		System.out.println("A porta " + servidor.getLocalPort() + " foi aberta. \nAguardando conexão...");
	}

	public void listenConnection() throws IOException {
		cliente = servidor.accept(); // O metodo .accept() "pausa" o processamento do programa até que receba uma conexão nova.
		
		System.out.println("Nova conexão");
		System.out.println("Nome: " + cliente.getInetAddress().getHostName());
		System.out.println("IP: " + cliente.getInetAddress().getHostAddress());
		
		JOptionPane.showMessageDialog(null, "Um usuario se conectou.");
	}

	public String getHostName() {
		String name = null;
		name = cliente.getInetAddress().getHostName();
		return name ;
	}

	public String getHostIp() {
		String ip = null;
		ip = cliente.getInetAddress().getHostAddress();
		return ip;
	}
	
	public void errorFix() throws IOException { //Criei esse metodo para solucionar um problema que estava fazendo com que a primeira mensagem enviada do servidor para o cliente não aparecesse.
		saida = new PrintStream(cliente.getOutputStream());
		String errorFix = "errorFix";
		saida.println(errorFix);	
	}

	public void sendTextMessage(String _tfChat) throws IOException {
		saida = new PrintStream(cliente.getOutputStream());
		saida.println(_tfChat);
	}
	
	public String receveMessage() throws IOException {
		String msg = null;
		leitorInput = new Scanner(cliente.getInputStream());
		while (leitorInput.hasNextLine()) {
			msg = leitorInput.nextLine();
			System.out.println(cliente.getInetAddress().getHostName() + ": " + msg);
			return msg;
		}
		return null;
	}


}
