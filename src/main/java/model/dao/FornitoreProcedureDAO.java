package model.dao;

import exception.DAOException;
import model.domain.Fornitore;
import model.domain.FornitoreContatti;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornitoreProcedureDAO  {

    public static boolean aggiungi_fornitore(Fornitore fornitore) throws DAOException {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_fornitore(?,?,?,?,?)}");
            cs.setString(1, fornitore.p_iva());
            cs.setString(2, fornitore.città());
            cs.setString(3, fornitore.via());
            cs.setInt(4,fornitore.num());
            cs.setString(5, fornitore.nome());

            cs.executeQuery();
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage()+"\n");
            return false;
        }
        return true;
    }


    public static boolean elimina(String fornitore) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call elimina_fornitore(?)}");
            cs.setString(1, fornitore);
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            rs.next();
            if(rs.getInt(1)>0)return true;
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage()+"\n");

        }
        return false;
    }

    public static boolean aggiungi_componente(String fornitore, String componente, float prezzo) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_componente_fornitore(?,?,?)}");
            cs.setString(1, fornitore);
            cs.setString(2,componente);
            cs.setFloat(3,prezzo);

            cs.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage()+"\n");
        }
        return false;
    }

    public static boolean aggiorna_prezzo(String fornitore, String componente, float prezzo) {

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiorna_prezzo_comp_fornitore(?,?,?)}");
            cs.setString(1,componente);
            cs.setString(2, fornitore);
            cs.setFloat(3,prezzo);
            ResultSet rs = cs.executeQuery();
            rs.next();
            if(rs.getInt(1)>0)return true;
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage()+"\n");
        }
        return false;
    }

    public static FornitoreContatti visualizza(String pIva) {
        try {
            List<String> num = new ArrayList<>();
            List<String> email = new ArrayList<>();
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_fornitore(?)}");
            cs.setString(1,pIva);
            ResultSet rs = cs.executeQuery();
            if(!rs.next())return null;
            else{
                Fornitore f = new Fornitore(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5) );
                if(cs.getMoreResults()){
                    rs= cs.getResultSet();
                    while (rs.next())num.add(rs.getString(1));
                }
                if(cs.getMoreResults()){
                    rs= cs.getResultSet();
                    while (rs.next())email.add(rs.getString(1));
                }
                return new FornitoreContatti(f,num,email);
            }

        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage()+"\n");
        }
        return null;
    }
}
