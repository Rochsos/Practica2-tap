package Controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMethod;

import Modelo.ListaTareas;
import Repositorio.RepositorioLista;

@Controller
@RequestMapping(value="/ListaTareas", method=RequestMethod.GET)
public class ControladorListaTarea {

	@Autowired
	private RepositorioLista repositorioLista;

	// Get All Users
    // URL: http://localhost:8080/api/users/1
    @GetMapping("/lista_tareas")
    public List<ListaTareas> getAllLista() {
        return repositorioLista.findAll();
    }
 
    // Create a new User
    // URL: http://localhost:8080/api/createUser
    // Object json: {"name":"Rosa3333","username":"Marfi3333l"}
    @PostMapping("/createLista")
    public ListaTareas createLista(@Valid @RequestBody ListaTareas listaTareas) {
        return repositorioLista.save(listaTareas);
    }
 
    // Get a User by id
    // URL: http://localhost:8080/api/users/1
    @GetMapping("/listaTareas/{id}")
    public ListaTareas getNoteById(@PathVariable(value = "id") Long id) {
        return repositorioLista.findById(id).orElse(null);
    }
 
    // Update a User
    // URL: http://localhost:8080/api/updateUser/1
    // Object json: {"name":"RosaUpdate","username":"Marfil"}
    @PutMapping("/updateUser/{id}")
    public ListaTareas updateLista(@PathVariable(value = "id") Long id, @Valid @RequestBody ListaTareas listaDetails) {
 
    	ListaTareas listaTareas = repositorioLista.findById(id).orElse(null);
 
    	listaTareas.setNombre(listaDetails.getNombre());
    	listaTareas.setIdUsuario(listaDetails.getIdUsuario());
 
    	ListaTareas updatedNote = repositorioLista.save(listaTareas);
        return updatedNote;
    }
 
    // Delete a User
    // URL: http://localhost:8080/api/deleteUser/6
    @DeleteMapping("/deleteLista/{id}")
    public ResponseEntity<?> deleteLista(@PathVariable(value = "id") Long id) {
         
    	ListaTareas note = repositorioLista.findById(id).orElse(null);
 
        repositorioLista.delete(note);
 
        return ResponseEntity.ok().build();
    }
    
    
	/*
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
	*/
}
