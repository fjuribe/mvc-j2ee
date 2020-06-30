// esto corresponde al Entity(que es parte de la capa modelo) del proyecto , es una especie de plantilla que nos
//  ayuda a manejar nuestra comunicacion con la tabla usuario , centrandonos complementante en el uso de java , usando lo menos posible sql
package net.proyecto.model;

public class Usuario {
	private int id;
	private String nombre;
	private String email;
	private int telefono;
	


	// aca creamos 3 constructores de nombre 'Usuario', uno vacio , otro constructor con todos los parametros y el ultimo con todos pero excluyendo al id
	// ...Esto nos permitira agregar los valores al furmulario tanto al crar como editar valores
	public Usuario() {
	}
	
	public Usuario(String nombre, String email, int telefono) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}

	public Usuario(int id, String nombre, String email, int telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
}
