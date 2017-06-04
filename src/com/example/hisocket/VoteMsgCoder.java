package com.example.hisocket;

import java.io.IOException;

/**
 * Created by Julian on 15/7/10.
 */
public interface VoteMsgCoder {
    byte[] toWire(VoteMsg msg) throws IOException;
    VoteMsg fromWire(byte[] input) throws IOException;
}
