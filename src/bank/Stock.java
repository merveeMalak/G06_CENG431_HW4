package bank;

public class Stock {
    private int id;
    private String name;
    private double price;

    public Stock(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getId(){
        return this.id;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("ID:%s STOCK - %s - Value: %s", this.id, this.name, this.price);
    }


}
