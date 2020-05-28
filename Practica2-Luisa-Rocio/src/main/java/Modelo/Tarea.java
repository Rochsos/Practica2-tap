// Clase que define los campos y atributos de la tabla tarea de nuestra base de datos
package Modelo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdTarea;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "Descripcion")
	private String Descripcion;
	@Column(name = "Prioridad")
	private String Prioridad;
	@Column(name = "DeadLine")
	private Date Deadline;
	@Column(name = "EstadoTarea")
	private String EstadoTarea;
	@Column(name = "IdLista")
	private Long IdLista;
	
	
	public Tarea() {
		super();
	}
	
	// Constructor
	public Tarea(String nombre, String descripcion, String prioridad, String estadoTarea,
			Long idLista) {
		super();
		this.nombre = nombre;
		this.Descripcion = descripcion;
		this.Prioridad = prioridad;
		this.EstadoTarea = estadoTarea;
	}
	
	// Getters y setters
	public Long getIdTarea() {
		return IdTarea;
	}
	
	public void setIdTarea(Long idTarea) {
		IdTarea = idTarea;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	public String getPrioridad() {
		return Prioridad;
	}
	public void setPrioridad(String prioridad) {
		Prioridad = prioridad;
	}
	
	public Date getDeadline() {
		return Deadline;
	}
	public void setDeadline(Date deadline) {
		Deadline = deadline;
	}
	
	public String getEstadoTarea() {
		return EstadoTarea;
	}
	public void setEstadoTarea(String estadoTarea) {
		EstadoTarea = estadoTarea;
	}
	
	public Long getIdLista() {
		return IdLista;
	}
	public void setIdLista(Long idLista) {
		IdLista = idLista;
	}
	
	
}
