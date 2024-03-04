package model.dao;

import model.domain.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InterventoConclusoDAO {
    public static void registra_conclusione(int cod_intervento) {
        Connection conn;
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        try {
            conn = ConnectionFactory.getConnection();
        CallableStatement cs = conn.prepareCall("{call registra_conclusione(?,?)}");
        cs.setInt(1,cod_intervento);
        cs.setDate(2,date);
        cs.execute();
        }
        catch (SQLException e) {
            System.out.print("Non è stata registrata la conclusione "+e);
        }

    }

    public static InterventoConclusoEComponenti visualizza(int cod_intervento) {
        try{
            InterventoConcluso intervento =null;
            List<ComponenteTipo> componentes = new ArrayList<>();
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_intervento_concluso(?)}");
            cs.setInt(1,cod_intervento);
            if(cs.execute()) {
                ResultSet rs = cs.getResultSet();
                if (rs.next()) {
                     intervento = new InterventoConcluso(
                            rs.getInt(1),
                            rs.getDate(2),
                            rs.getString(3),
                            rs.getFloat(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDate(8));
                }
                if(cs.getMoreResults()){
                    rs=cs.getResultSet();
                    while (rs.next()) {
                        componentes.add(new ComponenteTipo(rs.getString(1),Tipo.valueOf(rs.getString(2).toUpperCase())));
                    }
                    return new InterventoConclusoEComponenti(intervento,componentes);
                }
                    }

    }catch (SQLException e){
            System.out.print("Errore: "+e+"\n");
        }
        return null;
    }
}
