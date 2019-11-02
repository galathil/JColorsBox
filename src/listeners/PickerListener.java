package listeners;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.AWTEventListener;
import java.awt.event.FocusEvent;

import app.JColorsBox;

public class PickerListener implements AWTEventListener {
	
	private boolean waitingForPick = false;

    public void eventDispatched(AWTEvent event) {
        if(waitingForPick && event.getID()==FocusEvent.FOCUS_LOST) {
        	FocusEvent focusEvent = (FocusEvent)event;
        	if(focusEvent.isTemporary()) {
                
                Point p = MouseInfo.getPointerInfo().getLocation();
                
        		int alpha = JColorsBox.refColor.getAlpha();
        		Color newColor = null;
                try {
                    Color tmpColor = new Robot().getPixelColor(p.x, p.y);
                    newColor = new Color(tmpColor.getRed(),tmpColor.getGreen(),tmpColor.getBlue(),alpha);
                } 
                catch (Exception e) {}
                
                setWaitingForPick(false);
                if(newColor!=null){
        			if(newColor.getRed()!= JColorsBox.refColor.getRed() || 
        				newColor.getGreen()!= JColorsBox.refColor.getGreen() || 
        				newColor.getBlue()!= JColorsBox.refColor.getBlue() ||
        				newColor.getAlpha()!= JColorsBox.refColor.getAlpha()) {
        				JColorsBox.refColor = newColor;
        			}
        		}
                JColorsBox.updateUI();
        	}
        }
    }

	public boolean isWaitingForPick() {
		return waitingForPick;
	}

	public void setWaitingForPick(boolean waitingForPick) {
		this.waitingForPick = waitingForPick;
	}
    
}
