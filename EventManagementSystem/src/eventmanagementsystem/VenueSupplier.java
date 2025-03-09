package eventmanagementsystem;

public class VenueSupplier extends Supplier {

    private String location;
    private String pricePerDay;
    private String maxCapacity;

    public VenueSupplier(String location, String pricePerDay, String maxCapacity, String companyName, String category, String email, String supplierId) {
        super(companyName, category, email, supplierId);
        this.location = location;
        this.pricePerDay = pricePerDay;
        this.maxCapacity = maxCapacity;
    }

    public VenueSupplier() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        double doublePrice = Double.parseDouble(pricePerDay);
        String formattedPrice = String.format("%.2f", doublePrice);

        return "\nSupplier ID: " + super.getSupplierId()
                + "\nSupplier Name: " + super.getCompanyName()
                + "\nCategory: " + super.getCategory()
                + "\nEmail: " + super.getEmail()
                + "\nLocation: " + location
                + "\nPrice per day: RM " + formattedPrice
                + "\nMax capacity: " + maxCapacity;
    }

}
