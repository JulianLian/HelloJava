package com.example.hisocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by Julian on 15/7/19.
 */
public class TcpServerGUI {
    private static final int BUFSIZE = 64;

    public static void TcpServerGUI(int serverPort) throws IOException {
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
                System.out.println("rcved: " + Arrays.toString(byteBuffer));
                out.write(byteBuffer, 0, recvMsgSize);

//                if ((recvMsgSize >= 2) &&
//                        (byteBuffer[recvMsgSize - 2] == 0x0d &&
//                                byteBuffer[recvMsgSize - 1] == 0x0a)) {
//                    System.out.println("return");
//                    break;
//                }
            }

            clntSock.close();  // Close the socket.  We are done with this client!
        }
    /* NOT REACHED */
    }

    public static void main(String[] args) throws IOException {
        try {
            TcpServerGUI(8003);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
