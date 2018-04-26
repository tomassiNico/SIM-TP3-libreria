/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testChiCuadrado;

import distribuciones.Poisson;

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
        Poisson po = new Poisson(15);
        ChiPoisson chipo = new ChiPoisson(10, po.generarNumeros(50), 15);
        System.out.println(chipo.ejecutarTest());
        System.out.println("esperadas: " + chipo.esperadasAgrupadas.size());
        System.out.println("observadas: " + chipo.observadasAgrupadas.size());
        System.out.println("intervalos: "  + chipo.intervalosAgrupados.size());
        
    }
    
}
