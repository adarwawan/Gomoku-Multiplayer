/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servergomoku;

import java.util.Scanner;

/**
 *
 * @author adar
 */
public class User {
    /* data */
    private int idUser;
    private int idRoom; // Initiate = 0; Room = idx.room + 1;
    private int idUserInRoom; // Initiate = 0;
    private int isWin; // Initiate = -1; Win = 1; Lose = 0;
    private String username;
    private int status; // Disconnect = -1; Connect = 0;
    
    /* method */
    public User(int _idUser, String _username) {
        idUser = _idUser;
        idRoom = 0;
        idUserInRoom = 0;
        isWin = -1;
        username = _username;
        status = 0;
    }
    
    public int getIdUser() {
        return idUser;
    }
    
    public int getIdRoom() {
        return idRoom;
    }
    
    public int getIdUserInRoom() {
        return idUserInRoom;
    }
    
    public int getIsWin() {
        return isWin;
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getStatus () {
        return status;
    }
    
    public void setIdUser(int _idUser) {
        idUser = _idUser;
    }
    
    public void setIdRoom (int _idRoom) {
        idRoom = _idRoom;
    }
    
    public void setIdUserInRoom (int _idUserInRoom) {
        idUserInRoom = _idUserInRoom;
    }
    
    public void setIsWin (int _isWin) {
        isWin = _isWin;
    }
    
    public void setUsername (String _username) {
        username = _username;
    }
    
    public void setStatus (int _status) {
        status = _status;
    }
    
    public int [] getMove(int board[][], int numPlayer){
        int[] playMove = new int[numPlayer];
        
        playMove = setMove(board, idUserInRoom);

        return playMove;
    }
    
    public int[] setMove(int move[][], int NumberofPlayer)
    {
        int[] saveMove = new int[2];
        Scanner myScan = new Scanner(System.in);

        System.out.printf("(Giliran Player : %d)\n",NumberofPlayer);
        System.out.printf("x: ");
        saveMove[0] = myScan.nextInt();
        System.out.printf("y: ");
        saveMove[1] = myScan.nextInt();
        return saveMove;
    }
}
