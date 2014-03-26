package scheduleGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
 * This class takes a string of the day's name and Config, and using this
 * checks all of the actions related to the day within the Config provided
 * 
 */

public class DayListener implements ActionListener {

	Config con;
	String dayName;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		con.dayCheckActionPerformed(arg0, dayName);
	}
	
	public DayListener(Config con, String dayName){
		this.con = con;
		this.dayName = dayName;
	}

}
