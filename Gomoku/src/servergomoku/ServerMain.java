/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servergomoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author adar
 */
public class ServerMain {
    public static void main(String argv[]) throws IOException {
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        System.out.print("Masukkan port: ");
        int port = inFromUser.read();
        Server srv = new Server(port);
    }
}