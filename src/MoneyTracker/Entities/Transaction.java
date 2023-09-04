package MoneyTracker.Entities;

import MoneyTracker.Interfaces.TransactionMethods;

import java.time.LocalDate;

public class Transaction implements TransactionMethods {
    private int id;

    private Integer categoryId;
    private double amount;
    private LocalDate date;

    private String note;



    public Transaction(int id ,double amount, String note, Integer categoryId) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.note = note;
        this.date = LocalDate.now();
        this.id = id;


    }
    public Transaction(int id, double amount, String note, int categoryId, LocalDate date) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.id = id;


    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public  double sumDouble(double v){
        return this.amount+v;
    }

    @Override
    public void print() {
        System.out.println(this.getClass()
                               .getSimpleName() + " #" + this.getId() + ". On " + this.getDate() + ". Note:" + this.getNote() + ". Amount:" + this.getAmount() + (this.getCategoryId() != null ? ". Category: " + this.getCategoryId() : ""));
    }
}
