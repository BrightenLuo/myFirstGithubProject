public class Product {
    private String id;
    private String name;
    private float price;
    private String descr;

    @Override
    public String toString() {
        return "productId='" + id + '\'' +
                ", productName='" + name + '\'' +
                ", productPrice=" + price +
                ", productDescr='" + descr + '\'' ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
