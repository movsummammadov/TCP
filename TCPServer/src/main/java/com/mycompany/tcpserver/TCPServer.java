package com.mycompany.tcpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Movsum Mammadov
 *
 */
public class TCPServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(6789);
        while (true) {
            Socket socket = server.accept();
            DataInputStream dataInput = new DataInputStream(socket.getInputStream());
            String s = readRequest(dataInput);
            System.out.println(s);
            byte[] data = FileUtility.read("C:\\Users\\movsu\\OneDrive\\Рабочий стол\\aa.jpg");
            DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
            writeResponse(dataOutput, data);
//            writeResponse(dataOutput, "jgb");
            System.out.println("Success");
            socket.close();
        }
    }

    public static String readRequest(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String msg = "";
        while (true) {
            String s = reader.readLine();
            if (s == null || s.trim().length() == 0) {
                break;
            } else {
                msg = msg + s + "\r\n";
            }
            System.out.println("Server request : " + s);
            System.out.println();
        }
        return msg;
    }

    public static void writeResponse(OutputStream out,byte[] s /*String s*/) throws Exception {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Server: YarServer/2009-09-09\r\n"
                + "Content-Type: image/JPG\r\n"
//                + "Content-Type: text/html\r\n" +
//                 "Content-Lenght: "+s.length()+"\r\n" 
                + "Content-Lenght: " + s.length + "\r\n"
                + "Connection: close\r\n\r\n";
        String result = response + s;
        out.write(result.getBytes());
        out.flush();
    }

    public static void readAsByte() throws Exception {
        ServerSocket serverSockets = new ServerSocket(6789);
        while (true) {
            Socket connectionServer = serverSockets.accept();
            DataInputStream dataStream = new DataInputStream(connectionServer.getInputStream());
            byte[] arr = message(dataStream);
            FileUtility.write("C:\\Users\\movsu\\OneDrive\\Рабочий стол\\aa.jpg", arr);
        }
    }

    public static byte[] message(DataInputStream din) throws Exception {
        int msglen = din.readInt();
        byte[] msg = new byte[msglen];
        din.readFully(msg);
        return msg;
    }

    public static void readAsString() throws Exception {
        ServerSocket serverSockets = new ServerSocket(6789);
        while (true) {
            Socket connectionServer = serverSockets.accept();
            InputStreamReader isr = new InputStreamReader(connectionServer.getInputStream());
            BufferedReader messageFromClient = new BufferedReader(isr);
            String clientSentence = messageFromClient.readLine();
            System.out.println("clientSentence=" + clientSentence);
        }
    }
}
