package org.kainos.ea.api;

import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.RegisterRequest;
import org.kainos.ea.client.FailedToGenerateTokenException;
import org.kainos.ea.client.FailedToLoginException;
import org.kainos.ea.client.FailedToRegisterException;
import org.kainos.ea.db.AuthDao;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class AuthService {
    private final AuthDao authDao = new AuthDao();

    public String login(LoginRequest login) throws FailedToLoginException, FailedToGenerateTokenException {
        try {
            int id = authDao.validLogin(login);
            if (id != -1) {
                try {
                    return authDao.generateToken(id);
                } catch (SQLException e) {
                    e.printStackTrace();

                    throw new FailedToGenerateTokenException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new FailedToLoginException();
    }

    public void register(RegisterRequest request) throws FailedToRegisterException {
        String salt = BCrypt.gensalt(10);
        String hashedPassword = BCrypt.hashpw(request.getPassword(), salt);
        try {
            authDao.register(request.getUsername(), hashedPassword, request.getRole());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedToRegisterException();
        }
    }
}
