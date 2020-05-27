package Modelo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	// Variables
	private Long IdTarea;
	private String Nombre;
	private String Descripcion;
	private String Prioridad;
	private Date Deadline;
	private String EstadoTarea;
	private String IdLista;
	
	// Constructor
	public Tarea(String nombre, String descripcion, String prioridad, String estadoTarea,
			Long idLista) {
		this.Nombre = nombre;
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
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
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
	
	public String getIdLista() {
		return IdLista;
	}
	public void setIdLista(String idLista) {
		IdLista = idLista;
	}
	
	
}
