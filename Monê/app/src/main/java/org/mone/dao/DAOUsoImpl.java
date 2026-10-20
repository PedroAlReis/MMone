package org.mone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mone.database.Database;
import org.mone.model.Usuario;


public class DAOUsoImpl implements DAOUsu {

    @Override
    public Usuario getById(int id) throws SQLException {
        String sql = "SELECT id, name, username, role, status FROM Usuários WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int oid = rs.getInt("id");
                    String name = rs.getString("name");
                    String username = rs.getString("username");
                    String status = rs.getString("status");
                    int role = rs.getInt("role");

                    return new org.mone.model.Usuario(oid, name, username, null, status, role);
                }
            }
        }

        return null;
    }

    @Override
    public List<Usuario> getAll() throws SQLException {
        List<org.mone.model.Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, name, username, password, status, role FROM Usuários";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new org.mone.model.Usuario(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("status"),
                        rs.getInt("role")));
            }
        }

        return usuarios;
    }

    @Override
    public int insert(org.mone.model.Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuários (name, username, password, status, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getStatus());
            ps.setInt(5, usuario.getRole());

            return ps.executeUpdate();
        }
    }

    @Override
    public int update(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuários SET name = ?, username = ?, password = ?, status = ?, role = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getStatus());
            ps.setInt(5, usuario.getRole());
            ps.setInt(6, usuario.getId());

            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM Usuários WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public int delete(org.mone.model.Usuario usuario) throws SQLException {
        return delete(usuario.getId());
    }
}


