package eventmanagementsystem;

public class FoodDrinkSupplier extends Supplier {

    private String foodDrinkDescription;
    private String pricePerPax;

    public FoodDrinkSupplier(String foodDrinkDescription, String pricePerPax, String companyName, String category, String email, String supplierId) {
        super(companyName, category, email, supplierId);
        this.foodDrinkDescription = foodDrinkDescription;
        this.pricePerPax = pricePerPax;
    }

    public FoodDrinkSupplier() {
    }

    public String getFoodDrinkDescription() {
        return foodDrinkDescription;
    }

    public void setFoodDrinkDescription(String foodDrinkDescription) {
        this.foodDrinkDescription = foodDrinkDescription;
    }

    public String getPricePerPax() {
        return pricePerPax;
    }

    public void setPricePerPax(String pricePerPax) {
        this.pricePerPax = pricePerPax;
    }

    @Override
    public String toString() {
        double doublePrice = Double.parseDouble(pricePerPax);
        String formattedPrice = String.format("%.2f", doublePrice);
        
        return "\nSupplier ID: " + super.getSupplierId()
                + "\nSupplier Name: " + super.getCompanyName()
                + "\nCategory: " + super.getCategory()
                + "\nEmail: " + super.getEmail()
                + "\nDescription: " + foodDrinkDescription
                + "\nPrice per pax: RM " + formattedPrice;
    }

}
