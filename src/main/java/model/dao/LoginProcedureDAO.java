package model.dao;

import model.domain.Credentials;
import model.domain.Role;
import exception.DAOException;
import java.sql.*;
public class LoginProcedureDAO implements GenericProcedureDAO {

    @Override
    public Credentials execute(Object... params) throws DAOException {
        String username = (String) params[0];
        String password = (String) params[1];
        int role;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call login(?,?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.NUMERIC);

            cs.executeQuery();
            role = cs.getInt(3);
        } catch(SQLException e) {
            throw new DAOException("Login error: " + e.getMessage());
        }


        return new Credentials(username, password, Role.fromInt(role));
    }


}
