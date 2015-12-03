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
public class Gomoku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int player; 
        Scanner myScan = new Scanner(System.in);
        
        System.out.printf("Jumlah Player : ");
        player = myScan.nextInt();
        Game game = new Game(); 
        game.PlayGame(player);
    }
    
}
