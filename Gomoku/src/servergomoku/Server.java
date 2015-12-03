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
public class Server {
    /* data */
    private ServerSocket srvSocket;
    private int port;
    private int clientNumber;
    
    /* method */
    public Server (int _port) throws IOException {
        clientNumber = 0;
        port = _port;
        srvSocket = new ServerSocket(port);
        
        // Start Run
        try {
            while (true) {
                new Capitalizer(srvSocket.accept(), clientNumber++).start();
            }
        } finally {
            srvSocket.close();
        }
    }
        
        /*
        while (true) {
            Socket connectionSocket = srvSocket.accept();
            System.out.println("Fanda");
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            
            // Data yang ditangkap dari client
            String clientSentence = inFromClient.readLine();
            
            System.out.println("Data dari Client: " + clientSentence);
            
            // Bales pesan ke client
            outToClient.writeBytes(clientSentence + '\n');
        } 
        */
    
    private static class Capitalizer extends Thread {
        private Socket socket;
        private int clientNumber;

        public Capitalizer(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);
        }
        
        public void run() {
            try {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                String clientSentence = in.readLine();
                System.out.println("Received: " + clientSentence);
                out.writeBytes(clientSentence + '\n');
            } catch (IOException e) {
                log("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + clientNumber + " closed");
            }
        }

        /**
         * Logs a simple message.  In this case we just write the
         * message to the server applications standard output.
         */
        private void log(String message) {
            System.out.println(message);
        }
    }
}
