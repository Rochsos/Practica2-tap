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

import Modelo.ListaTareas;
import Repositorio.RepositorioLista;

@RestController
@RequestMapping("/lista")
public class ControladorListaTarea {

	@Autowired
	private RepositorioLista repositorioLista;

	@PostMapping(path="/")
	public @ResponseBody ListaTareas añadirNuevaLista (@RequestParam String name,
	                                        @RequestParam int idUsuario                   
	                                        ) {
	    try {
	        
	        ListaTareas nuevaLista = new ListaTareas(name, (long) idUsuario);
	        repositorioLista.save(nuevaLista);
	        return nuevaLista;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error");
	    }
	}

	@GetMapping(path="/")
	public @ResponseBody Iterable<ListaTareas> getAllListas() {
	    return repositorioLista.findAll();
	}

	@GetMapping(path="/{id}")
	public @ResponseBody Optional<ListaTareas> getLista(@PathVariable int id) {
	    return repositorioLista.findById((long) id);
	}

	@PutMapping(path="/{id}")
	public @ResponseBody ListaTareas modificarLista(@PathVariable int id,
	                                       @RequestParam String nombre,
	                                       @RequestParam int idUsuario
	                                       ) {
	   
	    Optional<ListaTareas> dbListaTareas = repositorioLista.findById((long) id);
	    if (dbListaTareas.isPresent()) {
	        ListaTareas listaModificada = dbListaTareas.get();
	        
	        listaModificada.setIdUsuario((long) idUsuario);
	        listaModificada.setNombreLista(nombre);
	        repositorioLista.save(listaModificada);
	
	        return listaModificada;
	    }
	
	    throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Unable to find resource");
	}

	@DeleteMapping(path = "/{id}")
	public @ResponseBody String borrarLista(@PathVariable int id) {
	    Optional<ListaTareas> dbListaTareas = repositorioLista.findById((long) id);
	    if (dbListaTareas.isPresent()) {
	        ListaTareas borrarLista = dbListaTareas.get();
	        repositorioLista.delete(borrarLista);
	
	        throw new ResponseStatusException(HttpStatus.OK, "Deleted");
	    }
	
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}
}
