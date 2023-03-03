package org.academiadecodigo.wizards;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;


// do servidor para os clientes
public class Server {

    private String message;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private GameLogic gameLogic;

    private String username;
    List<Client> clientList = new LinkedList<>();

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
            System.out.println("Server connected on port: " + serverSocket.getLocalPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }


    public void getConnections() {
        while (!serverSocket.isClosed()) {
            try {
                System.out.println("Server is accepting players...");

                clientSocket = serverSocket.accept();
                Client client = new Client(clientSocket);


                //ver o executor
                // 0 nosso client  vai ser runnable
                Thread clientThread = new Thread(client);
                clientThread.start();


                System.out.println("Player " + client.getUsername() + " is connected to port " + clientSocket.getPort());
                // podemos por a logica do serverworker
                // ver serverworker / thread


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
     * to do cliente
     */


    public class Client implements Runnable {
        Socket socket;
        String username;
        BufferedReader breader;
        Prompt prompt;


        GameLogic gameLogic;

        // "configuracao"
        public Client(Socket socket) throws IOException {

            this.socket = socket;
            try {
                prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        public String askQuestion(String message) {
            StringInputScanner question = new StringInputScanner();
            question.setMessage(message);
            return prompt.getUserInput(question);
        }

        public void eliminated() {
            StringInputScanner right = new StringInputScanner();
            // right.setMessage();

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

        public void logIn() throws IOException {

            StringInputScanner question1 = new StringInputScanner();
            question1.setMessage("\n" + "WRITE YOUR NICKNAME PLEASE \n");

            username = prompt.getUserInput(question1);
            clientList.add(this);

        }

        public String getUsername() {
            return username;
        }

        StringInputScanner question = new StringInputScanner();


        public void welcome() throws IOException {
            DataOutputStream out = null;
            try {
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            out.writeUTF("_  _   _      _____  _____  _____   ______  _       ___ __   __  _    _  _____  ______  ___  ______ ______   _  _ \n" +
                    "| || | | |    |  ___||_   _|/  ___|  | ___ \\| |     / _ \\\\ \\ / / | |  | ||_   _||___  / / _ \\ | ___ \\|  _  \\ | || |\n" +
                    "| || | | |    | |__    | |  \\ `--.   | |_/ /| |    / /_\\ \\\\ V /  | |  | |  | |     / / / /_\\ \\| |_/ /| | | | | || |\n" +
                    "| || | | |    |  __|   | |   `--. \\  |  __/ | |    |  _  | \\ /   | |/\\| |  | |    / /  |  _  ||    / | | | | | || |\n" +
                    "|_||_| | |____| |___   | |  /\\__/ /  | |    | |____| | | | | |   \\  /\\  / _| |_ ./ /___| | | || |\\ \\ | |/ /  |_||_|\n" +
                    "(_)(_) \\_____/\\____/   \\_/  \\____/   \\_|    \\_____/\\_| |_/ \\_/    \\/  \\/  \\___/ \\_____/\\_| |_/\\_| \\_||___/   (_)(_)\n" +
                    "                                                                                                                   \n" +
                    "\n" +
                    "\n" +
                    "                                      THE GAME WILL START WHEN ALL PLAYERS ARE ONLINE.\n" +
                    "\n" +
                    "                        A WORD WILL BE TYPED INTO THE TERMINAL AND YOU´LL HAVE TO WRITE IT IN REVERSE.\n" +
                    "\n" +
                    "                                             PRESS ENTER AFTER WRITING THE WORD.\n" +
                    "\n" +
                    "                                             YOU NEED TO BE AS QUICK AS POSSIBLE.\n" +
                    "\n" +
                    "                                      IF YOU ARE THE SLOWEST PLAYER, YOU WILL BE ELIMINATED.\n" +
                    "\n" +
                    "                                                     GOOD LUCK " + username.toUpperCase() + "!!");
        }


        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                logIn();
                welcome();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GameLogic game = new GameLogic(this);

        }

    }
}






