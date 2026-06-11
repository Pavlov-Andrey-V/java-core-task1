package org.example.bank_operations;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentBank {
    private ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
    private final AtomicLong accountGenerator = new AtomicLong(1);

    public BankAccount createAccount(long firstBalance) {
        long accountNumber = accountGenerator.getAndIncrement();
        BankAccount bankAccount = new BankAccount(firstBalance, accountNumber);
        accounts.put(accountNumber, bankAccount);
        return bankAccount;
    }

    public void transfer(BankAccount transferFromAccount, BankAccount transferToAccount, long value) {
        if (transferFromAccount.equals(transferToAccount)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        BankAccount from = transferFromAccount.getAccountNumber() < transferToAccount.getAccountNumber() ? transferFromAccount : transferToAccount;
        BankAccount to = transferFromAccount.getAccountNumber() < transferToAccount.getAccountNumber() ? transferToAccount : transferFromAccount;
        from.getLock().lock();
        to.getLock().lock();
        try {
            transferFromAccount.withdraw(value);
            transferToAccount.deposit(value);
        }
        finally {
            to.getLock().unlock();
            from.getLock().unlock();
        }

    }

    public long getTotalBalance() {
        return accounts.values().stream()
                .mapToLong(BankAccount::getBalance)
                .sum();
    }
}
