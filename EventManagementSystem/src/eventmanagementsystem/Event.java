package eventmanagementsystem;

public abstract class Event {

    private String name;
    private String date;
    private String price;
    private String location;
    private String eventId;
    private String eventStatus;

    public Event(String name, String date, String price, String location, String eventId, String eventStatus) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.location = location;
        this.eventId = eventId;
        this.eventStatus = eventStatus;
    }

    public Event() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    @Override
    public String toString() {
        return "Event{" + "name=" + name + ", date=" + date + ", price=" + price + ", location=" + location + ", eventId=" + eventId + ", eventStatus=" + eventStatus + '}';
    }

}
