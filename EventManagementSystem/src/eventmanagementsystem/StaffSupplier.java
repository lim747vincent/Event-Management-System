package eventmanagementsystem;

public class StaffSupplier extends Supplier {

    private String pricePerDay;

    public StaffSupplier(String pricePerDay, String companyName, String category, String email, String supplierId) {
        super(companyName, category, email, supplierId);
        this.pricePerDay = pricePerDay;
    }

    public StaffSupplier() {
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        double doublePrice = Double.parseDouble(pricePerDay);
        String formattedPrice = String.format("%.2f", doublePrice);

        return "\nSupplier ID: " + super.getSupplierId()
                + "\nSupplier Name: " + super.getCompanyName()
                + "\nCategory: " + super.getCategory()
                + "\nEmail: " + super.getEmail()
                + "\nPrice per day: RM " + formattedPrice;
    }

}
