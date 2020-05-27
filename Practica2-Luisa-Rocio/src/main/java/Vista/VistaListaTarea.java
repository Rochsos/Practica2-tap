package Vista;

import org.springframework.util.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import Modelo.ListaTareas;
import Repositorio.RepositorioLista;


@Route("ListaTarea")
public class VistaListaTarea extends VerticalLayout{

	private final RepositorioLista repositorioLista;
	private final ListaTareaEditor listaTareasEditor;
	
	final Grid<ListaTareas> grid;
	
	final TextField filter;
	
	private final Button addNewButton;
	
	private Long IdUsuario;
	
	public VistaListaTarea(RepositorioLista repositorioLista, ListaTareaEditor listaTareasEditor, Long idUsuario) {
		this.repositorioLista = repositorioLista;
		this.IdUsuario = idUsuario;
		this.listaTareasEditor = listaTareasEditor;
		this.grid = new Grid<>(ListaTareas.class);
		this.filter = new TextField();
		this.addNewButton = new Button("Nueva tarea", VaadinIcon.PLUS.create());

		HorizontalLayout actions = new HorizontalLayout(filter, addNewButton);
		add(actions, grid, listaTareasEditor);

		grid.setHeight("300px");
		grid.setColumns("IdListaTareas", "Nombre", "Descripcion");
		grid.getColumnByKey("IdListaTareas").setWidth("50px").setFlexGrow(0);

		filter.setPlaceholder("Filtrar por nombre");

		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listaListasTareas(e.getValue()));

		grid.asSingleSelect().addValueChangeListener(e -> {
			listaTareasEditor.editarListaTareas(e.getValue());
		});

		addNewButton.addClickListener(e -> listaTareasEditor.editarListaTareas(new ListaTareas("", IdUsuario)));

		listaTareasEditor.setChangeHandler(() -> {
			listaTareasEditor.setVisible(false);
			listaListasTareas(filter.getValue());
		});

		listaListasTareas(null);
    }

	void listaListasTareas(String filterText)
	{
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repositorioLista.findAll());
		}
		else {
			grid.setItems(repositorioLista.FindByName(filterText));
		}
	}
}
