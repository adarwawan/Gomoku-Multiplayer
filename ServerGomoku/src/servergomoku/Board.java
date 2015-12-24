/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servergomoku;

/**
 *
 * @author adar
 */
public class Board {
    private int SizeofBoard = 20;
    private int[][] GameBoard;
    private int NumberofPlayer;
    
    public Board () {
        init();
    }

    public void init() {
        GameBoard = new int[SizeofBoard][SizeofBoard];
    }
    
    //Getter Setter
    public int getBoardSize() {
        return SizeofBoard;
    }

    public void setBoardSize(int boardSize) {
        this.SizeofBoard = boardSize;
    }

    public int[][] getGameBoard() {
        return GameBoard;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.GameBoard = gameBoard;
    }
    
    public int getBoardContent(int x, int y){
        return this.GameBoard[x][y];
    }

    public int getNumPlayer() {
        return NumberofPlayer;
    }

    public void setNumPlayer(int NumberofPlayer) {
        this.NumberofPlayer = NumberofPlayer;
    }
    
    public int makeMove(int player, int x, int y) {
        try {
            if (GameBoard[x][y] != 0) {
                return 0;
            } else {
                GameBoard[x][y] = player;

                return 1;
            }
        } catch (Exception e) {
            return 0;
        }
    }
    
    public void printBoard(){
        //Kamus Lokal
        int i,j; 
        //Algoritma
        /*Baris Satu*/
        //init();
        for (i = 0; i<SizeofBoard+2; i++){
            if(i > 0 && i <= 20)
                System.out.printf(" %d ", (i-1)%10); 
            else
                System.out.printf("   ");
        }
        System.out.printf("\n"); 
        
        for (i = 0; i < SizeofBoard; i++) {
            for (j = 0; j < SizeofBoard; j++) {
                if (j == 0) {
                    System.out.printf(" %d ", i%10);
                }
                if (GameBoard[j][i] == 0) {
                    System.out.printf(" * ");
                } else {
                    System.out.printf(" %d ", GameBoard[j][i]);
                }

                if (j == SizeofBoard - 1) {
                    System.out.printf(" %d\n", i%10);
                }
            }
        }
        for (i = 0; i < SizeofBoard + 2; i++) {
            if (i > 0 && i <=20)
                System.out.printf(" %d ", (i-1)%10);
            else
                System.out.printf("   ");
        }
        System.out.printf("\n");
    
    }
    
    public int checkWin() {
        int[] playerCount = new int[NumberofPlayer];
        
        // cek kemenangan vertikal
        for (int i = 0; i < SizeofBoard; i++) {
            for (int j = 0; j < NumberofPlayer; j++) {
                playerCount[j] = 0;
            }
            for (int j = 0; j < SizeofBoard; j++) {
                if (GameBoard[i][j] == 0) {
                    for (int k = 0; k < NumberofPlayer; k++) {
                        playerCount[k] = 0;
                    }
                } else {
                    for (int k = 0; k < NumberofPlayer; k++) {
                        if (GameBoard[i][j] == (k+1)) {
                            playerCount[k] += 1;
                        } else {
                            playerCount[k] = 0;
                        }
                    }
                }
                for (int k = 0; k < NumberofPlayer; k++) {
                    if (playerCount[k] == 5) {
                        return (k+1);
                    }
                }
            }
        }
        
        // Cek kemenangan horizontal
        for (int i = 0; i < SizeofBoard; i++) {
            for (int j = 0; j < NumberofPlayer; j++) {
                playerCount[j] = 0;
            }
            for (int j = 0; j < SizeofBoard; j++) {
                if (GameBoard[j][i] == 0) {
                    for (int k = 0; k < NumberofPlayer; k++) {
                        playerCount[k] = 0;
                    }
                } else {
                    for (int k = 0; k < NumberofPlayer; k++) {
                        if (GameBoard[j][i] == (k+1)) {
                            playerCount[k] += 1;
                        } else {
                            playerCount[k] = 0;
                        }
                    }
                }
                
                for (int k = 0; k < NumberofPlayer; k++) {
                    if (playerCount[k] == 5) {
                        return (k+1);
                    }
                }
            }
        }

        // cek kemenangan diagonal 1 (sisi kiri atas ke kanan bawah)
        for (int i = 0; i < SizeofBoard - 5; i++) {
            for (int j = 0; j < SizeofBoard - 5; j++) {
                for (int k = 0; k < NumberofPlayer; k++) {
                    playerCount[k] = 0;
                }
                for (int k = i, m = j; k <= i + 5 && m <= j + 5; m++, k++) {
                    if (GameBoard[k][m] == 0) {
                        for (int n = 0; n < NumberofPlayer; n++) {
                            playerCount[n] = 0;
                        }
                    } else {
                        for (int n = 0; n < NumberofPlayer; n++) {
                            if (GameBoard[k][m] == (n+1)) {
                                playerCount[n] += 1;
                            } else {
                                playerCount[n] = 0;
                            }
                        }
                    }
                    
                    for (int n = 0; n < NumberofPlayer; n++) {
                        if (playerCount[n] == 5) {
                            return (n+1);
                        }
                    }
                }
            }
        }

        // diagonal 2 (sisi kanan atas ke sisi kiwi bawah
        for (int i = 0; i < SizeofBoard - 5; i++) {
            for (int j = 0; j < SizeofBoard - 5; j++) {
                for (int k = 0; k < NumberofPlayer; k++) {
                    playerCount[k] = 0;
                }
                for (int k = i, m = j + 5; k <= i + 5 && m >= j - 5; m--, k++) {
                    if (GameBoard[k][m] == 0) {
                        for (int n = 0; n < NumberofPlayer; n++) {
                            playerCount[n] = 0;
                        }
                    } else {
                        for (int n = 0; n < NumberofPlayer; n++) {
                            if (GameBoard[k][m] == (n+1)) {
                                playerCount[n] += 1;
                            } else {
                                playerCount[n] = 0;
                            }
                        }
                    }
                    
                    for (int n = 0; n < NumberofPlayer; n++) {
                        if (playerCount[n] == 5) {
                            return (n+1);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
