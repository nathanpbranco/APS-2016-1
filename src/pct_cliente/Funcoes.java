package pct_cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Funcoes{
	
	public Funcoes() {
	}
	
	//<INICIO> Variáveis globais
	int gateway = 5000;
	String IP = "192.168.56.1";
	//<FIM> Variáveis globais
	
	//<INICIO> Objetos globais
	Socket cliente;
	PrintStream saida;
	Scanner leitor;
	//<Fim> Objetos globais
	
	//<INICIO> Funções

	public void conectar() {
		try {
			cliente = new Socket(IP, gateway);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possivel realizar a conexão com o servidor.\nVerifique se o servidor está conectado ou se o IP escolhido está correto.");
			System.out.println("Não foi possivel realizar a conexão com o servidor.\nVerifique se o servidor está conectado ou se o IP escolhido está correto.");
		}
	}

	public void setIP() {
		// TODO Auto-generated method stub
		
	}

	public void setGateway() {
		// TODO Auto-generated method stub
		
	}

	public void enviarMensagemViaConsole() {
		try {
			leitor = new Scanner(System.in);
			saida = new PrintStream(cliente.getOutputStream());
			while (leitor.hasNextLine()) {
				saida.println(leitor.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMensagemViaTextField(String _fromtfChat) {
		saida.println(_fromtfChat);
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

}
