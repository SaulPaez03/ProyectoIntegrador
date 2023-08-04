package MoneyTracker;

import java.time.LocalDate;

public abstract class Transaction {
    private int id;

    private static int idCounter = 1;
    private String categoryName;
    private double amount;
    private LocalDate date;

    private String note;

    public Transaction(double amount, String note) {
        this.amount = amount;
        this.note = note;
        this.date= LocalDate.now();

        this.id = Transaction.idCounter++;
    }

    public Transaction( double amount, String note, String categoryName) {
        this.categoryName = categoryName.toLowerCase();
        this.amount = amount;
        this.note = note;
        this.date= LocalDate.now();
        this.id = Transaction.idCounter++;


    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryId(String categoryName) {
        this.categoryName =  categoryName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public abstract double sum(double accumulated);

    public void print(){
        System.out.println(
                this.getClass().getSimpleName()+" #" + this.getId() + ". On " + this.getDate() + ". Note:" + this.getNote() + ". Amount:" + this.getAmount()+ (this.getCategoryName()!=null?". Category: "+ this.getCategoryName():""));
    }
}
