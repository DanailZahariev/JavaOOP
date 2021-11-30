package testDrivenDevelopment;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChainblockImplTest {


    private Chainblock chainblock;
    private List<Transaction> transactions;

    @Before
    public void prepare() {
        this.chainblock = new ChainblockImpl();
        this.transactions = new ArrayList<>();
        prepareTransaction();
    }

    private void prepareTransaction() {
        Transaction transaction = new TransactionImpl(0, TransactionStatus.SUCCESSFUL, "Dani", "Gosho", 10.00);
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Dani", "Gosho", 90.00);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.UNAUTHORIZED, "Dani", "Gosho", 23.00);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.FAILED, "Dani", "Gosho", 12.00);
        this.transactions.add(transaction);
        this.transactions.add(transaction1);
        this.transactions.add(transaction2);
        this.transactions.add(transaction3);
    }

    private void fillChainblockWithTransactions() {
        this.transactions.forEach(existing -> this.chainblock.add(existing));
    }

    @Test
    public void testContainsReturnTrue() {
        Transaction transaction = transactions.get(0);
        chainblock.add(transaction);
        assertTrue(this.chainblock.contains(transaction));
    }

    @Test
    public void testContainsReturnFalse() {
        Transaction transaction = transactions.get(0);
        assertFalse(this.chainblock.contains(transaction));
    }

    @Test
    public void testContainsByIdReturnTrue() {
        Transaction transaction = transactions.get(0);
        this.chainblock.add(transaction);
        assertTrue(this.chainblock.contains(0));
    }

    @Test
    public void testContainsByIdReturnFalse() {
        assertFalse(this.chainblock.contains(0));
    }

    @Test
    public void testAddCorrectTransaction() {
        this.chainblock.add(transactions.get(0));
        assertEquals(1, this.chainblock.getCount());
        this.chainblock.add(this.transactions.get(1));
        assertEquals(2, this.chainblock.getCount());
    }

    @Test
    public void testAddTransactionFail() {
        Transaction transaction = this.transactions.get(0);
        this.chainblock.add(transaction);
        this.chainblock.add(transaction);
        assertEquals(1, this.chainblock.getCount());
    }

    @Test
    public void testGetCount() {
        Transaction transaction = this.transactions.get(0);
        Transaction transaction1 = this.transactions.get(1);
        Transaction transaction2 = this.transactions.get(3);
        assertEquals(0, this.chainblock.getCount());
        this.chainblock.add(transaction);
        assertEquals(1, this.chainblock.getCount());
        this.chainblock.add(transaction1);
        assertEquals(2, this.chainblock.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeInvalidTransactionStatus() {
        this.chainblock.changeTransactionStatus(100, TransactionStatus.ABORTED);
    }

    @Test
    public void testChangeTransactionStatusSuccess() {
        Transaction transaction = this.transactions.get(0);
        assertNotEquals(TransactionStatus.UNAUTHORIZED, transaction.getStatus());
        this.chainblock.add(transaction);
        this.chainblock.changeTransactionStatus(0, TransactionStatus.UNAUTHORIZED);
        assertEquals(TransactionStatus.UNAUTHORIZED, this.chainblock.getById(0).getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdWithException() {
        this.chainblock.getById(100);
    }

    @Test
    public void testGetByIdSuccess() {
        fillChainblockWithTransactions();
        Transaction actualTransaction = this.chainblock.getById(0);
        assertEquals(0, actualTransaction.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionWithInvalidId() {
        fillChainblockWithTransactions();
        this.chainblock.removeTransactionById(100);
    }

    @Test
    public void testRemoveTransactionSuccessfully() {
        fillChainblockWithTransactions();
        this.chainblock.removeTransactionById(0);
        assertEquals(3, this.chainblock.getCount());
        assertFalse(this.chainblock.contains(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusWithWrongStatus() {
        fillChainblockWithTransactions();
        this.chainblock.getByTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void getByTransactionStatusSuccess() {
        this.transactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
        fillChainblockWithTransactions();
        List<Transaction> sortedTransactions = this.transactions.stream()
                .filter(transaction -> transaction.getStatus().equals(TransactionStatus.SUCCESSFUL)).collect(Collectors.toList());

        Iterable<Transaction> actualTransactions = this.chainblock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);

        List<Transaction> actualSortedTransactionList = new ArrayList<>();

        actualTransactions.forEach(actualSortedTransactionList::add);

        assertEquals(sortedTransactions, actualSortedTransactionList);

    }

    @Test
    public void testGetAllAmountRangeSuccess() {
        fillChainblockWithTransactions();

        Iterable<Transaction> resultTransactions = this.chainblock.getAllInAmountRange(10, 20);
        for (Transaction resultTransaction : resultTransactions) {
            assertTrue(resultTransaction.getAmount() >= 10);
            assertTrue(resultTransaction.getAmount() <= 12);
        }
    }

    @Test
    public void testGetAllInAmountRangeResult() {
        fillChainblockWithTransactions();
        Iterable<Transaction> resultTransactions = this.chainblock.getAllInAmountRange(100, 200);
        List<Transaction> resultList = new ArrayList<>();
        resultTransactions.forEach(resultList::add);
        assertTrue(resultList.isEmpty());
    }
}