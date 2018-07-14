package hr.fer.opp.pizzeriasystem.models;

import javax.persistence.Transient;

public class PizzeriaMessage {

    private String to;
    private String subject;
    private String text;

    private String token;

    public String getToken() {
        return token;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getTo() {
        return to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "PizzeriaMessage{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
