package es.cic.taller;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
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
	  GridLayout sample;
	 
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	final VerticalLayout layout = new VerticalLayout();
        final TextField nombre = new TextField();
        final TextField apellidos = new TextField();
   	 	final TextField edad = new TextField();
   	 	final TextField domicilio = new TextField();
   	 	
   	 	TextField[] cuadrosTexto= {nombre,apellidos,edad,domicilio};
   	 	pintaTextField(nombre,"Introduzca nombre",30);
        
        pintaTextField(apellidos,"Introduzca apedillo",30);
        
        pintaTextField(edad,"Introduzca edad",3);
        
        pintaTextField(domicilio,"Introduzca domicilio",50);
 
        
        
        
        Button button = new Button("Clique");
        button.addClickListener( e -> {
        	 Notification.show(nombre.getValue() +" "+ apellidos.getValue() +" de "+ edad.getValue() +" años vive en " + domicilio.getValue(),
                     Type.TRAY_NOTIFICATION);
        });
        
       
        Label[] etiquetas= {new Label("Nombre:"),new Label("Apellidos:"),new Label("Edad:"),new Label("Domicilio:")};
        
        sample = new GridLayout();
        sample.addStyleName("outlined");
        sample.setSizeFull();
 
        generateMatrixGrid(4, 2,etiquetas,cuadrosTexto);
        layout.addComponents(sample,button);
        setContent(layout);
 

        
    }
    private void pintaTextField(TextField texto,String placeholder,int maxLength) {
          texto.setPlaceholder(placeholder);
          texto.setMaxLength(maxLength);
    }

	

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    private void generateMatrixGrid(final int rows, final int columns,Label[] etiquetas,TextField[] cuadrosTexto) {
        sample.removeAllComponents();
        sample.setRows(rows);
        sample.setColumns(columns);
 
        for (int row = 0; row < sample.getRows(); row++) {
            for (int col = 0; col < sample.getColumns(); col++) {
                if (col==0) {
                	Label etiqueta = etiquetas[row];
                	sample.addComponent(etiqueta);
                }else {
                	TextField cuadroTexto = cuadrosTexto[row];
                	sample.addComponent(cuadroTexto);
                }
                sample.setRowExpandRatio(row, 0.0f);
                sample.setColumnExpandRatio(col, 0.0f);
            }
        }
    }
   
}