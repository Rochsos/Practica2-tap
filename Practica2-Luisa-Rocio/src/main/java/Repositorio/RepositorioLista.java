package Repositorio;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import Modelo.ListaTareas;



public interface RepositorioLista extends JpaRepository<ListaTareas, Long>{
	@Bean
	List<ListaTareas> FindByName(String Nombre);

}
