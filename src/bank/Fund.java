package bank;

public class Fund {
    private int id;
    private String name;
    private double price;

    public Fund(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("ID:%s FUND - %s - Value: %s", this.id, this.name, this.price);
    }
}
