package hr.fer.opp.pizzeriasystem.models.enums;

import java.io.Serializable;

public enum ReportTimeSpan implements Serializable {
    WEEK("WEEK"),
    MONTH("MONTH"),
    QUARTER_YEAR("QUARTER_YEAR"),
    HALF_YEAR("HALF_YEAR"),
    YEAR("YEAR");

    String timeSpan;

    ReportTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    public String getTimeSpan() {
        return timeSpan;
    }
}