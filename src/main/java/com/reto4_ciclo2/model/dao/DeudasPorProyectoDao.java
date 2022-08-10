package com.reto4_ciclo2.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.reto4_ciclo2.model.vo.DeudasPorProyectoVo;
import com.reto4_ciclo2.util.JDBCUtilities;

public class DeudasPorProyectoDao {

    public List<DeudasPorProyectoVo> listar(Double limite) throws SQLException {
        ArrayList<DeudasPorProyectoVo> respuesta = new ArrayList<DeudasPorProyectoVo>();

        Connection conn = JDBCUtilities.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "SELECT p.ID_Proyecto ID, SUM(c.Cantidad * mc.Precio_Unidad) VALOR "
                + "FROM Proyecto p "
                + "JOIN Compra c ON (p.ID_Proyecto = c.ID_Proyecto) "
                + "JOIN MaterialConstruccion mc ON (c.ID_MaterialConstruccion = mc.ID_MaterialConstruccion) "
                + "WHERE c.Pagado = 'No' "
                + "GROUP BY p.ID_Proyecto "
                + "HAVING VALOR > ? "
                + "ORDER BY 2 DESC";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setDouble(1, limite);
            rs = pstm.executeQuery();
            while (rs.next()) {
                DeudasPorProyectoVo obj = new DeudasPorProyectoVo();
                obj.setId(rs.getInt("id"));
                obj.setValor(rs.getDouble("valor"));
                respuesta.add(obj);
                
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();

            }
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta;

    }
    
}
