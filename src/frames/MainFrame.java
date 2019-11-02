package frames;

import java.awt.AWTEvent;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import listeners.SliderChangeListener;
import listeners.TextFieldsListener;
import listeners.ButtonsListener;
import listeners.PickerListener;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 3638872605243330343L;
	
	public JPanel previewPanel;
	
	public JSlider redSlider;
	public JSlider greenSlider;
	public JSlider blueSlider;
	public JSlider alphaSlider;
	
	public JTextField redTextField;
	public JTextField greenTextField;
	public JTextField blueTextField;
	public JTextField alphaTextField;
	
	public JTextField cssColorField;
	public JTextField hexColorField;
	
	public PickerListener pickerListener;
	
	public JButton pickerButton;
	
	
	public MainFrame() {
		super("JColorsBox");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		Container mainPanel = getContentPane();
		mainPanel.setLayout(new GridBagLayout());
		
		
		JPanel leftPanel = new JPanel(new GridBagLayout());
		JPanel rightPanel = new JPanel(new GridBagLayout());
		
		
		redSlider = new JSlider(0, 255);
		greenSlider = new JSlider(0, 255);
		blueSlider = new JSlider(0, 255);
		alphaSlider = new JSlider(0, 255);
		
		SliderChangeListener scl = new SliderChangeListener();
		redSlider.addChangeListener(scl);
		greenSlider.addChangeListener(scl);
		blueSlider.addChangeListener(scl);
		alphaSlider.addChangeListener(scl);
		
		
		redTextField = new JTextField(3);
		greenTextField = new JTextField(3);
		blueTextField = new JTextField(3);
		alphaTextField = new JTextField(3);
		
		TextFieldsListener tfl = new TextFieldsListener();
		redTextField.getDocument().addDocumentListener(tfl);
		greenTextField.getDocument().addDocumentListener(tfl);
		blueTextField.getDocument().addDocumentListener(tfl);
		alphaTextField.getDocument().addDocumentListener(tfl);
		
		redTextField.addFocusListener(tfl);
		greenTextField.addFocusListener(tfl);
		blueTextField.addFocusListener(tfl);
		alphaTextField.addFocusListener(tfl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridwidth = 1;
		leftPanel.add(new JLabel("Rouge"),gbc);
		gbc.gridwidth = 2;
		leftPanel.add(redSlider,gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		leftPanel.add(redTextField,gbc);
		
		gbc.gridwidth = 1;
		leftPanel.add(new JLabel("Vert"),gbc);
		gbc.gridwidth = 2;
		leftPanel.add(greenSlider,gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		leftPanel.add(greenTextField,gbc);
		
		gbc.gridwidth = 1;
		leftPanel.add(new JLabel("Bleu"),gbc);
		gbc.gridwidth = 2;
		leftPanel.add(blueSlider,gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		leftPanel.add(blueTextField,gbc);
		
		gbc.gridwidth = 1;
		leftPanel.add(new JLabel("Alpha"),gbc);
		gbc.gridwidth = 2;
		leftPanel.add(alphaSlider,gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		leftPanel.add(alphaTextField,gbc);
		
		gbc.gridwidth = 1;
		leftPanel.add(new JLabel("CSS"),gbc);
		cssColorField = new JTextField();
		cssColorField.setPreferredSize(new Dimension(185,20));
		cssColorField.getDocument().addDocumentListener(tfl);
		cssColorField.addFocusListener(tfl);
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbc.gridheight=GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5+7, 5, 5);
		leftPanel.add(cssColorField,gbc);


		gbc.gridwidth = 1;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(0,0,0,0);
		
		mainPanel.add(leftPanel,gbc);

		pickerButton = new JButton();
		pickerButton.setPreferredSize(new Dimension(70, 26));
		pickerButton.addActionListener(new ButtonsListener());
		try {
			ImageIcon pickIcon = new ImageIcon(ImageIO.read(getClass().getResource("/img/color-picker.png")));
			setIconImage(pickIcon.getImage());
			pickerButton.setIcon(
				new ImageIcon(
					pickIcon.getImage().getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH )
				)
			);

		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.insets = new Insets(5, 5, 5, 5);
		rightPanel.add(pickerButton,gbc);

		
		// Prevent visual glitches (https://tips4java.wordpress.com/2009/05/31/backgrounds-with-transparency/)
		previewPanel = new JPanel()
		{
			private static final long serialVersionUID = 311938774621213198L;

			protected void paintComponent(Graphics g)
		    {
		        g.setColor( getBackground() );
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		previewPanel.setOpaque(false);
		previewPanel.setMinimumSize(new Dimension(70,70));
		previewPanel.setPreferredSize(new Dimension(70,70));
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbc.gridheight=2;
		rightPanel.add(previewPanel,gbc);
		
		hexColorField = new JTextField();
		hexColorField.getDocument().addDocumentListener(tfl);
		hexColorField.setPreferredSize(new Dimension(70, 20));
		hexColorField.addFocusListener(tfl);
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbc.gridheight=GridBagConstraints.REMAINDER;;
		rightPanel.add(hexColorField,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		mainPanel.add(rightPanel,gbc);
		
		pickerListener = new PickerListener();
		Toolkit.getDefaultToolkit().addAWTEventListener(
				pickerListener, 
				AWTEvent.MOUSE_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK
		);
		
		pack();
		setVisible(true);
	}
}
