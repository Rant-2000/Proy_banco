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
public class transaccion implements Serializable{
    private int idtran,idclie,numcuenta,tipocuenta;
    private String nom,fechaTrans,tipoTrans,des;
    private double monto;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public transaccion(int idtran, int idclie, int numcuenta, int tipocuenta, String nom, String fechaTrans, String tipoTrans, double monto) {
        this.idtran = idtran;
        this.idclie = idclie;
        this.numcuenta = numcuenta;
        this.tipocuenta = tipocuenta;
        this.nom = nom;
        this.fechaTrans = fechaTrans;
        this.tipoTrans = tipoTrans;
        this.monto = monto;
    }

    public transaccion() {
    }

    public int getIdtran() {
        return idtran;
    }

    public void setIdtran(int idtran) {
        this.idtran = idtran;
    }

    public int getIdclie() {
        return idclie;
    }

    public void setIdclie(int idclie) {
        this.idclie = idclie;
    }

    public int getNumcuenta() {
        return numcuenta;
    }

    public void setNumcuenta(int numcuenta) {
        this.numcuenta = numcuenta;
    }

    public int getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(int tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFechaTrans() {
        return fechaTrans;
    }

    public void setFechaTrans(String fechaTrans) {
        this.fechaTrans = fechaTrans;
    }

    public String getTipoTrans() {
        return tipoTrans;
    }

    public void setTipoTrans(String tipoTrans) {
        this.tipoTrans = tipoTrans;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
}
