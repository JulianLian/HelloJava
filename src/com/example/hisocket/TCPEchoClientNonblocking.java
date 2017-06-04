package com.example.hisocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Julian on 15/8/8.
 */
public class TCPEchoClientNonblocking {
    private static void tcpClientNonblocking(String serverIp, int port) throws IOException {
        // Create channel and set to nonblocking
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);

        // Initiate connection to server and repeatedly poll until complete
        if (!clientChannel.connect(new InetSocketAddress(serverIp, port))) {
            while (!clientChannel.finishConnect()) {
                System.out.println("."); // Do something else
            }
        }

        byte[] command = "hello".getBytes();
        ByteBuffer writeBuf = ByteBuffer.wrap(command);
        ByteBuffer readBuf = ByteBuffer.allocate(command.length);
        int totalBytesRcvd = 0;
        int bytesRcvd;
        while (totalBytesRcvd < command.length) {
            if (writeBuf.hasRemaining()) {
                clientChannel.write(writeBuf);
            }
            if ((bytesRcvd = clientChannel.read(readBuf)) == -1) {
                throw new SocketException("Connection closed prematurely");
            }
            totalBytesRcvd += bytesRcvd;
            System.out.println("."); // Do something else
        }

        System.out.println("Received: " + // convert to String per default charset
            new String(readBuf.array(), 0, totalBytesRcvd));
        clientChannel.close();
    }

    public static void main(String[] args) throws IOException {
        tcpClientNonblocking("127.0.0.1", 8003);
    }
}
