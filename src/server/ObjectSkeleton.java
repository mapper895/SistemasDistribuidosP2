package server;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ObjectSkeleton {
	String myURL = "localhost";
	
	public ObjectSkeleton() {
		try {
			myURL = InetAddress.getLocalHost().getHostName();
		}catch (UnknownHostException e) {
			myURL = "localhost";
		}
	}
	
	public String echo(String input) {
		String msg = "Servidor <"+myURL+">: "+input;
		
		return msg;
	}
	
	
	public int pago(String cantidad_a_pagar, String tarjeta, String cvv) {
    	int estatus = 0;
    	int saldo = 30000;
    	
    	if(tarjeta.length() == 16) {
    		if(cvv.length() == 3) {
    			if(Integer.parseInt(cantidad_a_pagar) < saldo) {
    				estatus = 1;
    				System.out.println("La transaccion ha sido exitosa");
    			}
    			else {
    				System.out.println("El saldo no es suficiente");
    			}
    		}else {
    			System.out.println("El cvv no es valido");
    		}
    	}else {
    		System.out.println("Tarjeta no valida");
    	}
    	
    	System.out.println("Procesamiento del pago terminado");
    	
		return estatus;  	
    }

}
