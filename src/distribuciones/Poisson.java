/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuciones;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nicolastomassi
 */
public class Poisson implements Distribucion{
    
    private double media;
    private ArrayList numeros;
    
    public Poisson() {
        
    }
    
    public Poisson(double me) {
        this.media = me;
        this.numeros = new ArrayList();
    }

    public double getMedia() {
        return media;
    }
    
    @Override
    public ArrayList generarNumeros(int cantidad) {
        ArrayList numeros = new ArrayList();
        Random r = new Random();
        int numeroGenerado;
        
        
        for (int i = 0; i < cantidad; i++) {
            double p = 1;
            double x = -1;
            double a = Math.pow(Math.E, -media);
            
            while(p >= a) {
                p *= r.nextFloat();
                x += 1;
            }
            x = Math.round(x*10000.0) / 10000.0;
            numeros.add(x);
        }
        this.numeros = numeros;
        
        return numeros;
    }
    
    public ArrayList generarNumeros(int cantidad, float media) {
        this.media = media;
        
        return this.generarNumeros(cantidad);
    }
    
    @Override
    public ArrayList getNumeros() {
        return this.numeros;
    }
}

