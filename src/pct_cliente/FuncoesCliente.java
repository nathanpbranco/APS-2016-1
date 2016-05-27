package pct_cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FuncoesCliente extends Cliente{
	
	private static final long serialVersionUID = 1L;
	
	Socket cliente;
	PrintStream saida;
	Scanner leitor;
	Scanner leitorInput;
	
	String msg = null;
	String IP = "192.168.56.1";
	int gateway = 5000;
	boolean conectionStatus = false;
		
	public FuncoesCliente() {
		leitor = new Scanner(System.in);
	}
	
	public String receveTextMessage() throws IOException {
		do {
			System.out.println("Aguardando conexão...\n"); //REMOVER
			if (cliente != null) {
				conectionStatus = true;
			} else {
				conectionStatus = false;
			}
		} while (conectionStatus == false);
		
		leitorInput = new Scanner(cliente.getInputStream());
		while (leitorInput.hasNextLine()) {
			msg = leitorInput.nextLine();
			System.out.println("Servidor: " + msg);
			return msg;
		}
		return null;
	}
	
	public void sendTextMessage(String _tfChat) throws IOException {
		saida = new PrintStream(cliente.getOutputStream());
		saida.println(_tfChat);
	}
	
	public String getIP() {
		return String.valueOf(IP);
	}
	
	public String setIP(String _tfIP) {
		IP = _tfIP;
		return IP;
	}
	
	public void connect() throws UnknownHostException, IOException {
		cliente = new Socket(IP, gateway);
		System.out.println("Conectado ao servidor!");
		JOptionPane.showMessageDialog(null, "Conectado com o servidor !");
	}
	
}