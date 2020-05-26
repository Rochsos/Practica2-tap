/*
package Vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;

import com.vaadin.data.Binder;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.flow.component.icon.VaadinIcon;

import Modelo.Usuario;
import Repositorio.RepositorioUsuario;


public class VistaUsuario extends VerticalLayout implements KeyNotifier {
	
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
	public VistaUsuario(RepositorioUsuario repositorioUsuario) {
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
	
	void Cancelar()
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
			// Find fresh entity for editing
			usuario = repositorioUsuario.findById(u.getIdUsuario()).get();
		}
		else {
			usuario = u;
		}
		cancelar.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(usuario);

		setVisible(true);

		// Focus first name initially
		nombre.focus();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		changeHandler = h;
	}
}
*/