package MoneyTracker;

public class Income extends Transaction{
    public Income(double amount, String note) {
        super(amount, note);
    }

    public Income(double amount,String note, String categoryName) {
        super(amount,note, categoryName);
    }

    @Override
    public double sum(double accumulated){
        return this.getAmount()+accumulated;
    };
}
