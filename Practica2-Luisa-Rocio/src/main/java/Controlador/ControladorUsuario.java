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

import Modelo.Usuario;
import Repositorio.RepositorioUsuario;


@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

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
}
