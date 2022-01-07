/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;

/**
 *
 * @author Rant AA
 */
public class cuenta implements Serializable{
    private int numCuenta,idcliente,idtipocuenta;
    private String fechaCreacion,nombreC,desC;
    private double saldo;

    public String getDesC() {
        return desC;
    }

    public void setDesC(String desC) {
        this.desC = desC;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }


    public cuenta(int numCuenta, int idcliente, int idtipocuenta, String fechaCreacion, double saldo) {
        this.numCuenta = numCuenta;
        this.idcliente = idcliente;
        this.idtipocuenta = idtipocuenta;
        this.fechaCreacion = fechaCreacion;
        this.saldo = saldo;
    }

    public cuenta() {
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdtipocuenta() {
        return idtipocuenta;
    }

    public void setIdtipocuenta(int idtipocuenta) {
        this.idtipocuenta = idtipocuenta;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
}
