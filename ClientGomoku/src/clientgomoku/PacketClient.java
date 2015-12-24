/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgomoku;

/**
 *
 * @author adar
 */

public class PacketClient {
    /*
        Pilihan packet : command <arg-1> <arg-2>
    */
    
    /* data */
    private String message;
    
    /* method */
    public PacketClient(String _message) {
        message = _message;
    }
}