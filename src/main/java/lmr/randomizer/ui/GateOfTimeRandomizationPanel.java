package lmr.randomizer.ui;

import lmr.randomizer.Settings;
import lmr.randomizer.Translations;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class GateOfTimeRandomizationPanel extends JPanel {
	private ButtonGroup gateOfTimeItem;
	private JLabel gateOfTimeItemLabel;
	
	private JRadioButton random;
	private JRadioButton mapOnly;
	
	public GateOfTimeRandomizationPanel() {
		super(new MigLayout("gapy 0, insets 8 0 0 0"));
		
		gateOfTimeItemLabel = new JLabel(Translations.getText("randomization.gateOfTimeItem"), JLabel.LEFT);
        add(gateOfTimeItemLabel);
        
        gateOfTimeItem = new ButtonGroup();
        
        random = new JRadioButton(Translations.getText("randomization.random"));
        random.setActionCommand("RANDOM");
        gateOfTimeItem.add(random);
        
        mapOnly = new JRadioButton(Translations.getText("randomization.gateOfTimeItem.mapOnly"));
        mapOnly.setActionCommand("MAPONLY");
        gateOfTimeItem.add(mapOnly);
        
        add(random);
        add(mapOnly);
        
        if (Settings.isGoTFullRandom()) {
        	random.setSelected(true);
        } else {
        	mapOnly.setSelected(true);
        }
	}
	
	public void updateSettings() {
		if ("RANDOM".equals(gateOfTimeItem.getSelection().getActionCommand())) {
			Settings.setGoTFullRandom(true, true);
		} else {
			Settings.setGoTFullRandom(false, true);
		}
	}
	
	public void updateTranslations() {
		gateOfTimeItemLabel.setText(Translations.getText("randomization.gateOfTimeItem"));
		random.setText(Translations.getText("randomization.random"));
		mapOnly.setText(Translations.getText("randomization.gateOfTimeItem.mapOnly"));
	}
	
	public void reloadSettings() {
        if (Settings.isGoTFullRandom()) {
        	random.setSelected(true);
        } else {
        	mapOnly.setSelected(true);
        }
	}
}