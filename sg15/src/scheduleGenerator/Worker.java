package scheduleGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A worker contains days available to work with jobs.
 * 
 * @author schneimd. Created Oct 15, 2012.
 */
// SMELL: Shotgun Surgery - this class as well as several others makes use of
// the idea
// of jobs, however there is no class for such a concept. Instead, its
// functionality is
// spread across many classes and changing it would require many different small
// modifications.
// SMELL: Data class - the Worker class is mostly get and set methods with
// minimal useful
// logic. If more functionality could be pushed into it, it would save changes
// elsewhere.
public class Worker implements Serializable {

	private String name;
	private ArrayList<Day> days = new ArrayList<Day>();
	private HashMap<String, Integer> timesWorked;
	private HashMap<String, Integer> willingToWork;

	/**
	 * Builds a worker with available days.
	 * 
	 * @param name
	 * @param days
	 * @param jobAmounts
	 * 
	 * @param jobs
	 */
	public Worker(String name, ArrayList<Day> days,
			HashMap<String, Integer> amountToWork) {
		this.name = name;
		this.days = days;
		this.timesWorked = new HashMap<String, Integer>();
		// SWAP 1, TEAM 5
		// ADDITIONAL FEATURE
		// Reading input from text fields
		this.willingToWork = amountToWork;
		for (Day day : days) {
			for (int i = 0; i < day.getJobs().size(); i++) {
				String job = day.getJobs().get(i);
				this.timesWorked.put(job, 0);
			}
		}
	}

	/**
	 * Gives the name of the worker.
	 * 
	 * @return name of worker
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Increments the time a job is worked by one.
	 * 
	 * @param jobName
	 */
	public void addWorkedJob(String jobName) {
		this.timesWorked.put(jobName,
				this.timesWorked.get(jobName).intValue() + 1);
	}

	/**
	 * Returns the number of times a job has been worked.
	 * 
	 * @param jobName
	 * @return number of times job has been worked.
	 */
	public int numWorkedForJob(String jobName) {
		if (this.timesWorked.containsKey(jobName)) {
			return this.timesWorked.get(jobName);
		}
		return -1;
	}

	// SWAP 1, TEAM 5
	// ADDITIONAL FEATURE
	// Reading input from text fields
	/**
	 * Returns the number of times a worker is willing to work a job.
	 * 
	 * @param jobName
	 * @return number of times job can be worked.
	 */
	public int willingToWorkForJob(String jobName) {
		return this.willingToWork.get(jobName);
	}

	/**
	 * SWAP 2, TEAM 6
	 * 
	 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - DATA CLASS.
	 * 
	 * This was done using the Move Method fix by Fowler. This logic was
	 * originally in Schedule, but uses a lot of Worker's features. Therefore,
	 * some of the logic should moved here.
	 * 
	 * addJob() and canAddJob() were added. They go through all the logic of
	 * figuring out if a worker should work a job or not based on their
	 * preferences and constraints. This puts the responsibility of job
	 * assignment logic in the worker. This removes excessive logic from the
	 * Schedule class and puts more functionality into this class.
	 * 
	 * The benefits of this refactor mean that more criteria could be added for
	 * job assignments. They would only need to be added to this method and no
	 * others.
	 * 
	 * This refactor was very successful because it provides an easy why to
	 * expand on worker constraints when assigning jobs.
	 */
	public boolean addJob(String jobName) {
		boolean possible = canAddJob(jobName);
		if (possible) {
			addWorkedJob(jobName);
		}
		return possible;
	}

	public boolean canAddJob(String jobName) {
		boolean possible = this.willingToWork.containsKey(jobName);
		if (possible) {
			possible = this.willingToWork.get(jobName) > this.timesWorked
					.get(jobName);
		}
		return possible;
	}

	/**
	 * Returns the workers day based on name.
	 * 
	 * @param name
	 * @return day with same name
	 */
	public Day getDayWithName(String name) {
		for (Day d : this.days) {
			if (d.getNameOfDay().equals(name)) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Returns the worker's days.
	 * 
	 * @return days
	 */
	public ArrayList<Day> getDays() {
		return this.days;
	}

	/**
	 * Adds a day to the worker.
	 * 
	 * @param d
	 */
	public void addDay(Day d) {
		this.days.add(d);
	}

}
