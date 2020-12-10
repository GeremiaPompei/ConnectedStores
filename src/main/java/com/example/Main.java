/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author geremiapompei
 */
package com.example;

import com.example.view.Config;
import com.example.server.*;

public class Main {
    public static void main(String [] args) {
        Config config = new Config();
        config.init();
        new Server().start(config.getAddress());
    }
}
