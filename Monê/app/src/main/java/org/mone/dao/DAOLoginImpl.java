package org.mone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mone.database.Database;

public class DAOLoginImpl implements DAOLogin {

    @Override
    public boolean authenticate(String username, String password) throws SQLException {

        String sql = "SELECT * FROM Usuários WHERE username = ? AND password = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}

