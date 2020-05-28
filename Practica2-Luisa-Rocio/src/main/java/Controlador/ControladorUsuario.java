package Controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import Modelo.Usuario;
import Repositorio.RepositorioUsuario;


@RestController
@RequestMapping("/Usuario")
public class ControladorUsuario {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	// Get All Users
    // URL: http://localhost:8080/api/users/1
    @GetMapping("/usuario")
    public List<Usuario> getAllUsers() {
        return repositorioUsuario.findAll();
    }
 
    // Create a new User
    // URL: http://localhost:8080/api/createUser
    // Object json: {"name":"Rosa3333","username":"Marfi3333l"}
    @PostMapping("/createUser")
    public Usuario createUser(@Valid @RequestBody Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }
 
    // Get a User by id
    // URL: http://localhost:8080/api/users/1
    @GetMapping("/users/{id}")
    public Usuario getNoteById(@PathVariable(value = "id") Long id) {
        return repositorioUsuario.findById(id).orElse(null);
    }
 
    // Update a User
    // URL: http://localhost:8080/api/updateUser/1
    // Object json: {"name":"RosaUpdate","username":"Marfil"}
    @PutMapping("/updateUser/{id}")
    public Usuario updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody Usuario userDetails) {
 
    	Usuario usuario = repositorioUsuario.findById(id).orElse(null);
 
    	usuario.setNombre(userDetails.getNombre());
    	usuario.setContrasenia(userDetails.getContrasenia());
 
        Usuario updatedNote = repositorioUsuario.save(usuario);
        return updatedNote;
    }
 
    // Delete a User
    // URL: http://localhost:8080/api/deleteUser/6
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
         
        Usuario note = repositorioUsuario.findById(id).orElse(null);
 
        repositorioUsuario.delete(note);
 
        return ResponseEntity.ok().build();
    }
	/*
	@PostMapping(path="/")
	public @ResponseBody Usuario añadirNuevoUsuario (@RequestParam String nombre,
	                                        @RequestParam String contrasenia
	                                        ) {
	    try {
	        
	        Usuario nuevoUsuario = new Usuario(nombre, contrasenia);
	        repositorioUsuario.save(nuevoUsuario);
	        return nuevoUsuario;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error");
	    }
	}

	@GetMapping(path="/")
	public @ResponseBody Iterable<Usuario> getAllUsuarios() {
	    return repositorioUsuario.findAll();
	}

	@GetMapping(path="/{id}")
	public @ResponseBody Optional<Usuario> getUsuario(@PathVariable int id) {
	    return repositorioUsuario.findById((long) id);
	}

	@PutMapping(path="/{id}")
	public @ResponseBody Usuario modificarUsuario(@PathVariable int id,
									@RequestParam String nombre,
						            @RequestParam String contrasenia
	                                       ) {
	   
	    Optional<Usuario> dbUsuarios = repositorioUsuario.findById((long) id);
	    if (dbUsuarios.isPresent()) {
	        Usuario usuarioModificado = dbUsuarios.get();
	        
	        usuarioModificado.setIdUsuario((long) id);
	        usuarioModificado.setNombre(nombre);
	        usuarioModificado.setContrasenia(contrasenia);
	        repositorioUsuario.save(usuarioModificado);
	
	        return usuarioModificado;
	    }
	
	    throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Unable to find resource");
	}

	@DeleteMapping(path = "/{id}")
	public @ResponseBody String borrarUsuario(@PathVariable int id) {
	    Optional<Usuario> dbUsuarios = repositorioUsuario.findById((long) id);
	    if (dbUsuarios.isPresent()) {
	        Usuario borrarUsuario = dbUsuarios.get();
	        repositorioUsuario.delete(borrarUsuario);
	
	        throw new ResponseStatusException(HttpStatus.OK, "Deleted");
	    }
	
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}
	*/
}
