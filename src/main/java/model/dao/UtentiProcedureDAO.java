package model.dao;

import exception.DAOException;
import model.domain.Credentials;
import model.domain.Role;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UtentiProcedureDAO {


    public static void execute(Credentials utente) throws DAOException {

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_utente(?,?,?)}");
            cs.setString(1, utente.username());
            cs.setString(2, utente.password());
            int r =  utente.role().getId();
            String role = Role.fromInt(r).toString();
            cs.setString(3, role);

            cs.executeQuery();
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage());
        }


    }
}
