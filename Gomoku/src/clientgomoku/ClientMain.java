/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgomoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author adar
 */
public class ClientMain {
    public static void main(String argv[]) throws IOException {
        Scanner inFromUser = new Scanner(System.in);
        System.out.print("Masukkan username: ");
        String username = inFromUser.nextLine();
        System.out.print("Masukkan address: ");
        String addr = inFromUser.nextLine();
        System.out.print("Masukkan port: ");
        int port = inFromUser.nextInt();
        Client cli = new Client(addr, port, username);
    }
}