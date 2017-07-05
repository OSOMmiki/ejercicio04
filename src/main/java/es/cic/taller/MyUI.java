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
	 final TextField name = new TextField();
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
       
        name.setCaption("Escribe lo que quieras aqui:");
        name.addValueChangeListener(event -> updateCaption(event.getValue().length()));
        name .setMaxLength(25);
        name.addValueChangeListener(event -> Notification.show("Texto cambiado:",
                String.valueOf(event.getValue()),
                Type.TRAY_NOTIFICATION));
        
        Button button = new Button("Clique");
        button.addClickListener( e -> {
            layout.addComponent(new Label(name.getValue() ));
        });
        
        Button miBoton = new Button("Pulse");
        miBoton.addClickListener( event -> layout.addComponent(new Label("Este botón no hace nada, es troll")));
        
        Button otroBoton = new Button("¿?");
        otroBoton.addFocusListener(event -> layout.addComponent(new Label("Pero")));
        otroBoton.addClickListener( event -> layout.addComponent(new Label("Pero que es ezto?!")));
         
        
        layout.addComponents(name, button, miBoton,otroBoton);
        
        setContent(layout);
    }

    private void updateCaption(final int textLength) {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(textLength));
        if (name.getMaxLength() >= 0) {
            builder.append("/").append(name.getMaxLength());
        }
        builder.append(" caracteres");
        name.setCaption(builder.toString());
    }
	

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}