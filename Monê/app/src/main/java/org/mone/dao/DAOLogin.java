package org.mone.dao;

import java.sql.SQLException;

public interface DAOLogin {

   public boolean authenticate(String username, String password) throws SQLException;

}