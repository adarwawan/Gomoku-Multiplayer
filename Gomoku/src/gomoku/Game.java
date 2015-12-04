/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomoku;


/**
 *
 * @author mochamadtry
 */
public class Game {
    int AbsisOrdinatMove = 2; 
    
    public void PlayGame(int NumberofPlayer) {
        Board GameBoard = new Board(); //terbentuk board array 20x20
        GameBoard.setNumPlayer(NumberofPlayer);//set jumlah player
        boolean checkwin = false;//validasi kemenangan
        int win = 0;
        int validMove = 0;
        int[] move = new int[AbsisOrdinatMove];

        Player[] p = new Player[NumberofPlayer];
        
        for (int i = 0; i < NumberofPlayer; i++) {
            p[i] = new Player(i+1);
        }
        
        while (true) {
            for (int i = 0; i < NumberofPlayer; i++) {
                // perpindahan player
                GameBoard.printBoard();
                while (validMove != 1) {
                    move = p[i].getMove(GameBoard.getGameBoard(),NumberofPlayer);
                    validMove = GameBoard.makeMove((i+1), move[0], move[1]);
                }
                validMove = 0;

                // cek kemenangan 
                win = GameBoard.checkWin();
                if (win == (i+1)) {
                    GameBoard.printBoard();
                    System.out.println("Yeay Player "+(i+1)+" Menang");
                    System.out.println("Follow soundcloud.com/cintaparent :* ");
                    checkwin = true;
                    break;
                }
            }
            if (checkwin) {
                break;
            }
        }    
    }
}
