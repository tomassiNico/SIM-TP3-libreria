/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testChiCuadrado;

import distribuciones.Normal;
import java.util.ArrayList;

/**
 *
 * @author aleex
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Normal n = new Normal(12,2);
        ArrayList<Double> prueba = new ArrayList<>();
        prueba.add(5.0);
        prueba.add(5.0);
        prueba.add(5.0);
        prueba.add(5.0);
        prueba.add(5.0);
        prueba.add(5.0);
        ChiNormal cn = new ChiNormal(10,prueba, 12, 2);
        System.out.println(cn.ejecutarTest());
    }
    
}
