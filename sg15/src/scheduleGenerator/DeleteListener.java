package scheduleGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class takes a string of the day's name and Config, and using this
 * removing selected jobs in the respectful day within the Config provided
 * 
 */

public class DeleteListener implements ActionListener {

	Config con;
	String dayName;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		con.deleteJob(dayName);
	}
	
	public DeleteListener(Config con, String dayName){
		this.con = con;
		this.dayName = dayName;
	}

}
