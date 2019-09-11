package ServerC;

//import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//****Clase del servidor que recibe datos y los envía a Calculator

public class Receiver {
	//condicional del ciclo
	//static Boolean condi = true;

	public static void main(String[] args) {
		
		try {
			//se crear el server con el puerto
			ServerSocket servidor = new ServerSocket(9999);

			//se deja un ciclo abierto para que reciba peticiones constantes
			while (true) {
				
				//aceptación de la conexión
				Socket miSocket = servidor.accept();
				//Se crea una instancia de la clase hilo enviándole el socket
				Hilos conexiones = new Hilos(miSocket);
				//Se crea el hilo
				Thread hilo = new Thread(conexiones);
				//se inicia el hilo
				hilo.start();
				
/*				DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream());
				String Mensaje_texto = flujo_entrada.readUTF();
				
				if(Mensaje_texto.equals("/exit")) {
					
					miSocket.close();
					condi = false;
					
				}else if (Mensaje_texto.indexOf(" ") == 1) {
					
					Calculator.Calcular(Mensaje_texto);
					Sender.Responder(miSocket);
					miSocket.close();					

				} else {

					Calculator calcu = new Calculator(Mensaje_texto);
					calcu.Comandos();
					Sender.Responder(miSocket);
					miSocket.close();
				}*/
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
