package lw01.unguided;

public abstract class Rental implements Chargeable {
    private String rentalId;
    private int rentalDays;

    public Rental(String rentalId, int rentalDays) {
        this.rentalId = rentalId;
        this.rentalDays = rentalDays;
    }

    public String getRentalId() {
        return rentalId;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    @Override 
    public abstract int calculateCharge();

    public void displayRentalInfo() {
        System.out.println("Rental ID: " + rentalId);
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Total Charge: " + calculateCharge());
    }

    public String label() {
        return "Rental";
    }

    public String summary() {
        return rentalId + " | " + label() + " | " + calculateCharge();
    }
    
}