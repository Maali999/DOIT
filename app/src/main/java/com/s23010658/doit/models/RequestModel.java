package com.s23010658.doit.models;

public class RequestModel {
    private String subject, deadline, amount;
    private int imageRes;

    public RequestModel(String subject, String deadline, String amount, int imageRes) {
        this.subject = subject;
        this.deadline = deadline;
        this.amount = amount;
        this.imageRes = imageRes;
    }

    public String getSubject() { return subject; }
    public String getDeadline() { return deadline; }
    public String getAmount() { return amount; }
    public int getImageRes() { return imageRes; }
}
