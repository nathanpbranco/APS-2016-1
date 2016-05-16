package pct_cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FuncoesCliente extends Cliente{
	
	private static final long serialVersionUID = 1L;

	public FuncoesCliente() {
	}
	
	//<INICIO> Vari�veis globais
	
	int gateway = 5000;
	String IP = "192.168.56.1";
	
	//<FIM> Vari�veis globais
	
	
	//<INICIO> Objetos globais
	
	Socket cliente;
	PrintStream saida;
	Scanner leitor = new Scanner(System.in);
	
	//<Fim> Objetos globais
	
	
	//<INICIO> Fun��es

	public void conectar() {
		try {
			cliente = new Socket(IP, gateway);
			System.out.println("Conectado ao servidor!");
			JOptionPane.showMessageDialog(null, "Conectado com o servidor !");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "N�o foi possivel realizar a conex�o com o servidor.\nVerifique se o servidor est� conectado ou se o IP escolhido est� correto.");
			System.out.println("N�o foi possivel realizar a conex�o com o servidor.\nVerifique se o servidor est� conectado ou se o IP escolhido est� correto.");
		}
	}
	
	public void desconectar() {
		try {
			cliente.close();
			JOptionPane.showMessageDialog(null, "Voc� se desconectou.");
			System.out.println("Conex�o encerrada.");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao desconectar.");
		}
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

	public void enviarMensagemViaConsole() { //N�o esta sendo usado.
		try {
			saida = new PrintStream(cliente.getOutputStream());
			while (leitor.hasNextLine()) {
				saida.println(leitor.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro desconhecido");
		}
	}
	
	public void enviarMensagemViaTextField(String _tfChat) {
		try {
			saida = new PrintStream(cliente.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		saida.println(_tfChat);
	}
	
	public void encerrar() {
		try {
			saida.close();
			leitor.close();
			cliente.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//<Fim> Fun��es

}
