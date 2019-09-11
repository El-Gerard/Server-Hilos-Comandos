package ServerC;

//import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
import java.net.Socket;

/*Clase hilo que se ejecuta en cada solicitud de conexion*/
public class Hilos extends Thread {

	// Atributo que será un socket
	Socket conectado;

	// Constructor simple que recibe los datos del socket
	public Hilos(Socket soc) {
		this.conectado = soc;

	}

	// Ejecución del hilo con los datos que recibe del cliente
	public void run() {

		try {

			// VAriables con recepción de datos
			DataInputStream flujo_entrada = new DataInputStream(conectado.getInputStream());
			String Mensaje_texto = new String();
			// flujo_entrada.readUTF();
//			System.out.println("recibido de: " + conectado.getRemoteSocketAddress());

//			if (Mensaje_texto.equals("/exit")) {
//
//				conectado.close();
//				// condi = false;
//
			// }
			
			// Ciclo que mantiene la conexión cliente-server mientras no se escriba /exit
			// como salida
			while (!(Mensaje_texto = flujo_entrada.readUTF()).equals("/exit")) {

				// Muestra de que dirección vienen las solicitudes+el token del pool
				System.out.println("recibido de: " + conectado.getRemoteSocketAddress());

				/*
				 * Si el mensaje contiene un espacio, se asume que el cliente envió números y se
				 * envían para su calculo
				 */
				if (Mensaje_texto.indexOf(" ") == 1) {

					Calculator.Calcular(Mensaje_texto);
					Sender.Responder(conectado);
					// conectado.close();

					// Sino contiene espacio entonces será un comando
				} else {

					Calculator calcu = new Calculator(Mensaje_texto);
					calcu.Comandos();
					Sender.Responder(conectado);
					// conectado.close();
				}
			}
			// Cierre de conexion y aviso
			conectado.close();
			System.out.println("Conexion con: " + conectado.getRemoteSocketAddress() + " finalizada");
			System.out.println("Esperando....");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		/*
		 * BufferedReader leer = null;
		 * 
		 * 
		 * try {
		 * 
		 * leer = new BufferedReader(new InputStreamReader(conectado.getInputStream()));
		 * String msjRecibido = new String(); PrintWriter esServer = new
		 * PrintWriter(conectado.getOutputStream(),true);
		 * System.out.println("A la espera del mensaje....");
		 * 
		 * while(!(msjRecibido = leer.readLine()).equals("salir")) {
		 * 
		 * System.out.println("Guardando..."); System.out.println("recibido de: " +
		 * conectado.getRemoteSocketAddress() + " :" +msjRecibido ); }
		 * 
		 * esServer.println("recibido con exito :D"); conectado.close();
		 * System.out.println("Conectado con: " + conectado.getRemoteSocketAddress() +
		 * " Finalizada"); System.out.println("Esperando....");
		 * 
		 * 
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}
}