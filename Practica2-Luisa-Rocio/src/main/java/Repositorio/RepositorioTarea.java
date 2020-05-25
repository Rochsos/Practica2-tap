package Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Modelo.Tarea;

public interface RepositorioTarea extends JpaRepository<Tarea, Long>{
	
	List<Tarea> FindByName(String Nombre);

}
