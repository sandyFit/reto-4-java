package com.reto4_ciclo2.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.reto4_ciclo2.model.vo.ComprasDeLiderVo;
import com.reto4_ciclo2.util.JDBCUtilities;

public class ComprasDeLiderDao {
    public List<ComprasDeLiderVo> listar() throws SQLException {
        ArrayList<ComprasDeLiderVo> respuesta = new ArrayList<ComprasDeLiderVo>();
        Connection conn = JDBCUtilities.getConnection();
        Statement stm = null;
        ResultSet rs = null;

        String sql = "SELECT l.Nombre || ' ' || l.Primer_Apellido || ' ' || l.Segundo_Apellido LIDER, "
                    + "SUM(c.Cantidad * mc.Precio_Unidad) VALOR "
                    + "FROM Lider l "
                    + "JOIN Proyecto p ON (l.ID_Lider = p.ID_Lider) "
                    + "JOIN Compra c ON (p.ID_Proyecto = c.ID_Proyecto) "
                    + "JOIN MaterialConstruccion mc ON (c.ID_MaterialConstruccion = mc.ID_MaterialConstruccion) "
                    + "GROUP BY LIDER "
                    + "ORDER BY 2 DESC "
                    + "LIMIT 10";
                    

        try {        
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                ComprasDeLiderVo obj = new ComprasDeLiderVo();
                obj.setLider(rs.getString("lider"));
                obj.setValor(rs.getDouble("valor"));
                respuesta.add(obj);

            }

        } 
        finally {

            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();

            }
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta;

    }
    
}
