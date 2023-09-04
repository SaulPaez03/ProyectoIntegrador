package MoneyTracker.DTOs;

import MoneyTracker.Entities.Transaction;
import MoneyTracker.Interfaces.TransactionMethods;

import java.time.LocalDate;

public class TransactionDTO implements TransactionMethods {
    public int id;
    public String note;
    public double amount;
    public String categoryName;
    public LocalDate date;

    public TransactionDTO(int id, String note, double amount, String categoryName, LocalDate date) {
        this.amount = amount;
        this.note = note;
        this.id = id;
        this.categoryName = categoryName;
        this.date = date;
    }

    @Override
    public double sumDouble(double v) {
        return this.amount + v;
    }

    ;

    @Override
    public void print() {
        System.out.printf("#%1$s - $%2$s - %3$s - %4$s - %5$s%n\r", this.id, this.amount,
                          this.note, this.date, this.categoryName);

}}
