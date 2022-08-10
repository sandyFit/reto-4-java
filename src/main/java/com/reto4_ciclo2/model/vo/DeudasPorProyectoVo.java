package com.reto4_ciclo2.model.vo;

public class DeudasPorProyectoVo {
    private Integer id;
    private Double valor;

    public Integer getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("%3d %,15.1f", this.id, this.valor);
    }
        
}
