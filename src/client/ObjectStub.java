package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ObjectStub {
	Socket socket = null;
	OutputStreamWriter outputStreamWriter = null;
	BufferedReader bufferedReader = null;
	BufferedWriter bufferedWriter = null;
	
	public void setHostAndPort(String host, int port) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		/*inputStreamReader = new InputStreamReader(socket.getInputStream());
		outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
		
		bufferedReader = new BufferedReader(inputStreamReader);
		bufferedWriter = new BufferedWriter(outputStreamWriter);*/
	}
	
	public void echo(String input, Socket clientSocket) throws IOException {
		outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
		bufferedWriter = new BufferedWriter(outputStreamWriter);
		
		bufferedWriter.write(input);
		bufferedWriter.newLine();
		bufferedWriter.flush();
	}
	
	
	public String pago(String tarjeta, String cvv) {  
		String errmsg = "";
		
    	if(tarjeta.length() != 16) {
    		errmsg = "Tarjeta no valida";
    	}else if(cvv.length() != 3) {
    		errmsg = "El cvv no es valido";
    	}
    	
    	return errmsg;	
    }

}
