package scheduleGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class takes a string of the day's name and Config, and using this
 * adds a job in the respectful day within the Config provided
 * 
 */

public class AddListener implements ActionListener {

	Config con;
	String dayName;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		con.addJob(dayName);
	}
	
	public AddListener(Config con, String dayName){
		this.con = con;
		this.dayName = dayName;
	}

}
