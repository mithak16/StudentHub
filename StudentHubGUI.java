//package testingAPA6;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StudentHubGUI extends JFrame {
    private StudentHub hub = new StudentHub();

    private JTextArea outputArea = new JTextArea(15, 40);

    public StudentHubGUI() {
        super("GMU Student Hub Tester");
        setLayout(new BorderLayout());

        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);

        // === TOP PANEL ===
        JPanel topPanel = new JPanel();
        JButton addStudentBtn = new JButton("Add Student");
        JButton buildHubDataBtn = new JButton("Build Hub Data");

        topPanel.add(addStudentBtn);
        topPanel.add(buildHubDataBtn);

        // === MIDDLE PANEL ===
        JPanel midPanel = new JPanel();
        JButton getBillBtn = new JButton("Get Student Bill");
        JButton totalProfitBtn = new JButton("Calculate Total Profit");
        JButton sortNameBtn = new JButton("Sort by Name");
        JButton sortBillBtn = new JButton("Sort by Bill Amount");
        JButton printHubBtn = new JButton("Print Hub Data");

        midPanel.add(getBillBtn);
        midPanel.add(totalProfitBtn);
        midPanel.add(sortNameBtn);
        midPanel.add(sortBillBtn);
        midPanel.add(printHubBtn);

        // === Add to Frame ===
        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // ===== BUTTON ACTIONS =====

        addStudentBtn.addActionListener(e -> {
            try {
                String name = JOptionPane.showInputDialog(this, "Enter student name:");
                //if (name == null || name.isBlank()) return;

                String area = JOptionPane.showInputDialog(this, "Enter area (Lounge / Study Room / Cafe):");
                //if (area == null || area.isBlank()) return;

                String timeStr = JOptionPane.showInputDialog(this, "Enter minutes spent:");
                int time = Integer.parseInt(timeStr);

                // Create the student
                Student newStudent = new Student(name, area, time);

                // Prompt for billable items (comma separated)
                String ordersInput = JOptionPane.showInputDialog(this,
                        "Enter billable orders separated by commas:\n" +
                        "(Options: CAKE, COOKIE, BROWNIE, PASTRY, ESPRESSO, LATTE, MOCHA, AMERICANO)");

                ArrayList<Billable> orderList = new ArrayList<>();

                if (ordersInput != null) {
                    String[] items = ordersInput.split(",");

                    for (String itemName : items) {
                        String trimmed = itemName.trim().toUpperCase();

                        try {
                            // Try DessertType first
                            orderList.add(new CafeOrder<DessertType>(DessertType.valueOf(trimmed)));
                        } catch (IllegalArgumentException ex1) {
                            try {
                                // Then try CoffeeType
                                orderList.add(new CafeOrder<CoffeeType>(CoffeeType.valueOf(trimmed)));
                            } catch (IllegalArgumentException ex2) {
                                outputArea.append("Unknown order type ignored: " + trimmed + "\n");
                            }
                        }
                    }
                }

                // Add billable orders to student
                newStudent.addOrders(orderList);

                // Add student to hub queue
                hub.buildQueue(newStudent);

                outputArea.append("\nAdded Student: " + name + "\n");
                outputArea.append("Area: " + area + "\n");
                outputArea.append("Time: " + time + " mins\n");
                outputArea.append("Orders Added: " + orderList.size() + "\n\n");

            } catch (Exception ex) {
                outputArea.append("Invalid input. Student not created.\n");
            }
        });

       buildHubDataBtn.addActionListener(e -> {
            hub.buildHubData();
           outputArea.append("Hub Data Built.\n");
        });

        getBillBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter student name:");
            try {
                outputArea.append("Bill for " + name + ": $" + hub.getStudentBill(name) + "\n");
            } catch (UnKnownStudentException ex) {
                outputArea.append("Student not found.\n");
            }
        });

        totalProfitBtn.addActionListener(e -> {
            outputArea.append("Total Hub Profit: $" + hub.calculateTotalHubProfit() + "\n");
        });

        sortNameBtn.addActionListener(e -> {
            hub.sortHubDatabyStudentName();
            outputArea.append("Sorted hub data by student name.\n");
        });

        sortBillBtn.addActionListener(e -> {
            hub.sortHubDatabyStudentBill();
            outputArea.append("Sorted hub data by bill amount.\n");
        });

        printHubBtn.addActionListener(e -> {
        	 outputArea.append("\n--- HUB DATA ---\n");
        	for(int i = 0; i < hub.getHubData().size(); i++)
        	{
        		for(int j = 0; j < hub.getHubData().get(i).size(); j++)
        		{
        			outputArea.append(hub.getHubData().get(i).get(j).toString() + "\n");
        		}
        	}
            
        });

        // Window settings
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) throws UnKnownStudentException{
    	
    	StudentHubGUI test = new StudentHubGUI();
    	
    	
    	/*The below is testing code without GUI
    	Student s1 = new Student("Mike", "Lounge", 30);
    	Student s2 = new Student("Jake", "Lounge", 20);
    	Student s3 = new Student("Sue", "Lounge", 15);
    	Student s4 = new Student("Lany", "Lounge", 40);
    	Student s5 = new Student("Liz", "Study Room", 25);
    	Student s6 = new Student("Brian", "Study Room", 34);
    	Student s7 = new Student("Angel", "Study Room", 55);
    	Student s8 = new Student("Cassidy", "Study Room", 34);
    	Student s9 = new Student("Dwight", "Cafe", 10);
    	Student s10 = new Student("Elise", "Cafe", 30);
    	Student s11 = new Student("Zane", "Cafe", 50);
    	Student s12 = new Student("Yasmine", "Cafe", 60);
    	
    	Billable a = new CafeOrder<DessertType>(DessertType.CAKE);
    	Billable b = new CafeOrder<DessertType>(DessertType.COOKIE);
    	Billable c = new CafeOrder<DessertType>(DessertType.BROWNIE);
    	Billable d = new CafeOrder<DessertType>(DessertType.PASTRY);
    	Billable e = new CafeOrder<CoffeeType>(CoffeeType.ESPRESSO);
    	Billable f = new CafeOrder<CoffeeType>(CoffeeType.LATTE);
    	Billable g = new CafeOrder<CoffeeType>(CoffeeType.MOCHA);
    	Billable h = new CafeOrder<CoffeeType>(CoffeeType.AMERICANO);
    	
    	s1.addOrders(new ArrayList<Billable>(Arrays.asList(a, b, c)));
    	s2.addOrders(new ArrayList<Billable>(Arrays.asList(a, d, e, h)));
    	s3.addOrders(new ArrayList<Billable>(Arrays.asList(b, f)));
    	s4.addOrders(new ArrayList<Billable>(Arrays.asList(c, b, g)));
    	s5.addOrders(new ArrayList<Billable>(Arrays.asList(f)));
    	s6.addOrders(new ArrayList<Billable>(Arrays.asList(h)));
    	s7.addOrders(new ArrayList<Billable>(Arrays.asList(e, f, g, h)));
    	s8.addOrders(new ArrayList<Billable>(Arrays.asList(a, b, c, d)));
    	s9.addOrders(new ArrayList<Billable>(Arrays.asList(a)));
    	s10.addOrders(new ArrayList<Billable>(Arrays.asList(a, b, c, d, e, f, g, h)));
    	
    	ArrayList<Student> s = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12));
    	
    	StudentHub gmuHub = new StudentHub();
    	for(int i = 0; i < s.size(); i++)
    	{
    		gmuHub.buildQueue(s.get(i));
    	}
    	
    	System.out.println(gmuHub.getWaitingQueue());
    	
    	gmuHub.buildHubData();
    	
    	System.out.println(gmuHub.getHubData());
    	
    	//gmuHub.sortHubDatabyStudentName();
    	//System.out.println(gmuHub.getHubData());
    	
    	//gmuHub.sortHubDatabyStudentBill();
    	//System.out.println(gmuHub.getHubData());
    	
    	//System.out.println(gmuHub.getStudentBill("Mike"));
    	//System.out.println(gmuHub.getStudentBill("Bo"));
    	
    	
    	//System.out.println(gmuHub.calculateTotalHubProfit()); //90.75
    	//System.out.println(gmuHub.calculateTotalHubProfit()); //90.75
    	*/
    }

}
