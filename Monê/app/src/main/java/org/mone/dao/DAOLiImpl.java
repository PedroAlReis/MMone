package org.mone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.mone.database.Database;
import org.mone.model.Livro;

public class DAOLiImpl implements DAOLi  {


    @Override 
    public Livro getById(int id) throws SQLException {
        String sql = "SELECT id, title, author, creationdate, editor, stock FROM Livros WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int oid = rs.getInt("id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    LocalDate creationdate = rs.getObject("creationdate", LocalDate.class);
                    String editor = rs.getString("editor");
                    int stock = rs.getInt("stock");

                    return new Livro(oid, title, author, creationdate, editor, stock);
                }
            }
        }

        return null;
    }

    @Override
    public List<Livro> getAll() throws SQLException {
        String sql = "SELECT id, title, author, creationdate, editor, stock FROM Livros";
        List<Livro> livros = new java.util.ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                livros.add(new Livro(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getObject("creationDate", LocalDate.class),
                        rs.getString("editor"),
                        rs.getInt("stock")));
            }
        }

        return livros;
    }

    @Override
    public int insert(Livro livros) throws SQLException {
        String sql = "INSERT INTO Livros (title, author, creationdate, editor, stock) VALUES (?,?,?,?,?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, livros.getTitle());
            ps.setString(2, livros.getAuthor());
            ps.setObject(3, livros.getCreationDate());
            ps.setString(4, livros.getEditor());
            ps.setInt(5, livros.getStock());

            return ps.executeUpdate();
        }
    }

    @Override
    public int update(Livro livros) throws SQLException {
        String sql = "UPDATE Livros SET title = ?, author = ?, creationdate = ?, editor = ?, stock = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, livros.getTitle());
            ps.setString(2, livros.getAuthor());
            ps.setObject(3, livros.getCreationDate());
            ps.setString(4, livros.getEditor());
            ps.setInt(5, livros.getStock());
            ps.setInt(6, livros.getId());

            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM Livros WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate();
        }
    }

    }


