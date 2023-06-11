package account;

public abstract class Account extends AccountComponent {
    protected double value;

    public Account(int id) {
        super(id);
    }

    public abstract double getValue();

    public void setValue(double value) {
        this.value = value;
    }

    public void increaseValue(double value) {
        this.value += value;
    }

    public boolean decreaseValue(double value) {
        if (this.value >= value) {
            this.value -= value;
            return true;
        }
        return false;
    }

    public abstract String getType();

}
