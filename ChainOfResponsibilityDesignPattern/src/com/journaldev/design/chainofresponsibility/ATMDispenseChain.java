package com.journaldev.design.chainofresponsibility;
import java.util.Scanner;
public class ATMDispenseChain {

    private DispenseChain c1;
    public ATMDispenseChain(){
        this.c1 = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();

        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    public static void main(String[] args) {
    ATMDispenseChain atmDispenseChain = new ATMDispenseChain();

    while (true){
        int amt = 0;
        System.out.println("Enter amt to dispense in multiples of 10s");
        Scanner input = new Scanner(System.in);
        amt = input.nextInt();
        if(amt%10 !=0){
            System.out.println("Entered amount is not in multiples of 10s. kindly retry.");
            System.out.println("");

        }
        atmDispenseChain.c1.dispense(new Currency(amt));

    }
    }


}
