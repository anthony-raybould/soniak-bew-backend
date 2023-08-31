package org.kainos.ea.cli;

public enum Role {
    SUPERUSER("superuser"),
    HR("hr"),
    MANAGEMENT("management"),
    SALES("sales");

    private final String databaseKey;

    Role(String databaseKey) {
        this.databaseKey = databaseKey;
    }

    @Override
    public String toString() {
        return databaseKey;
    }

}