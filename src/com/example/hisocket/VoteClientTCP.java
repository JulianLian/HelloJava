package com.example.hisocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Julian on 15/7/12.
 */
public class VoteClientTCP {
    public static final int CANDIDATEID = 888;

    protected static void voteClient(String destAddr, int destPort) throws IOException {

        Socket socket = new Socket(destAddr, destPort);
        OutputStream outputStream = socket.getOutputStream();

        // Change Bin to Test for a different framing strategy
        VoteMsgCoder coder = new VoteMsgBinCoder();
        // Change Length to Delim for a different encoding strategy
        Framer framer = new LengthFramer(socket.getInputStream());

        // Create an inquiry request (2nd arg = true)
        VoteMsg msg = new VoteMsg(false, true, CANDIDATEID, 0);
        byte[] encodedMsg = coder.toWire(msg);

        // Send request
        System.out.println("Sending inquiry (" + encodedMsg.length + " bytes): ");
        System.out.println(msg);
        framer.frameMsg(encodedMsg, outputStream);

        // Now send a vote
        msg.setInquiry(false);
        encodedMsg = coder.toWire(msg);
        System.out.println("Sending Vote (" + encodedMsg.length + " bytes): ");
        framer.frameMsg(encodedMsg, outputStream);

        // Receive inquiry response
        encodedMsg = framer.nextMsg();
        msg = coder.fromWire(encodedMsg);
        System.out.println("Received Response (" + encodedMsg.length + " bytes): ");
        System.out.println(msg);

        // Receive vote response
        msg = coder.fromWire(framer.nextMsg());
        System.out.println("Received Response (" + encodedMsg.length + " bytes): ");
        System.out.println(msg);

        socket.close();
    }

    public static void main(String[] args) {
        try {
            voteClient("127.0.0.1", 7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
