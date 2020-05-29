
package Vista;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import Modelo.Tarea;
import Repositorio.RepositorioTarea;

public class TareaEditor extends VerticalLayout implements KeyNotifier{

private RepositorioTarea repositorioTarea;
	
	private Tarea tarea;
	private Long IdLista;
	
	TextField Nombre = new TextField("Nombre");
	TextField Descripcion = new TextField("Descripcion");
	TextField Prioridad = new TextField("Prioridad");
	
	Button Guardar = new Button("Guardar", VaadinIcon.CHECK.create());
	Button Cancelar  = new Button("Cancelar");
	Button Borrar = new Button("Borrar", VaadinIcon.TRASH.create());
	HorizontalLayout acciones = new HorizontalLayout(Guardar, Cancelar, Borrar);
		
	Binder<Tarea> binder = new Binder<>(Tarea.class);
	
	private ChangeHandler changehandler;
	
	@Autowired
	public TareaEditor( RepositorioTarea repositorioTarea,  Long idLista)
	{
		this.IdLista = idLista;
		this.repositorioTarea = repositorioTarea;
		
		add(Nombre, Descripcion, Prioridad);
		
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
		repositorioTarea.save(tarea);
		changehandler.onChange();
	}
	
	void Borrar()
	{
		repositorioTarea.delete(tarea);
		changehandler.onChange();
	}
	
	public interface ChangeHandler
	{
		void onChange();
	}
	
	public final void editarTarea(Tarea u)
	{
		if(u == null)
		{
			setVisible(false);
			return;
		}
		final boolean persisted = u.getIdTarea() != null;
		if(persisted) 
		{
			tarea = repositorioTarea.findById(u.getIdTarea()).get();
		}
		else
		{
			tarea = u;
		}
		Cancelar.setVisible(persisted);
		
		binder.setBean(tarea);
		
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
