/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomoku;

import java.util.Scanner;

/**
 *
 * @author mochamadtry
 */
public class Player {
    private int idPlayer;
    
    //ctor dengan parameter
    public Player (int id) {
        idPlayer = id;
    }
    
    // Getter Setter
     public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }
    
    // fungsi dan prosedur lain
    public int [] getMove(int board[][], int numPlayer){
        int[] playMove = new int[numPlayer];
        
        playMove = setMove(board,idPlayer);

        return playMove;
    }
    
    public int[] setMove(int move[][],int NumberofPlayer)
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