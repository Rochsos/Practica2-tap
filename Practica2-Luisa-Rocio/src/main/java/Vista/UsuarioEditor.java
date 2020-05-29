
package Vista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import Modelo.Usuario;
import Repositorio.RepositorioUsuario;


public class UsuarioEditor extends VerticalLayout implements KeyNotifier {
	
	private final RepositorioUsuario repositorioUsuario;
	private Usuario usuario;
	TextField nombre = new TextField("Nombre de usuario: ");
	TextField contrasenia = new TextField("Contraseña: ");
	Button guardar = new Button("Guardar", VaadinIcon.CHECK.create());
	Button cancelar = new Button("Cancelar");
	Button borrar = new Button("Borrar", VaadinIcon.TRASH.create());
	HorizontalLayout acciones = new HorizontalLayout(guardar, cancelar, borrar);
	Binder<Usuario> binder = new Binder<>(Usuario.class);
	private ChangeHandler changeHandler;
	
	@Autowired
	public UsuarioEditor(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
		add(nombre, contrasenia, acciones);
		// bind using naming convention
		binder.bindInstanceFields(this);
		// Configure and style components
		setSpacing(true);
		guardar.getElement().getThemeList().add("primary");
		borrar.getElement().getThemeList().add("error");
		// wire action buttons to save, delete and reset
		guardar.addClickListener(e -> guardar());
		borrar.addClickListener(e -> borrar());
		cancelar.addClickListener(e -> cancelar());
		setVisible(false);
	}
	void borrar() {
		repositorioUsuario.delete(usuario);
		changeHandler.onChange();
	}
	void guardar() {
		repositorioUsuario.save(usuario);
		changeHandler.onChange();
	}
	
	void cancelar()
	{
		setVisible(false);
	}
	public interface ChangeHandler {
		void onChange();
	}
	public final void editUsuario(Usuario u) {
		if (u == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = u.getIdUsuario() != null;
		if (persisted) {
			
			usuario = repositorioUsuario.findById(u.getIdUsuario()).get();
		}
		else {
			usuario = u;
		}
		cancelar.setVisible(persisted);
		
		binder.setBean(usuario);
		setVisible(true);
		
		nombre.focus();
	}
	public void setChangeHandler(ChangeHandler h) {

		changeHandler = h;
	}
}
