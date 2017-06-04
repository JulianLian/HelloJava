package com.example.hisocket;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * Created by Julian on 15/8/8.
 */
public interface TCPProtocol {
    void handleAccept(SelectionKey key) throws IOException;
    void handleRead(SelectionKey key) throws IOException;
    void handleWrite(SelectionKey key) throws IOException;
}
