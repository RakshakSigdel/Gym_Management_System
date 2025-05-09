
/**
 * main class for gym management system
 * This class contains GUI of the program and handles the actions of the user
 * It manages both Regular and Premium gym members through a graphical interface
 * 
 * @author Rakshak Sigdel
 * @version 0.1
 */
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GymGUI {
    /**
     * Main method that initializes the GUI and sets up all components
     * Creates sample data and configures the main application window
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        ArrayList<GymMember> gymMembers = new ArrayList<>();
        // gymMembers.add(new PremiumMember(1, "Rakshak sigdel", "sundarharaicha-04,
        // Morang", "9819322151",
        // "rakshaksigdel55@gmail.com", "male", "2005-10-1-", "2025-04-20",
        // "Jon Doe"));
        // Creating and setting up the window
        JFrame frame = new JFrame("Mayhem GYM Management System");
        frame.setSize(1300, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        JPanel headingPanel = new JPanel();
        headingPanel.setBounds(0, 0, 1300, 80);

        JPanel personalDetailsPanel = new JPanel();
        personalDetailsPanel.setLayout(null);
        personalDetailsPanel.setBorder(BorderFactory.createTitledBorder("Personal Details"));
        JPanel membershipDetailsPanel = new JPanel();
        membershipDetailsPanel.setLayout(null);
        membershipDetailsPanel.setBorder(BorderFactory.createTitledBorder("Membership Details"));
        JPanel paymentDetailsPanel = new JPanel();
        paymentDetailsPanel.setLayout(null);
        paymentDetailsPanel.setBorder(BorderFactory.createTitledBorder("Payment Details"));
        JPanel otherActionsPanel = new JPanel();
        otherActionsPanel.setLayout(null);
        otherActionsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        personalDetailsPanel.setBounds(35, 100, 396, 500);
        membershipDetailsPanel.setBounds(446, 100, 396, 500);
        paymentDetailsPanel.setBounds(862, 100, 396, 260);
        otherActionsPanel.setBounds(862, 370, 396, 230);

        frame.add(headingPanel);
        frame.add(personalDetailsPanel);
        frame.add(membershipDetailsPanel);
        frame.add(paymentDetailsPanel);
        frame.add(otherActionsPanel);

        // Heading Panel
        JLabel headingLabel = new JLabel("Mayhem GYM Management System", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingPanel.setLayout(new GridBagLayout());
        headingPanel.add(headingLabel);
        // Global Components for year
        // Day ComboBox
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.format("%02d", i);
        }
        // Month ComboBox
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        // Year ComboBox (1950–2025)
        String[] years = new String[76];
        for (int i = 0; i < 76; i++) {
            years[i] = Integer.toString(1950 + i);
        }

        // Personal Details Fields
        // ID
        JLabel IDLabel = new JLabel("ID");
        JTextField IDField = new JTextField();
        personalDetailsPanel.add(IDLabel);
        personalDetailsPanel.add(IDField);
        // Name
        JLabel nameLabel = new JLabel("Name");
        JTextField nameField = new JTextField();
        personalDetailsPanel.add(nameLabel);
        personalDetailsPanel.add(nameField);
        // Location
        JLabel locationLabel = new JLabel("Location");
        JTextField locationField = new JTextField();
        personalDetailsPanel.add(locationLabel);
        personalDetailsPanel.add(locationField);
        // Phone
        JLabel phoneLabel = new JLabel("Phone");
        JTextField phoneField = new JTextField();
        personalDetailsPanel.add(phoneLabel);
        personalDetailsPanel.add(phoneField);
        // Email
        JLabel emailLabel = new JLabel("Email");
        JTextField emailField = new JTextField();
        personalDetailsPanel.add(emailLabel);
        personalDetailsPanel.add(emailField);
        // Date of Birth
        JLabel dobLabel = new JLabel("Date of Birth");
        JComboBox<String> dobDayComboBox = new JComboBox<>(days);
        JComboBox<String> dobMonthComboBox = new JComboBox<>(months);
        JComboBox<String> dobYearComboBox = new JComboBox<>(years);
        personalDetailsPanel.add(dobLabel);
        personalDetailsPanel.add(dobDayComboBox);
        personalDetailsPanel.add(dobMonthComboBox);
        personalDetailsPanel.add(dobYearComboBox);
        // Gender
        JLabel genderLabel = new JLabel("Gender");
        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setActionCommand("Male");
        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setActionCommand("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        personalDetailsPanel.add(genderLabel);
        personalDetailsPanel.add(maleButton);
        personalDetailsPanel.add(femaleButton);
        // Referral Source
        JLabel referralSourceLabel = new JLabel("Referral Source");
        JTextField referralSourceField = new JTextField();
        personalDetailsPanel.add(referralSourceLabel);
        personalDetailsPanel.add(referralSourceField);
        // Membership Detail Fields
        // Membership Start Date
        JLabel msdLabel = new JLabel("Membership Start Date");
        JComboBox<String> msdDayComboBox = new JComboBox<>(days);
        JComboBox<String> msdMonthComboBox = new JComboBox<>(months);
        JComboBox<String> msdYearComboBox = new JComboBox<>(years);
        Calendar today = Calendar.getInstance();
        msdDayComboBox.setSelectedIndex(today.get(Calendar.DAY_OF_MONTH) - 1);
        msdMonthComboBox.setSelectedIndex(today.get(Calendar.MONTH));
        int yearIndex = today.get(Calendar.YEAR) - 1950;
        msdYearComboBox.setSelectedIndex(Math.max(0, Math.min(yearIndex, years.length - 1)));
        membershipDetailsPanel.add(msdLabel);
        membershipDetailsPanel.add(msdDayComboBox);
        membershipDetailsPanel.add(msdMonthComboBox);
        membershipDetailsPanel.add(msdYearComboBox);
        // Plan
        JLabel planLabel = new JLabel("Plan");
        String[] planOptions = { "basic", "standard", "deluxe" };
        JComboBox<String> planDropdown = new JComboBox<>(planOptions);
        membershipDetailsPanel.add(planLabel);
        membershipDetailsPanel.add(planDropdown);
        // Trainers Name
        JLabel trainersNameLabel = new JLabel("Trainers Name");
        JTextField trainersNameField = new JTextField();
        membershipDetailsPanel.add(trainersNameLabel);
        membershipDetailsPanel.add(trainersNameField);
        // Removal Reason
        JLabel removalReasonLabel = new JLabel("Removal Reason");
        JTextArea removalReasonArea = new JTextArea();
        removalReasonArea.setLineWrap(true);
        removalReasonArea.setWrapStyleWord(true);
        JScrollPane removalReasonScrollPane = new JScrollPane(removalReasonArea);
        membershipDetailsPanel.add(removalReasonLabel);
        membershipDetailsPanel.add(removalReasonScrollPane);
        // Payment Detail Fields
        // Paid Amount
        JLabel paidAmountLabel = new JLabel("Paid Amount");
        JTextField paidAmountField = new JTextField();
        paymentDetailsPanel.add(paidAmountLabel);
        paymentDetailsPanel.add(paidAmountField);
        // Regular Plan Price
        JLabel regularPlanPriceLabel = new JLabel("Regular Plan Price");
        JTextField regularPlanPriceField = new JTextField("6,500");
        regularPlanPriceField.setEditable(false);
        paymentDetailsPanel.add(regularPlanPriceLabel);
        paymentDetailsPanel.add(regularPlanPriceField);
        // Premium Plan Price
        JLabel premiumPlanPriceLabel = new JLabel("Premium Plan Price");
        JTextField premiumPlanPriceField = new JTextField("50,000");
        premiumPlanPriceField.setEditable(false);
        paymentDetailsPanel.add(premiumPlanPriceLabel);
        paymentDetailsPanel.add(premiumPlanPriceField);
        // Discount
        JLabel discountLabel = new JLabel("Discount");
        JTextField discountField = new JTextField("0");
        discountField.setEditable(false);
        paymentDetailsPanel.add(discountLabel);
        paymentDetailsPanel.add(discountField);
        // Buttons
        // Personal Details Buttons
        // Mark Attendance
        JButton markAttendance = new JButton("Mark Attendance");
        markAttendance.setBackground(Color.decode("#ADD8E6"));
        markAttendance.setForeground(Color.BLACK);
        markAttendance.setFont(new Font("Arial", Font.BOLD, 12));
        personalDetailsPanel.add(markAttendance);
        /**
         * Action listener for the Mark Attendance button
         * Records attendance for gym members and increases their loyalty points
         * Only works for members with active membership status
         */
        markAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(IDField.getText());
                    for (GymMember member : gymMembers) {
                        if (id == member.getId()) {
                            if (!member.activeStatus) {
                                JOptionPane.showMessageDialog(frame,
                                        "The user membership must be activated to track attendance", "Inactive User",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            member.markAttendance();
                            JOptionPane.showMessageDialog(frame, "Attendance Marked Successfully", "Attendance Marked",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID", "Invalid ID",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Revert Regular Member
        JButton revertRegularMember = new JButton("Revert Regular Member");
        revertRegularMember.setBackground(Color.decode("#ADD8E6"));
        revertRegularMember.setForeground(Color.BLACK);
        revertRegularMember.setFont(new Font("Arial", Font.BOLD, 12));
        personalDetailsPanel.add(revertRegularMember);
        /**
         * Action listener for the Revert Regular Member button
         * Resets a Regular member's attributes to default values
         * Requires a removal reason before reverting the membership
         */
        revertRegularMember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (IDField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please Fill in the ID", "Empty ID Field",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt(IDField.getText());
                    for (GymMember member : gymMembers) {
                        if (id == member.getId()) {
                            String removalReason = removalReasonArea.getText();
                            if (removalReason.isEmpty()) {
                                JOptionPane.showMessageDialog(frame, "Please provide a reason for removal",
                                        "Empty Removal Reason", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            JOptionPane.showMessageDialog(frame, "Regular Member Reverted Successfully",
                                    "Member Reverted", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID", "Invalid ID",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Revert Premium Member
        JButton revertPremiumMember = new JButton("Revert Premium Member");
        revertPremiumMember.setBackground(Color.decode("#ADD8E6"));
        revertPremiumMember.setForeground(Color.BLACK);
        revertPremiumMember.setFont(new Font("Arial", Font.BOLD, 12));
        personalDetailsPanel.add(revertPremiumMember);
        /**
         * Action listener for the Revert Premium Member button
         * Resets a Premium member's attributes to default values
         * Requires a removal reason before reverting the membership
         */
        revertPremiumMember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (IDField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please Fill in the ID", "Empty ID Field",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt(IDField.getText());
                    for (GymMember member : gymMembers) {
                        if (id == member.getId()) {
                            String removalReason = removalReasonArea.getText();
                            if (removalReason.isEmpty()) {
                                JOptionPane.showMessageDialog(frame, "Please provide a reason for removal",
                                        "Empty Removal Reason", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            JOptionPane.showMessageDialog(frame, "Premium Member Reverted Successfully",
                                    "Member Reverted", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID", "Invalid ID",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Membership Details Buttons
        // Upgrade Plan
        JButton upgradePlan = new JButton("Upgrade Plan");
        upgradePlan.setBackground(Color.decode("#ADD8E6"));
        upgradePlan.setForeground(Color.BLACK);
        upgradePlan.setFont(new Font("Arial", Font.BOLD, 12));
        membershipDetailsPanel.add(upgradePlan);
        /**
         * Action listener for the Upgrade Plan button
         * Allows Regular members to upgrade their membership plan
         * Checks eligibility based on attendance requirements
         */
        upgradePlan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (IDField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please Fill in the ID", "Empty ID Field",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt(IDField.getText());
                    for (GymMember member : gymMembers) {
                        if (id == member.getId()) {
                            if (member.activeStatus == false) {
                                JOptionPane.showMessageDialog(frame,
                                        "The user membership must be activated to Upgrade the plan", "Inactive User",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            if (member instanceof RegularMember) {
                                RegularMember regularMember = (RegularMember) member;
                                String newPlan = (String) planDropdown.getSelectedItem();
                                String message = regularMember.upgradePlan(newPlan);
                                JOptionPane.showMessageDialog(frame, message, "Upgrade Message",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Upgrade is only available for Regular Member",
                                        "Invalid Member", JOptionPane.ERROR_MESSAGE);
                            }
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID", "Invalid ID",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Add Regular Member Button
        JButton addRegularMember = new JButton("Add Regular Member");
        addRegularMember.setBackground(Color.decode("#ADD8E6"));
        addRegularMember.setForeground(Color.BLACK);
        addRegularMember.setFont(new Font("Arial", Font.BOLD, 12));
        membershipDetailsPanel.add(addRegularMember);
        /**
         * Action listener for the Add Regular Member button
         * Creates and registers a new Regular member in the system
         * Validates all required fields before adding the member
         */
        addRegularMember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (IDField.getText().isEmpty() || nameField.getText().isEmpty()
                            || locationField.getText().isEmpty()
                            || phoneField.getText().isEmpty() || emailField.getText().isEmpty()
                            || dobDayComboBox.getSelectedItem() == null || dobMonthComboBox.getSelectedItem() == null
                            || dobYearComboBox.getSelectedItem() == null || genderGroup.getSelection() == null
                            || referralSourceField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all fields required for regular member.");
                        return;
                    }

                    for (GymMember member : gymMembers) {
                        if (member.getId() == Integer.parseInt(IDField.getText())) {
                            JOptionPane.showMessageDialog(frame, "ID already exists. Please enter a unique ID.");
                            return;
                        }
                    }

                    int id = Integer.parseInt(IDField.getText());
                    String name = nameField.getText();
                    String location = locationField.getText();
                    String phone = phoneField.getText();
                    String eMail = emailField.getText();
                    String gender = genderGroup.getSelection() == null ? ""
                            : genderGroup.getSelection().getActionCommand();
                    String DOB = dobYearComboBox.getSelectedItem() + "-" + dobMonthComboBox.getSelectedItem() + "-"
                            + dobDayComboBox.getSelectedItem();
                    String membershipStartDate = msdYearComboBox.getSelectedItem() + "-"
                            + msdMonthComboBox.getSelectedItem() + "-" + msdDayComboBox.getSelectedItem();
                    String referralSource = referralSourceField.getText();
                    gymMembers.add(new RegularMember(id, name, location, phone, eMail, gender, DOB, membershipStartDate,
                            referralSource));
                    JOptionPane.showMessageDialog(frame, "Regular Member added successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add Premium Member
        JButton addPremiumMember = new JButton("Add Premium Member");
        addPremiumMember.setBackground(Color.decode("#ADD8E6"));
        addPremiumMember.setForeground(Color.BLACK);
        addPremiumMember.setFont(new Font("Arial", Font.BOLD, 12));
        membershipDetailsPanel.add(addPremiumMember);
        /**
         * Action listener for the Add Premium Member button
         * Creates and registers a new Premium member in the system
         * Validates all required fields before adding the member
         */
        addPremiumMember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (IDField.getText().isEmpty() || nameField.getText().isEmpty()
                            || locationField.getText().isEmpty()
                            || phoneField.getText().isEmpty() || emailField.getText().isEmpty()
                            || dobDayComboBox.getSelectedItem() == null || dobMonthComboBox.getSelectedItem() == null
                            || dobYearComboBox.getSelectedItem() == null || genderGroup.getSelection() == null
                            || trainersNameField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all fields required for premium member.");
                        return;
                    }
                    for (GymMember member : gymMembers) {
                        if (member.getId() == Integer.parseInt(IDField.getText())) {
                            JOptionPane.showMessageDialog(frame, "ID already exists. Please enter a unique ID.");
                            return;
                        }
                    }
                    int id = Integer.parseInt(IDField.getText());
                    String name = nameField.getText();
                    String location = locationField.getText();
                    String phone = phoneField.getText();
                    String eMail = emailField.getText();
                    String gender = genderGroup.getSelection() == null ? ""
                            : genderGroup.getSelection().getActionCommand();
                    String DOB = dobYearComboBox.getSelectedItem() + "-" + dobMonthComboBox.getSelectedItem() + "-"
                            + dobDayComboBox.getSelectedItem();
                    String membershipStartDate = msdYearComboBox.getSelectedItem() + "-"
                            + msdMonthComboBox.getSelectedItem() + "-" + msdDayComboBox.getSelectedItem();
                    String personalTrainer = trainersNameField.getText();
                    gymMembers.add(new PremiumMember(id, name, location, phone, eMail, gender, DOB, membershipStartDate,
                            personalTrainer));
                    JOptionPane.showMessageDialog(frame, "Premium Member added successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Activate Membership
        JButton activateMembership = new JButton("Activate Membership");
        activateMembership.setBackground(Color.decode("#ADD8E6"));
        activateMembership.setForeground(Color.BLACK);
        activateMembership.setFont(new Font("Arial", Font.BOLD, 12));
        membershipDetailsPanel.add(activateMembership);
        /**
         * Action listener for the Activate Membership button
         * Activates an inactive membership for a gym member
         * Prevents activation of already active memberships
         */
        activateMembership.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (IDField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please Fill in the ID", "Empty ID Field",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt(IDField.getText());
                    for (GymMember member : gymMembers) {
                        if (id == member.getId()) {
                            if (member.activeStatus == true) {
                                JOptionPane.showMessageDialog(frame,
                                        "Membership is Already Activated for the given user",
                                        "Membership Already Active", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                            member.activeMembership();
                            JOptionPane.showMessageDialog(frame, "Membership Activated Successfully",
                                    "Membership Activated", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID", "Invalid ID",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Deactivate Membership
        JButton deactivateMembership = new JButton("Deactivate Membership");
        deactivateMembership.setBackground(Color.decode("#ADD8E6"));
        deactivateMembership.setForeground(Color.BLACK);
        deactivateMembership.setFont(new Font("Arial", Font.BOLD, 12));
        membershipDetailsPanel.add(deactivateMembership);
        /**
         * Action listener for the Deactivate Membership button
         * Deactivates an active membership for a gym member
         * Prevents deactivation of already inactive memberships
         */
        deactivateMembership.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (IDField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please Fill in the ID", "Empty ID Field",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt(IDField.getText());
                    for (GymMember member : gymMembers) {
                        if (id == member.getId()) {
                            if (member.activeStatus == false) {
                                JOptionPane.showMessageDialog(frame,
                                        "Membership is Already inactive for the given user",
                                        "Membership Already Deactive", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                            member.deactivateMembership();
                            JOptionPane.showMessageDialog(frame, "Membership Deactivated Successfully",
                                    "Membership Deactivated", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID", "Invalid ID",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Payment Details Buttons
        // Calculate Discount
        JButton calculateDiscount = new JButton("Calculate Discount");
        calculateDiscount.setBackground(Color.decode("#ADD8E6"));
        calculateDiscount.setForeground(Color.BLACK);
        calculateDiscount.setFont(new Font("Arial", Font.BOLD, 12));
        paymentDetailsPanel.add(calculateDiscount);
        /**
         * Action listener for the Calculate Discount button
         * Calculates discount for Premium members who have made full payment
         * Updates the discount field with the calculated amount
         */
        calculateDiscount.addActionListener(new ActionListener() {
            /**
             * Handles the calculate discount button click event
             * Verifies the member is Premium type before calculating discount
             * Displays appropriate error messages for invalid ID or non-Premium members
             * 
             * @param e the action event triggered by button click
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    if (IDField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please Fill in the ID", "Empty ID Field",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt(IDField.getText());
                    for (GymMember member : gymMembers) {
                        if (id == member.getId()) {
                            if (member instanceof PremiumMember) {
                                PremiumMember premiumMember = (PremiumMember) member;
                                double discount = premiumMember.calculateDiscount();
                                discountField.setText(String.valueOf(discount));
                                System.out.println("Discount Amount: " + discount);
                                JOptionPane.showMessageDialog(frame, "Discount Calculated Successfully", "Discount",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Discount is only available for Premium Member",
                                        "Invalid Member", JOptionPane.ERROR_MESSAGE);
                            }
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID", "Invalid ID",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Pay Due Amount
        JButton payDueAmount = new JButton("Pay Due Amount");
        payDueAmount.setBackground(Color.decode("#ADD8E6"));
        payDueAmount.setForeground(Color.BLACK);
        payDueAmount.setFont(new Font("Arial", Font.BOLD, 12));
        paymentDetailsPanel.add(payDueAmount);
        /**
         * Action listener for the Pay Due Amount button
         * Processes payments for Premium members towards their membership fee
         * Updates payment status and checks if full payment is completed
         */
        payDueAmount.addActionListener(new ActionListener() {
            /**
             * Handles the pay due amount button click event
             * Validates input fields for ID and payment amount
             * Calls the payDueAmount method of PremiumMember class
             * Displays payment status and remaining balance information
             * 
             * @param e the action event triggered by button click
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    if (IDField.getText().isEmpty() || paidAmountField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame,
                                "Please Fill in all the required field (ID and paid Amount)",
                                "Empty ID Field",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt(IDField.getText());
                    for (GymMember member : gymMembers) {
                        if (id == member.getId()) {
                            if (member instanceof PremiumMember) {
                                PremiumMember premiumMember = (PremiumMember) member;
                                double paidAmount = Double.parseDouble(paidAmountField.getText());
                                String message = premiumMember.payDueAmount(paidAmount);
                                JOptionPane.showMessageDialog(frame, message, "Payment Message",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Payment is only available for Premium Member",
                                        "Invalid Member", JOptionPane.ERROR_MESSAGE);
                            }
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID", "Invalid ID",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Invalid input. Please enter numeric values for ID and Paid Amount.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Other Actions Buttons
        // Display
        JButton display = new JButton("Display");
        display.setBackground(Color.decode("#7ed2ed"));
        otherActionsPanel.add(display);
        /**
         * Action listener for the Display button
         * Creates and displays a table showing all gym members' details
         * Formats data differently based on member type (Regular or Premium)
         */
        display.addActionListener(new ActionListener() {
            /**
             * Handles the display button click event
             * Creates a table with all gym members' information
             * Differentiates between Regular and Premium members in the display
             * 
             * @param e the action event triggered by button click
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] columnNames = {
                            "ID", "Name", "Address", "Phone", "Email", "Gender", "Date of Birth",
                            "Membership Start Date", "Attendance", "Loyalty Points", "Active Status",
                            "Type", "Plan", "Price", "Referral Source",
                            "Personal Trainer", "Full Payment", "Paid Amount", "Discount Amount"
                    };

                    String[][] data = new String[gymMembers.size()][columnNames.length];

                    for (int i = 0; i < gymMembers.size(); i++) {
                        GymMember member = gymMembers.get(i);
                        String type = (member instanceof PremiumMember) ? "Premium" : "Regular";

                        data[i][0] = String.valueOf(member.getId());
                        data[i][1] = member.getName();
                        data[i][2] = member.getLocation();
                        data[i][3] = member.getPhone();
                        data[i][4] = member.getEmail();
                        data[i][5] = member.getGender();
                        data[i][6] = member.getDOB();
                        data[i][7] = member.getMembershipStartDate();
                        data[i][8] = String.valueOf(member.getAttendance());
                        data[i][9] = String.valueOf(member.getLoyaltyPoints());
                        data[i][10] = String.valueOf(member.isActiveStatus());
                        data[i][11] = type;

                        if (member instanceof RegularMember) {
                            RegularMember r = (RegularMember) member;
                            data[i][12] = r.getPlan();
                            data[i][13] = String.valueOf(r.getPrice());
                            data[i][14] = r.getReferralSource();

                            // leave the premium fields blank
                            data[i][15] = "N/A";
                            data[i][16] = "N/A";
                            data[i][17] = "N/A";
                            data[i][18] = "N/A";
                        } else if (member instanceof PremiumMember) {
                            PremiumMember p = (PremiumMember) member;

                            // leave the regular fields blank
                            data[i][12] = "N/A";
                            data[i][13] = "N/A";
                            data[i][14] = "N/A";

                            // Premium fields
                            data[i][15] = p.getPersonalTrainer();
                            data[i][16] = String.valueOf(p.getIsFullPayment());
                            data[i][17] = String.valueOf(p.getPaidAmount());
                            data[i][18] = String.valueOf(p.getDiscountAmount());
                        }
                    }

                    JTable table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setPreferredSize(new Dimension(1300, 650));

                    JOptionPane.showMessageDialog(frame, scrollPane, "🏋️ Gym Members",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Clear
        JButton clear = new JButton("Clear");
        clear.setBackground(Color.decode("#7ed2ed"));
        otherActionsPanel.add(clear);
        /**
         * Action listener for the Clear button
         * Resets all form fields to their default values
         * Provides confirmation when fields are cleared
         */
        clear.addActionListener(new ActionListener() {
            /**
             * Handles the clear button click event
             * Resets all text fields, combo boxes, and selections to default values
             * Shows a confirmation message upon successful clearing
             * 
             * @param e the action event triggered by button click
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    IDField.setText("");
                    nameField.setText("");
                    locationField.setText("");
                    phoneField.setText("");
                    emailField.setText("");
                    dobDayComboBox.setSelectedIndex(0);
                    dobMonthComboBox.setSelectedIndex(0);
                    dobYearComboBox.setSelectedIndex(0);
                    genderGroup.clearSelection();
                    referralSourceField.setText("");
                    msdDayComboBox.setSelectedIndex(today.get(Calendar.DAY_OF_MONTH) - 1);
                    msdMonthComboBox.setSelectedIndex(today.get(Calendar.MONTH));
                    msdYearComboBox.setSelectedIndex(Math.max(0, Math.min(yearIndex, years.length - 1)));
                    planDropdown.setSelectedIndex(0);
                    trainersNameField.setText("");
                    removalReasonArea.setText("");
                    paidAmountField.setText("");
                    discountField.setText("0");
                    JOptionPane.showMessageDialog(frame, "All fields cleared successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,
                            "An unexpected error occurred while clearing fields: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Save To File
        JButton saveToFile = new JButton("Save To File");
        saveToFile.setBackground(Color.decode("#7ed2ed"));
        otherActionsPanel.add(saveToFile);

        // Save To File Action Listener
        saveToFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("gym_members.txt");
                    String header = String.format(
                            "| %-4s | %-16s | %-27s | %-12s | %-27s | %-8s | %-17s | %-23s | %-12s | %-16s | %-15s | %-9s | %-7s | %-8s | %-17s | %-21s | %-14s | %-11s | %-15s |\n",
                            "ID", "Name", "Address", "Phone", "Email", "Gender", "Date of Birth",
                            "Membership Start Date", "Attendance", "Loyalty Points", "Active Status", "Type", "Plan",
                            "Price", "Referral Source", "Personal Trainer", "Full Payment", "Paid Amount",
                            "Discount Amount");
                    String separator = "+------+------------------+-----------------------------+--------------+-----------------------------+----------+-------------------+-------------------------+--------------+------------------+-----------------+-----------+---------+----------+-------------------+-----------------------+----------------+---------------+-------------------+\n";
                    writer.write(separator);
                    writer.write(header);
                    writer.write(separator);
                    for (GymMember member : gymMembers) {
                        if (member instanceof RegularMember) {
                            RegularMember r = (RegularMember) member;
                            writer.write(String.format(
                                    "| %-4d | %-16s | %-27s | %-12s | %-27s | %-8s | %-17s | %-23s | %-12d | %-16.1f | %-15s | %-9s | %-7s | %-8.1f | %-17s | %-21s | %-14s | %-11s | %-15s |\n",
                                    r.getId(), r.getName(), r.getLocation(), r.getPhone(), r.getEmail(), r.getGender(),
                                    r.getDOB(), r.getMembershipStartDate(),
                                    r.getAttendance(), r.getLoyaltyPoints(), r.isActiveStatus(), "Regular", r.getPlan(),
                                    r.getPrice(), r.getReferralSource(),
                                    "N/A", "N/A", "N/A", "N/A"));
                        } else if (member instanceof PremiumMember) {
                            PremiumMember p = (PremiumMember) member;
                            writer.write(String.format(
                                    "| %-4d | %-16s | %-27s | %-12s | %-27s | %-8s | %-17s | %-23s | %-12d | %-16.1f | %-15s | %-9s | %-7s | %-8s | %-17s | %-21s | %-14s | %-11.1f | %-15.1f |\n",
                                    p.getId(), p.getName(), p.getLocation(), p.getPhone(), p.getEmail(), p.getGender(),
                                    p.getDOB(), p.getMembershipStartDate(),
                                    p.getAttendance(), p.getLoyaltyPoints(), p.isActiveStatus(), "Premium", "N/A",
                                    "N/A", "N/A",
                                    p.getPersonalTrainer(), p.getIsFullPayment(), p.getPaidAmount(),
                                    p.getDiscountAmount()));
                        }
                    }
                    writer.write(separator);
                    writer.close();
                    JOptionPane.showMessageDialog(frame, "Data saved to gym_members.txt successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving to file: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Read From File
        JButton readFromFile = new JButton("Read From File");
        readFromFile.setBackground(Color.decode("#7ed2ed"));
        otherActionsPanel.add(readFromFile);

        // Read From File Action Listener
        readFromFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("gym_members.txt");
                    if (!file.exists()) {
                        JOptionPane.showMessageDialog(frame, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    FileReader reader = new FileReader(file);
                    StringBuilder sb = new StringBuilder();
                    int ch;

                    // Read character by character
                    while ((ch = reader.read()) != -1) {
                        sb.append((char) ch);
                    }

                    // Split content into lines
                    String[] lines = sb.toString().split("\\n");

                    gymMembers.clear();

                    // Process each line
                    for (String line : lines) {
                        if (line.startsWith("| ") && !line.contains("ID ")) {
                            String[] parts = line.split("\\|");
                            if (parts.length < 20)
                                continue;

                            int id = Integer.parseInt(parts[1].trim());
                            String name = parts[2].trim();
                            String address = parts[3].trim();
                            String phone = parts[4].trim();
                            String email = parts[5].trim();
                            String gender = parts[6].trim();
                            String dob = parts[7].trim();
                            String msd = parts[8].trim();
                            int attendance = Integer.parseInt(parts[9].trim());
                            double loyaltyPoints = Double.parseDouble(parts[10].trim());
                            boolean activeStatus = Boolean.parseBoolean(parts[11].trim());
                            String type = parts[12].trim();

                            if (type.equals("Regular")) {
                                String plan = parts[13].trim();
                                double price = Double.parseDouble(parts[14].trim());
                                String referralSource = parts[15].trim();
                                RegularMember r = new RegularMember(id, name, address, phone, email, gender, dob, msd,
                                        referralSource);
                                r.setAttendance(attendance);
                                r.setLoyaltyPoints(loyaltyPoints);
                                if (!plan.equals("N/A"))
                                    r.setPlan(plan);
                                r.setPrice(price);
                                if (activeStatus)
                                    r.activeMembership();
                                gymMembers.add(r);
                            } else if (type.equals("Premium")) {
                                String personalTrainer = parts[16].trim();
                                boolean isFullPayment = Boolean.parseBoolean(parts[17].trim());
                                double paidAmount = Double.parseDouble(parts[18].trim());
                                double discountAmount = Double.parseDouble(parts[19].trim());
                                PremiumMember p = new PremiumMember(id, name, address, phone, email, gender, dob, msd,
                                        personalTrainer);
                                p.setAttendance(attendance);
                                p.setLoyaltyPoints(loyaltyPoints);
                                p.setIsFullPayment(isFullPayment);
                                p.setPaidAmount(paidAmount);
                                p.setDiscountAmount(discountAmount);
                                if (activeStatus)
                                    p.activeMembership();
                                gymMembers.add(p);
                            }
                        }
                    }

                    reader.close();
                    JOptionPane.showMessageDialog(frame, "Data loaded from gym_members.txt successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error reading from file: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Positioning the personal details panel
        IDLabel.setBounds(10, 30, 100, 30);
        IDField.setBounds(120, 30, 230, 30);
        nameLabel.setBounds(10, 70, 100, 30);
        nameField.setBounds(120, 70, 230, 30);
        locationLabel.setBounds(10, 110, 100, 30);
        locationField.setBounds(120, 110, 230, 30);
        phoneLabel.setBounds(10, 150, 100, 30);
        phoneField.setBounds(120, 150, 230, 30);
        emailLabel.setBounds(10, 190, 100, 30);
        emailField.setBounds(120, 190, 230, 30);
        dobLabel.setBounds(10, 230, 100, 30);
        dobDayComboBox.setBounds(120, 230, 50, 30);
        dobMonthComboBox.setBounds(180, 230, 70, 30);
        dobYearComboBox.setBounds(260, 230, 90, 30);
        genderLabel.setBounds(10, 270, 100, 30);
        maleButton.setBounds(120, 270, 70, 30);
        femaleButton.setBounds(190, 270, 150, 30);
        referralSourceLabel.setBounds(10, 310, 100, 30);
        referralSourceField.setBounds(120, 310, 230, 30);
        // Positioning the buttons in the personal details panel
        markAttendance.setBounds(10, 390, 376, 40);
        revertRegularMember.setBounds(10, 440, 183, 40);
        revertPremiumMember.setBounds(203, 440, 183, 40);

        // Positioning the membership details panel
        msdLabel.setBounds(10, 30, 150, 30);
        msdDayComboBox.setBounds(170, 30, 50, 30);
        msdMonthComboBox.setBounds(230, 30, 70, 30);
        msdYearComboBox.setBounds(310, 30, 80, 30);
        planLabel.setBounds(10, 70, 150, 30);
        planDropdown.setBounds(170, 70, 220, 30);
        trainersNameLabel.setBounds(10, 110, 150, 30);
        trainersNameField.setBounds(170, 110, 220, 30);
        removalReasonLabel.setBounds(10, 170, 150, 30);
        removalReasonScrollPane.setBounds(10, 210, 380, 100);
        // Positioning the buttons in the membership details panel
        addRegularMember.setBounds(10, 340, 183, 40);
        addPremiumMember.setBounds(203, 340, 183, 40);
        upgradePlan.setBounds(10, 390, 376, 40);
        activateMembership.setBounds(10, 440, 183, 40);
        deactivateMembership.setBounds(203, 440, 183, 40);
        // Positioning the payment details panel
        paidAmountLabel.setBounds(10, 30, 150, 30);
        paidAmountField.setBounds(170, 30, 150, 30);
        regularPlanPriceLabel.setBounds(10, 70, 150, 30);
        regularPlanPriceField.setBounds(170, 70, 150, 30);
        premiumPlanPriceLabel.setBounds(10, 110, 150, 30);
        premiumPlanPriceField.setBounds(170, 110, 150, 30);
        discountLabel.setBounds(10, 150, 150, 30);
        discountField.setBounds(170, 150, 150, 30);
        // positioning the buttons in the payment details panel
        calculateDiscount.setBounds(10, 210, 183, 40);
        payDueAmount.setBounds(203, 210, 183, 40);
        // Positioning the other actions panel
        display.setBounds(10, 30, 376, 40);
        clear.setBounds(10, 80, 376, 40);
        saveToFile.setBounds(10, 130, 376, 40);
        readFromFile.setBounds(10, 180, 376, 40);

        // Setting the frame to be visible
        frame.setVisible(true);
    }
}