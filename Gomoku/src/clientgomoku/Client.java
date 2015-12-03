/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgomoku;

import java.io.*;
import java.net.*;
/**
 *
 * @author adar
 */
public class Client {
    /* data */
    private Socket cliSocket;
    private String addr;
    private int port;
    
    /* method */
    public Client(String _addr, int _port) throws IOException {
        addr = _addr;
        port = _port;
        
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        
        cliSocket = new Socket(addr, port);
        
        DataOutputStream outToServer = new DataOutputStream(cliSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(cliSocket.getInputStream()));

        String welcomeMessage = inFromServer.readLine();
        System.out.println(welcomeMessage);
        
        String sentence;
        
        do {
        // Data yang dikirim ke server
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
        
            // Data yang diterima dari server
            String srvSentence = inFromServer.readLine();
            System.out.println("Sudah kekirim pesan ini: " + srvSentence); 
        } while (!sentence.equals("Disconnect"));
        
        cliSocket.close();
    }
}