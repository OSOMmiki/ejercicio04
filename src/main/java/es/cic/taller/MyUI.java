package es.cic.taller;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
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
	 
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final VerticalLayout layoutNombres = new VerticalLayout();
        final HorizontalLayout layoutFinal = new HorizontalLayout();
        final TextField nombre = new TextField();
        final TextField apellidos = new TextField();
   	 	final TextField edad = new TextField();
   	 	final TextField domicilio = new TextField();
   	 	
   	 	pintaTextField(nombre,"Introduzca nombre",30);
        nombre.addValueChangeListener(event -> updateCaption(event.getValue().length(),nombre));
        
        pintaTextField(apellidos,"Introduzca apedillo",30);
        apellidos.addValueChangeListener(event -> updateCaption(event.getValue().length(),apellidos));
        
        pintaTextField(edad,"Introduzca edad",3);
        edad.addValueChangeListener(event -> updateCaption(event.getValue().length(),edad));
        
        pintaTextField(domicilio,"Introduzca domicilio",50);
        domicilio.addValueChangeListener(event -> updateCaption(event.getValue().length(),domicilio));
        
        
        
        Button button = new Button("Clique");
        button.addClickListener( e -> {
        	 Notification.show(nombre.getValue() +" "+ apellidos.getValue() +" de "+ edad.getValue() +" años vive en " + domicilio.getValue(),
                     Type.TRAY_NOTIFICATION);
        });
        
       
        layoutNombres.addComponents(new Label("Nombre:"),new Label("Apellidos:"),new Label("Edad:"),new Label("Domicilio:"));
        setContent(layoutNombres);
        layout.addComponents(nombre,apellidos,edad,domicilio, button);
        setContent(layout);
        layoutFinal.addComponents(layoutNombres,layout);
        setContent(layoutFinal);
        
    }
    private void pintaTextField(TextField texto,String placeholder,int maxLength) {
          texto.setPlaceholder(placeholder);
          texto.setMaxLength(maxLength);
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