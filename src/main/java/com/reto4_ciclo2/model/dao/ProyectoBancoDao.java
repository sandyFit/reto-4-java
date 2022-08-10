package com.reto4_ciclo2.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.reto4_ciclo2.model.vo.ProyectoBancoVo;
import com.reto4_ciclo2.util.JDBCUtilities;

public class ProyectoBancoDao {
    public List<ProyectoBancoVo> listar(String banco) throws SQLException {
        // La consulta es un conjunto de datos que pueden cambiar-dinámicos-
        // ArrayList permite manejar el comportamiento dinámico de los datos
        ArrayList<ProyectoBancoVo> respuesta = new ArrayList<ProyectoBancoVo>();
        Connection conn = JDBCUtilities.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String consulta = "SELECT p.ID_Proyecto ID, p.Constructora, p.Ciudad, "
                + "p.Clasificacion, t.Estrato, l.Nombre || ' ' || l.Primer_Apellido "
                + "|| ' ' || l.Segundo_Apellido LIDER "
                + "FROM Proyecto p "
                + "JOIN Tipo t ON (p.ID_Tipo = t.ID_Tipo) "
                + "JOIN Lider l ON (p.ID_Lider = l.ID_Lider) "
                + "WHERE p.Banco_Vinculado = ? "
                + "ORDER BY p.Fecha_Inicio DESC, p.Ciudad, p.Constructora ";

        try {
            pstm = conn.prepareStatement(consulta);
            pstm.setString(1, banco);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ProyectoBancoVo obj = new ProyectoBancoVo();
                obj.setId(rs.getInt("id"));
                obj.setConstructora(rs.getString("constructora"));
                obj.setCiudad(rs.getString("ciudad"));
                obj.setClasificacion(rs.getString("clasificacion"));
                obj.setEstrato(rs.getInt("estrato"));
                obj.setLider(rs.getString("lider"));
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
