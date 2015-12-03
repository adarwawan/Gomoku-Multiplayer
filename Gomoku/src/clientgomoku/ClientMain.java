/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgomoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author adar
 */
public class ClientMain {
    public static void main(String argv[]) throws IOException {
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        System.out.print("Masukkan address: ");
        String addr = inFromUser.readLine();
        System.out.print("Masukkan port: ");
        int port = inFromUser.read();
        Client cli = new Client(addr, port);
    }
}
