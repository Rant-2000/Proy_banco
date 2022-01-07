/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiclases;

import clases.cliente;
import clases.cuenta;
import clases.transaccion;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Rant AA
 */
public interface interfazRemota extends Remote{
    //CLIENTES
    public ArrayList<cliente> ListaClientes() throws RemoteException;
    public boolean AgregarCliente(cliente nuevo) throws RemoteException;
    public boolean ModiCliente(cliente nuevo) throws RemoteException;
    public boolean ElimCli(cliente nuevo) throws RemoteException;
    public cliente busInCli(int idc) throws RemoteException;
  
    //CONSULTAS
    public ArrayList<cliente> con2(int tc) throws RemoteException;
    public ArrayList<transaccion> con3(int idc) throws RemoteException;
    public ArrayList<transaccion> con4(int idc,int nc) throws RemoteException;
    public ArrayList<transaccion> con5(int idc,int nc,String fe) throws RemoteException;
    public ArrayList<cuenta> con6(int nc) throws RemoteException;
    public ArrayList<transaccion> con7(int mes) throws RemoteException;
    public ArrayList<cuenta> con8(double a,double b) throws RemoteException;
    public ArrayList<transaccion> con9(int an) throws RemoteException;
    public ArrayList<transaccion> con10(int idc,int nc,int an) throws RemoteException;
    
    //CUENTAS
    public ArrayList<cuenta> ListaCuentas() throws RemoteException;
    public boolean AgregarCuenta(cuenta c) throws RemoteException;
    public boolean ModiCuenta(cuenta c) throws RemoteException;
    public boolean ElimCuenta(cuenta c) throws RemoteException;
    public int haySaldo(int c) throws RemoteException;
    public cuenta busCuIn(int idcu) throws RemoteException;
    
    //TRANSACCIONES
    public ArrayList<transaccion> ListaTransa() throws RemoteException;
    public ArrayList<transaccion> consNCTransa(int nc) throws RemoteException;
    
    public boolean AgregarTransa(transaccion transa,int tipo) throws RemoteException;
    public boolean ModiTransa(transaccion transa,int tipo) throws RemoteException;
    public boolean ElimTransa(transaccion transa) throws RemoteException;
    public transaccion busTranIn(int idt) throws RemoteException;

}
