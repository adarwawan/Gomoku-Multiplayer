/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgomoku;

import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author adar
 */
public class Client {
    /* data */
    private Socket cliSocket;
    private String addr;
    private int port;
    private String username;
    private int idUser;
    private int idRoom;
    
    /* method */
    public Client(String _addr, int _port, String _username) throws IOException {
        addr = _addr;
        port = _port;
        username = _username;
        
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        
        cliSocket = new Socket(addr, port);
        
        DataOutputStream outToServer = new DataOutputStream(cliSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(cliSocket.getInputStream()));
        
        outToServer.writeBytes(username + '\n');
        
        String userId = inFromServer.readLine();
        idUser = Integer.parseInt(userId);
        System.out.println("User ID: " + userId);
        
        // Looping sampe mati
        String sentence;
        do {
            Timer timer  = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        outToServer.writeBytes("update" + '\n');
                        String srvSentence = inFromServer.readLine();
                        System.out.println("Sudah kekirim pesan ini: " + srvSentence); 
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, 1*1000, 1*1000);

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