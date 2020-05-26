package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	// Variables
	private Long IdUsuario;
	private String Nombre;
	private String Contrasenia;
	
	// Constructor
	public Usuario(String nombre, String contrasenia) {
		this.Nombre = nombre;
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
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public String getContrasenia() {
		return Contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		Contrasenia = contrasenia;
	}
	
	@Override
	public String toString() {
		return String.format("Usuario[IdUsuario=%d, Nombre='%s', Contrasenia='%s']", IdUsuario,
				Nombre, Contrasenia);
	}
		
}
