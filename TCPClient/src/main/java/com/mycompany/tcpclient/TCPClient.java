package com.mycompany.tcpclient;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Movsum Mammadov
 *
 */
public class TCPClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 6789);
        System.out.println("sending");
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        byte[] bytes = FileUtility.read("C:\\Users\\movsu\\OneDrive\\Рабочий стол\\aa.jpg");
        dos.writeInt(bytes.length);
        dos.write(bytes);
        System.out.println("send");
        socket.close();

    }

    public static void sendText() throws IOException {
        Socket socket = new Socket("localhost", 6789);
        OutputStream os = socket.getOutputStream();
        try (DataOutputStream dos = new DataOutputStream(os)) {
            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();
            dos.writeBytes(message);
            System.out.println("Message=" + message);
        }
    }
}
