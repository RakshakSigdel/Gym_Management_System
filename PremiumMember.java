/**
 * Child Class for the GymMember representing a Premium member of the gym
 * It adds specific attributes and methods for Premium members
 * 
 * @author Rakshak Sigdel
 * @version 0.1
 */
public class PremiumMember extends GymMember {
    private final double premiumCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount, discountAmount;

    /**
     * A constructor for RegulaeMember class. It initializes the member's
     * attributes.
     * It also make call to the parent class constructor
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
     * @param personalTrainer     includes the name of the personal trainer
     */
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB,
            String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.premiumCharge = 50000;
        this.isFullPayment = false;
        this.paidAmount = 0.0;
        this.discountAmount = 0.0;
    }

    /**
     * A method to increase the attendance of the premium member. It also increases
     * the
     * loyalty points of the member by 10 points
     */
    public void markAttendance() {
        attendance += 1;
        loyaltyPoints += 10;
    };

    /**
     * A method to pay the due amount for the premium member. It checks if the
     * payment is already made or not. It also checks if the amount entered is valid
     * and if it exceeds the due amount. It updates the paid amount and checks if
     * the payment is completed or not. It returns a message indicating the payment
     * status and the remaining due amount.
     */
    public String payDueAmount(double paidAmount) {
        if (isFullPayment) {
            return "Full payment has already been made. No due amount left.";
        }
        if (paidAmount <= 0) {
            return "Invalid amount entered. Please enter a valid amount.";
        }
        if (this.paidAmount + paidAmount > premiumCharge) {
            return "Amount entered exceeds the due amount. Please enter a valid amount.";
        }
        this.paidAmount += paidAmount;
        double remainingAmount = premiumCharge - this.paidAmount; 
        isFullPayment = (this.paidAmount == premiumCharge);
        if (isFullPayment) {
            return "Full payment completed successfully. No due amount left.";
        } else {
            return "Amount paid successfully. Remaining due amount: " + remainingAmount;
        }
    }

    /**
     * A method to calculate the discount for the premium member. It checks if the
     * payment is made in full. If yes, it calculates the discount amount as 10% of
     * the premium charge.
     * 
     * @return double value for the discount amount
     */
    public double calculateDiscount() {
        if (isFullPayment) {
            discountAmount = (10.0 / 100) * premiumCharge;
            return discountAmount;
        }
        return 0.0;
    }

    /**
     * A method to reset the member's attributes to default values. It sets the
     * activeStatus to false, attendance to 0, and loyaltyPoints to 0.0.
     */
    public void revertPremiumMember() {
        resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0.0;
        this.discountAmount = 0.0;
    }

    /**
     * * A method to display the member's attributes. It calls the parent class
     * display method and also displays the premium member's specific attributes.
     */
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Premium Charge: " + premiumCharge);
        System.out.println("Full Payment Status: " + isFullPayment);
        System.out.println("Remaining Due Amount: " + (premiumCharge - paidAmount));
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }

    // GETTERS FOR ALL THE ATTRIBUTES
    public double getPremiumCharge() {
        return premiumCharge;
    }

    public String getPersonalTrainer() {
        return personalTrainer;
    }

    public boolean getIsFullPayment() {
        return isFullPayment;
    }
    public boolean setIsFullPayment(boolean isFullPayment) {
        this.isFullPayment = isFullPayment;
        return isFullPayment;
    }

    public double getPaidAmount() {
        return paidAmount;
    }
    public double setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
        return paidAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
    public double setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
        return discountAmount;
    }
}
