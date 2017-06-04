package com.example.hisocket;

/**
 * Created by Julian on 2015/7/10.
 */
public class VoteMsg {
    private boolean isInquiry; // true if inquiry; false if vote
    private boolean isResponse; // true if response from server
    private int candidateId; // in [0, 1000]
    private long voteCount;

    public static final int MAX_CANDIDATE_ID = 1000;

    public VoteMsg(boolean isResponse, boolean isInquiry, int candidateId, long voteCount)
        throws IllegalArgumentException {
        // check invariants
        if (voteCount != 0 && !isResponse) {
            throw new IllegalArgumentException("Request vote count must be zero");
        }

        if (candidateId < 0 || candidateId > MAX_CANDIDATE_ID) {
            throw new IllegalArgumentException("Bad Candidate ID: " + candidateId);
        }

        if (voteCount < 0) {
            throw new IllegalArgumentException("Total must be >= zero");
        }

        this.candidateId = candidateId;
        this.isResponse = isResponse;
        this.isInquiry = isInquiry;
        this.voteCount = voteCount;
    }

    public void setInquiry(boolean isInquiry) { this.isInquiry = isInquiry;}

    public void setResponse(boolean isResponse) { this.isResponse = isResponse;}

    public boolean isInquiry() { return isInquiry;}

    public boolean isResponse() { return isResponse;}

    public void setCandidateId(int candidateId) throws IllegalArgumentException {
        if (candidateId < 0 || candidateId > MAX_CANDIDATE_ID) {
            throw new IllegalArgumentException("Bad candidate ID: " + candidateId);
        }
        this.candidateId = candidateId;
    }

    public int getCandidateId() { return candidateId;}

    public void setVoteCount(long count) {
        if ((count != 0 && !isResponse) || count < 0) {
            throw new IllegalArgumentException("Bad vote count");
        }
        this.voteCount = count;
    }

    public long getVoteCount() { return voteCount;}

    public String toString() {
        String res = (isInquiry ? "inquiry" : "vote") + " for candidat " + candidateId;
        if (isResponse) {
            res = "response to " + res + " who now has " + voteCount + " vote(s)";
        }
        return res;
    }
}
