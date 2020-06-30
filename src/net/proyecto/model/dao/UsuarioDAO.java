package net.proyecto.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.proyecto.model.Usuario;

/**
 * Esta clase DAO proporciona operaciones de base de datos CRUD para comunicarse con
 * la tabla usuario definida en el entity conectandose con la base de datos. En resumen contiene la logica con la que trabajara nuestra app
 * 
 * @author fjruibe
 *
 */
public class UsuarioDAO {

	// definimos nuestras variables de conexion, direccion teniendo en cuenta que estamos usando xampp ,
	// el username es el nombre de user de mysql y clave es la clave de usuario
	private String url = "jdbc:mysql://localhost:3306/venta_plantas?useSSL=false";
	private String username = "root";
	private String clave = "";


    // definimos nuestras variables de consulta como constante, para manipular de manera mas limpia nuestras operaciones
    //si quieren hacer mas consultas deben agregar mas variables como las de abajo
	private static final String INSERTAR_SQL = "INSERT INTO usuarios" + "  (nombre, email, telefono) VALUES "
			+ " (?, ?, ?);";
    //tambien se puede escribir asi ->	private static final String INSERTAR_SQL = "INSERT INTO usuarios(nombre, email, telefono) VALUES (?, ?, ?);";
	private static final String BUSCAR_POR_ID = "select id,nombre,email,telefono from usuarios where id =?";
	private static final String SELECCIONAR_TODOS_USUARIOS = "select * from usuarios";
	private static final String BORRAR_USUARIO = "delete from usuarios where id = ?;";
	private static final String ACTUALIZAR_USUARIO = "update usuarios set nombre = ?,email= ?, telefono =? where id = ?;";

	public UsuarioDAO() {
	}
     


     //definimos nuestra clase de conexion , al  establecer un metodo de conexion debemos usar exception
	protected Connection getConnection() {
		Connection connection = null;
		try {

			// aqui nos conectamos con nuestro diver de manera local
			Class.forName("com.mysql.jdbc.Driver");

			//establecemos la conexion usando nuestras variables de conexion
			connection = DriverManager.getConnection(url, username, clave);
		} catch (SQLException e) {
			//si hay problemas de sintaxis de sql, se mostraran errores en nuestro log(CONSOLA)
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//si hay problemas de sintaxis de clases, se mostraran errores en nuestro log(CONSOLA)
			e.printStackTrace();
		}

		//si nada de lo de arriba se ejecuta ,se establece la conexion retornando la conexion (ES LO QUE QUEREMOS)
		return connection;
	}



//DESDE ACA EN ADELANTE ESTABLECEMOS(CREAMOS) NUESTROS METODOS QUE USAREMOS PARA HACER LAS CONSULTAS EN NUESTRO CONTROLADOR
	public void insertarUsuario(Usuario usuario) throws SQLException {
		System.out.println(INSERTAR_SQL); 
// La declaración try-with-resource cerrará automáticamente la conexión

		// en el try de establece la conexion(que es un objeto) almacenando el objeto en la variable conection , luego preparamos la consulta usando usando prepareStatement
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERTAR_SQL)) {

			//con el objeto encapsulado , procedimos a setear el atributo en la posicion 1 de la tabla get que esta en nuestro entity(modelo)
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setInt(3, usuario.getTelefono());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Usuario seleccionarUsuario(int id) {
		Usuario usuario = null;
		// paso 1: establecemos conexion
		try (Connection connection = getConnection();
				// paso 2:creamos un statement usando el objeto connection
				PreparedStatement preparedStatement = connection.prepareStatement(BUSCAR_POR_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// paso 3: ejecutamos la consulta o actualizamos la consulta
			ResultSet rs = preparedStatement.executeQuery();

			// paso 4: aca se recorre cada uno de los registros para luego ser mostrados
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				int telefono = rs.getInt("telefono");
				usuario = new Usuario(id, nombre, email, telefono);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return usuario;
	}

	public List<Usuario> seleccionarMuchoUsuarios() {

		// utilizando try-with- para evitar cerrar recursos
		List<Usuario> usuarios = new ArrayList<>();
		// Step 1: estableciendo conexion
		try (Connection connection = getConnection();

				// paso 2:creamos un statement usando el objeto connection
			PreparedStatement preparedStatement = connection.prepareStatement(SELECCIONAR_TODOS_USUARIOS);) {
			System.out.println(preparedStatement);
				// paso 3: ejecutamos la consulta o actualizamos la consulta
			ResultSet rs = preparedStatement.executeQuery();

			// paso 4: aca se recorre cada uno de los registros para luego ser mostrados
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				int telefono = rs.getInt("telefono");
				usuarios.add(new Usuario(id, nombre, email, telefono));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return usuarios;
	}


//aca se sigue la misma logica de los pasos anteriores descritos
	public boolean borrarUsuario(int id) throws SQLException {
		boolean filaBorrada;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(BORRAR_USUARIO);) {
			statement.setInt(1, id);
			filaBorrada = statement.executeUpdate() > 0;
		}
		return filaBorrada;
	}

	public boolean actualizarUsuario(Usuario usuario) throws SQLException {
		boolean filaActualizada;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(ACTUALIZAR_USUARIO);) {
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getEmail());
			statement.setInt(3, usuario.getTelefono());
			statement.setInt(4, usuario.getId());

			filaActualizada = statement.executeUpdate() > 0;
		}
		return filaActualizada;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("estado del SQL: " + ((SQLException) e).getSQLState());
				System.err.println("Error de código: " + ((SQLException) e).getErrorCode());
				System.err.println("Mensaje: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Causa: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
