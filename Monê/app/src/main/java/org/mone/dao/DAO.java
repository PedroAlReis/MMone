package org.mone.dao;

import java.sql.SQLException;
import java.util.List;


public interface DAO<T> {

   List<T> getAll() throws SQLException;

    int insert(T t) throws SQLException;

    int update(T t) throws SQLException;

    int delete(int id) throws SQLException;

    T getById(int id) throws SQLException;


}
