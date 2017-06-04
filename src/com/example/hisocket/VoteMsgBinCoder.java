package com.example.hisocket;

import javax.imageio.metadata.IIOInvalidTreeException;
import java.io.*;

/**
 * Created by Julian on 15/7/11.
 * Wire Format
 *  0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |      Magic      |Flags|        ZERO           |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                 Candidate ID                  |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                                               |
 * |        Vote Count (only in response)          |
 * |                                               |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 */
public class VoteMsgBinCoder implements VoteMsgCoder {
    private static final int MAGIC = 0x5400;
    private static final int MAGIC_MASK = 0xfc00;
    private static final int MAGIC_SHIFT = 8;
    private static final int INQUIRE_FLAG = 0x0100;
    private static final int RESPONSE_FLG = 0x0200;
    private static final int MIN_WIRE_LENGTH = 4;
    private static final int MAX_WIRE_LENGTH = 16;

    @Override
    public byte[] toWire(VoteMsg msg) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteStream); // converts ints

        short magicAndFlags = MAGIC;
        if (msg.isInquiry()) {
            magicAndFlags |= INQUIRE_FLAG;
        }
        if (msg.isResponse()) {
            magicAndFlags |= RESPONSE_FLG;
        }
        out.writeShort(magicAndFlags);
        // We know the candidate ID will fit in a short: it's > 0 && < 1000
        out.writeShort((short) msg.getCandidateId());
        if (msg.isResponse()) {
            out.writeLong(msg.getVoteCount());
        }
        out.flush();
        byte[] data = byteStream.toByteArray();
        return data;
    }

    @Override
    public VoteMsg fromWire(byte[] input) throws IOException {
        // sanity checks
        if (input.length < MIN_WIRE_LENGTH) {
            throw new IOException("Runt message");
        }
        ByteArrayInputStream bs = new ByteArrayInputStream(input);
        DataInputStream in = new DataInputStream(bs);
        int magic = in.readShort();
        if ((magic & MAGIC_MASK) != MAGIC) {
            throw new IOException("Bad Magic #: " + ((magic & MAGIC_MASK) >> MAGIC_SHIFT));
        }
        boolean resp = ((magic & RESPONSE_FLG) != 0);
        boolean inq = ((magic & INQUIRE_FLAG) != 0);
        int candidateId = in.readShort();
        if (candidateId < 0 || candidateId > 1000) {
            throw new IOException("Bad candidate ID: " + candidateId);
        }
        long count = 0;
        if (resp) {
            count = in.readLong();
            if (count < 0) {
                throw new IOException("Bad vote count: " + count);
            }
        }
        // Ignore any extra bytes
        return new VoteMsg(resp, inq, candidateId, count);
    }
}
