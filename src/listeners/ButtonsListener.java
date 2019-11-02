package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.JColorsBox;

public class ButtonsListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		JColorsBox.mainFrame.pickerListener.setWaitingForPick(true);
		JColorsBox.updateUI();
	}

}
