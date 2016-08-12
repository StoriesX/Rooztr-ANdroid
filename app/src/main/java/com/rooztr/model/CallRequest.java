package com.rooztr.model;

import java.util.Date;

public class CallRequest{
    private String _id;
    private String requester;
    private String requestee;
    private long start;
    private long end;
    private int status;
    private String message;
    private long sentAt;

    public CallRequest(){}

    public CallRequest(String _id, String requester, String requestee, long start, long end, int status, String message, long sentAt){
        this._id = _id;
        this.requester = requester;
        this.requestee = requestee;
        this.start = start;
        this.end = end;
        this.status = status;
        this.message = message;
        this.sentAt = sentAt;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getRequestee() {
        return requestee;
    }

    public void setRequestee(String requestee) {
        this.requestee = requestee;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public long getSentAt() {
        return sentAt;
    }

    public void setSentAt(long sentAt) { this.sentAt = sentAt; }
}