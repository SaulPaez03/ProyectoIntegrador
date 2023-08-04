package MoneyTracker;

public class Expenditure extends Transaction{
    public Expenditure(double amount,String note) {
        super(amount, note);
    }

    public Expenditure(double amount,String note, String categoryName) {
        super(amount, note,categoryName);
    }

    @Override
    public double sum(double accumulated) {
        return accumulated - this.getAmount();
    }
}
