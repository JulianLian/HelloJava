package com.example.hisocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Julian on 15/6/20.
 */
public class TCPEchoClient {
    protected static void tcpEcho(String server, byte[] data, int servPort) throws IOException {

        // Create socket that is connected to server on specified port
        Socket socket = new Socket(server, servPort);
        System.out.println("Connect to server...sending echo string");

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        outputStream.write(data);

        //Receive the same string back from server
        int totalBytesRcvd = 0;
        int bytesRcvd;
        while (totalBytesRcvd < data.length) {
            if ((bytesRcvd = inputStream.read(data, totalBytesRcvd,
                    data.length - totalBytesRcvd)) == -1) {
                throw new SocketException("Connection closed prematurely");
            }
            totalBytesRcvd += bytesRcvd;
        }

        System.out.println("Received: " + new String(data));

        socket.close();
    }

    public static void main(String[] args) throws IOException {
        try {
            tcpEcho("127.0.0.1", "Echo This".getBytes(), 7);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
