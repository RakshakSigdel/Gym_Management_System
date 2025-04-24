/**
 * Child Class for the GymMember representing a regular member of the gym
 * It adds specific attributes and methods for regular members
 * 
 * @author Rakshak Sigdel
 * @version 0.1
 */
public class RegularMember extends GymMember {
    // ATTRIBUTES OF THE CLASS REGULARMEMBER
    private final int attendanceLimit;
    private boolean isEligibleForUpgrade;
    private String removalReason, referralSource, plan;
    private double price;

    /**
     * A constructor for RegulaeMember class. It initializes the member's
     * attributes. It also make call to the parent class constructor
     * 
     * @param id                  includes the id of the gym member
     * @param name                includes the name of the gym member
     * @param location            includes the location of the gym member
     * @param phone               includes the phone number of the gym member
     * @param email               includes the email of the gym member
     * @param gender              includes the gender of the gym member
     * @param DOB                 includes the date of birth of the gym member
     * @param membershipStartDate includes the membership star date of the gym
     *                            member
     * @param referralSource      includes the referral source of the gym member
     */
    public RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB,
            String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.referralSource = referralSource;
        this.attendanceLimit = 30;
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.plan = "basic";
        this.price = 6500;
    }

    /**
     * A method to increase the attendance of the regular member. It also increases the
     * loyalty points of the member by 5 points
     */
    public void markAttendance() {
        attendance += 1;
        loyaltyPoints += 5;
    };

    /**
     * A method to get the price for the desired user's plan
     * 
     * @param plan
     * @return double value for the price of the selected plan
     */
    public double getPlanPrice(String plan) {
        switch (plan) {
            case "basic":
                return 6500;
            case "standard":
                return 12500;
            case "deluxe":
                return 18500;
            default:
                return -1;
        }
    }

    /**
     * A method to check if the member is eligible for upgrade or not. It checks
     * the attendance of the member and compares it with the attendance limit
     * @return boolean value for the eligibility of the member for upgrade
     */
    public boolean checkEligibility() {
        isEligibleForUpgrade = (attendance >= attendanceLimit);
        return isEligibleForUpgrade;
    }

    /**
     * A method to upgrade the plan of the member. It checks if the member is
     * eligible for upgrade or not. It also checks if the new plan is valid or not.
     * @param newplan
     * @return String value for the status of the upgrade
     */
    public String upgradePlan(String newplan) {
        if (!checkEligibility()) {
            return "Upgrade not allowed. Attendance requirement not met";
        }
        double newprice = getPlanPrice(newplan);
        if (price == -1) {
            return "Invalid plan selected. Please choose a valid plan";
        }
        if (this.plan.equals(newplan)) {
            return "You are already on the same plan";
        }
        this.plan = newplan;
        this.price = newprice;
        return "Congratulation! You have been shifted to " + newplan + " plan";
    }

    /**
     * A method to reset the regular member's attributes to default values. It sets the
     * activeStatus to false, attendance to 0, loyalty points to 0, and takes the removal reason
     * @param removalReason
     */
    public void revertRegularMember(String removalReason) {
        resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason;
    }

    /**
     * A method to display the regular member's attributes. It also displays the
     * parent class attributes
     */
    public void display() {
        super.display();
        System.out.println("Attendance Limit: " + attendanceLimit);
        System.out.println("Eligible for Upgrade: " + isEligibleForUpgrade);
        if (removalReason != null) {
            System.out.println("Removal Reason: " + removalReason);
        }
        System.out.println("Referral Source: " + referralSource);
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
    }

    // GETTER METHODS FOR ALL ATTRIBUTE
    public int getAttendanceLimit() {
        return attendanceLimit;
    }

    public boolean getIsEligibleForUpgrade() {
        return isEligibleForUpgrade;
    }

    public String getRemovalReason() {
        return removalReason;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public String getPlan() {
        return plan;
    }

    public double getPrice() {
        return price;
    }
}
