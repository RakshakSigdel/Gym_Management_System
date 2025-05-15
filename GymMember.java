/**
 * Root Class for the Gym Management Systen
 * This class contains the common attributes and methods for all gym members
 * 
 * @author Rakshak Sigdel
 * @version 0.1
 */
public abstract class GymMember {
    protected int id;
    protected String name, location, phone, email, gender, DOB, membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;

    /**
     * Constructor for GymMember class
     * 
     * @param id                  includes the id of the gym member
     * @param name                includes the name of the gym member
     * @param location            includes the location of the gym member
     * @param phone               includes the phone number of the gym member
     * @param email               includes the email of the gym member
     * @param gender              includes the gender of the gym member
     * @param DOB                 includes the date of birth of the gym member
     * @param membershipStartDate includes the membership start date of the gym
     *                            member
     */
    public GymMember(int id, String name, String location, String phone, String email, String gender, String DOB,
            String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0.0;
        this.activeStatus = false;

    }

    /**
     * Abstract method that needs to be implemented by regular and premium member
     * classes.
     */
    public abstract void markAttendance();

    /**
     * method that activates the membership of the member. It sets the activeStatus
     * to true.
     */
    public void activateMembership() {
        activeStatus = true;
    }

    /**
     * method that deactivates the membership of the member. It sets the
     * activeStatus to false.
     */
    public void deactivateMembership() {
        if (activeStatus == false) {
            System.out.println("Membership must be activated to deactivate");
            return;
        }
        activeStatus = false;
    }

    /**
     * method that resets the member's attributes to default values. It sets
     * activeStatus to false, attendance to 0, and loyaltyPoints to 0.0.
     */
    public void resetMember() {
        activeStatus = false;
        attendance = 0;
        loyaltyPoints = 0.0;
    }

    /**
     * method that displays the member's attributes. It prints the id, name,
     * location,phone,email,gender,DOB,membershipStartDate,attendance,loyaltyPoints,
     * and activeStatus of the member.
     */
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phone);
        System.out.println("E-Mail: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("Date of Birth: " + DOB);
        System.out.println("Membership Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Membership Active Status: " + activeStatus);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getDOB() {
        return DOB;
    }

    public String getMembershipStartDate() {
        return membershipStartDate;
    }

    public int getAttendance() {
        return attendance;
    }
    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }
}
