// Interfaz de tipo repositorio que nos va a permitir realizar operaciones sobre la base de datos
package Repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Modelo.ListaTareas;

@Repository
public interface RepositorioLista extends JpaRepository<ListaTareas, Long>{

	List<ListaTareas> findBynombre(String nombre);

}
