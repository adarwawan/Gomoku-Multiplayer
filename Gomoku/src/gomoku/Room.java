/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomoku;

import java.util.ArrayList;

/**
 *
 * @author i-ONe
 */
public class Room {
    private String name;
    private Board board = new Board();
    private ArrayList<Player> daftarPlayer = new ArrayList<Player>();
    public Room(String _name){
        name = _name;
    }
}
