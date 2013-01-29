import java.io.*;
import java.net.*;

public class TCPClient {
	public void runClient(String hostname, int port) {
		//Basic writing client
		String sentence = null;
		String echoedSentence;
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
		
		try{
			Socket clientSocket = new Socket(hostname, port);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println("Type done to quit");
			while(true){
				sentence = inFromUser.readLine();
				if (sentence.equals("done")){
					break;
				}
				outToServer.writeBytes(sentence + '\n');
				echoedSentence = inFromServer.readLine();
				System.out.println("Message From Server: " + echoedSentence);
			}
			clientSocket.close();
		}
		catch(Exception e){
			e.printStackTrace();
			}
	}
}