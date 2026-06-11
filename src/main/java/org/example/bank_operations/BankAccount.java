package org.example.bank_operations;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private long balance;
    private final long accountNumber;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(long balance, long accountNumber) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance must be positive");
        }
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public void deposit(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Deposit value must be over zero");
        }
        lock.lock();
        try {
            balance += value;
        }
        finally {
            lock.unlock();
        }

    }

    public void withdraw(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Withdraw value must be over zero");
        }
        lock.lock();
        try {
            if (balance >= value) {
                balance -= value;
            } else {
                throw new IllegalArgumentException("Not enough balance");
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean withdrawTransfer(long value) {
        lock.lock();
        try {
            if (balance >= value) {
                balance -= value;
                return true;
            }
            return false;
        }
        finally {
            lock.unlock();
        }
    }

    public long getBalance() {
        lock.lock();
        try {
            return balance;
        }
        finally {
            lock.unlock();
        }
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
