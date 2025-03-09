package eventmanagementsystem;

public class AvailableEvent extends Event {

    private String currentCapacity;
    private String maxCapacity;
    private String eventOrganizerId;
    private String foodDrinkName;
    private String foodDrinkEmail;
    private String foodDrinkDescription;
    private String foodDrinkPricePerPax;
    private String venueName;
    private String venueEmail;
    private String venueLocation;
    private String venuePricePerDay;
    private String venueMaxCapacity;
    private String staffName;
    private String staffEmail;
    private String staffPricePerDay;

    public AvailableEvent(String eventId, String name, String date, String price, String currentCapacity, String maxCapacity, String location, String eventStatus, String eventOrganizerId, String foodDrinkName, String foodDrinkEmail, String foodDrinkDescription, String foodDrinkPricePerPax, String venueName, String venueEmail, String venueLocation, String venuePricePerDay, String venueMaxCapacity, String staffName, String staffEmail, String staffPricePerDay) {
        super(name, date, price, location, eventId, eventStatus);
        this.currentCapacity = currentCapacity;
        this.maxCapacity = maxCapacity;
        this.eventOrganizerId = eventOrganizerId;
        this.foodDrinkName = foodDrinkName;
        this.foodDrinkEmail = foodDrinkEmail;
        this.foodDrinkDescription = foodDrinkDescription;
        this.foodDrinkPricePerPax = foodDrinkPricePerPax;
        this.venueName = venueName;
        this.venueEmail = venueEmail;
        this.venueLocation = venueLocation;
        this.venuePricePerDay = venuePricePerDay;
        this.venueMaxCapacity = venueMaxCapacity;
        this.staffName = staffName;
        this.staffEmail = staffEmail;
        this.staffPricePerDay = staffPricePerDay;
    }

    public AvailableEvent() {
    }

    public String getFoodDrinkName() {
        return foodDrinkName;
    }

    public void setFoodDrinkName(String foodDrinkName) {
        this.foodDrinkName = foodDrinkName;
    }

    public String getFoodDrinkEmail() {
        return foodDrinkEmail;
    }

    public void setFoodDrinkEmail(String foodDrinkEmail) {
        this.foodDrinkEmail = foodDrinkEmail;
    }

    public String getFoodDrinkDescription() {
        return foodDrinkDescription;
    }

    public void setFoodDrinkDescription(String foodDrinkDescription) {
        this.foodDrinkDescription = foodDrinkDescription;
    }

    public String getFoodDrinkPricePerPax() {
        return foodDrinkPricePerPax;
    }

    public void setFoodDrinkPricePerPax(String foodDrinkPricePerPax) {
        this.foodDrinkPricePerPax = foodDrinkPricePerPax;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueEmail() {
        return venueEmail;
    }

    public void setVenueEmail(String venueEmail) {
        this.venueEmail = venueEmail;
    }

    public String getVenueLocation() {
        return venueLocation;
    }

    public void setVenueLocation(String venueLocation) {
        this.venueLocation = venueLocation;
    }

    public String getVenuePricePerDay() {
        return venuePricePerDay;
    }

    public void setVenuePricePerDay(String venuePricePerDay) {
        this.venuePricePerDay = venuePricePerDay;
    }

    public String getVenueMaxCapacity() {
        return venueMaxCapacity;
    }

    public void setVenueMaxCapacity(String venueMaxCapacity) {
        this.venueMaxCapacity = venueMaxCapacity;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffPricePerDay() {
        return staffPricePerDay;
    }

    public void setStaffPricePerDay(String staffPricePerDay) {
        this.staffPricePerDay = staffPricePerDay;
    }

    public String getEventOrganizerId() {
        return eventOrganizerId;
    }

    public void setEventOrganizerId(String eventOrganizerId) {
        this.eventOrganizerId = eventOrganizerId;
    }

    public String getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(String currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "AvailableEvent{" + "currentCapacity=" + currentCapacity + ", maxCapacity=" + maxCapacity + '}';
    }

}
