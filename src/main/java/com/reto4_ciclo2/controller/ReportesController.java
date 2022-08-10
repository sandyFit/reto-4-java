package com.reto4_ciclo2.controller;

import com.reto4_ciclo2.model.vo.ComprasDeLiderVo;
import com.reto4_ciclo2.model.vo.DeudasPorProyectoVo;
import com.reto4_ciclo2.model.vo.ProyectoBancoVo;

import java.sql.SQLException;
import java.util.List;

import com.reto4_ciclo2.model.dao.ComprasDeLiderDao;
import com.reto4_ciclo2.model.dao.DeudasPorProyectoDao;
import com.reto4_ciclo2.model.dao.ProyectoBancoDao;

public class ReportesController {
    private ProyectoBancoDao proyectoBancoDao;
    private DeudasPorProyectoDao deudasPorProyectoDao;
    private ComprasDeLiderDao comprasDeLiderDao;

    public ReportesController() {
        proyectoBancoDao = new ProyectoBancoDao();
        deudasPorProyectoDao = new DeudasPorProyectoDao();
        comprasDeLiderDao = new ComprasDeLiderDao();

    }

    public List<ProyectoBancoVo> listarProyectosPorBanco(String banco) throws SQLException {
        return proyectoBancoDao.listar(banco);

    }
    
    public List<DeudasPorProyectoVo> listarDeudasPorProyecto(Double limite) throws SQLException {
        return deudasPorProyectoDao.listar(limite);

    }
    
    public List<ComprasDeLiderVo> listarComprasDeLider() throws SQLException {
        return comprasDeLiderDao.listar();

    }
    
}
