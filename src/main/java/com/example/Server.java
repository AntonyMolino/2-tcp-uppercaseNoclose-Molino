package com.example;

import java.io.*;
import java.net.*;

public class Server {

    //var
    ServerSocket server = null;
    Socket socketClient = null;
    int porta = 6789; // porta server
    String letto;

    DataOutputStream out; 
    DataInputStream in;

    

    public void Comunica()
    {
        for(;;){
            attendi();
        try {
                do{         
                    System.out.println();
                    System.out.println("[SERVER] - aspetto un messaggio");
                    letto = in.readLine();
                    System.out.println("[-] - messaggio ricevuto ");
                    String risposta = letto.toUpperCase();
                    System.out.println("[-] - rispondo con " + risposta);
                    out.writeBytes(risposta + "\n");
                }while(!letto.toLowerCase().equals("bye"));
        
                    socketClient.close(); //chiusura connessione
                    System.out.println( "[!] - CONNECTION CLOSED");    
            } catch (IOException e) {

            e.printStackTrace();
            }
        }   
    }


    public Socket attendi() {
        try {
            System.out.println( "[PROCEDURE] - inizializzazione");
            server = new ServerSocket(porta);
            System.out.println("[-] Server in ascolto sulla porta: " + porta);
            socketClient = server.accept();
            System.out.println("[-] connessione effettuata");
            server.close();
            
            in = new DataInputStream(socketClient.getInputStream());
            out = new DataOutputStream(socketClient.getOutputStream());
            
        } catch (IOException e) {
            e.printStackTrace();
        } 

        return socketClient;


    }

    
}
