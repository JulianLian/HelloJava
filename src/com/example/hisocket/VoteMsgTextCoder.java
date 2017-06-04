package com.example.hisocket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Julian on 15/7/10.
 * Wire Format "VOTEPROTO" <"v"|"i"> [<RESPFLAG>] <CANDIDATE> [<VOTECNT>]
 * Charset is fixed by the wire format.
 */
public class VoteMsgTextCoder implements VoteMsgCoder {

    private static final String MAGIC = "Voting";
    private static final String DELIMSTR = " ";
    private static final String INQSTR = "i";
    private static final String VOTESTR = "v";
    private static final String RESPONSESTR = "R";
    private static final String CHARSETNAME = "US-ASCII";

    @Override
    public byte[] toWire(VoteMsg msg) throws IOException {
        String msgString = MAGIC + DELIMSTR + (msg.isInquiry() ? INQSTR : VOTESTR)
                + DELIMSTR + (msg.isResponse() ? RESPONSESTR + DELIMSTR : "")
                + Integer.toString(msg.getCandidateId()) + DELIMSTR
                + Long.toString(msg.getVoteCount());
        byte data[] = msgString.getBytes(CHARSETNAME);
        return data;
    }

    @Override
    public VoteMsg fromWire(byte[] message) throws IOException {
        ByteArrayInputStream msgStream = new ByteArrayInputStream(message);
        Scanner scanner = new Scanner(new InputStreamReader(msgStream, CHARSETNAME));
        boolean isInquiry;
        boolean isResponse;
        int candidateId;
        long voteCount;
        String token;

        try {
            token = scanner.next();
            if (!token.equals(MAGIC)) {
                throw new IOException("Bad magic string: " + token);
            }
            token = scanner.next();
            if (token.equals(VOTESTR)) {
                isInquiry = false;
            } else if (!token.equals(INQSTR)) {
                throw new IOException("Bad vote/iinq indicator: " + token);
            } else {
                isInquiry = true;
            }

            token = scanner.next();
            if (token.equals(RESPONSESTR)) {
                isResponse = true;
                token = scanner.next();
            } else {
                isResponse = false;
            }

            // Current token is candidateId
            // Note: isResponse now valid
            candidateId = Integer.parseInt(token);
            if (isResponse) {
                token = scanner.next();
                voteCount = Long.parseLong(token);
            } else {
                voteCount = 0;
            }
        } catch (IOException ioe) {
            throw new IOException("Parse error...");
        }

        return new VoteMsg(isResponse, isInquiry, candidateId, voteCount);
    }
}
