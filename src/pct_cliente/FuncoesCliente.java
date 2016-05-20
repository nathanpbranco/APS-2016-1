package pct_cliente;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class FuncoesCliente extends Cliente{
	
	private static final long serialVersionUID = 1L;
	
	//<INICIO> Objetos globais
		
		Socket cliente;
		PrintStream saida;
		Scanner leitor;
		
		//<Fim> Objetos globais

	public FuncoesCliente() {
		leitor = new Scanner(System.in);
	}
	
	//<INICIO> Variáveis globais
	
	int gateway = 5000;
	String IP = "192.168.56.1";
	
	//<FIM> Variáveis globais
	
	
	//<INICIO> Funções

	public void connect() {
		try {
			cliente = new Socket(IP, gateway);
			System.out.println("Conectado ao servidor!");
			JOptionPane.showMessageDialog(null, "Conectado com o servidor !");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possivel realizar a conexão com o servidor.\nVerifique se o servidor está conectado ou se o IP escolhido está correto.");
			System.out.println("Não foi possivel realizar a conexão com o servidor.\nVerifique se o servidor está conectado ou se o IP escolhido está correto.");
			System.exit(0);
		}
	}
	
	public void desconnect() {
		try {
			cliente.close();
			JOptionPane.showMessageDialog(null, "Você se desconectou.");
			System.out.println("Conexão encerrada.");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao desconectar.");
		}
	}
	
	public void heartbeat() {
		
	}	
	
	public String getIP() {
		return String.valueOf(IP);
	}
	
	public String setIP(String _tfIP) {
		IP = _tfIP;
		return IP;
	}
	
	public String getGateway() {
		return String.valueOf(gateway);
	}
	
	public void setGateway(int _tfGateway) {
		gateway = _tfGateway;
	}

	public void sendMessageByConsole() {
		boolean conectionStatus = false;
		do {
			if (cliente != null) {
				conectionStatus = true;
			} else {
				conectionStatus = false;
			}
		} while (conectionStatus == false);
		
		try {
			saida = new PrintStream(cliente.getOutputStream());
			while (leitor.hasNextLine()) {
				saida.println(leitor.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessageByTextField(String _tfChat) {
		try {
			saida = new PrintStream(cliente.getOutputStream());
			saida.println(_tfChat);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exit() {
		try {
			saida.close();
			leitor.close();
			cliente.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//<Fim> Funções

}
