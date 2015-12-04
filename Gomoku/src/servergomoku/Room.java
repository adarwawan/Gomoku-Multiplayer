/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servergomoku;

import java.util.ArrayList;

/**
 *
 * @author adar
 */
public class Room {
    /* data */
    private int idRoom;
    private String roomname;
    private int playerMax;
    int AbsisOrdinatMove = 2;
    private ArrayList<User> listPlayer;
    private int playerAvailable;
    private int idTurn; // Lagi jalan siapa
    private int status; // Waiting = 0; Play = 1; End = 2;
    private Board board;
    private int idWin; // Initiate = 0;
    
    /* method */
    public Room (int _idRoom, String _roomname, int _playerMax, User _user) {
        idRoom = _idRoom;
        roomname = _roomname;
        playerMax = _playerMax;
        listPlayer = new ArrayList<>();
        _user.setIdRoom(idRoom);
        _user.setIdUserInRoom(listPlayer.size() + 1);
        listPlayer.add(_user);
        playerAvailable = 1;
    }
    
    public ArrayList<User> getPlayers(){
        return listPlayer;
    }
    
    public void addPlayer(User _user) {
        _user.setIdRoom(idRoom);
        _user.setIdUserInRoom(listPlayer.size() + 1);
        listPlayer.add(_user);
        playerAvailable++;
    }
    
    public void startGame () {
        board = new Board();
        board.setNumPlayer(playerMax);//set jumlah player
        status = 1;
        idTurn = 1;
    }
    
    public void userTurn (int[] move, int _idUserInRoom) {
        int validMove = 0;
        validMove = board.makeMove((_idUserInRoom), move[0], move[1]);
        int win = board.checkWin();
        if (win == (_idUserInRoom)) {
            status = 2;
            idWin = _idUserInRoom;
        }
    }
    
    public void pesanKemenangan (int _idUserInRoom) {
        if (idWin == _idUserInRoom) {
            System.out.println("Selamat Anda Menang!");
        }
        else {
            System.out.println("Pemenangnya adalah user ke- " + _idUserInRoom);
        }
    }
    
    public int getIdRoom () {
        return idRoom;
    }
    
    public String getRoomname () {
        return roomname;
    }
    
    public int getPlayerMax () {
        return playerMax;
    }
    
    public int getPlayerAvailable () {
        return playerAvailable;
    }
    
    public int getIdTurn () {
        return idTurn;
    }
    
    public int getStatus () {
        return status;
    }
    
    public void setIdRoom (int _idRoom) {
        idRoom = _idRoom;
    }
    
    public void setRoomname (String _roomname) {
        roomname = _roomname;
    }
    
    public void setPlayerMax (int _playerMax) {
        playerMax = _playerMax;
    }
    
    public void setPlayerAvailable (int _playerAvailable) {
        playerAvailable = _playerAvailable;
    }
    
    public void setIdTurn (int _idTurn) {
        idTurn = _idTurn;
    }
    
    public void setStatus (int _status) {
        status = _status;
    }
    
}