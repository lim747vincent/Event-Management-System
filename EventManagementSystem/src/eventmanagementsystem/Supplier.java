package eventmanagementsystem;

public class Supplier {

    private String companyName;
    private String category;
    private String email;
    private String supplierId;

    public Supplier(String companyName, String category, String email, String supplierId) {
        this.companyName = companyName;
        this.category = category;
        this.email = email;
        this.supplierId = supplierId;
    }

    public Supplier() {
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Supplier{" + "companyName=" + companyName + ", category=" + category + ", email=" + email + '}';
    }

}
