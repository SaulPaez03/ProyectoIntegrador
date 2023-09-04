package MoneyTracker.Controllers;

import MoneyTracker.DAL.TransactionDAOImpl;
import MoneyTracker.DTOs.TransactionDTO;
import MoneyTracker.Entities.Transaction;
import MoneyTracker.Mappers.TransactionMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;


public class TransactionsController {
    private TransactionDAOImpl transactionDAO;

    public TransactionsController() {
        this.transactionDAO = new TransactionDAOImpl();
    }

    public TransactionDTO getTransactionById(int id){
        Transaction transaction =  this.transactionDAO.getById(id);
        return TransactionMapper.toDto(transaction);
    }

    public List<TransactionDTO> getAllTransactions(){
        List<Transaction> list = this.transactionDAO.getAll();
        return list.stream().map((TransactionMapper::toDto)).toList();
    }

    public List<TransactionDTO> getTransactionByCategoryID(int categoryId){
        List<Transaction> list = this.transactionDAO.getAllByCategoryId(categoryId);
        return  list.stream().map(TransactionMapper::toDto).toList();
    };
    public boolean saveTransaction(Double amount, String Note, LocalDate date, String category){
        Transaction trn = TransactionMapper.toEntity(new TransactionDTO(1,Note, amount, category, date ));
        return this.transactionDAO.insert(trn);
    }



}
