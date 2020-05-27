package Vista;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import Modelo.ListaTareas;
import Repositorio.RepositorioLista;

public class ListaTareaEditor extends VerticalLayout implements KeyNotifier {

private RepositorioLista repositorioLista;
	
	private ListaTareas listaTareas;
	private Long IdUsuario;
	
	TextField Nombre = new TextField("Nombre");
	
	Button Guardar = new Button("Guardar", VaadinIcon.CHECK.create());
	Button Cancelar  = new Button("Cancelar");
	Button Borrar = new Button("Borrar", VaadinIcon.TRASH.create());
	HorizontalLayout acciones = new HorizontalLayout(Guardar, Cancelar, Borrar);

	Binder<ListaTareas> binder = new Binder<>(ListaTareas.class);
	
	private ChangeHandler changehandler;
	
	@Autowired
	public ListaTareaEditor( RepositorioLista repositorioLista,  Long idUsuario)
	{
		this.IdUsuario = idUsuario;
		this.repositorioLista = repositorioLista;
		
		add(Nombre);
		
		binder.bindInstanceFields(this);
		
		setSpacing(true);
		
		Guardar.getElement().getThemeList().add("primary");
		Borrar.getElement().getThemeList().add("error");
		
		Guardar.addClickListener(e -> Guardar());
		Borrar.addClickListener(e -> Borrar());
		Cancelar.addClickListener(e -> Cancelar());
		setVisible(false);
		
	}
	
	void Guardar()
	{
		repositorioLista.save(listaTareas);
		changehandler.onChange();
	}
	
	void Borrar()
	{
		repositorioLista.delete(listaTareas);
		changehandler.onChange();
	}
	
	public interface ChangeHandler
	{
		void onChange();
	}
	
	public final void editarListaTareas(ListaTareas listaTarea)
	{
		if(listaTarea == null)
		{
			setVisible(false);
			return;
		}
		final boolean persisted = listaTarea.getIdListaTareas() != null;
		if(persisted) 
		{
			listaTareas = repositorioLista.findById(listaTarea.getIdListaTareas()).get();
		}
		else
		{
			listaTareas = listaTarea;
		}
		Cancelar.setVisible(persisted);
		
		binder.setBean(listaTareas);
		
		setVisible(true);
		
		Nombre.focus();
		
	}
	
	public void setChangeHandler(ChangeHandler h)
	{
		changehandler = h;
	}
	void Cancelar()
	{
		setVisible(false);

	}
}
