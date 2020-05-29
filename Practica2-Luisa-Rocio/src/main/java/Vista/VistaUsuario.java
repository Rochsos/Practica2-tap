
package Vista;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import Modelo.Usuario;
import Repositorio.RepositorioUsuario;


@Route("Usuario")
public class VistaUsuario extends VerticalLayout {
	
	private final RepositorioUsuario repositorioUsuario;
	private final UsuarioEditor editorUsuario;
	
	final Grid<Usuario> gridUsuario;
	
	final TextField filter;
	
	private final Button addNewButton;
	
	public VistaUsuario(RepositorioUsuario repositorioUsuario, UsuarioEditor editorUsuario) {
		this.repositorioUsuario = repositorioUsuario;
		this.editorUsuario = editorUsuario;
		this.gridUsuario = new Grid<>(Usuario.class);
		this.filter = new TextField();
		this.addNewButton = new Button("Nuevo Usuario", VaadinIcon.PLUS.create());


		HorizontalLayout actions = new HorizontalLayout(filter, addNewButton);
		add(actions, gridUsuario, editorUsuario);

		gridUsuario.setHeight("300px");
		gridUsuario.setColumns("IdUsuario", "Nombre", "Email");
		gridUsuario.getColumnByKey("IdUsuario").setWidth("50px").setFlexGrow(0);

		filter.setPlaceholder("Filtrar por nombre");

		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listaUsuarios(e.getValue()));
	

		gridUsuario.asSingleSelect().addValueChangeListener(e -> {
			editorUsuario.editUsuario(e.getValue());
		});
	
		addNewButton.addClickListener(e -> editorUsuario.editUsuario(new Usuario("", "")));	

		editorUsuario.setChangeHandler(() -> {
			editorUsuario.setVisible(false);
			listaUsuarios(filter.getValue());
		});
	
		listaUsuarios(null);
	}
	
	void listaUsuarios(String filterText)
	{
		gridUsuario.setItems(repositorioUsuario.findAll());
	}
	
	@Service
	public static class MyService
	{
		public String sayHi()
		{
			return "Hello there" + LocalDateTime.now();
		}
	}
}
