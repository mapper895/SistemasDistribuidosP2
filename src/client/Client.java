package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private static ObjectStub objectStub;
	
	public static void main(String[] args) {
		Socket socket = null;
		InputStreamReader inputStreamReader = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		
		if(args.length < 2) {
			System.out.println("Para ejecutar, hazlo en este formato: Client <nombre o IP del Equipo> <numero de puerto>");
			System.exit(1);
		}
		
		objectStub = new ObjectStub();
		try {
			
			//objectStub.setHostAndPort(args[0], Integer.parseInt(args[1]));
			socket = new Socket(args[0], Integer.parseInt(args[1]));
			
			Scanner scanner = new Scanner(System.in);
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			
			bufferedReader = new BufferedReader(inputStreamReader);	
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			
			System.out.println("Introduzca la cantidad a pagar: ");
			String cantidad_a_pagar = scanner.nextLine();
			objectStub.echo(cantidad_a_pagar, socket);
			System.out.println(bufferedReader.readLine());
			
			System.out.println("Introduzca el numero de tarjeta: ");
			String tarjeta = scanner.nextLine();
			objectStub.echo(tarjeta, socket);
			System.out.println( bufferedReader.readLine());
			
			System.out.println("Introduzca el cvv de la tarjeta: ");
			String cvv = scanner.nextLine();
			objectStub.echo(cvv, socket);
			System.out.println(bufferedReader.readLine());
			
			System.out.println(objectStub.pago(tarjeta, cvv));
			
			int estatus = 0;
			estatus = bufferedReader.read();
			
			if(estatus == 1) {
				System.out.println("La transaccion ha sido exitosa");
				System.exit(1);
			}else {
				System.out.println("La transaccion NO fue exitosa");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			try {
				if(socket != null)
					socket.close();
				if(inputStreamReader != null)
					inputStreamReader.close();
				if(outputStreamWriter != null)
					outputStreamWriter.close();
				if(bufferedReader != null)
					bufferedReader.close();
				if(bufferedWriter != null)
					bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
