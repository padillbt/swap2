package scheduleGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Used to store predicted days and generate new days.
 * 
 * @author schneimd. Created Oct 18, 2012.
 */
public class Schedule extends Thread implements Serializable {

	private ArrayList<Worker> workers;
	private ArrayList<Day> days;
	private TreeMap<String, TreeMap<String, Worker>> schedule;
	private GregorianCalendar cal;
	private HashMap<Integer, ArrayList<Worker>> workerIndices;
	private boolean workerForEveryJob = true;

	private ArrayList<Worker> freeWorkers;
	private ArrayList<Worker> assignedWorkers;

	/**
	 * Used to construct an initial schedule, used if one does not exist.
	 * 
	 * @param daySlots
	 * @param wrks
	 */
	public Schedule(ArrayList<Day> daySlots, ArrayList<Worker> wrks) {
		this.workers = wrks;
		this.days = daySlots;
		this.workerIndices = new HashMap<Integer, ArrayList<Worker>>();
		for (int i = 1; i <= 7; i++) {
			this.workerIndices.put(i, new ArrayList<Worker>());
		}
		this.generateIndices();

		// Key is year/month/day format and item is a hashmap with key nameOfJob
		// and item Worker
		this.schedule = new TreeMap<String, TreeMap<String, Worker>>();

		this.cal = new GregorianCalendar();

		this.calculateNextMonth();
	}

	@Override
	public void run() {
		this.calculateNextMonth();
	}

	/**
	 * returns workers in schedule.
	 * 
	 * @return workers
	 */
	public ArrayList<Worker> getWorkers() {
		return this.workers;
	}

	private void generateIndices() {
		for (int i = 0; i < this.workers.size(); i++) {
			for (Day day : this.workers.get(i).getDays()) {
				int numDay = this.numForName(day.getNameOfDay());
				this.workerIndices.get(numDay).add(this.workers.get(i));
			}
		}
	}

	// SWAP 1, TEAM 5
	// QUALITY CHANGES
	// This method was pulled out of the long calculateNextMonth method.
	// It handles the case in that a schedule has already been generated.
	// This refactor would allow us to add features to the scheduler that allow
	// a user to specify behavior
	// when a schedule was already generated. For instance, we could simply
	// switch on behavior
	// whether it be this method or another given a user's input, say, if they
	// want to overwrite any
	// previously generated schedules in case of changes.
	public void handleGeneratedSchedule() {
		if (this.schedule.size() > 0) {
			String lastDateMade = this.schedule.lastKey();
			String[] parts = lastDateMade.split("/");
			int year = Integer.parseInt(parts[0]);
			int month = Integer.parseInt(parts[1]) - 1;
			int day = Integer.parseInt(parts[2]);
			this.cal = new GregorianCalendar(year, month, day);
			int tempNum = this.cal.get(Calendar.MONTH);
			while (tempNum == this.cal.get(Calendar.MONTH)) {
				this.cal.add(Calendar.DATE, 1);
			}
		}
	}

	/*
	 * SWAP 2, TEAM 6
	 * 
	 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - DATA CLASS
	 * 
	 * This code is no longer needed because the refactor no longer requires it.
	 */
	// // SWAP 1, TEAM 5
	// // QUALITY CHANGES
	// // This method was pulled out of the long calculateNextMonth method.
	// // This method handles the case in which a worker is assigned to a
	// // particular job.
	// // This refactor allows us to add features regarding when a worker is
	// // assigned to a particular job.
	// // For instance, the feature could be a double-check on worker's jobs.
	// // We could put some kind of pop-up in this function, and any time a user
	// is
	// // going
	// // to be added to a particular job, there would be a notification.
	// public void handleWorker(ArrayList<Worker> workersForJob,
	// ArrayList<String> workersWorking,
	// TreeMap<String, Worker> jobsWithWorker, String job, Day day) {
	// Worker workerForJob = workersForJob.get(new Random()
	// .nextInt(workersForJob.size()));
	//
	// // SWAP 1, TEAM 5
	// // BONUS FEATURE
	// // The bonus feature I chose to implement was the ability to schedule
	// // each person once before repeating
	// // a person. The feature also will then schedule each person a second
	// // time before scheduling someone
	// // a third time. I couldn't tell if that was inherently a part of the
	// // feature, so I implemented it anyway.
	// // The smell I overcame for this change was mostly shotgun surgery. The
	// // problem was that, originally,
	// // to implement this feature, we had to go and make changes all over the
	// // place. But, thanks to the
	// // refactoring from the first part of the SWAP assignment, the code was
	// // refactored enough to require
	// // very few changes to actually implement this feature.
	// // In fact, the only real changes required to implement this feature
	// // (once the refactoring was done)
	// // was to put an if statement around this piece of code and add the
	// // checkbox to the GUI.
	// // The checkbox will store the boolean in the configuration of whether
	// // or not repeats are to be allowed.
	// // Then, here in this code, the workers will be chosen randomly if
	// // noRepeats is false, or
	// // they'll be chosen in a way that accomplishes the task if noRepeats is
	// // true. One of the reasons that
	// // this feature was such a simple change is that most of the required
	// // functionality was already in place.
	// // Also, I could have factored out this for loop into a different
	// // method, but that change is incredibly
	// // insignificant in the grand scheme of things, doesn't really merit the
	// // refactor.
	// if (Main.config.noRepeats) {
	// for (Worker w : workersForJob) {
	// if (w.numWorkedForJob(job) < workerForJob.numWorkedForJob(job)) {
	// workerForJob = w;
	// }
	// }
	// }
	//
	// jobsWithWorker.put(job, workerForJob);
	// workersWorking.add(workerForJob.getName());
	// workerForJob.addWorkedJob(job);
	// }

	// SWAP 1, TEAM 5
	// QUALITY CHANGES
	// This method was pulled out of the long calculateNextMonth method.
	// This method handles the case in which there is no worker assigned to a
	// particular job.
	// This refactor allows us to add features in regards to the case in which
	// no worker is assigned to a particular job.
	// For instance, we could want a feature where the user can specify that
	// every job must have a worker assigned to it.
	// We could simply put a selection pop-up in this method where the user can
	// create a new worker, assign an old worker, or
	// delete the job because no one can handle it.
	// NOTE: This functionality was bugged out before any changes were made.
	// This refactor didn't break anything.
	public void handleNoWorker(TreeMap<String, Worker> jobsWithWorker,
			String job, Day day) {
		jobsWithWorker.put(job, new Worker("Empty", new ArrayList<Day>(),
				new HashMap<String, Integer>()));
		JOptionPane.showMessageDialog(
				new JFrame(),
				"No workers are able to work as a(n) " + job + " on "
						+ day.getNameOfDay());
		this.workerForEveryJob = false;
	}

	/*
	 * SWAP 2, TEAM 6
	 * 
	 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - DATA CLASS
	 * 
	 * This code is no longer needed because the refactor no longer requires it.
	 */
	// // SWAP 1, TEAM 5
	// // QUALITY CHANGES
	// // This method was pulled out of the long calculateNextMonth method.
	// // This method gets all of the workers assigned to working on a
	// particular
	// // job.
	// // This refactor allows us to add features dealing with the way workers
	// are
	// // retrieved for a job.
	// // For instance, we could want a feature where specific workers won't
	// work
	// // on a specific day
	// // (Bob doesn't want to work June 16th, Mary won't work Friday the 13th)
	// // and we could implement the logic in this method, allowing for the
	// feature
	// // to happen.
	// // Additionally, we could want a review screen for a specific job, where
	// we
	// // get more information about
	// // the job and who works it. We could use this method for that feature as
	// // well.
	// public ArrayList<Worker> getWorkersForJob(ArrayList<String>
	// workersWorking,
	// String job, Day day) {
	// ArrayList<Worker> workersForJob = new ArrayList<Worker>();
	//
	// // SWAP 1, TEAM 5
	// // ADDITIONAL FEATURE
	// // As well as checking that a worker has signed up to work for a
	// // particular job
	// // and isn't working another one on that day, this method now checks how
	// // many days
	// // of work a worker has stated that they are willing to work a
	// // particular job. Once
	// // a worker has worked a job for the specified number of days, the
	// // method will no
	// // longer assign that job to that worker. The main code smell that had
	// // to be surmounted
	// // was shotgun surgery, as the idea of jobs is very decentralized. In
	// // the future it
	// // may be a good idea to create an actual class to deal with its
	// // functionality.
	// for (Worker worker : this.workerIndices.get(this.numForName(day
	// .getNameOfDay()))) {
	// Day workerDay = worker.getDayWithName(day.getNameOfDay());
	// if (workerDay.getJobs().contains(job)
	// && !workersWorking.contains(worker.getName())
	// && (worker.numWorkedForJob(job) < worker
	// .willingToWorkForJob(job) || worker
	// .willingToWorkForJob(job) == -1)) {
	// workersForJob.add(worker);
	//
	// }
	// }
	// return workersForJob;
	// }

	/**
	 * Calculates another month of schedule based on workers availability.
	 * 
	 */
	private synchronized void calculateNextMonth() {

		int initialSize = this.schedule.size();

		// SWAP 1, TEAM 5
		// QUALITY CHANGES
		// Code was extracted. Extracted method is called here.
		handleGeneratedSchedule();

		// Used to see if month changes
		int currentMonth = this.cal.get(Calendar.MONTH);

		int daysInMonth = 0;
		ArrayList<Integer> numOfJobs = new ArrayList<Integer>();

		// While still in the current month generate a schedule for each day
		while (currentMonth == this.cal.get(Calendar.MONTH)) {

			for (Day day : this.days) {

				if (this.cal.get(Calendar.DAY_OF_WEEK) == this.numForName(day
						.getNameOfDay())) {

					TreeMap<String, Worker> jobsWithWorker = new TreeMap<String, Worker>();
					// ArrayList<Worker> workersWorking = new
					// ArrayList<Worker>();

					ArrayList<String> jobsInOrder = day.getJobs();

					// Used for html later

					daysInMonth++;
					numOfJobs.add(jobsInOrder.size());

					/*
					 * SWAP 2, TEAM 6 
					 * 
					 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - DATA CLASS.
					 */
					for (String job : jobsInOrder) {
						boolean jobAssignedSuccessfully = this
								.assignWorkerToJob(job, jobsWithWorker);

						if (!jobAssignedSuccessfully) {
							handleNoWorker(jobsWithWorker, job, day);
							break;
						}

					}

					/*
					 * SWAP 2, TEAM 6
					 * 
					 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - DATA CLASS
					 * 
					 * This code is no longer needed because the refactor no
					 * longer requires it.
					 */
					// for (String job : jobsInOrder) {
					//
					// // SWAP 1, TEAM 5
					// // QUALITY CHANGES
					// // Code was extracted. Extracted method is called here.
					// ArrayList<Worker> workersForJob = getWorkersForJob(
					// workersWorking, job, day);
					//
					// if (workersForJob.size() > 0) {
					//
					// // SWAP 1, TEAM 5
					// // QUALITY CHANGES
					// // Code was extracted. Extracted method is called
					// // here.
					// handleWorker(workersForJob, workersWorking,
					// jobsWithWorker, job, day);
					// } else {
					//
					// // SWAP 1, TEAM 5
					// // QUALITY CHANGES
					// // Code was extracted. Extracted method is called
					// // here.
					// handleNoWorker(jobsWithWorker, job, day);
					// break;
					// }
					//
					// }

					String date = this.cal.get(Calendar.YEAR)
							+ "/"
							+ String.format("%02d",
									(this.cal.get(Calendar.MONTH) + 1))
							+ "/"
							+ String.format("%02d",
									this.cal.get(Calendar.DAY_OF_MONTH));
					this.schedule.put(date, jobsWithWorker);
					break; // Breaks so it doesn't check the other days
				}
			}
			this.cal.add(Calendar.DATE, 1);
		}
		HTMLGenerator.makeTable(daysInMonth, numOfJobs);
		// Calls itself if there aren't many days generated
		// For instance if the date it was created is the last day of the
		// month it would only makes one day of schedule.
		if (this.schedule.size() - initialSize < 2 && !this.workerForEveryJob) {
			this.calculateNextMonth();
		}

		Main.dumpConfigFile();
	}

	/**
	 * SWAP 2, TEAM 6
	 * 
	 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - DATA CLASS IN WORKER.
	 * 
	 * To make Worker more than a data class, responsibility of job assignments
	 * is being moved to that class. therefore, the method calculateNextMonth()
	 * needs to change to handle moved responsibility. So this helper method was
	 * created.
	 */
	private boolean assignWorkerToJob(String jobName,
			TreeMap<String, Worker> jobsWithWorker) {

		if (!Main.config.noRepeats) {
			return assignWorkerToJobRandomly(jobName, jobsWithWorker);
		}

		// This part is a refactor of Swap 1's bonus feature.
		if (this.freeWorkers == null) {
			this.freeWorkers = new ArrayList<Worker>();
			for (Worker w : this.workers) {
				this.freeWorkers.add(w);
			}
			this.assignedWorkers = new ArrayList<Worker>();
		}

		Worker leastWorked = findLeastWorked(this.freeWorkers, jobName);

		if (leastWorked != null) {
			jobsWithWorker.put(jobName, leastWorked);
			this.assignedWorkers.add(leastWorked);
			this.freeWorkers.remove(leastWorked);
			if (this.freeWorkers.isEmpty()) {
				this.freeWorkers = this.assignedWorkers;
				this.assignedWorkers = new ArrayList<Worker>();
			}
			return leastWorked.addJob(jobName);
		}

		leastWorked = findLeastWorked(this.assignedWorkers, jobName);

		if (leastWorked != null) {
			jobsWithWorker.put(jobName, leastWorked);
			return leastWorked.addJob(jobName);
		}

		return false;
	}

	/**
	 * SWAP 2, TEAM 6
	 * 
	 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - DATA CLASS.
	 * 
	 * To make Worker more than a data class, responsibility of job assignments
	 * is being moved to that class. therefore, the method calculateNextMonth()
	 * needs to change to handle moved responsibility. So this helper method was
	 * created. This is a refactored version of swap 1's bonus feature.
	 */
	private Worker findLeastWorked(ArrayList<Worker> workerList, String jobName) {

		Worker leastWorked = workerList.get(0);

		for (Worker w : workerList) {
			if (w.canAddJob(jobName)) {
				if (w.numWorkedForJob(jobName) < leastWorked
						.numWorkedForJob(jobName)) {
					w = leastWorked;
				}
			}
		}

		if (!leastWorked.canAddJob(jobName)) {
			return null;
		}

		return leastWorked;
	}

	/**
	 * SWAP 2, TEAM 6
	 * 
	 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL - DATA CLASS.
	 * 
	 * To make Worker more than a data class, responsibility of job assignments
	 * is being moved to that class. therefore, the method calculateNextMonth()
	 * needs to change to handle moved responsibility. So this helper method was
	 * created.
	 */
	private boolean assignWorkerToJobRandomly(String jobName,
			TreeMap<String, Worker> jobsWithWorker) {
		ArrayList<Worker> workerList = new ArrayList<Worker>();
		for (Worker w : this.workers) {
			workerList.add(w);
		}

		boolean addSuccessful = false;

		while (workerList.size() > 0) {
			Worker randomWorker = workerList.get(new Random()
					.nextInt(workerList.size()));
			addSuccessful = randomWorker.addJob(jobName);
			if (addSuccessful) {
				jobsWithWorker.put(jobName, randomWorker);
				break;
			} else {
				workerList.remove(randomWorker);
			}
		}

		return addSuccessful;
	}

	private int numForName(String nameOfDay) {
		int dayNum = 0;
		if (nameOfDay.equals("Sunday")) {
			dayNum = 1;
		} else if (nameOfDay.equals("Monday")) {
			dayNum = 2;
		} else if (nameOfDay.equals("Tuesday")) {
			dayNum = 3;
		} else if (nameOfDay.equals("Wednesday")) {
			dayNum = 4;
		} else if (nameOfDay.equals("Thursday")) {
			dayNum = 5;
		} else if (nameOfDay.equals("Friday")) {
			dayNum = 6;
		} else if (nameOfDay.equals("Saturday")) {
			dayNum = 7;
		}
		return dayNum;
	}

	// /**
	// * Returns the month/day/year of next date with the name of day.
	// *
	// * @param nameOfDay
	// * @return string of year/month/day format
	// */
	// private String getNextDate(String nameOfDay) {
	// int dayNum = numForName(nameOfDay);
	// GregorianCalendar tempCal = (GregorianCalendar) this.cal.clone();
	//
	// tempCal.add(Calendar.DATE, 1);
	// while (tempCal.get(Calendar.DAY_OF_WEEK) != dayNum) {
	// tempCal.add(Calendar.DATE, 1);
	// }
	// return String.valueOf(tempCal.get(Calendar.YEAR)) + "/" +
	// String.valueOf(tempCal.get(Calendar.MONTH)) + "/"
	// + String.valueOf(tempCal.get(Calendar.DAY_OF_MONTH));
	// }

	/**
	 * Returns the schedule.
	 * 
	 * @return HashMap schedule
	 */
	public TreeMap<String, TreeMap<String, Worker>> getSchedule() {
		return this.schedule;
	}

}
