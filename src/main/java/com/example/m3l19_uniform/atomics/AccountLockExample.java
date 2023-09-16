package com.example.m3l19_uniform.atomics;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountLockExample {

    private final Lock lock = new ReentrantLock(true);

    private final Condition balanceCondition = lock.newCondition();

    private long balance;

    public AccountLockExample() {
        this(0);
    }

    public AccountLockExample(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public void deposit(long amount) {
        checkNonNegativeAmount(amount);
        lock.lock();
        try {
            balance += amount;
            balanceCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void waitAndWithdraw(long amount) throws InterruptedException {
        checkNonNegativeAmount(amount);
        lock.lock();
        try {
            while (balance < amount) {
                balanceCondition.await();
            }
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    private static void checkNonNegativeAmount(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount < 0");
        }
    }
}
