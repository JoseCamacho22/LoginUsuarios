import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Modelo {
	final String password="jose";
	final String url="jdbc:mysql://localhost/LoginUsuarios";
	final String usuario ="jose";
	
	// Realizamos la conexion a la base de datos llamada LoginUsuarios

	private Connection conexion = null;

	// Indicamos el metodo para la conexion

	public void conexionBBDD() {
		
		//String basedatos = "LoginUsuarios";
		//String url = "jdbc:mysql://localhost/" + basedatos;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url,password,usuario);
			if (conexion != null) {
				//System.out
					//.println(" Su conexion a la base de datos " + url + " con Mysql a sido establecida con exito");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(" Driver JDBC  no ha sido encontrado ");
			e.printStackTrace();
		} catch (SQLException sqlex) {
			// TODO: handle exception
			System.out.println(" Error al conectarse a la base de datos ");
			sqlex.printStackTrace();
		} catch (Exception ex) {
			System.out.println(" Error General");
			ex.printStackTrace();
		}
	}
	
	
	// Creamos el metodo, para comparar el Usuario y la clave son correctas

	public ResultSet ConsultaLogin(String nombre, String clave) {

		boolean ok = false;
		this.conexionBBDD();
		ResultSet res = null;
		Statement sentencia = null;
		String sql = "SELECT * FROM Usuarios WHERE Nombre='" + nombre + " ' AND Clave = '" + clave + "'";

		try {
			sentencia = conexion.createStatement();
			res = sentencia.executeQuery(sql);
			int contador = 0;
			while (res.next()) {
				System.out.println("Username: " + res.getString(1));
			     System.out.println("Password: " + res.getString(2));
				ok = true;
			}
			if (ok) {
				
				  TablaDatos datos = new TablaDatos(); 
				  datos.setLocationRelativeTo(null);
				  datos.setVisible(true);
				  } 
			else{
				JOptionPane.showMessageDialog(null, " Los datos introducidos no son correctos","Mensaje",
			    JOptionPane.INFORMATION_MESSAGE);
				contador++;
			}
			if (contador==3){
				JOptionPane.showInputDialog(null, " Usted agoto sus intentos para conectarse,Gracias Por usar la aplicacion");
				System.exit(0);
			}
			else if (nombre.isEmpty() || clave.isEmpty()){
			JOptionPane.showMessageDialog(null,
			"Por favor, introduzca datos en ambos campos.",
			"Error.", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se logro establecer conexion a la base de datos","error",JOptionPane.ERROR_MESSAGE);
			}
		return res;
			}
				
			
	public ResultSet seleccionarUsuarios() {
		this.conexionBBDD();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conexion.createStatement();
			rs = st.executeQuery("SELECT * FROM Usuarios");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	// METODO PARA AÑADIR USUARIOs A LA BASE DE DATOS

	public void añadirUsuario(String nombre, String clave, int codigopostal, String ciudad, String pais, String sexo) {
		this.conexionBBDD();
		ResultSet res = null;

		try {
			PreparedStatement pstm = conexion.prepareStatement(
					"INSERT INTO Usuarios(Nombre,Clave,CodPostal,Ciudad,Pais,Sexo) VALUES(?,?,?,?,?,?)");
			pstm.setString(1, nombre);
			pstm.setString(2, clave);
			pstm.setInt(3, codigopostal);
			pstm.setString(4, ciudad);
			pstm.setString(5, pais);
			pstm.setString(6, sexo);
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// metodo `para eliminar los usuarios que hay en la tabla
	public void Delete(String nombre) {
		this.conexionBBDD();
		//this.arrancarINI();
		PreparedStatement pstmt = null;
		try {
			pstmt = conexion.prepareStatement("DELETE FROM Usuarios WHERE Nombre= ?");
			pstmt.setString(1, nombre);
			int resul = pstmt.executeUpdate();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// METODO PARA MODIFICAR LOS USUARIOS
	public void modificar(String nombre, String clave, int codigopostal, String ciudad, String pais, String sexo) {
		this.conexionBBDD();
		PreparedStatement ps = null;
		try {
			ps = conexion.prepareStatement(
					"UPDATE Usuarios SET Nombre = ?,CodPostal = ?,Ciudad = ?,Pais = ?,Sexo = ? WHERE Clave = ?");
			ps.setString(1, nombre);
			ps.setInt(2, codigopostal);
			ps.setString(3, ciudad);
			ps.setString(4, pais);
			ps.setString(5, sexo);
			ps.setString(6, clave);
			ps.executeUpdate();
			seleccionarUsuarios();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
public void EscribirINI() {
		Properties propiedades = new Properties();
		OutputStream salida = null;
		try {
			File miFichero = new File("configuracion.ini");
			if (miFichero.exists()) {
				salida = new FileOutputStream(miFichero);
				// asignamos los valores a las propiedades
				propiedades.setProperty("url",url);
				propiedades.setProperty("password",password);
				propiedades.setProperty("usuario",usuario);

				// guardamos el archivo de propiedades en la carpeta de

				// aplicación
				propiedades.store(salida, "Comentario para el fichero");
			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (salida != null) {
				try {
					salida.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
}

	public void LeerINI() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File miFichero = new File("configuracion.ini");
			if (miFichero.exists()) {
				entrada = new FileInputStream(miFichero);
				propiedades.load(entrada);
				System.out.println(propiedades.getProperty("url"));
				System.out.println(propiedades.getProperty("password"));
				System.out.println(propiedades.getProperty("usuario"));
				System.out.println("Su conexion se a establecido con exito :");
			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}