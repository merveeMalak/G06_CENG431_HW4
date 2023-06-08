package account;

<<<<<<< Updated upstream
public abstract class Account extends AccountComponent{

=======
public abstract class Account extends AccountComponent {
    protected double value;
>>>>>>> Stashed changes

    public Account(int id) {
        super(id);
    }

    public double getBalance(){
        return value;
    };


<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes
}
