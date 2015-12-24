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
                
                String _uname = in.readLine();
                boolean found = false;
                for(int i = 0; i <srv.userLogin.size(); i++){
                    System.out.println(i);
                    if (srv.userLogin.get(i).getUsername().equals(_uname)) found = true;
                }
                if (found){
                    out.writeBytes("loginFailed\n");
                }else{
                    User user = new User(clientNumber, _uname);
                    srv.AddUser(user);
                    out.writeBytes("loginSuccess " + clientNumber + "\n");
                }                
                
                // Looping sampe mati
                String command;
                do {
                    command = in.readLine();
                    System.out.println(command);
                    
                    parsing(command);                    
                    
                    //out.writeBytes("makasih" + '\n');
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
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    int playerMax = Integer.parseInt(splitSentence[2]);
                    Room room = new Room(srv.roomLogin.size(),splitSentence[1],playerMax,srv.userLogin.get(clientNumber));
                    boolean found = false;
                    for (int i = 0; i < srv.roomLogin.size(); i++){
                        if (srv.roomLogin.get(i).getRoomname().equals(splitSentence[1])) found = true;
                    }
                    if (found){
                        out.writeBytes("createFailed\n");
                    }else{
                        srv.roomLogin.add(room);
                        String clientSentence = "createSuccess";
                        int i = srv.roomLogin.size() - 1;
                        clientSentence = clientSentence + " " + srv.roomLogin.get(i).getRoomname();
                        clientSentence = clientSentence + " " + srv.roomLogin.get(i).getPlayerAvailable();
                        clientSentence = clientSentence + " " + srv.roomLogin.get(i).getPlayerMax();
                        clientSentence = clientSentence + " " + srv.roomLogin.get(i).getIdRoom();
                        out.writeBytes(clientSentence + '\n');
                    }
                    break;
                case "selectroom" :
                    out = new DataOutputStream(socket.getOutputStream());
                    int idxRoom = Integer.parseInt(splitSentence[1]);
                    String clientSentence = "";
                    if (srv.roomLogin.get(idxRoom).getPlayerMax() == srv.roomLogin.get(idxRoom).getPlayers().size()){
                        clientSentence = "selectroomFailed";
                    }else{
                        srv.roomLogin.get(idxRoom).addPlayer(srv.userLogin.get(clientNumber));
                        clientSentence = "selectroomSuccess";
                    }
                    out.writeBytes(clientSentence + '\n');
                    break;
                case "listplayer" :
                    out = new DataOutputStream(socket.getOutputStream());
                    idxRoom = Integer.parseInt(splitSentence[1]);;
                    
                    clientSentence = srv.roomLogin.get(idxRoom).getStatus() + "listplayer " + srv.roomLogin.get(idxRoom).getPlayers().size();
                    
                    for(int i = 0; i < srv.roomLogin.get(idxRoom).getPlayers().size(); i++){
                        clientSentence = clientSentence +  " " + srv.roomLogin.get(idxRoom).getPlayers().get(i).getUsername();
                    }
                    out.writeBytes(clientSentence + '\n');
                    break;
                case "play" :
                    out = new DataOutputStream(socket.getOutputStream());
                    if (srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getStatus() == 0){
                        srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).startGame();
                        clientSentence = "1play " + srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getPlayers().get(srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getIdTurn() - 1).getUsername();
                        for(int i = 0; i < 20; i++){
                            for (int j = 0; j < 20; j++){
                                clientSentence = clientSentence + " " + srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getBoard(i,j);
                            }
                        }
                        out.writeBytes(clientSentence + '\n');
                    }else if (srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getStatus() == 1){
                        clientSentence = "1play " + srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getPlayers().get(srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getIdTurn() - 1).getUsername();
                        for(int i = 0; i < 20; i++){
                            for (int j = 0; j < 20; j++){
                                clientSentence = clientSentence + " " + srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getBoard(i,j);
                            }
                        }
                        out.writeBytes(clientSentence + '\n');
                    }else{
                        clientSentence = "2play " + srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getPlayers().get(srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getWinner() - 1).getUsername();
                        for(int i = 0; i < 20; i++){
                            for (int j = 0; j < 20; j++){
                                clientSentence = clientSentence + " " + srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).getBoard(i,j);
                            }
                        }
                        out.writeBytes(clientSentence + '\n');
                    }
                    break;
                case "userturn" :
                    int[] move = new int[2];
                    move[0] = Integer.parseInt(splitSentence[1]);
                    move[1] = Integer.parseInt(splitSentence[2]);
                    int idUserInRoom = srv.userLogin.get(clientNumber).getIdUserInRoom();
                    srv.roomLogin.get(srv.userLogin.get(clientNumber).getIdRoom()).userTurn(move, idUserInRoom);
                    break;
                case "update" :
                case "listRoom" :
                    out = new DataOutputStream(socket.getOutputStream());
                    clientSentence = "listRoom " + srv.roomLogin.size();
                    for (int i = 0; i < srv.roomLogin.size(); i++){
                        clientSentence = clientSentence + " " + srv.roomLogin.get(i).getRoomname();
                        clientSentence = clientSentence + " " + srv.roomLogin.get(i).getPlayerAvailable();
                        clientSentence = clientSentence + " " + srv.roomLogin.get(i).getPlayerMax();
                        clientSentence = clientSentence + " " + srv.roomLogin.get(i).getIdRoom();
                    }
                    out.writeBytes(clientSentence + '\n');
                    break;
            }
        }
    }
}