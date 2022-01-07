/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiclases;

import clases.Conexion;
import clases.cliente;
import clases.cuenta;
import clases.transaccion;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rant AA
 */
public class bancoremoto extends UnicastRemoteObject implements interfazRemota {

    public bancoremoto() throws RemoteException {
        System.out.println("Objeto invocado");
    }

    @Override
    public ArrayList<cliente> ListaClientes() throws RemoteException {
        ArrayList<cliente> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
//id_cliente	Nombre	Apaterno	Amaterno	Sexo	Direccion	Ciudad	Colonia	Correo

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT * FROM cliente";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                cliente provi = new cliente();
                provi.setIdcliente(rs.getInt("idcliente"));
                provi.setNom(rs.getString("nombre"));
                provi.setApp(rs.getString("ap_p"));
                provi.setApm(rs.getString("ap_m"));
                provi.setSex(rs.getString("sex"));
                provi.setDir(rs.getString("direccion"));
                provi.setCiudad(rs.getString("ciudad"));
                provi.setCol(rs.getString("colonia"));
                provi.setCorreo(rs.getString("correo"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
        }
        return Estudiosos;
    }

    @Override
    public boolean AgregarCliente(cliente nuevo) throws RemoteException {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
            PreparedStatement ps = null;

            if (nuevo.getIdcliente() > 0) {
                ps = con.prepareStatement("INSERT INTO cliente (idcliente,nombre,ap_p,ap_m,sex,direccion,ciudad,colonia,correo) "
                        + "VALUES (?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, nuevo.getIdcliente());
                ps.setString(2, nuevo.getNom());
                ps.setString(3, nuevo.getApp());
                ps.setString(4, nuevo.getApm());
                ps.setString(5, nuevo.getSex());
                ps.setString(6, nuevo.getDir());
                ps.setString(7, nuevo.getCiudad());
                ps.setString(8, nuevo.getCol());
                ps.setString(9, nuevo.getCorreo());
            } else {
                ps = con.prepareStatement("INSERT INTO cliente (nombre,ap_p,ap_m,sex,direccion,ciudad,colonia,correo) "
                        + "VALUES (?,?,?,?,?,?,?,?)");
                ps.setString(1, nuevo.getNom());
                ps.setString(2, nuevo.getApp());
                ps.setString(3, nuevo.getApm());
                ps.setString(4, nuevo.getSex());
                ps.setString(5, nuevo.getDir());
                ps.setString(6, nuevo.getCiudad());
                ps.setString(7, nuevo.getCol());
                ps.setString(8, nuevo.getCorreo());
            }
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);

            return false;
        }
    }

    @Override
    public boolean ModiCliente(cliente nuevo) throws RemoteException {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
            PreparedStatement ps = null;

            ps = con.prepareStatement("UPDATE cliente set nombre=?,ap_p=?,ap_m=?,sex=?,direccion=?,ciudad=?,colonia=?,correo=? WHERE idcliente=?");

            ps.setString(1, nuevo.getNom());
            ps.setString(2, nuevo.getApp());
            ps.setString(3, nuevo.getApm());
            ps.setString(4, nuevo.getSex());
            ps.setString(5, nuevo.getDir());
            ps.setString(6, nuevo.getCiudad());
            ps.setString(7, nuevo.getCol());
            ps.setString(8, nuevo.getCorreo());
            ps.setInt(9, nuevo.getIdcliente());
            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println(e);

            return false;
        }
    }

    @Override
    public boolean ElimCli(cliente nuevo) throws RemoteException {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
            PreparedStatement ps = null;

            ps = con.prepareStatement("DELETE  FROM cliente WHERE idcliente=?");

            ps.setInt(1, nuevo.getIdcliente());
            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println(e);

            return false;
        }
    }

    @Override
    public cliente busInCli(int idc) throws RemoteException {
        cliente provi = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT * FROM cliente where idcliente=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idc);
            rs = ps.executeQuery();

            if (rs.next()) {
                provi = new cliente();
                provi.setIdcliente(rs.getInt("idcliente"));
                provi.setNom(rs.getString("nombre"));
                provi.setApp(rs.getString("ap_p"));
                provi.setApm(rs.getString("ap_m"));
                provi.setSex(rs.getString("sex"));
                provi.setDir(rs.getString("direccion"));
                provi.setCiudad(rs.getString("ciudad"));
                provi.setCol(rs.getString("colonia"));
                provi.setCorreo(rs.getString("correo"));

            }

        } catch (Exception e) {
        }
        return provi;
    }

    @Override
    public ArrayList<cuenta> ListaCuentas() throws RemoteException {

        ArrayList<cuenta> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT cu.numcuenta 'Cuenta',c.idcliente 'ID Cliente',c.nombre 'Cliente',tc.descripcion 'T. Cuenta',cu.fechaExpedicion 'Fecha de Expedicion',cu.saldo 'Saldo' FROM cuenta cu inner join cliente c on cu.fk_idcliente=c.idcliente inner join tipo_cuenta tc on cu.fk_idtipocuenta=tc.idTipo_cuenta";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                cuenta provi = new cuenta();
                provi.setNumCuenta(rs.getInt("Cuenta"));
                provi.setIdcliente(rs.getInt("ID Cliente"));
                provi.setNombreC(rs.getString("Cliente"));
                provi.setDesC(rs.getString("T. Cuenta"));
                provi.setFechaCreacion(rs.getString("Fecha de Expedicion"));
                provi.setSaldo(rs.getDouble("Saldo"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return Estudiosos;
    }

    @Override
    public boolean AgregarCuenta(cuenta c) throws RemoteException {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
            PreparedStatement ps = null;

            if (c.getNumCuenta() > 0) {
                ps = con.prepareStatement("INSERT INTO cuenta (numcuenta,fk_idcliente,fk_idtipocuenta,fechaExpedicion,saldo) values (?,?,?,?,?);");
                ps.setInt(1, c.getNumCuenta());
                ps.setInt(2, c.getIdcliente());
                ps.setInt(3, c.getIdtipocuenta());
                ps.setString(4, c.getFechaCreacion());
                ps.setDouble(5, c.getSaldo());

            } else {
                ps = con.prepareStatement("INSERT INTO cuenta (fk_idcliente,fk_idtipocuenta,fechaExpedicion,saldo) values (?,?,?,?);");
                ps.setInt(1, c.getIdcliente());
                ps.setInt(2, c.getIdtipocuenta());
                ps.setString(3, c.getFechaCreacion());
                ps.setDouble(4, c.getSaldo());
            }
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);

            return false;
        }
    }

    @Override
    public boolean ModiCuenta(cuenta c) throws RemoteException {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
            PreparedStatement ps = null;

            ps = con.prepareStatement("UPDATE  cuenta set fk_idcliente=?,fk_idtipocuenta=?,fechaExpedicion=?,saldo=? WHERE numcuenta=?");

            ps.setInt(1, c.getIdcliente());
            ps.setInt(2, c.getIdtipocuenta());
            ps.setString(3, c.getFechaCreacion());
            ps.setDouble(4, c.getSaldo());
            ps.setInt(5, c.getNumCuenta());

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println(e);

            return false;
        }
    }

    @Override
    public boolean ElimCuenta(cuenta c) throws RemoteException {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
            PreparedStatement ps = null;
            System.out.println(c.getNumCuenta() + " ES ID");
            ps = con.prepareStatement("select borrarCuenta(?)");

            ps.setInt(1, c.getNumCuenta());
            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println(e);

            return false;
        }
    }

    @Override
    public int haySaldo(int c) throws RemoteException {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("select saldo from cuenta where numcuenta=?");

            ps.setInt(1, c);
            ps.execute();
            rs = ps.executeQuery();
            double saldo;
            rs.next();
            saldo = rs.getDouble("saldo");
            if (saldo == 0) {
                return 0;
            } else {
                return 1;
            }

        } catch (SQLException e) {
            System.out.println(e);

            return 2;
        }
    }

    @Override
    public cuenta busCuIn(int idcu) throws RemoteException {
        cuenta provi = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT cu.numcuenta 'Cuenta',c.idcliente 'ID Cliente',c.nombre 'Cliente',tc.descripcion 'T. Cuenta',cu.fechaExpedicion 'Fecha de Expedicion',cu.saldo 'Saldo' FROM cuenta cu inner join cliente c on cu.fk_idcliente=c.idcliente inner join tipo_cuenta tc on cu.fk_idtipocuenta=tc.idTipo_cuenta where cu.numcuenta=? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idcu);
            rs = ps.executeQuery();

            if (rs.next()) {
                provi = new cuenta();
                provi.setNumCuenta(rs.getInt("Cuenta"));
                provi.setIdcliente(rs.getInt("ID Cliente"));
                provi.setNombreC(rs.getString("Cliente"));
                provi.setDesC(rs.getString("T. Cuenta"));
                provi.setFechaCreacion(rs.getString("Fecha de Expedicion"));
                provi.setSaldo(rs.getDouble("Saldo"));

            }

        } catch (Exception e) {
        }
        return provi;
    }

    @Override
    public ArrayList<transaccion> ListaTransa() throws RemoteException {
        ArrayList<transaccion> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT c.idcliente 'idc',c.nombre 'cnom',t.idtransaccion 'idtran',t.fecha_transaccion 'ftran',t.tipoTransaccion 'tipot',t.monto 'tmonto',t.numCuenta 'ncuenta',tc.descripcion 'tcdes',tc.idTipo_cuenta 'tcu' from transaccion t inner join cuenta cu on  t.numCuenta=cu.numcuenta inner join cliente c on cu.fk_idcliente=c.idcliente inner join tipo_cuenta tc on tc.idTipo_cuenta=cu.fk_idtipocuenta order by t.idtransaccion;";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                transaccion provi = new transaccion();
                provi.setIdclie(rs.getInt("idc"));
                provi.setNom(rs.getString("cnom"));
                provi.setIdtran(rs.getInt("idtran"));
                provi.setFechaTrans(rs.getString("ftran"));
                provi.setTipoTrans(rs.getString("tipot"));
                provi.setMonto(rs.getDouble("tmonto"));
                provi.setNumcuenta(rs.getInt("ncuenta"));
                provi.setDes(rs.getString("tcdes"));
                provi.setTipocuenta(rs.getInt("tcu"));
                Estudiosos.add(provi);

            }

        } catch (Exception e) {
        }
        return Estudiosos;
    }

    @Override
    public boolean AgregarTransa(transaccion transa, int tipo) throws RemoteException {
        try {
            //DEPOSITO 0
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
            PreparedStatement ps = null;

            if (transa.getIdtran() > 0) {
                ps = con.prepareStatement("call altaTrans (?,?,?,?,?,?)");

                ps.setInt(1, transa.getIdtran());

                ps.setInt(2, transa.getNumcuenta());

                ps.setString(3, transa.getTipoTrans());
                ps.setString(4, transa.getFechaTrans());
                ps.setDouble(5, transa.getMonto());
                ps.setInt(6, tipo);
            } else {
                ps = con.prepareStatement("call altaTrans (?,?,?,?,?,?)");
                ps.setInt(1, -1);

                ps.setInt(2, transa.getNumcuenta());

                ps.setString(3, transa.getTipoTrans());
                ps.setString(4, transa.getFechaTrans());
                ps.setDouble(5, transa.getMonto());
                ps.setInt(6, tipo);
            }
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            e.getLocalizedMessage();
            return false;
        }
    }

    @Override
    public boolean ModiTransa(transaccion transa, int tipo) throws RemoteException {
        try {
            //DEPOSITO 0
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
            PreparedStatement ps = null;

            ps = con.prepareStatement("call modiTransa(?,?,?,?,?,?)");
//             (idt int,nc int,tt varchar(30),ft date,monto double,tipoTran int)
            System.out.println(transa.toString());
            ps.setInt(1, transa.getIdtran());
            ps.setInt(2, transa.getNumcuenta());
            ps.setString(3, transa.getTipoTrans());
            ps.setString(4, transa.getFechaTrans());
            ps.setDouble(5, transa.getMonto());
            ps.setInt(6, tipo);
            System.out.println("TIPO TRANSACCION " + tipo);
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            e.getLocalizedMessage();
            return false;
        }
    }

    @Override
    public boolean ElimTransa(transaccion transa) throws RemoteException {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();
            PreparedStatement ps = null;
            ps = con.prepareStatement("call elimTrans(?)");

            ps.setInt(1, transa.getIdtran());

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println(e);

            return false;
        }
    }

    @Override
    public transaccion busTranIn(int idt) throws RemoteException {
        transaccion provi = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT c.idcliente 'idc',c.nombre 'cnom',t.idtransaccion 'idtran',t.fecha_transaccion 'ftran',t.tipoTransaccion 'tipot',t.monto 'tmonto',t.numCuenta 'ncuenta',tc.descripcion 'tcdes',tc.idTipo_cuenta 'tcu' from transaccion t inner join cuenta cu on  t.numCuenta=cu.numcuenta inner join cliente c on cu.fk_idcliente=c.idcliente inner join tipo_cuenta tc on tc.idTipo_cuenta=cu.fk_idtipocuenta where t.idtransaccion=? order by t.idtransaccion;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idt);
            rs = ps.executeQuery();

            if (rs.next()) {
                provi = new transaccion();
                provi.setIdclie(rs.getInt("idc"));
                provi.setNom(rs.getString("cnom"));
                provi.setIdtran(rs.getInt("idtran"));
                provi.setFechaTrans(rs.getString("ftran"));
                provi.setTipoTrans(rs.getString("tipot"));
                provi.setMonto(rs.getDouble("tmonto"));
                provi.setNumcuenta(rs.getInt("ncuenta"));
                //  provi.setTipocuenta(rs.getInt("tipocuenta"));
                provi.setDes(rs.getString("tcdes"));

            }

        } catch (Exception e) {
        }
        return provi;

    }

    @Override
    public ArrayList<transaccion> consNCTransa(int nc) throws RemoteException {
        ArrayList<transaccion> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT c.idcliente 'idc',c.nombre 'cnom',t.idtransaccion 'idtran',t.fecha_transaccion 'ftran',t.tipoTransaccion 'tipot',t.monto 'tmonto',t.numCuenta 'ncuenta',tc.descripcion 'tcdes',tc.idTipo_cuenta 'tcu' from transaccion t inner join cuenta cu on  t.numCuenta=cu.numcuenta inner join cliente c on cu.fk_idcliente=c.idcliente inner join tipo_cuenta tc on tc.idTipo_cuenta=cu.fk_idtipocuenta where cu.numcuenta=? order by t.idtransaccion;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, nc);
            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                transaccion provi = new transaccion();
                provi.setIdclie(rs.getInt("idc"));
                provi.setNom(rs.getString("cnom"));
                provi.setIdtran(rs.getInt("idtran"));
                provi.setFechaTrans(rs.getString("ftran"));
                provi.setTipoTrans(rs.getString("tipot"));
                provi.setMonto(rs.getDouble("tmonto"));
                provi.setNumcuenta(rs.getInt("ncuenta"));
                provi.setDes(rs.getString("tcdes"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
        }
        return Estudiosos;
    }

    @Override
    public ArrayList<cliente> con2(int tc) throws RemoteException {
        ArrayList<cliente> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT c.nombre \"NOMBRE\", c.ap_p \"APATARENO\", c.ap_m \"AMATERNO\", cu.numcuenta \"NUMCTA\",tc.descripcion \"TIPO DE CUENTA\", cu.fechaExpedicion \"FECHA EXPEDICION\",cu.saldo \"SALDO\" from cuenta cu inner join cliente c on cu.fk_idcliente=c.idcliente inner join tipo_cuenta tc on cu.fk_idtipocuenta=tc.idTipo_cuenta WHERE tc.idTipo_cuenta=?;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, tc);
            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                cliente provi = new cliente();
                provi.setNom(rs.getString("NOMBRE"));
                provi.setApp(rs.getString("APATARENO"));
                provi.setApm(rs.getString("AMATERNO"));
                provi.setNumc(rs.getInt("NUMCTA"));
                provi.setTipoc(rs.getString("TIPO DE CUENTA"));
                provi.setFechEx(rs.getString("FECHA EXPEDICION"));
                provi.setSaldo(rs.getDouble("SALDO"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return Estudiosos;
    }

    @Override
    public ArrayList<transaccion> con3(int idc) throws RemoteException {
        ArrayList<transaccion> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT t.idtransaccion, cu.numcuenta, t.fecha_transaccion, t.tipoTransaccion, t.monto, cu.fk_idcliente, c.nombre\n"
                    + "from transaccion t inner join cuenta cu on t.numCuenta=cu.numcuenta\n"
                    + "inner join cliente c on cu.fk_idcliente=c.idcliente where c.idcliente=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idc);
            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                transaccion provi = new transaccion();
                provi.setIdclie(rs.getInt("cu.fk_idcliente"));
                provi.setNom(rs.getString("c.nombre"));

                provi.setIdtran(rs.getInt("t.idtransaccion"));
                provi.setFechaTrans(rs.getString("t.fecha_transaccion"));
                provi.setTipoTrans(rs.getString("t.tipoTransaccion"));
                provi.setMonto(rs.getDouble("t.monto"));
                provi.setNumcuenta(rs.getInt("cu.numcuenta"));
                // provi.setDes(rs.getString("tcdes"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
        }
        return Estudiosos;
    }

    @Override
    public ArrayList<transaccion> con4(int idc, int nc) throws RemoteException {
        ArrayList<transaccion> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT t.idtransaccion, cu.numcuenta, t.fecha_transaccion, t.tipoTransaccion, t.monto, cu.fk_idcliente, c.nombre\n"
                    + "from transaccion t inner join cuenta cu on t.numCuenta=cu.numcuenta\n"
                    + "inner join cliente c on cu.fk_idcliente=c.idcliente where c.idcliente=? AND cu.numcuenta=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idc);
            ps.setInt(2, nc);
            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                transaccion provi = new transaccion();
                provi.setIdclie(rs.getInt("cu.fk_idcliente"));
                provi.setNom(rs.getString("c.nombre"));

                provi.setIdtran(rs.getInt("t.idtransaccion"));
                provi.setFechaTrans(rs.getString("t.fecha_transaccion"));
                provi.setTipoTrans(rs.getString("t.tipoTransaccion"));
                provi.setMonto(rs.getDouble("t.monto"));
                provi.setNumcuenta(rs.getInt("cu.numcuenta"));
                // provi.setDes(rs.getString("tcdes"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
        }
        return Estudiosos;
    }

    @Override
    public ArrayList<transaccion> con5(int idc, int nc, String fe) throws RemoteException {
        ArrayList<transaccion> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT t.idtransaccion, cu.numcuenta, t.fecha_transaccion, t.tipoTransaccion, t.monto, cu.fk_idcliente, c.nombre\n"
                    + "from transaccion t inner join cuenta cu on t.numCuenta=cu.numcuenta\n"
                    + "inner join cliente c on cu.fk_idcliente=c.idcliente where c.idcliente=? AND cu.numcuenta=? AND t.fecha_transaccion=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idc);
            ps.setInt(2, nc);
            ps.setString(3, fe);
            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                transaccion provi = new transaccion();
                provi.setIdclie(rs.getInt("cu.fk_idcliente"));
                provi.setNom(rs.getString("c.nombre"));

                provi.setIdtran(rs.getInt("t.idtransaccion"));
                provi.setFechaTrans(rs.getString("t.fecha_transaccion"));
                provi.setTipoTrans(rs.getString("t.tipoTransaccion"));
                provi.setMonto(rs.getDouble("t.monto"));
                provi.setNumcuenta(rs.getInt("cu.numcuenta"));
                // provi.setDes(rs.getString("tcdes"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
        }
        return Estudiosos;
    }

    @Override
    public ArrayList<transaccion> con7(int mes) throws RemoteException {
        ArrayList<transaccion> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT t.idtransaccion, cu.numcuenta, t.fecha_transaccion, t.tipoTransaccion, t.monto, cu.fk_idcliente, c.nombre,tc.descripcion\n"
                    + "from transaccion t inner join cuenta cu on t.numCuenta=cu.numcuenta\n"
                    + "inner join cliente c on cu.fk_idcliente=c.idcliente \n"
                    + "inner join tipo_cuenta tc on tc.idTipo_cuenta=cu.fk_idtipocuenta\n"
                    + "where MONTH(t.fecha_transaccion) =?";
            ps = con.prepareStatement(sql);

            ps.setInt(1, mes);
            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                transaccion provi = new transaccion();
                provi.setIdclie(rs.getInt("cu.fk_idcliente"));
                provi.setNom(rs.getString("c.nombre"));

                provi.setIdtran(rs.getInt("t.idtransaccion"));
                provi.setFechaTrans(rs.getString("t.fecha_transaccion"));
                provi.setTipoTrans(rs.getString("t.tipoTransaccion"));
                provi.setMonto(rs.getDouble("t.monto"));
                provi.setNumcuenta(rs.getInt("cu.numcuenta"));
                provi.setDes(rs.getString("tc.descripcion"));
                // provi.setDes(rs.getString("tcdes"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
        }
        return Estudiosos;
    }

    @Override
    public ArrayList<cuenta> con8(double a, double b) throws RemoteException {
        ArrayList<cuenta> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT c.numcuenta, cl.nombre,c.saldo, tc.descripcion, c.fechaExpedicion\n"
                    + "from cuenta c inner join cliente cl on c.fk_idcliente=cl.idcliente\n"
                    + "inner join tipo_cuenta tc on c.fk_idtipocuenta=tc.idTipo_cuenta where c.saldo>=? AND c.saldo <=?";
            ps = con.prepareStatement(sql);
            ps.setDouble(1, a);
            ps.setDouble(2, b);
            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                cuenta provi = new cuenta();
                provi.setNumCuenta(rs.getInt("c.numcuenta"));

                provi.setNombreC(rs.getString("cl.nombre"));
                provi.setDesC(rs.getString("tc.descripcion"));
                provi.setFechaCreacion(rs.getString("c.fechaExpedicion"));
                provi.setSaldo(rs.getDouble("c.saldo"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return Estudiosos;
    }

    @Override
    public ArrayList<transaccion> con9(int an) throws RemoteException {
        ArrayList<transaccion> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT t.idtransaccion, cu.numcuenta, t.fecha_transaccion, t.tipoTransaccion, t.monto, cu.fk_idcliente, c.nombre,tc.descripcion\n"
                    + "from transaccion t inner join cuenta cu on t.numCuenta=cu.numcuenta\n"
                    + "inner join cliente c on cu.fk_idcliente=c.idcliente \n"
                    + "inner join tipo_cuenta tc on tc.idTipo_cuenta=cu.fk_idtipocuenta\n"
                    + "where  YEAR(t.fecha_transaccion) =?";
            ps = con.prepareStatement(sql);

            ps.setInt(1, an);
            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                transaccion provi = new transaccion();
                provi.setIdclie(rs.getInt("cu.fk_idcliente"));
                provi.setNom(rs.getString("c.nombre"));

                provi.setIdtran(rs.getInt("t.idtransaccion"));
                provi.setFechaTrans(rs.getString("t.fecha_transaccion"));
                provi.setTipoTrans(rs.getString("t.tipoTransaccion"));
                provi.setMonto(rs.getDouble("t.monto"));
                provi.setNumcuenta(rs.getInt("cu.numcuenta"));
                provi.setDes(rs.getString("tc.descripcion"));
                // provi.setDes(rs.getString("tcdes"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
        }
        return Estudiosos;
    }

    @Override
    public ArrayList<transaccion> con10(int idc, int nc, int an) throws RemoteException {
        ArrayList<transaccion> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT t.idtransaccion, cu.numcuenta,t.fecha_transaccion,t.tipoTransaccion,t.monto,cl.idcliente, cl.nombre,cu.fk_idcliente\n"
                    + "from transaccion t inner join cuenta cu on t.numCuenta=cu.numcuenta inner join cliente cl on cu.fk_idcliente=cl.idcliente WHERE cl.idcliente=? AND cu.numcuenta=? AND YEAR(t.fecha_transaccion)=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idc);
            ps.setInt(2, nc);
            ps.setInt(3, an);
            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                transaccion provi = new transaccion();
                provi.setIdclie(rs.getInt("cu.fk_idcliente"));
                provi.setNom(rs.getString("cl.nombre"));

                provi.setIdtran(rs.getInt("t.idtransaccion"));
                provi.setFechaTrans(rs.getString("t.fecha_transaccion"));
                provi.setTipoTrans(rs.getString("t.tipoTransaccion"));
                provi.setMonto(rs.getDouble("t.monto"));
                provi.setNumcuenta(rs.getInt("cu.numcuenta"));
                // provi.setDes(rs.getString("tcdes"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return Estudiosos;
    }
@Override
    public ArrayList<cuenta> con6(int nc) throws RemoteException {

        ArrayList<cuenta> Estudiosos = null;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            //String sql = "SELECT id,numCon,Nombre,ApellidoP, Genero,ApellidoM,Tel,Correo,Carrera,Permiso FROM estudiante WHERE " + tex + "= '" + dato + "'";
            String sql = "SELECT cu.numcuenta 'Cuenta',c.idcliente 'ID Cliente',c.nombre 'Cliente',tc.descripcion 'T. Cuenta',cu.fechaExpedicion 'Fecha de Expedicion',cu.saldo 'Saldo' FROM cuenta cu inner join cliente c on cu.fk_idcliente=c.idcliente inner join tipo_cuenta tc on cu.fk_idtipocuenta=tc.idTipo_cuenta where c.idcliente=? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, nc);
            rs = ps.executeQuery();

            Estudiosos = new ArrayList<>();

            while (rs.next()) {
                cuenta provi = new cuenta();
                provi.setNumCuenta(rs.getInt("Cuenta"));
                provi.setIdcliente(rs.getInt("ID Cliente"));
                provi.setNombreC(rs.getString("Cliente"));
                provi.setDesC(rs.getString("T. Cuenta"));
                provi.setFechaCreacion(rs.getString("Fecha de Expedicion"));
                provi.setSaldo(rs.getDouble("Saldo"));

                Estudiosos.add(provi);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return Estudiosos;
    }
}
