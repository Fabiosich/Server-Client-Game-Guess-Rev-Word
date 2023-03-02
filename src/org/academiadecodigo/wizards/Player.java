package org.academiadecodigo.wizards;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.net.Socket;
import java.util.Scanner;

public class Player {
    Socket socket;
    String name;



    public String getName() {
        return name;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
