/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servergomoku;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adar
 */
public class Server {
    /* data */
    private ServerSocket srvSocket;
    private int port;
    private int clientNumber;
    private ArrayList<User> userLogin = new ArrayList<>();
    private ArrayList<Room> roomLogin = new ArrayList<>();
    
    /* method */
    public Server (int _port) throws IOException {
        clientNumber = 0;
        port = _port;
        srvSocket = new ServerSocket(port);
        
        // Start Run
        try {
            while(true) {
                new Capitalizer(srvSocket.accept(), clientNumber++, this).start();
            }
        } finally {
            srvSocket.close();
        }
    }
    
    public void AddUser(User user) {
        userLogin.add(user);
        //System.out.println("Ayam" + userLogin.get(clientNumber-1).getUsername());
    }
        
    
    private static class Capitalizer extends Thread {
        private Socket socket;
        private int clientNumber;
        private Server srv;

        public Capitalizer(Socket socket, int clientNumber, Server _srv) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            this.srv = _srv;
            log("New connection with client# " + clientNumber + " at " + socket);
        }
        
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                
                User user = new User(clientNumber, in.readLine());
                srv.AddUser(user);
                
                // Kasih ucapan selamat datang dulu
                String welcomeMessage = "" + clientNumber;
                out.writeBytes(welcomeMessage + '\n');
                
                
                // Looping sampe mati
                String command;
                do {
                    command = in.readLine();
                    System.out.println(command);
                    
                    parsing(command);                    
                    
                    out.writeBytes("makasih" + '\n');
                    System.out.println(srv.roomLogin);
                    /*
                    clientSentence = in.readLine();
                    System.out.println(srv.userLogin.get(clientNumber).getUsername() + ": " + clientSentence);
                    out.writeBytes(clientSentence + '\n');
                    */
                } while (!command.equals("Disconnect"));
                
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

        private void parsing(String command) throws IOException {
            String[] splitSentence = command.split(" ");
            switch(splitSentence[0]) {
                case "createroom" :
                    int playerMax = Integer.parseInt(splitSentence[2]);
                    Room room = new Room(srv.roomLogin.size(),splitSentence[1],playerMax,srv.userLogin.get(clientNumber));
                    srv.roomLogin.add(room);
                    break;
                case "selectroom" :
                    int idxRoom = Integer.parseInt(splitSentence[1]);
                    srv.roomLogin.get(idxRoom).addPlayer(srv.userLogin.get(clientNumber));
                    break;
                case "start" :
                    srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).startGame();
                    break;
                case "userturn" :
                    int[] move = new int[2];
                    move[0] = Integer.parseInt(splitSentence[1]);
                    move[1] = Integer.parseInt(splitSentence[2]);
                    int idUserInRoom = srv.userLogin.get(clientNumber).getIdUserInRoom();
                    srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).userTurn(move, idUserInRoom);
                    break;
                case "update" :
                    break;
                case "listRoom" :
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    String clientSentence = "listroom " + srv.roomLogin.size();
                    for (int i = 0; i < srv.roomLogin.size(); i++) {
                        clientSentence = clientSentence + " " + srv.roomLogin.get(i).getRoomname();
                    }
                    out.writeBytes(clientSentence + '\n');  
                    break;
            }
        }
    }
}