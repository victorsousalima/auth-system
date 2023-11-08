package br.com.victor.authsystem.entities.enums;

public enum TypeUser {

    ADMIN("admin"),
    USER("user");

    private String label;

    private TypeUser(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
