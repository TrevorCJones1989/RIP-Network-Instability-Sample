import java.io.*;
import java.net.*;

public class TCPServer {
	ServerSocket serverSocket;
	public void runServer(int port) {
		//Basic echoing tcp server
		String clientSentence;
		String capitalizedSentence;
		try{
			serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
			System.out.println("Client Connected");
			
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
			
			while (true){			
				clientSentence = inFromClient.readLine();
				System.out.println("Received: " + clientSentence);
				capitalizedSentence = "Echo From Server: "+clientSentence + '\n';
				outToClient.writeBytes(capitalizedSentence);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}