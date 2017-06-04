package com.example.hisocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Julian on 15/7/19.
 */
public class TCPEchoServer {
    private static final int BUFSIZE = 32;

    public static void tcpServer(int serverPort) throws IOException {
        // Create a server socket to accept client connection requests
        ServerSocket servSock = new ServerSocket(serverPort);

        int recvMsgSize;   // Size of received message
        byte[] byteBuffer = new byte[BUFSIZE];  // Receive buffer

        for (;;) { // Run forever, accepting and servicing connections
            Socket clntSock = servSock.accept();     // Get client connection

            System.out.println("Handling client at " +
                    clntSock.getInetAddress().getHostAddress() + " on port " +
                    clntSock.getPort());

            InputStream in = clntSock.getInputStream();
            OutputStream out = clntSock.getOutputStream();

            // Receive until client closes connection, indicated by -1 return
            while ((recvMsgSize = in.read(byteBuffer)) != -1) {
                System.out.println("rcved: " + byteBuffer.toString());
                out.write(byteBuffer, 0, recvMsgSize);
            }

            clntSock.close();  // Close the socket.  We are done with this client!
        }
    /* NOT REACHED */
    }

    public static void main(String[] args) throws IOException {
        try {
            tcpServer(8003);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
