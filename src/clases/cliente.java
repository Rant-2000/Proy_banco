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
public class cliente implements Serializable{
    private String nom,app,apm,sex,dir,ciudad,col,correo,tipoc,fechEx;
    private int idcliente;
    protected int numc;
    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public String getFechEx() {
        return fechEx;
    }

    public void setFechEx(String fechEx) {
        this.fechEx = fechEx;
    }

    public String getTipoc() {
        return tipoc;
    }

    public void setTipoc(String tipoc) {
        this.tipoc = tipoc;
    }


    public int getNumc() {
        return numc;
    }

    public void setNumc(int numc) {
        this.numc = numc;
    }
    public cliente(String nom, String app, String apm, String sex, String dir, String ciudad, String col, String correo, int idcliente) {
        this.nom = nom;
        this.app = app;
        this.apm = apm;
        this.sex = sex;
        this.dir = dir;
        this.ciudad = ciudad;
        this.col = col;
        this.correo = correo;
        this.idcliente = idcliente;
    }

    public cliente() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getApm() {
        return apm;
    }

    public void setApm(String apm) {
        this.apm = apm;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }
    
    
}
