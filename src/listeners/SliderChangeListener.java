package listeners;

import java.awt.Color;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.JColorsBox;

public class SliderChangeListener implements ChangeListener{

	@Override
	public void stateChanged(ChangeEvent event) {
		
		int red = JColorsBox.refColor.getRed();
		int green = JColorsBox.refColor.getGreen();
		int blue = JColorsBox.refColor.getBlue();
		int alpha = JColorsBox.refColor.getAlpha();
		
		if(event.getSource().equals(JColorsBox.mainFrame.redSlider)) {
			red = JColorsBox.mainFrame.redSlider.getValue();
		} else if(event.getSource().equals(JColorsBox.mainFrame.greenSlider)) {
			green = JColorsBox.mainFrame.greenSlider.getValue();
		} else if(event.getSource().equals(JColorsBox.mainFrame.blueSlider)) {
			blue = JColorsBox.mainFrame.blueSlider.getValue();
		} else if(event.getSource().equals(JColorsBox.mainFrame.alphaSlider)) {
			alpha = JColorsBox.mainFrame.alphaSlider.getValue();
		}
		
		JColorsBox.refColor = new Color(red,green,blue,alpha);
		JColorsBox.updateUI();
	}

}
