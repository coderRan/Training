package com.example;

public class MyClass {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Log:Runnable_1 = " + i);

                }
            }
        });
        t1.start();

    }

    public static void startThread2() {
        Thread t = new Thread(new MyRundable());
        t.start();
    }

}

class MyRundable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Log:MyRunnable = " + i);

        }
    }
}
