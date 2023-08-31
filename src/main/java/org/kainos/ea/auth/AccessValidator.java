package org.kainos.ea.auth;

import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.AccessValidationException;
import org.kainos.ea.client.ForbiddenAccessException;
import org.kainos.ea.client.TokenExpiredException;
import org.kainos.ea.db.AuthDao;

import java.sql.SQLException;

public class AccessValidator {

    private static final AuthDao authDao = new AuthDao();

    public static void validateAccess(String token, Role... requiredRole) throws ForbiddenAccessException, AccessValidationException {
        try {
            User user = authDao.validateToken(token);
            if (user.getRole() == Role.SUPERUSER) {
                return;
            }
            for (Role role : requiredRole) {
                if (user.getRole() == role) {
                    return;
                }
            }
            throw new ForbiddenAccessException();
        } catch (TokenExpiredException e) {
            throw new ForbiddenAccessException();
        } catch (SQLException e) {
            e.printStackTrace();

            throw new AccessValidationException();
        }
    }

}
