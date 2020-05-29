
package Vista;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import Modelo.Tarea;
import Repositorio.RepositorioTarea;


@Route("Tarea")
public class VistaTarea extends VerticalLayout{

	private final RepositorioTarea repositorioTarea;
	private final TareaEditor editorTarea;
	
	final Grid<Tarea> grid;
	
	final TextField filter;
	
	private final Button addNewButton;
	
	private Long IdLista;
	
	public VistaTarea(RepositorioTarea repositorioTarea, TareaEditor editorTarea, Long idLista) {
		this.repositorioTarea = repositorioTarea;
		this.IdLista = idLista;
		this.editorTarea = editorTarea;
		this.grid = new Grid<>(Tarea.class);
		this.filter = new TextField();
		this.addNewButton = new Button("Nueva tarea", VaadinIcon.PLUS.create());


		HorizontalLayout actions = new HorizontalLayout(filter, addNewButton);
		add(actions, grid, editorTarea);

		grid.setHeight("300px");
		grid.setColumns("IdTarea", "Nombre", "Descripcion");
		grid.getColumnByKey("IdTarea").setWidth("50px").setFlexGrow(0);

		filter.setPlaceholder("Filtrar por nombre");


		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listaTareas(e.getValue()));


		grid.asSingleSelect().addValueChangeListener(e -> {
			editorTarea.editarTarea(e.getValue());
		});

		
		addNewButton.addClickListener(e -> editorTarea.editarTarea(new Tarea("", "", "", "", IdLista)));

		editorTarea.setChangeHandler(() -> {
			editorTarea.setVisible(false);
			listaTareas(filter.getValue());
		});

		// Initialize listing
		listaTareas(null);
    }

	void listaTareas(String filterText)
	{
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repositorioTarea.findAll());
		}
		else {
			grid.setItems(repositorioTarea.findBynombre(filterText));
		}
	}
}
