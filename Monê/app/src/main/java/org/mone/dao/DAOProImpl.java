package org.mone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.mone.database.Database;
import org.mone.model.Processo;

public class DAOProImpl implements DAOPro {

    @Override
    public Processo getById(int id) {
        String sql = "SELECT id, dateProcess, bookName, type FROM processos WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int oid = rs.getInt("id");
                    LocalDate dateProcess = rs.getObject("dateProcess", LocalDate.class);
                    String bookName = rs.getString("bookName");
                    String type = rs.getString("type");

                    return new Processo(oid, dateProcess, bookName, type);

                }
                return null;
            }

        } catch (Exception e) {
            throw new IllegalStateException("Failed to achieve the process" + id, e);
        }

    }


    @Override
    public int delete(int id) {
        String sql = "DELETE FROM processos WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Failed to delete processo with id " + id, e);
        }
    }


    @Override
    public List<Processo> getAll() {
        String sql = "SELECT id, dateProcess, bookName, type FROM processos";
        List<Processo> processo = new java.util.ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                processo.add(new Processo(
                        rs.getInt("id"),
                        rs.getObject("dateProcess", LocalDate.class),
                        rs.getString("bookName"),
                        rs.getString("type")));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to retrieve processes", e);
        }

        return processo;
    }


    @Override
    public int update(Processo processo) throws SQLException {
        String sql ="UPDATE Processos SET dateProcess = ?, bookName = ?, type = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, processo.getBookName());
                ps.setObject(2, processo.getDateProcess());
                ps.setString(3, processo.getType());
                ps.setInt(4, processo.getId());

                return ps.executeUpdate();
             }

             }
    

    @Override
    public int insert(Processo processo) {
        String sql = "INSERT INTO Processos (id, dateProcess, bookName, type) = ?, ?, ?, ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, processo.getDateProcess());
            ps.setString(2, processo.getBookName());
            ps.setString(3, processo.getType());

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to insert processo", e);
        }
    }
}