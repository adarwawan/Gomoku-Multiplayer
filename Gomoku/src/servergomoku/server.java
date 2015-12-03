/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servergomoku;

import java.io.*;
import java.net.*;

/**
 *
 * @author adar
 */
public class server {
    /* data */
    private ServerSocket srvSocket;
    private int port;
    
    
    /* method */
    public server (int _port) throws IOException {
        port = _port;
        srvSocket = new ServerSocket(port);
        
        // Start Run
        while (true) {
            Socket connectionSocket = srvSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            
            // Data yang ditangkap dari client
            String clientSentence = inFromClient.readLine();
            
            System.out.println("Data dari Client: " + clientSentence);
            
            // Bales pesan ke client
            outToClient.writeBytes(clientSentence);
        }
    }
}
