// Clase que define los campos y atributos de la tabla usuarios de nuestra base de datos
package Modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdUsuario;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "Contrasenia")
	private String Contrasenia;
	
	public Usuario() {
		super();
	}
	
	// Constructor
	public Usuario(String nombre, String contrasenia) {
		super();
		this.nombre = nombre;
		this.Contrasenia = contrasenia;
	}
	
	// Getters y setters
	public Long getIdUsuario() {
		return IdUsuario;
	}	
	public void setIdUsuario(Long idUsuario) {
		IdUsuario = idUsuario;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getContrasenia() {
		return Contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		Contrasenia = contrasenia;
	}
	/*
	@Override
	public String toString() {
		return String.format("Usuario[IdUsuario=%d, Nombre='%s', Contrasenia='%s']", IdUsuario,
				Nombre, Contrasenia);
	}
		*/
}
