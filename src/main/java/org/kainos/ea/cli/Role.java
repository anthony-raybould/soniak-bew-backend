package org.kainos.ea.cli;

public enum Role {
    SUPERUSER(0),
    HR(1),
    MANAGEMENT(2),
    SALES(3);

    private final int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public static Role fromId(int id) {
        switch (id) {
            case 0:
                return SUPERUSER;
            case 1:
                return HR;
            case 2:
                return MANAGEMENT;
            case 3:
                return SALES;
        }

        return null;
    }
}