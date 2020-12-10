/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.view;
import java.util.Scanner;
/**
 *
 * @author geremiapompei
 */
public class Config {
    private String address = "";
    
    public void init() {
        Scanner scanner = new Scanner(System.in);
        while(this.address.equals("")) {
            System.out.print("Inserisci indirizzo sender: ");
            this.address = scanner.nextLine().trim();
        }
    }
    
    public String getAddress() {
        return this.address;
    }
}
