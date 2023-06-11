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

    public double getPrice() {
        return price;
    }
}
