package listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.JColorsBox;

public class TextFieldsListener implements DocumentListener, FocusListener{

	
	// @Override
	public void changedUpdate(DocumentEvent event) {
		proceed(event);
		
	}

	// @Override
	public void insertUpdate(DocumentEvent event) {
		proceed(event);
		
	}

	// @Override
	public void removeUpdate(DocumentEvent event) {
		proceed(event);
		
	}
	
	public void proceed(DocumentEvent event) {
		Color newColor = null;
		
		int red = JColorsBox.refColor.getRed();
		int green = JColorsBox.refColor.getGreen();
		int blue = JColorsBox.refColor.getBlue();
		int alpha = JColorsBox.refColor.getAlpha();
		
		try {
			if(event.getDocument()==JColorsBox.mainFrame.hexColorField.getDocument() && 
					!JColorsBox.mainFrame.hexColorField.getText().equals("")) {
				newColor = Color.decode(JColorsBox.mainFrame.hexColorField.getText());
				newColor = new Color(newColor.getRed(),newColor.getGreen(),newColor.getBlue(),alpha);
			} else if(event.getDocument()==JColorsBox.mainFrame.redTextField.getDocument()) {
				red = Integer.parseInt(JColorsBox.mainFrame.redTextField.getText());
				newColor = new Color(red,green,blue,alpha);
			} else if(event.getDocument()==JColorsBox.mainFrame.greenTextField.getDocument()) {
				green = Integer.parseInt(JColorsBox.mainFrame.greenTextField.getText());
				newColor = new Color(red,green,blue,alpha);
			} else if(event.getDocument()==JColorsBox.mainFrame.blueTextField.getDocument()) {
				blue = Integer.parseInt(JColorsBox.mainFrame.blueTextField.getText());
				newColor = new Color(red,green,blue,alpha);
			} else if(event.getDocument()==JColorsBox.mainFrame.alphaTextField.getDocument()) {
				alpha = Integer.parseInt(JColorsBox.mainFrame.alphaTextField.getText());
				newColor = new Color(red,green,blue,alpha);
			}
		} catch(NumberFormatException e) {}
		
		if(newColor!=null){
			if(newColor.getRed()!= JColorsBox.refColor.getRed() || 
				newColor.getGreen()!= JColorsBox.refColor.getGreen() || 
				newColor.getBlue()!= JColorsBox.refColor.getBlue() ||
				newColor.getAlpha()!= JColorsBox.refColor.getAlpha()) {
				JColorsBox.refColor = newColor;
			}
		}
	}

	@Override
	public void focusGained(FocusEvent event) {
		JTextField field = (JTextField)event.getSource();
		field.selectAll();
	}

	@Override
	public void focusLost(FocusEvent event) {
		JColorsBox.updateUI();
	}
}
