package es.cic.taller;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	 final TextField nombre = new TextField();
	 final TextField apellidos = new TextField();
	 final TextField edad = new TextField();
	 final TextField domicilio = new TextField();
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
       
        nombre.setCaption("Nombre:");
        nombre.setPlaceholder("Introduzca su nombre");
        nombre.setMaxLength(30);
        nombre.addValueChangeListener(event -> updateCaption(event.getValue().length(),nombre));
        
        apellidos.setCaption("Apellidos:");
        apellidos.setPlaceholder("Introduzca sus apellidos");
        apellidos.setMaxLength(30);
        apellidos.addValueChangeListener(event -> updateCaption(event.getValue().length(),apellidos));
        
        edad.setCaption("Edad:");
        edad.setPlaceholder("Introduzca su edad");
        edad.setMaxLength(3);
        edad.addValueChangeListener(event -> updateCaption(event.getValue().length(),edad));
        
        domicilio.setCaption("Domicilio:");
        domicilio.setPlaceholder("Introduzca su direccion");
        domicilio.setMaxLength(50);
        domicilio.addValueChangeListener(event -> updateCaption(event.getValue().length(),domicilio));
        
        
        
        Button button = new Button("Clique");
        button.addClickListener( e -> {
            layout.addComponent(new Label(nombre.getValue() + apellidos.getValue() +" de "+ edad.getValue() +" años vive en " + domicilio.getValue()));
        });
        
       
        
        layout.addComponents(nombre,apellidos,edad,domicilio, button);
        
        setContent(layout);
    }

    private void updateCaption(final int textLength, final TextField texto) {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(textLength));
        if (texto.getMaxLength() >= 0) {
            builder.append("/").append(texto.getMaxLength());
        }
        builder.append(" caracteres");
        texto.setCaption(builder.toString());
    }
	

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}