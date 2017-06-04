package com.example.hisocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Julian on 15/7/12.
 */
public class VoteServerTCP {

    protected static void voteServer(int port) throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        // Change Bin to Text on both client and server for different encoding
        VoteMsgCoder coder = new VoteMsgBinCoder();
        VoteService service = new VoteService();

        while (true) {
            Socket clientSock = serverSocket.accept();
            System.out.println("Handling client at " + clientSock.getRemoteSocketAddress());
            // Change Length to Delim for a different framing strategy
            Framer framer = new LengthFramer(clientSock.getInputStream());
            try {
                byte[] req;
                while ((req = framer.nextMsg()) != null) {
                    System.out.println("Received message (" + req.length + " bytes)");
                    VoteMsg responseMsg = service.handleRequest(coder.fromWire(req));
                    framer.frameMsg(coder.toWire(responseMsg), clientSock.getOutputStream());
                }
            } catch (IOException ioe) {
                System.err.println("Error handling client: " + ioe.getMessage());
            } finally {
                System.out.println("Closing connection");
                clientSock.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            voteServer(7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
