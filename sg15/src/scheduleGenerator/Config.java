/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleGenerator;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author schneimd
 */



/* SWAP 2, TEAM 6
* 
* REFACTORING FOR ENHANCEMENT FROM BAD SMELL : Large Class
* 
* 
* Used Parameterize Method to create a single function for the treatment of all days.
* 
* Any smaller features that must be implemented across each day is now easier, as you only have to implement the change
* once in the code and never again.  An example would be a feature that differentiates between jobs and meetings, both needing
* to be added to the days in the config.  This change could be done once in the method for setting up a day, and it could effect
* all of the days rather than having to copy and paste the code and change it for each day.
* 
* Removed all the duplication of methods mentioned that was causing the class to be so large.  Furthermore, all of the fields
* were updated into just a few HashMaps containing values by keys determined by each day's name.  Removal of such unneeded
* duplication has made the class much smaller than it was before.
* 
* 
* 
*/


	//SWAP 2, TEAM 6 
	// REFACTORING FOR ENHANCEMENT FROM BAD SMELL   : Divergent Change
	//First Step: We used the Extract Class
	//Second Step: Whenever the action is required to be change rather than changing the Config class, you would change 
	// the NextButtonListener class. Additionaly whenever a new Action is required a new class would be created rather than
	// adding functionality to Config.java
	//Third Step: The refactoring was a success because there are now less reasons to modify Config.java

//SMELL: Large Class - Config is much larger than other classes in the project,
// mostly due to massive amounts of code duplication. There are also an enormous
// number of fields at the bottom of the class.
//SMELL: Shotgun Surgery - this class as well as several others makes use of the idea
// of jobs, however there is no class for such a concept. Instead, its functionality is
// spread across many classes and changing it would require many different small
// modifications.
//SMELL: Divergent Change - there are several logically separate segments in this class
// for changing largely different pieces of functionality: pieces handling the GUI, handling
// actions, running the class, and even stretching pieces of the interface. These could
// possibly be segregated into different places to increase clarity.
public class Config extends javax.swing.JFrame {

	// SWAP 1, TEAM 5
	// BONUS FEATURE
	public boolean noRepeats;
    private boolean firstSelection = true;
    private int numSelected = 0;
    @SuppressWarnings("rawtypes")
	private DefaultListModel[] models;
    
    
    // SWAP 2, TEAM 6
    HashMap<String, JCheckBox> daysChecked = new HashMap<String, JCheckBox>();
	private HashMap<String, JList> daysList = new HashMap<String, JList>();
	private HashMap<String, JScrollPane> daysScrollPane = new HashMap<String, JScrollPane>();
	private HashMap<String, JTextField> daysTextField = new HashMap<String, JTextField>();
	private HashMap<String, JButton> daysAdd = new HashMap<String, JButton>();
	private HashMap<String, JButton> daysDelete = new HashMap<String, JButton>();
	private HashMap<String, JLabel> daysLabel = new HashMap<String, JLabel>();
	private HashMap<String, JPanel> daysPanelTab = new HashMap<String, JPanel>();

	private String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday" };
    
	public int getDayNum(String dayName) {

		int dayNum = 0;
		if (dayName.equals("Sunday")) {
			dayNum = 0;
		} else if (dayName.equals("Monday")) {
			dayNum = 1;
		} else if (dayName.equals("Tuesday")) {
			dayNum = 2;
		} else if (dayName.equals("Wednesday")) {
			dayNum = 3;
		} else if (dayName.equals("Thursday")) {
			dayNum = 4;
		} else if (dayName.equals("Friday")) {
			dayNum = 5;
		} else if (dayName.equals("Saturday")) {
			dayNum = 6;
		}
		return dayNum;
	}
    
    /**
     * Used to edit days.
     *
     * @param days
     */
    @SuppressWarnings("unchecked")
	public Config(ArrayList<Day> days) {
    	this.noRepeats = false;
    	this.models = new DefaultListModel[7];
        initDyn();
        initComponents();
        
     /* SWAP 2, TEAM 6
   	 * 
   	 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL : Switch Statements
   	 * 
   	 * Used Parameterize Method to create a single function for the treatment of all days.
   	 * 
   	 * Any smaller features that must be implemented across each day is now easier, as you only have to implement the change
   	 * once in the code and never again.  An example would be a feature that differentiates between jobs and meetings, both needing
   	 * to be added to the days in the config.  This change could be done once in the method for setting up a day, and it could effect
   	 * all of the days rather than having to copy and paste the code and change it for each day.
   	 * 
   	 * Removed all the duplications in the switch statement based on days mentioned below into a for loop so that 
   	 * changes only need to be made once.  This was done using the new updated HashMaping days to values
   	 * and extracting out all similarities based on day names.  Now a simple for loop can handle all the days
   	 * the same way, just by knowing the name of the day.
   	 * 
   	 * 
   	 */
        
        //SMELL: Switch Statements - the code in this if statement could be extracted
        // to make it cleaner and easier to modify.
    	
        for (Day day : days) {
			ArrayList<String> jobs = day.getJobs();
			this.daysChecked.get(day.getNameOfDay()).doClick();
			for (String job : jobs) {
				this.models[this.getDayNum(day.getNameOfDay())].addElement(job);
				this.daysList.get(day.getNameOfDay()).setModel(
						this.models[this.getDayNum(day.getNameOfDay())]);
			}
		}
    }
    
    /**
     * Creates new form.
     */
    public Config() {
        this.models = new DefaultListModel[7];
        initDyn();
        
        initComponents();
    }
    
    @SuppressWarnings("rawtypes")
    private void initDyn() {

		for (String day : daysOfWeek) {

			this.daysAdd.put(day, new JButton());
			this.daysDelete.put(day, new JButton());
			this.daysScrollPane.put(day, new JScrollPane());
			this.daysScrollPane.get(day).setPreferredSize(
					new Dimension(185, 150));
			this.daysList.put(day, new JList());
			this.daysLabel.put(day, new JLabel());
			this.daysTextField.put(day, new JTextField());
			this.daysChecked.put(day, new JCheckBox());
			this.daysPanelTab.put(day, new JPanel());
		}

	}

    private void initComponents() {

    	this.jPanel1 = new javax.swing.JPanel();
        this.jLabel1 = new javax.swing.JLabel();
        this.nextButton = new javax.swing.JButton();
        this.dayTabs = new javax.swing.JTabbedPane();
        
        // SWAP 1, TEAM 5
        // BONUS FEATURE
        this.repeatCheck = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configuration");
        setPreferredSize(new java.awt.Dimension(1201, 87));
        setResizable(false);
        
        // SWAP 1, TEAM 5
        // BONUS FEATURE
        this.repeatCheck.setText("Don't repeat a worker until every worker has had an opportunity to work.");
        this.repeatCheck.setName("repeatCheck");
        this.repeatCheck.addActionListener(new java.awt.event.ActionListener() {
        	@Override
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		Config.this.noRepeats = Config.this.repeatCheck.isSelected();
        	}
        });
        
        for (String dayName : this.daysOfWeek){
        	this.daysChecked.get(dayName).setText(dayName);
        	this.daysChecked.get(dayName).setName(dayName.toLowerCase()+"Check");
        	this.daysChecked.get(dayName).addActionListener(new DayListener(this, dayName));
        }
        
       

        this.jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        this.jLabel1.setText("Days:");


        this.nextButton.setText("Next");
        this.nextButton.addActionListener(new NextButtonListener(this));

        // SWAP 1, TEAM 5
        // BONUS FEATURE
        // (I simply added the checkbox to the layout. Simple GUI stuff.)
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.jLabel1)
                .addGap(18, 18, 18)
                .addComponent(this.daysChecked.get("Sunday"))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.daysChecked.get("Monday"), javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.daysChecked.get("Tuesday"))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.daysChecked.get("Wednesday"), javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.daysChecked.get("Thursday"))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.daysChecked.get("Friday"), javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.daysChecked.get("Saturday"), javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(this.nextButton)
                .addGap(18, 18, 18)
                .addComponent(this.repeatCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.daysChecked.get("Sunday"), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(this.daysChecked.get("Friday"), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(this.daysChecked.get("Saturday"), javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(this.nextButton).addComponent(this.repeatCheck))
                    .addComponent(this.daysChecked.get("Wednesday"), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.daysChecked.get("Tuesday"), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(this.jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(this.daysChecked.get("Thursday"), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.daysChecked.get("Monday"), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
            .addComponent(this.dayTabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(this.dayTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

        this.dayTabs.getAccessibleContext().setAccessibleName("Days Tab");

        pack();
    }// </editor-fold>

    
    /* SWAP 2, TEAM 6
	 * 
	 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL : Duplicated code
	 * 
	 * Used Parameterize Method to create a single function for the treatment of all days.
	 * 
	 * Any smaller features that must be implemented across each day is now easier, as you only have to implement the change
	 * once in the code and never again.  An example would be a feature that differentiates between jobs and meetings, both needing
	 * to be added to the days in the config.  This change could be done once in the method for setting up a day, and it could effect
	 * all of the days rather than having to copy and paste the code and change it for each day.
	 * 
	 * Removed all the duplication mentioned below into a single dayCheckActionPerformed function so that 
	 * changes only need to be made once.  This was done using the new updated HashMaping days to values
	 * and extracting out all similarities based on day names.
	 * 
	 * Note: Had to add new Listener classes to allow for parameterization of fields and methods
	 * to be called for each day, and so that functionality was clear
	 * 
	 * 
	 */
    
    /**
	 * @param evt  
	 */
    // SWAP 1, TEAM 5
    // SMELL: Duplicated code - the code across the CheckActionPerformed methods
    // was extremely similar before refactoring.
    
    public void dayCheckActionPerformed(java.awt.event.ActionEvent evt, String dayName){
    	if(this.daysChecked.get(dayName).isSelected()) {
            this.daysAdd.get(dayName).setText("Add Job");
            this.daysAdd.get(dayName).addActionListener(new AddListener(this, dayName));

            this.daysDelete.get(dayName).setText("Delete Job");
            this.daysDelete.get(dayName).addActionListener(new DeleteListener(this, dayName));
    	}
    	
    	/* SWAP 2, TEAM 6
    	 * 
    	 * Moved their code change into a more globally tolerant function usable by each of the days
    	 * which is now able to be done thanks to the hash maps. Basically just a relocation of their refactoring into
    	 * a method that handles all of the days the same.
    	 * 
    	 * 
    	 */
    	
    	
    	// SWAP 1, TEAM 5
        // QUALITY CHANGES
        // This method (sundayCheckActionPerformed) was very long, and so a large 
        // portion of the code was extracted into the checkActionPerformed method. 
        // As such, I've deleted the duplicated code and instead called the method
        // with the appropriate parameters. You can see above the actionlisteners,
        // which needed to stay, as they aren't handled in the method.
    	checkActionPerformed(evt, this.daysScrollPane.get(dayName), this.daysAdd.get(dayName), this.daysDelete.get(dayName), 
				 this.daysList.get(dayName), this.daysTextField.get(dayName), this.daysLabel.get(dayName), this.daysPanelTab.get(dayName), 
				 this.daysChecked.get(dayName), dayName, this.getDayNum(dayName));
    }
    
    /*
     * SWAP 2, TEAM 6
     * 
     * extracted some functionality thrown into checks for specific days so it could be utilized
     * for all days, treating them all the same.
     * 
     * 
     */
    public void addJob(String dayName){
    	 if(!this.daysTextField.get(dayName).getText().isEmpty()) {
             this.models[this.getDayNum(dayName)].addElement(this.daysTextField.get(dayName).getText());
             this.daysList.get(dayName).setModel(Config.this.models[this.getDayNum(dayName)]);
             this.daysTextField.get(dayName).setText("");
         }
    }
    
    public void deleteJob(String dayName){
    	 while(!this.daysList.get(dayName).isSelectionEmpty()) {
             int n = this.daysList.get(dayName).getSelectedIndex();
             this.models[this.getDayNum(dayName)].remove(n);
         }
    }
    
    
    

    // SWAP 1, TEAM 5
    // QUALITY CHANGES
    // I've extracted the majority of the code of each of the weekday's action performed
    // methods into a generic action performed method. This method sets up all of the
    // configuration stuff exactly like it did in the individual methods, save for the
    // action listeners. Because the action listeners required the specific configuration
    // variable instances, I thought that adding them to the method would be inelegant
    // and so those remain in the individual action performed methods.
    // This refactor allows for more features in regards to the configuration manager.
    // For instance, if we wanted the user to be able to specify different configurations for
    // every day of a month, as opposed to the weekdays, we could set up the action performance
    // handlers for each of those days using this method, as these are no longer hard-coded
    // to be specific to a weekday.
    public void checkActionPerformed(java.awt.event.ActionEvent evt, 
    								 javax.swing.JScrollPane scrollPane, 
    								 javax.swing.JButton addJob,
    								 javax.swing.JButton deleteJob,
    								 javax.swing.JList jobList, 
    								 javax.swing.JTextField jobName, 
    								 javax.swing.JLabel label, 
    								 javax.swing.JPanel tab, 
    								 javax.swing.JCheckBox check,
    								 String dayName,
    								 int dayNum) {
    	if(check.isSelected()) {
            this.numSelected++;
            if(this.firstSelection) {
                stretch();
            }
            this.models[dayNum] = new DefaultListModel();
            jobList.setModel(this.models[dayNum]);
            scrollPane.setViewportView(jobList);

            jobName.setColumns(20);

            label.setText("Job Name:");

            javax.swing.GroupLayout tabLayout = new javax.swing.GroupLayout(tab);
            tab.setLayout(tabLayout);
            tabLayout.setHorizontalGroup(
                tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tabLayout.createSequentialGroup()
                            .addComponent(label)
                            .addGroup(tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(tabLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(addJob))
                                .addGroup(tabLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jobName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(deleteJob))
                    .addContainerGap(431, Short.MAX_VALUE))
            );
            tabLayout.setVerticalGroup(
                tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(tabLayout.createSequentialGroup()
                            .addGroup(tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jobName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addJob)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteJob))
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
            );
            this.dayTabs.addTab(dayName, tab);
        } else {
            this.numSelected--;
            stretch();
            this.dayTabs.remove(tab);
        }
    }
 
    // SWAP 1, TEAM 5
    // QUALITY CHANGES
    // I extracted duplicate code into this method. The code that was duplicated
    // was simply creating a new Day object for and adding it a list of days.
    // This method returns the newly modified array.
    // This refactor could allow for features based on how the days are entered into the list.
    // For instance, if the user wanted to add the day to the front of the list,
    // that could be easily changed through a boolean in this method. Additionally, if the user
    // wanted to add a particular day more than once, this method would just need to be called
    // multiple times, as opposed to the old code, where that feature was harder to implement.
    public ArrayList<Day> addDayToList(ArrayList<Day> days, String dayName, int dayNum) {
		ArrayList<Object> dayJob = new ArrayList<Object>();
		List<Object> jobs = Arrays.asList(this.models[dayNum].toArray());
		dayJob.addAll(jobs);
    	days.add(new Day(dayName,dayJob));
    	return days;
    }
    
    private void stretch() {
        if(this.numSelected > 0) {
            this.setSize(1201, 290);
            this.firstSelection = false;
        } else {
            this.setSize(1201, 87);
            this.firstSelection = true;
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {
                new Config().setVisible(true);
            }
        });
    }
    
    
    /*
     * SWAP 2, TEAM 6
     * 
     * Were able to remove many duplicate and redundant initializations from the bottom of this file
     * by creating a HashMap with everything each day has specific to itself.  Using this new route, we
     * can use for loops to initialize these variables, and make the process more efficient.
     * 
     * 
     */
    
    private javax.swing.JTabbedPane dayTabs;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JButton nextButton;
    private javax.swing.JCheckBox repeatCheck;
}
