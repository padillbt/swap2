package scheduleGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Day is used to store jobs for a given day.
 * 
 * @author schneimd. Created Oct 15, 2012.
 */
// SMELL: Lazy Class - the Day class does very little aside from hold data for
// other
// classes to manipulate. It is likely that some related functionality could be
// moved
// into this class to make it more useful.
public class Day implements Serializable {

	private String dayOfWeek;
	// private ArrayList<String> jobs = new ArrayList<String>();

	private TreeMap<String, Worker> jobsAndWorkers;

	/*
	 * SWAP 2, TEAM 6
	 * 
	 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - LAZY CLASS
	 * 
	 * The Fowler refactor of Move Field fix. In Schedule, there is a parameter
	 * of jobsWithWorkers which is a TreeMap<String, Worker> where the String is
	 * the job name and worker is the assigned worker to that job. By putting
	 * that field in this class instead, then other functionality will be able
	 * to access the job assignments a lot easier than before.
	 * 
	 * A new feature that could be done with this refactor would be the ability
	 * to modify an already created schedule for a specific day. Since that
	 * field and all its data is now permanently associated in this class, it
	 * would be easy to just add that functionality.
	 * 
	 * This was a simple and effective refactor.
	 */

	/**
	 * Construct a day with a name and jobs.
	 * 
	 * @param name
	 * 
	 * @param jobs
	 */
	public Day(String name, ArrayList<Object> jobs) {
		this.dayOfWeek = name;
		for (Object i : jobs) {
			addJob((String) i);
			// this.jobs.add((String) i);
		}
	}

	/**
	 * Add one jobName.
	 * 
	 * @param jobName
	 */
	public void addJob(String jobName) {
		/*
		 * SWAP 2, TEAM 6
		 * 
		 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - LAZY CLASS
		 */
		this.jobsAndWorkers.put(jobName, null);
		// this.jobs.add(jobName);
	}

	/**
	 * Set jobs to new jobs.
	 * 
	 * @param jobNames
	 */
	// public void setJobs(ArrayList<String> jobNames) {
	// //this.jobs = jobNames;
	// for (String i : jobNames) {
	// addJob((String) i);
	// // this.jobs.add((String) i);
	// }
	// }

	/**
	 * return current jobs.
	 * 
	 * @return jobs
	 */
	public ArrayList<String> getJobs() {
		// return this.jobs;
		/*
		 * SWAP 2, TEAM 6
		 * 
		 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - LAZY CLASS
		 */
		ArrayList<String> jobList = new ArrayList<String>();
		for (String i : this.jobsAndWorkers.keySet()) {
			jobList.add(i);
		}
		return jobList;
	}

	/**
	 * Gives the name of this day.
	 * 
	 * @return day of week
	 */
	public String getNameOfDay() {
		return this.dayOfWeek;
	}
}
