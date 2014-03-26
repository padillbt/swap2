/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleGenerator;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author schneimd
 */
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
        
        //SMELL: Switch Statements - the code in this if statement could be extracted
        // to make it cleaner and easier to modify.
    	for(Day day: days) {
    		if(day.getNameOfDay().equals("Sunday")) {
    			this.sundayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[0].addElement(job);
    				this.sundayJobList.setModel(this.models[0]);
    			}
    		} else if(day.getNameOfDay().equals("Monday")) {
    			this.mondayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[1].addElement(job);
    				this.sundayJobList.setModel(this.models[1]);
    			}
    		} else if(day.getNameOfDay().equals("Tuesday")) {
    			this.tuesdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[2].addElement(job);
    				this.sundayJobList.setModel(this.models[2]);
    			}
    		} else if(day.getNameOfDay().equals("Wednesday")) {
    			this.wednesdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[3].addElement(job);
    				this.sundayJobList.setModel(this.models[3]);
    			}
    		} else if(day.getNameOfDay().equals("Thursday")) {
    			this.thursdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[4].addElement(job);
    				this.sundayJobList.setModel(this.models[4]);
    			}
    		} else if(day.getNameOfDay().equals("Friday")) {
    			this.fridayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[5].addElement(job);
    				this.sundayJobList.setModel(this.models[5]);
    			}
    		} else if(day.getNameOfDay().equals("Saturday")) {
    			this.saturdayCheck.doClick();
    			ArrayList<String> jobs = day.getJobs();
    			for(String job: jobs) {
    				this.models[6].addElement(job);
    				this.sundayJobList.setModel(this.models[6]);
    			}
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
        this.sundayScrollPane = new javax.swing.JScrollPane();
        this.sundayScrollPane.setPreferredSize(new Dimension(185,150));
        this.sundayJobList = new javax.swing.JList();
        this.sundayJobName = new javax.swing.JTextField();
        this.sundayLabel = new javax.swing.JLabel();
        this.sundayAddJob = new javax.swing.JButton();
        this.sundayDeleteJob = new javax.swing.JButton();
        
        this.mondayScrollPane = new javax.swing.JScrollPane();
        this.mondayScrollPane.setPreferredSize(new Dimension(185,150));
        this.mondayJobList = new javax.swing.JList();
        this.mondayJobName = new javax.swing.JTextField();
        this.mondayLabel = new javax.swing.JLabel();
        this.mondayAddJob = new javax.swing.JButton();
        this.mondayDeleteJob = new javax.swing.JButton();
        
        this.tuesdayScrollPane = new javax.swing.JScrollPane();
        this.tuesdayScrollPane.setPreferredSize(new Dimension(185,150));
        this.tuesdayJobList = new javax.swing.JList();
        this.tuesdayJobName = new javax.swing.JTextField();
        this.tuesdayLabel = new javax.swing.JLabel();
        this.tuesdayAddJob = new javax.swing.JButton();
        this.tuesdayDeleteJob = new javax.swing.JButton();
        
        this.wednesdayScrollPane = new javax.swing.JScrollPane();
        this.wednesdayScrollPane.setPreferredSize(new Dimension(185,150));
        this.wednesdayJobList = new javax.swing.JList();
        this.wednesdayJobName = new javax.swing.JTextField();
        this.wednesdayLabel = new javax.swing.JLabel();
        this.wednesdayAddJob = new javax.swing.JButton();
        this.wednesdayDeleteJob = new javax.swing.JButton();
        
        this.thursdayScrollPane = new javax.swing.JScrollPane();
        this.thursdayScrollPane.setPreferredSize(new Dimension(185,150));
        this.thursdayJobList = new javax.swing.JList();
        this.thursdayJobName = new javax.swing.JTextField();
        this.thursdayLabel = new javax.swing.JLabel();
        this.thursdayAddJob = new javax.swing.JButton();
        this.thursdayDeleteJob = new javax.swing.JButton();
        
        this.fridayScrollPane = new javax.swing.JScrollPane();
        this.fridayScrollPane.setPreferredSize(new Dimension(185,150));
        this.fridayJobList = new javax.swing.JList();
        this.fridayJobName = new javax.swing.JTextField();
        this.fridayLabel = new javax.swing.JLabel();
        this.fridayAddJob = new javax.swing.JButton();
        this.fridayDeleteJob = new javax.swing.JButton();
        
        this.saturdayScrollPane = new javax.swing.JScrollPane();
        this.saturdayScrollPane.setPreferredSize(new Dimension(185,150));
        this.saturdayJobList = new javax.swing.JList();
        this.saturdayJobName = new javax.swing.JTextField();
        this.saturdayLabel = new javax.swing.JLabel();
        this.saturdayAddJob = new javax.swing.JButton();
        this.saturdayDeleteJob = new javax.swing.JButton();
        
        this.mondayTab = new javax.swing.JPanel();
        this.tuesdayTab = new javax.swing.JPanel();
        this.wednesdayTab = new javax.swing.JPanel();
        this.thursdayTab = new javax.swing.JPanel();
        this.fridayTab = new javax.swing.JPanel();
        this.saturdayTab = new javax.swing.JPanel();
        this.sundayTab = new javax.swing.JPanel();
    }

    private void initComponents() {

    	this.jPanel1 = new javax.swing.JPanel();
        this.sundayCheck = new javax.swing.JCheckBox();
        this.wednesdayCheck = new javax.swing.JCheckBox();
        this.mondayCheck = new javax.swing.JCheckBox();
        this.tuesdayCheck = new javax.swing.JCheckBox();
        this.jLabel1 = new javax.swing.JLabel();
        this.thursdayCheck = new javax.swing.JCheckBox();
        this.fridayCheck = new javax.swing.JCheckBox();
        this.saturdayCheck = new javax.swing.JCheckBox();
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
        
        this.sundayCheck.setText("Sunday");
        this.sundayCheck.setName("sundayCheck"); // NOI18N
        this.sundayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                sundayCheckActionPerformed(evt);
            }
        });

        this.wednesdayCheck.setText("Wednesday");
        this.wednesdayCheck.setName("wednesdayCheck"); // NOI18N
        this.wednesdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                wednesdayCheckActionPerformed(evt);
            }
        });

        this.mondayCheck.setText("Monday");
        this.mondayCheck.setName("mondayCheck"); // NOI18N
        this.mondayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                mondayCheckActionPerformed(evt);
            }
        });

        this.tuesdayCheck.setText("Tuesday");
        this.tuesdayCheck.setName("tuesdayCheck"); // NOI18N
        this.tuesdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuesdayCheckActionPerformed(evt);
            }
        });

        this.jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        this.jLabel1.setText("Days:");

        this.thursdayCheck.setText("Thursday");
        this.thursdayCheck.setName("thursdayCheck"); // NOI18N
        this.thursdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                thursdayCheckActionPerformed(evt);
            }
        });

        this.fridayCheck.setText("Friday");
        this.fridayCheck.setName("fridayCheck"); // NOI18N
        this.fridayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                fridayCheckActionPerformed(evt);
            }
        });

        this.saturdayCheck.setText("Saturday");
        this.saturdayCheck.setName("saturdayCheck"); // NOI18N
        this.saturdayCheck.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                saturdayCheckActionPerformed(evt);
            }
        });

        this.nextButton.setText("Next");
        this.nextButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

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
                .addComponent(this.sundayCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.mondayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.tuesdayCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.wednesdayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.thursdayCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.fridayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.saturdayCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(this.sundayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(this.fridayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(this.saturdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(this.nextButton).addComponent(this.repeatCheck))
                    .addComponent(this.wednesdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.tuesdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(this.jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(this.thursdayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.mondayCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    
    /**
	 * @param evt  
	 */
    //SMELL: Duplicated code - the code across the CheckActionPerformed methods
    // was extremely similar before refactoring.
    @SuppressWarnings("unchecked")
	private void sundayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(this.sundayCheck.isSelected()) {
            this.sundayAddJob.setText("Add Job");
            this.sundayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.sundayJobName.getText().isEmpty()) {
                        Config.this.models[0].addElement(Config.this.sundayJobName.getText());
                        Config.this.sundayJobList.setModel(Config.this.models[0]);
                        Config.this.sundayJobName.setText("");
                    }
                }
            });

            this.sundayDeleteJob.setText("Delete Job");
            this.sundayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.sundayJobList.isSelectionEmpty()) {
                        int n = Config.this.sundayJobList.getSelectedIndex();
                        Config.this.models[0].remove(n);
                    }
                    
                }
            });
        }

        // SWAP 1, TEAM 5
        // QUALITY CHANGES
        // This method (sundayCheckActionPerformed) was very long, and so a large 
        // portion of the code was extracted into the checkActionPerformed method. 
        // As such, I've deleted the duplicated code and instead called the method
        // with the appropriate parameters. You can see above the actionlisteners,
        // which needed to stay, as they aren't handled in the method.
        checkActionPerformed(evt, this.sundayScrollPane, this.sundayAddJob, this.sundayDeleteJob, 
        					 this.sundayJobList, this.sundayJobName, this.sundayLabel, this.sundayTab, 
        					 this.sundayCheck, "Sunday", 0);
    }                                           

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void mondayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(this.mondayCheck.isSelected()) {
            this.mondayAddJob.setText("Add Job");
            this.mondayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.mondayJobName.getText().isEmpty()) {
                        Config.this.models[1].addElement(Config.this.mondayJobName.getText());
                        Config.this.mondayJobList.setModel(Config.this.models[1]);
                        Config.this.mondayJobName.setText("");
                    }
                }
            });

            this.mondayDeleteJob.setText("Delete Job");
            this.mondayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.mondayJobList.isSelectionEmpty()) {
                        int n = Config.this.mondayJobList.getSelectedIndex();
                        Config.this.models[1].remove(n);
                    }
                    
                }
            });
        }
        
        // SWAP 1, TEAM 5
        // QUALITY CHANGES
        // This method (mondayCheckActionPerformed) was very long, and so a large 
        // portion of the code was extracted into the checkActionPerformed method. 
        // As such, I've deleted the duplicated code and instead called the method
        // with the appropriate parameters. You can see above the actionlisteners,
        // which needed to stay, as they aren't handled in the method.
        checkActionPerformed(evt, this.mondayScrollPane, this.mondayAddJob, this.mondayDeleteJob, 
        					 this.mondayJobList, this.mondayJobName, this.mondayLabel, this.mondayTab, 
        					 this.mondayCheck, "Monday", 1);       
    }                                           

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void tuesdayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if(this.tuesdayCheck.isSelected()) {
            this.tuesdayAddJob.setText("Add Job");
            this.tuesdayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.tuesdayJobName.getText().isEmpty()) {
                        Config.this.models[2].addElement(Config.this.tuesdayJobName.getText());
                        Config.this.tuesdayJobList.setModel(Config.this.models[2]);
                        Config.this.tuesdayJobName.setText("");
                    }
                }
            });

            this.tuesdayDeleteJob.setText("Delete Job");
            this.tuesdayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.tuesdayJobList.isSelectionEmpty()) {
                        int n = Config.this.tuesdayJobList.getSelectedIndex();
                        Config.this.models[2].remove(n);
                    }
                    
                }
            });
        }
        
        // SWAP 1, TEAM 5
        // QUALITY CHANGES
        // This method (tuesdayCheckActionPerformed) was very long, and so a large 
        // portion of the code was extracted into the checkActionPerformed method. 
        // As such, I've deleted the duplicated code and instead called the method
        // with the appropriate parameters. You can see above the actionlisteners,
        // which needed to stay, as they aren't handled in the method.
        checkActionPerformed(evt, this.tuesdayScrollPane, this.tuesdayAddJob, this.tuesdayDeleteJob, 
        					 this.tuesdayJobList, this.tuesdayJobName, this.tuesdayLabel, this.tuesdayTab, 
        					 this.tuesdayCheck, "Tuesday", 2);
    }                                            

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void wednesdayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                               
        if(this.wednesdayCheck.isSelected()) {
            this.wednesdayAddJob.setText("Add Job");
            this.wednesdayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.wednesdayJobName.getText().isEmpty()) {
                        Config.this.models[3].addElement(Config.this.wednesdayJobName.getText());
                        Config.this.wednesdayJobList.setModel(Config.this.models[3]);
                        Config.this.wednesdayJobName.setText("");
                    }
                }
            });

            this.wednesdayDeleteJob.setText("Delete Job");
            this.wednesdayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.wednesdayJobList.isSelectionEmpty()) {
                        int n = Config.this.wednesdayJobList.getSelectedIndex();
                        Config.this.models[3].remove(n);
                    }
                    
                }
            });
        }
        
        // SWAP 1, TEAM 5
        // QUALITY CHANGES
        // This method (wednesdayCheckActionPerformed) was very long, and so a large 
        // portion of the code was extracted into the checkActionPerformed method. 
        // As such, I've deleted the duplicated code and instead called the method
        // with the appropriate parameters. You can see above the actionlisteners,
        // which needed to stay, as they aren't handled in the method.
        checkActionPerformed(evt, this.wednesdayScrollPane, this.wednesdayAddJob, this.wednesdayDeleteJob, 
        					 this.wednesdayJobList, this.wednesdayJobName, this.wednesdayLabel, this.wednesdayTab, 
        					 this.wednesdayCheck, "Wednesday", 3);
        
    }                                              

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void thursdayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if(this.thursdayCheck.isSelected()) {
            this.thursdayAddJob.setText("Add Job");
            this.thursdayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.thursdayJobName.getText().isEmpty()) {
                        Config.this.models[4].addElement(Config.this.thursdayJobName.getText());
                        Config.this.thursdayJobList.setModel(Config.this.models[4]);
                        Config.this.thursdayJobName.setText("");
                    }
                }
            });

            this.thursdayDeleteJob.setText("Delete Job");
            this.thursdayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.thursdayJobList.isSelectionEmpty()) {
                        int n = Config.this.thursdayJobList.getSelectedIndex();
                        Config.this.models[4].remove(n);
                    }
                    
                }
            });
        }
        
        // SWAP 1, TEAM 5
        // QUALITY CHANGES
        // This method (thursdayCheckActionPerformed) was very long, and so a large 
        // portion of the code was extracted into the checkActionPerformed method. 
        // As such, I've deleted the duplicated code and instead called the method
        // with the appropriate parameters. You can see above the actionlisteners,
        // which needed to stay, as they aren't handled in the method.
        checkActionPerformed(evt, this.thursdayScrollPane, this.thursdayAddJob, this.thursdayDeleteJob, 
        					 this.thursdayJobList, this.thursdayJobName, this.thursdayLabel, this.thursdayTab, 
        					 this.thursdayCheck, "Thursday", 4);
    }                                             

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void fridayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                            
       if(this.fridayCheck.isSelected()) {
    	   
            this.fridayAddJob.setText("Add Job");
            this.fridayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.fridayJobName.getText().isEmpty()) {
                        Config.this.models[5].addElement(Config.this.fridayJobName.getText());
                        Config.this.fridayJobList.setModel(Config.this.models[5]);
                        Config.this.fridayJobName.setText("");
                    }
                }
            });

            this.fridayDeleteJob.setText("Delete Job");
            this.fridayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.fridayJobList.isSelectionEmpty()) {
                        int n = Config.this.fridayJobList.getSelectedIndex();
                        Config.this.models[5].remove(n);
                    }
                    
                }
            });
        }
       
       // SWAP 1, TEAM 5
       // QUALITY CHANGES
       // This method (fridayCheckActionPerformed) was very long, and so a large 
       // portion of the code was extracted into the checkActionPerformed method. 
       // As such, I've deleted the duplicated code and instead called the method
       // with the appropriate parameters. You can see above the actionlisteners,
       // which needed to stay, as they aren't handled in the method.
       	checkActionPerformed(evt, this.fridayScrollPane, this.fridayAddJob, this.fridayDeleteJob, 
       			             this.fridayJobList, this.fridayJobName, this.fridayLabel, this.fridayTab, 
       					     this.fridayCheck, "Friday", 5);
        
    }                                           

    /**
	 * @param evt  
	 */
    @SuppressWarnings("unchecked")
	private void saturdayCheckActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if(this.saturdayCheck.isSelected()) {
            this.saturdayAddJob.setText("Add Job");
            this.saturdayAddJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!Config.this.saturdayJobName.getText().isEmpty()) {
                        Config.this.models[6].addElement(Config.this.saturdayJobName.getText());
                        Config.this.saturdayJobList.setModel(Config.this.models[6]);
                        Config.this.saturdayJobName.setText("");
                    }
                }
            });

            this.saturdayDeleteJob.setText("Delete Job");
            this.saturdayDeleteJob.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    while(!Config.this.saturdayJobList.isSelectionEmpty()) {
                        int n = Config.this.saturdayJobList.getSelectedIndex();
                        Config.this.models[6].remove(n);
                    }
                    
                }
            });
        }
        
        // SWAP 1, TEAM 5
        // QUALITY CHANGES
        // This method (saturdayCheckActionPerformed) was very long, and so a large 
        // portion of the code was extracted into the checkActionPerformed method. 
        // As such, I've deleted the duplicated code and instead called the method
        // with the appropriate parameters. You can see above the actionlisteners,
        // which needed to stay, as they aren't handled in the method.
        checkActionPerformed(evt, this.saturdayScrollPane, this.saturdayAddJob, this.saturdayDeleteJob, 
        					 this.saturdayJobList, this.saturdayJobName, this.saturdayLabel, this.saturdayTab, 
        					 this.saturdayCheck, "Saturday", 6);
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
    
    /**
	 * @param evt  
	 */
    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	ArrayList<Day> days = new ArrayList<Day>();
    	
    	// SWAP 1, TEAM 5
    	// QUALITY CHANGES
    	// Each of the innards of these if statements have been changed
    	// to use the addDayToList method to create the new days
    	// arrayList, getting rid of the duplicate code.
    	if(this.sundayCheck.isSelected())
        {
    		days = addDayToList(days, "Sunday", 0);
        }
    	if(this.mondayCheck.isSelected())
        {
    		days = addDayToList(days, "Monday", 1);
        }
    	if(this.tuesdayCheck.isSelected())
        {
    		days = addDayToList(days, "Tuesday", 2);
        }
    	if(this.wednesdayCheck.isSelected())
        {
    		days = addDayToList(days, "Wednesday", 3);
        }
    	if(this.thursdayCheck.isSelected())
        {
    		days = addDayToList(days, "Thursday", 4);
        }
    	if(this.fridayCheck.isSelected())
        {
    		days = addDayToList(days, "Friday", 5);
        }
    	if(this.saturdayCheck.isSelected())
        {
    		days = addDayToList(days, "Saturday", 6);
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
		    	Main.config = this;
		    	Main.toggleConfig();
    		} else {
    			JOptionPane.showMessageDialog(this, "You must have at least one job each day.");
    		}
    	} else {
    		JOptionPane.showMessageDialog(this, "You have not added any days.");
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
    
    private javax.swing.JScrollPane sundayScrollPane;
    private javax.swing.JButton sundayAddJob;
    private javax.swing.JButton sundayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList sundayJobList;
    private javax.swing.JTextField sundayJobName;
    private javax.swing.JLabel sundayLabel;
    private javax.swing.JPanel sundayTab;
    
    private javax.swing.JScrollPane mondayScrollPane;
    private javax.swing.JButton mondayAddJob;
    private javax.swing.JButton mondayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList mondayJobList;
    private javax.swing.JTextField mondayJobName;
    private javax.swing.JLabel mondayLabel;
    private javax.swing.JPanel mondayTab;
    
    private javax.swing.JScrollPane tuesdayScrollPane;
    private javax.swing.JButton tuesdayAddJob;
    private javax.swing.JButton tuesdayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList tuesdayJobList;
    private javax.swing.JTextField tuesdayJobName;
    private javax.swing.JLabel tuesdayLabel;
    private javax.swing.JPanel tuesdayTab;
    
    private javax.swing.JScrollPane wednesdayScrollPane;
    private javax.swing.JButton wednesdayAddJob;
    private javax.swing.JButton wednesdayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList wednesdayJobList;
    private javax.swing.JTextField wednesdayJobName;
    private javax.swing.JLabel wednesdayLabel;
    private javax.swing.JPanel wednesdayTab;
    
    private javax.swing.JScrollPane thursdayScrollPane;
    private javax.swing.JButton thursdayAddJob;
    private javax.swing.JButton thursdayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList thursdayJobList;
    private javax.swing.JTextField thursdayJobName;
    private javax.swing.JLabel thursdayLabel;
    private javax.swing.JPanel thursdayTab;
    
    private javax.swing.JScrollPane fridayScrollPane;
    private javax.swing.JButton fridayAddJob;
    private javax.swing.JButton fridayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList fridayJobList;
    private javax.swing.JTextField fridayJobName;
    private javax.swing.JLabel fridayLabel;
    private javax.swing.JPanel fridayTab;
    
    private javax.swing.JScrollPane saturdayScrollPane;
    private javax.swing.JButton saturdayAddJob;
    private javax.swing.JButton saturdayDeleteJob;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList saturdayJobList;
    private javax.swing.JTextField saturdayJobName;
    private javax.swing.JLabel saturdayLabel;
    private javax.swing.JPanel saturdayTab;
    
    private javax.swing.JTabbedPane dayTabs;
    private javax.swing.JCheckBox fridayCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox mondayCheck;
    private javax.swing.JButton nextButton;
    private javax.swing.JCheckBox saturdayCheck;
    private javax.swing.JCheckBox sundayCheck;
    private javax.swing.JCheckBox thursdayCheck;
    private javax.swing.JCheckBox tuesdayCheck;
    private javax.swing.JCheckBox wednesdayCheck;
    private javax.swing.JCheckBox repeatCheck;
}
