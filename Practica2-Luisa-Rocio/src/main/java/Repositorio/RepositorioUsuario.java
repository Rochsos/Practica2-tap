package Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Modelo.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long>{
	
	List<Usuario> FindByName(String Nombre);

}
