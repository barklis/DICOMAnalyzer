package pl.edu.pw.fizyka.pojava.BozekKlis.application.EventHandlers;

import javax.swing.JColorChooser;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import pl.edu.pw.fizyka.pojava.BozekKlis.application.Preferences;
import pl.edu.pw.fizyka.pojava.BozekKlis.application.GUI.GUI;

public class ContourLineColorHandler implements EventHandler<ActionEvent> {

GUI gui;
	
	public ContourLineColorHandler(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void handle(ActionEvent arg0) {
		Thread chooseColor = new Thread(new Runnable() {
			@Override
			public void run() {
				int r = (int) (255*Preferences.getContourLineColor().getRed());
				int g = (int) (255*Preferences.getContourLineColor().getGreen());
				int b = (int) (255*Preferences.getContourLineColor().getBlue());
				
				java.awt.Color result = JColorChooser.showDialog(null, "Choose color", new java.awt.Color(r,g,b));
				if(result != null) {
					Color newColor = Color.rgb(result.getRed(), result.getGreen(), result.getBlue());
					Preferences.setContourLineColor(newColor);
				}
			}
		});
		chooseColor.start();
		
		try {
			chooseColor.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gui.getCenterPanel().getDrawingPanel().getCanvasPanel().drawFrame();
	}
}