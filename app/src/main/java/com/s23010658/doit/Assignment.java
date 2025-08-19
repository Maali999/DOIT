package com.s23010658.doit;

public class Assignment {
    public String subject;
    public String description;
    public String amount;
    public String deadline;
    public String pdfUri;

    public Assignment(String subject, String description, String amount, String deadline, String pdfUri) {
        this.subject = subject;
        this.description = description;
        this.amount = amount;
        this.deadline = deadline;
        this.pdfUri = pdfUri;
    }
}
