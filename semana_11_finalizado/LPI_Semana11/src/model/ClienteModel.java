package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.tipo_cliente;
import util.MySqlDBConexion;

public class ClienteModel {
	public List<Cliente> listarCliente(){
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con =null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql ="select c.*, tp.nombre  from cliente c inner join tipo_cliente tp on c.idTipoCliente = tp.idTpoCliente";
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->"+pstm);
			rs = pstm.executeQuery();
			
			Cliente c = null;
			tipo_cliente tp = null;
			while(rs.next()) 
			{
				c = new Cliente();
				c.setIdCliente(rs.getInt(1));
				c.setNombres(rs.getString(2));
				c.setApellidos(rs.getString(3));
				c.setDni(rs.getString(4));
				c.setFechaNacimiento(rs.getDate(5));
				
				tp = new tipo_cliente();
				tp.setIdTpoCliente(rs.getInt(6));
				tp.setNombre(rs.getString(7));
				
				
				c.setIdCliente(tp.getIdTpoCliente());
				c.setNombre(tp.getNombre());
				data.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (pstm != null)con.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}
	
	public List<Cliente> listaDNICliente(String dni)
	{
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con =null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql ="select c.* tp.nombre  from cliente c inner join tipo_cliente tp on c.idTipoCliente = tp.idTpoCliente where c.dni=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dni);
			rs = pstm.executeQuery();
			
			Cliente c = null;
			tipo_cliente tp = null;
			while(rs.next()) 
			{
				c = new Cliente();
				c.setIdCliente(rs.getInt(1));
				c.setNombres(rs.getString(2));
				c.setApellidos(rs.getString(3));
				c.setDni(rs.getString(4));
				c.setFechaNacimiento(rs.getDate(5));
				
				tp = new tipo_cliente();
				tp.setIdTpoCliente(rs.getInt(6));
				tp.setNombre(rs.getString(7));
				
				c.setIdCliente(tp.getIdTpoCliente());
				c.setNombre(tp.getNombre());
				data.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (pstm != null)con.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}
}
