/*package Vista;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.stereotype.Service;

import com.vaadin.data.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.icons.*;

import Modelo.Usuario;
import Repositorio.RepositorioUsuario;

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

		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewButton);
		add(actions, gridUsuario, editorUsuario);

		gridUsuario.setHeight("300px");
		gridUsuario.setColumns("IdUsuario", "Nombre", "Email");
		gridUsuario.getColumnByKey("IdUsuario").setWidth("50px").setFlexGrow(0);

		filter.setPlaceholder("Filtrar por nombre");
		
		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listaUsuarios(e.getValue()));
	
		// Connect selected Customer to editor or hide if none is selected
		gridUsuario.asSingleSelect().addValueChangeListener(e -> {
			editorUsuario.editarUsuario(e.getValue());
		});
	
		// Instantiate and edit new Customer the new button is clicked
		addNewButton.addClickListener(e -> editorUsuario.editarUsuario(new Usuario("", "", "")));
	
		// Listen changes made by the editor, refresh data from backend
		editorUsuario.setChangeHandler(() -> {
			editor.setVisible(false);
			listaUsuarios(filter.getValue());
		});
	
		// Initialize listing
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
}*/