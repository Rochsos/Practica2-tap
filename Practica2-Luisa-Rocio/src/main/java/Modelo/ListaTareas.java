// Clase que define los campos y atributos de la tabla lista_tareas de nuestra base de datos
package Modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "lista_tareas")
public class ListaTareas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdListaTareas;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "IdUsuario")
	private Long IdUsuario;
	
	
	public ListaTareas() {
		super();
	}
	
	// Constructor
	public ListaTareas(String nombre, Long idUsuario) {
		super();
		this.nombre = nombre;
		IdUsuario = idUsuario;
	}
	
	// Getters y setters
	public Long getIdListaTareas() {
		return IdListaTareas;
	}
	
	public void setIdListaTareas(Long idListaTareas) {
		IdListaTareas = idListaTareas;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Long getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		IdUsuario = idUsuario;
	}
}
