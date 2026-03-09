package org.example;

class Account {
    private double balance;
    public Account(double balance) { this.balance = balance; }

    // Copy constructor
    public Account(Account acc) {
        this.balance = acc.balance;
    }

    public double getBalance() { return balance; }
    public void setBalance(double b) { this.balance = b; }
}

class Person {
    private String name;
    private Account account;

    public Person(String name, Account account) {
        this.name = name;
        this.account = account; // 這裡可能會有隱私洩漏嗎？ (建立時)
    }

    public Account getAccount() {
        // Method 1
        Account newAccount = new Account(account);

        // Method 2
        // Account newAccount = new Account(account.getBalance());
        return newAccount;
    }

    public void display() {
        System.out.println(name + " 的帳戶餘額為: " + account.getBalance());
    }
}

public class PrivacyLeakDemo {
    public static void main(String[] args) {
        Account myAcc = new Account(1000);
        Person p = new Person("John", myAcc);

        // 觀察 1: 透過外部參考修改
        myAcc.setBalance(500);
        p.display(); // 餘額變成了 500!

        // 觀察 2: 透過 Getter 修改
        Account leakedAcc = p.getAccount();
        leakedAcc.setBalance(0);
        p.display(); // 餘額變成了 0!
    }
}