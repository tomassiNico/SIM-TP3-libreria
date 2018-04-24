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
        //Normal n = new Normal(12,2);
        /*ArrayList prueba = new ArrayList<>();
        prueba.add(1.56);
        prueba.add(2.21);
        prueba.add(3.15);
        prueba.add(4.61);
        prueba.add(4.18);
        prueba.add(5.2);
        prueba.add(6.94);
        prueba.add(7.71);
        prueba.add(5.15);
        prueba.add(6.76);
        prueba.add(7.28);
        prueba.add(4.23);
        prueba.add(3.21);
        prueba.add(2.75);
        prueba.add(4.69);
        prueba.add(5.86);
        prueba.add(6.25);
        prueba.add(4.27);
        prueba.add(4.91);
        prueba.add(4.78);
        prueba.add(2.46);
        prueba.add(3.97);
        prueba.add(5.71);
        prueba.add(6.19);
        prueba.add(4.2);
        prueba.add(3.48);
        prueba.add(5.83);
        prueba.add(6.36);
        prueba.add(5.9);
        prueba.add(5.43);
        ChiNormal cn = new ChiNormal(9,prueba, 4.8410, 1.5574);
        System.out.println(cn.ejecutarTest());
        System.out.println(cn.listarIntervalos());
        System.out.println(cn.listarFrecuenciasObservadas());*/
        ArrayList prueba = new ArrayList();
        prueba.add(5);
        prueba.add(100);
        prueba.add(1000);
        ChiNormal cn = new ChiNormal(3,prueba, 368.33, 1.5574);
        System.out.println(cn.ejecutarTest());
    }
    
}
