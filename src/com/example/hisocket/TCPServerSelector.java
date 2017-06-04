package com.example.hisocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Created by Julian on 15/8/8.
 */
public class TCPServerSelector {
    private static final int BUFSIZE = 256;
    private static final int TIMEOUT = 300; // Wait timeout (milliseconds)

    public static void main(String[] args) throws IOException {
        int[] ports = {8003};
        tcpServerSelector(ports);
    }

    private static void tcpServerSelector(int[] ports) throws IOException {
        // Create a selector to multiplex listening sockets and connections
        Selector selector = Selector.open();

        // Create listening socket channel for each port and register selector
        for (int port : ports) {
            ServerSocketChannel listnChannel = ServerSocketChannel.open();
            listnChannel.socket().bind(new InetSocketAddress(port));
            listnChannel.configureBlocking(false);
            // Register selector with channel. The returned key is ignored
            listnChannel.register(selector, SelectionKey.OP_ACCEPT);
        }

        // Create a handler that will implement the protocol
        TCPProtocol protocol = new EchoSelectorProtocol(BUFSIZE);

        while (true) {
            // Wait for some channel to be ready (or timeout)
            if (selector.select(TIMEOUT) == 0) {
                System.out.println(".");
                continue;
            }

            // Get iterator on set of keys with I/O to process
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next(); // Key is bit mask
                // Server socket channel has pending connection requests?
                if (key.isAcceptable())
                    protocol.handleAccept(key);
                // Client socket channel has pending data?
                if (key.isReadable())
                    protocol.handleRead(key);
                // Client socket channel is available for waiting and
                // key is valid (i.e. channel not closed)?
                if (key.isValid())
                    protocol.handleWrite(key);
                keyIterator.remove(); // remove form set of selected keys
            }
        }
    }
}
