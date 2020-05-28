// Interfaz de tipo repositorio que nos va a permitir realizar operaciones sobre la base de datos
package Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Modelo.Tarea;

@Repository
public interface RepositorioTarea extends JpaRepository<Tarea, Long>{
	
	List<Tarea> findBynombre(String nombre);

}
