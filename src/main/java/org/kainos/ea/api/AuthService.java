package org.kainos.ea.api;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.RegisterRequest;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedToGenerateTokenException;
import org.kainos.ea.client.FailedToLoginException;
import org.kainos.ea.client.FailedToRegisterException;
import org.kainos.ea.db.AuthDao;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class AuthService {
    private final AuthDao authDao = new AuthDao();

    public ImmutablePair<User, String> login(LoginRequest login) throws FailedToLoginException, FailedToGenerateTokenException {
        try {
            User user = authDao.validLogin(login);
            if (user != null) {
                try {
                    return new ImmutablePair<>(user, authDao.generateToken(user.getUserId()));
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
