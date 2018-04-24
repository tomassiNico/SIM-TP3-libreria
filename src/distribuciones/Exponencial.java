/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuciones;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

/**
 *
 * @author nicolastomassi
 */
public class Exponencial implements Distribucion{
    
    private double media;
    private double lambda;
    private ArrayList numeros;
    
    public Exponencial(){
        
    }
    
    public Exponencial(double media) {
        this.media = media;
        this.lambda = 1/this.media;
        this.numeros = new ArrayList();
    }
    
    @Override
    public ArrayList generarNumeros(int cantidad) {
        ArrayList numeros = new ArrayList();
        Random r = new Random();
        double numeroGenerado;
        
        for (int i = 0; i < cantidad; i++) {
            numeroGenerado = (-1/this.lambda) * Math.log((1-r.nextFloat()));
            numeroGenerado = Math.round(numeroGenerado*10000.0) / 10000.0;
            numeros.add(numeroGenerado);
        }
        
        this.numeros = numeros;
        
        return numeros;
    }
        
    public ArrayList generarNumeros(int cantidad, double media) {
        this.media = media;
        this.lambda = 1 / this.media;
        
        return this.generarNumeros(cantidad);
    }

    @Override
    public ArrayList getNumeros() {
        return this.numeros;
    }
    
    
    
    
}
