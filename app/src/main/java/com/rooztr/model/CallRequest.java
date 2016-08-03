package com.rooztr.model;

import java.util.Date;

public class CallRequest{
    private String _id;
    private String requester;
    private String requestee;
    private Date start;
    private Date end;
    private int status;
    private String message;
    private Date sentAt;

    public CallRequest(){}

    public CallRequest(String _id, String requester, String requestee, Date start, Date end, int status, String message, Date sentAt){
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

}