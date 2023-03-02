package org.academiadecodigo.wizards;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;


// do servidor para os clientes
public class Server {

    private String message;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    List<Client> clientList = new LinkedList<Client>();

    public static void main(String[] args) {

        // instanciar server socket
        Server server = new Server();
        // meter o servidor a ouvir
        server.getConnections();

    }

    //configurar o server
    public Server() {
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("server connected on port" + serverSocket.getLocalPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void getConnections() {
        while (!serverSocket.isClosed()) {
            try {
                System.out.println("server listening for clients");

                clientSocket = serverSocket.accept();
                Client client = new Client(clientSocket);


                //ver o executor
                // 0 nosso client  vai ser runnable
                Thread clientThread = new Thread(client);
                clientThread.start();


                System.out.println("client connected" + clientSocket);
                // podemos por a logica do serverworker
                // ver serverworker / thread

                for (Client x :clientList) {
                    System.out.println( x.getUsername());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void sendStream() {
        // connecta com um client
        PrintWriter out;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // envia uma mensagem de test
        message = "teste, cliente ligado";


        out.println(message);


    }

    /**
     * CLIENTE
     * <p>
     *                       todo  cliente
     */

    public class Client implements Runnable {
        Socket socket;
        String username;
        BufferedReader breader;
        Prompt prompt;

        // "configuracao"
        public Client(Socket socket) {
            this.socket = socket;
            try {
                prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        public void listen() {
            // prompt

            System.out.println("client waiting for input");
            try {
                breader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                breader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            // vamos roubar logica de ouvir algures
        }


        public void setUsername(String username) {
            this.username = username;
        }

        public void logIn() {

            StringInputScanner question1 = new StringInputScanner();
            question1.setMessage("What is your name?");

            username = prompt.getUserInput(question1);
            clientList.add(this);

        }

        public String getUsername() {
            return username;
        }

        @Override
        public void run() {
            logIn();

        }
    }

}