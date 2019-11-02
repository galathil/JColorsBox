package app;

import java.awt.Color;

import frames.MainFrame;

public class JColorsBox {

	public static MainFrame mainFrame;
	public static Color refColor;
	
	public static void main(String[] args) {
		mainFrame = new MainFrame();
		refColor = new Color(255,255,255,255);
		updateUI();
	}
	
	
	public static Color getRefColor() {
		return refColor;
	}
	
	public static void updateUI() {

		mainFrame.previewPanel.setBackground(refColor);
		
		mainFrame.redSlider.setValue(refColor.getRed());
		mainFrame.greenSlider.setValue(refColor.getGreen());
		mainFrame.blueSlider.setValue(refColor.getBlue());
		mainFrame.alphaSlider.setValue(refColor.getAlpha());
		
		mainFrame.redTextField.setText(Integer.toString(refColor.getRed()));
		mainFrame.greenTextField.setText(Integer.toString(refColor.getGreen()));
		mainFrame.blueTextField.setText(Integer.toString(refColor.getBlue()));
		mainFrame.alphaTextField.setText(Integer.toString(refColor.getAlpha()));
		
		mainFrame.hexColorField.setText(String.format("#%02x%02x%02x", refColor.getRed(), refColor.getGreen(), refColor.getBlue()));
		int alpha = Math.round((float)refColor.getAlpha()*100/255);
		mainFrame.cssColorField.setText(String.format("rgba(%d,%d,%d,%d)", refColor.getRed(), refColor.getGreen(), refColor.getBlue(),alpha));
		
		mainFrame.pickerButton.setEnabled(!mainFrame.pickerListener.isWaitingForPick());
		System.gc();
	}
}
