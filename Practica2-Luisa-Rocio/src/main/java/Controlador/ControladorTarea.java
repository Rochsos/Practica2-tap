package Controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Modelo.Tarea;
import Repositorio.RepositorioTarea;

@RestController
@RequestMapping("/tareas")
public class ControladorTarea {

	@Autowired
	private RepositorioTarea repositorioTareas;

	@PostMapping(path="/")
	public @ResponseBody Tarea añadirNuevaTarea (@RequestParam String nombre,
												@RequestParam String descripcion,
												@RequestParam String prioridad,
												@RequestParam String estadoTarea,
												@RequestParam Long idLista
	                                        ) {
	    try {
	        
	        Tarea nuevaTarea = new Tarea(nombre, descripcion, prioridad, estadoTarea, idLista);
	        repositorioTareas.save(nuevaTarea);
	        return nuevaTarea;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error");
	    }
	}

	@GetMapping(path="/")
	public @ResponseBody Iterable<Tarea> getallTareas() {
	    return repositorioTareas.findAll();
	}

	@GetMapping(path="/{id}")
	public @ResponseBody Optional<Tarea> getTarea(@PathVariable int idTarea) {
	    return repositorioTareas.findById((long) idTarea);
	}

	@PutMapping(path="/{id}")
	public @ResponseBody Tarea modificarTarea(@PathVariable int idTarea,
												@RequestParam String nombre,
												@RequestParam String descripcion,
												@RequestParam String prioridad,
												@RequestParam String estadoTarea,
												@RequestParam Long idLista
	                                       ) {
	   
	    Optional<Tarea> dbTareas = repositorioTareas.findById((long) idTarea);
	    if (dbTareas.isPresent()) {
	        Tarea tareaModificada = dbTareas.get();
	        
	        tareaModificada.setIdLista((long) idLista);
	        tareaModificada.setNombre(nombre);
	        tareaModificada.setDescripcion(descripcion);
	        tareaModificada.setPrioridad(prioridad);
	        tareaModificada.setEstadoTarea(estadoTarea);
	        tareaModificada.setIdTarea((long) idTarea);
	        repositorioTareas.save(tareaModificada);
	
	        return tareaModificada;
	    }
	
	    throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Unable to find resource");
	}

	@DeleteMapping(path = "/{id}")
	public @ResponseBody String borrarTarea(@PathVariable int idTarea) {
	    Optional<Tarea> dbTareas = repositorioTareas.findById((long) idTarea);
	    if (dbTareas.isPresent()) {
	        Tarea borrarTarea = dbTareas.get();
	        repositorioTareas.delete(borrarTarea);
	
	        throw new ResponseStatusException(HttpStatus.OK, "Deleted");
	    }
	
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}
}
