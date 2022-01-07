/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiclases;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Rant AA
 */
public class Servidor {

    public static void main(String[] args) {
        try {
            String miIP = InetAddress.getLocalHost().getHostAddress();
            String puerto = "6060";
            String nomObjeto = "obje";
            //String cadenaServ="rmi://"+miIP+":"+puerto+"/"+nomObjeto;
            //tiempoRemoto miobjetolocal=new carreraRemota();
            Registry registro = LocateRegistry.createRegistry(6060);
            //  Naming.rebind(nomObjeto, miobjetolocal);
            registro.rebind(nomObjeto, new bancoremoto());
            System.out.println("Objeto disponible");
        } catch (java.net.UnknownHostException e) {
            System.out.println("Host Desconocido " + e);
        } catch (RemoteException e) {
            System.out.println("Host en el objeto remoto " + e);
        }
        /*catch (MalformedURLException ed) {
            System.out.println("URL del objeto invalida "+ed);
        }*/
    }
}
