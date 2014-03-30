package scheduleGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class NextButtonListener implements ActionListener {

	private Config con;
	public NextButtonListener(Config con){
		this.con = con;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		ArrayList<Day> days = new ArrayList<Day>();
    	
    	
    	// arrayList, getting rid of the duplicate code.
    	if(this.con.daysChecked.get("Sunday").isSelected())
        {
    		days = this.con.addDayToList(days, "Sunday", 0);
        }
    	if(this.con.daysChecked.get("Monday").isSelected())
        {
    		days = this.con.addDayToList(days, "Monday", 1);
        }
    	if(this.con.daysChecked.get("Tuesday").isSelected())
        {
    		days = this.con.addDayToList(days, "Tuesday", 2);
        }
    	if(this.con.daysChecked.get("Wednesday").isSelected())
        {
    		days = this.con.addDayToList(days, "Wednesday", 3);
        }
    	if(this.con.daysChecked.get("Thursday").isSelected())
        {
    		days = this.con.addDayToList(days, "Thursday", 4);
        }
    	if(this.con.daysChecked.get("Friday").isSelected())
        {
    		days = this.con.addDayToList(days, "Friday", 5);
        }
    	if(this.con.daysChecked.get("Saturday").isSelected())
        {
    		days = this.con.addDayToList(days, "Saturday", 6);
        }
    	if(days.size() > 0) {
    		boolean hasJobs = true;
    		int i = 0;
    		while(hasJobs && i<days.size()) {
    			if(days.get(i).getJobs().size() == 0) {
    				hasJobs = false;
    			}
    			i++;
    		}
    		if(hasJobs) {
		    	Main.setDays(days);
		    	Main.wSet = new WorkerSetup();
		    	Main.toggleWorkerSetup();
		    	Main.config = this.con;
		    	Main.toggleConfig();
    		} else {
    			JOptionPane.showMessageDialog(this.con, "You must have at least one job each day.");
    		}
    	} else {
    		JOptionPane.showMessageDialog(this.con, "You have not added any days.");
    	}

	}

}
