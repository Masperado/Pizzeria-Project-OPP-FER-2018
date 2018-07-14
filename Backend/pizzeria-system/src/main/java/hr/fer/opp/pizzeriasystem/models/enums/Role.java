package hr.fer.opp.pizzeriasystem.models.enums;

import java.io.Serializable;

public enum Role implements Serializable {
    USER("USER"),
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
