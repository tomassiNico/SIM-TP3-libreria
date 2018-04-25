/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testChiCuadrado;

import distribuciones.Exponencial;
import distribuciones.Normal;
import distribuciones.Poisson;
import distribuciones.Uniforme;

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
        //Exponencial ANDA BIEN
        /*Exponencial exp = new Exponencial(15);
        ChiExponencial chiexp = new ChiExponencial(10,exp.generarNumeros(1500), 15);
        System.out.println(chiexp.ejecutarTest());*/
       
        /*NormalOK
        Normal no = new Normal(15,2);
        ChiNormal chinom = new ChiNormal(9,no.generarNumeros(150),15,2);
        System.out.println(chinom.listarIntervalos());
        System.out.println(chinom.listarFrecuenciasEsperadas());
        System.out.println(chinom.listarFrecuenciasObservadas());*/
        
        /*UNIFROME OK
        Uniforme uni = new Uniforme(0,1);
        ChiUniforme chiuni = new ChiUniforme(10,uni.generarNumeros(150));
        System.out.println(chiuni.listarIntervalos());
        System.out.println(chiuni.listarFrecuenciasEsperadas());
        System.out.println(chiuni.listarFrecuenciasObservadas());*/
        
        //********************************************************************
        
        Poisson po = new Poisson(15);
        ChiPoisson chipo = new ChiPoisson(9,po.generarNumeros(150),15);
        System.out.println(chipo.listarFrecuenciasEsperadas());
        System.out.println(chipo.listarFrecuenciasObservadas());
        
    }
    
}
