package com.reto4_ciclo2;

import com.reto4_ciclo2.view.ReportesView;

public class App 
{
    public static void main(String[] args) {
        System.out.println("Requerimiento 1");
        var reportesView = new ReportesView();
        var banco = "Conavi";
        reportesView.proyectosFinanciadosPorBanco(banco);

        System.out.println("\nRequerimiento 2");
        var reportesView1 = new ReportesView();
        reportesView1.totalAdeudadoPorProyectosSuperioresALimite(50_000d);

        System.out.println("\nRequerimiento 3");
        var reportesView2 = new ReportesView();
        reportesView2.lideresQueMasGastan();
       
    }
}
