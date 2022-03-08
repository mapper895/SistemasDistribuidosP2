package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	private static ObjectSkeleton objectSkeleton = new ObjectSkeleton();
	public static String myURL = "localhost";
	
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		InputStreamReader inputStreamReader = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		ServerSocket serverSocket = null;
		
		try {
			myURL = InetAddress.getLocalHost().getHostName();
		}catch (UnknownHostException e) {
			System.out.println("Host Desconocido: " + e.toString());
			System.exit(1);
		}
		
		try {
			serverSocket = new ServerSocket(1007);
		}catch(IOException e) {
			System.out.println(myURL + ": no puedo escuchar en el puerto: 1007, " + e.toString());
			System.exit(1);
		}
		System.out.println(myURL + ": Server esta escuchando en el puerto: 1007");
		
		try {
			socket = serverSocket.accept();
			
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
		
		
			String cantidad_a_pagar = bufferedReader.readLine();
			System.out.println(objectSkeleton.echo("La cantidad a pagar es: " + cantidad_a_pagar));
			bufferedWriter.write("Servidor <" + myURL + ">: Mensaje recibido");
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			String tarjeta = bufferedReader.readLine();
			System.out.println(objectSkeleton.echo("El numero de tarjeta es: " + tarjeta));
			bufferedWriter.write("Servidor <" + myURL + ">: Mensaje recibido");
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			String cvv = bufferedReader.readLine();
			System.out.println(objectSkeleton.echo("El cvv de la tarjeta es: " + cvv));
			bufferedWriter.write("Servidor <" + myURL + ">: Mensaje recibido");
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			int estatus = objectSkeleton.pago(cantidad_a_pagar, tarjeta, cvv);
			bufferedWriter.write(estatus);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			socket.close();
			inputStreamReader.close();
			outputStreamWriter.close();
			bufferedReader.close();
			bufferedWriter.close();
			serverSocket.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
