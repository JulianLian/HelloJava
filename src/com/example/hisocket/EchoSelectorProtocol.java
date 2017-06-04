package com.example.hisocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Julian on 15/8/8.
 */
public class EchoSelectorProtocol implements TCPProtocol {
    private int bufSize; // Size of I/O buffer

    public EchoSelectorProtocol(int bufSize) {
        this.bufSize = bufSize;
    }

    @Override
    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
        clientChannel.configureBlocking(false); // Must be nonblocking to register
        // Register the selector with new channel for read and attach byte buffer
        clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufSize));
    }

    @Override
    public void handleRead(SelectionKey key) throws IOException {
        // Client socket channel has pending data
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        long bytesRead = clientChannel.read(buffer);
        if (bytesRead == -1)
            clientChannel.close();
        else if (bytesRead > 0) {
            // Indicates via key that reading/writing are both of interest now.
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }

    @Override
    public void handleWrite(SelectionKey key) throws IOException {
        /*
         * Channel is available for writing, and key is valid (i.e. client channel no closed).
         */
        // Retrieve data read earlier
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.flip(); // Prepare buffer for writing
        SocketChannel clientChannel = (SocketChannel) key.channel();
        clientChannel.write(buffer);
        if (!buffer.hasRemaining()) { // Buffer completely written?
            // Nothing left, so no longer interested in writes
            key.interestOps(SelectionKey.OP_READ);
        }
        buffer.compact(); // Make room for more data to be read in
    }
}
