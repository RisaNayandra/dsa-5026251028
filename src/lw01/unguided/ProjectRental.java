package lw01.unguided;

public class ProjectRental extends Rental {

    public ProjectRental(String id, int days) {
        super(id, days);
    }

    @Override 
    public int calculateCharge() {
        int days = getRentalDays();
        int baseCharge;
        
        if (days <= 3) {
            baseCharge = days * 60000;
        } else {
            baseCharge = (3 * 60000) + ((days - 3) * 45000);
        }
        
        return baseCharge + 20000;
    }

    @Override
    public String label() {
        return "Project";
    }
}
