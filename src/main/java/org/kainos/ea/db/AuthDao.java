package org.kainos.ea.db;

import org.apache.commons.lang3.time.DateUtils;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.client.TokenExpiredException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Date;
import java.util.UUID;


public class AuthDao {
    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    public int validLogin(LoginRequest login) throws SQLException {
        Connection c = databaseConnector.getConnection();

        PreparedStatement ps = c.prepareStatement("SELECT UserID, Password FROM `Users` WHERE Username=?;");

        ps.setString(1, login.getUsername());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String hashedPassword = rs.getString("Password");
            String candidatePassword = login.getPassword();
            if (BCrypt.checkpw(candidatePassword, hashedPassword)) {
                return rs.getInt("UserID");
            }
        }

        return -1;
    }

    public String generateToken(int userId) throws SQLException {
        String token = UUID.randomUUID().toString();
        Connection c = databaseConnector.getConnection();
        Date expiry = DateUtils.addHours(new Date(), 8);

        PreparedStatement ps = c.prepareStatement("INSERT INTO Tokens (UserID, Token, Expiry) VALUES (?, ?, ?)");

        ps.setInt(1, userId);
        ps.setString(2, token);
        ps.setTimestamp(3, new java.sql.Timestamp(expiry.getTime()));

        ps.executeUpdate();

        return token;
    }

    public void register(String username, String password, Role role) throws SQLException {
        Connection c = databaseConnector.getConnection();

        PreparedStatement ps = c.prepareStatement("INSERT INTO `Users` (Username, Password, RoleID) VALUES (?, ?, ?)");

        ps.setString(1, username);
        ps.setString(2, password);
        ps.setInt(3, role.getRoleId());

        ps.executeUpdate();
    }

}
