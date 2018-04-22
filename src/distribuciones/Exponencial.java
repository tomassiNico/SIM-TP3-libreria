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
public class Exponencial {
    
    private float media;
    private float lambda;
    
    public Exponencial(){
        
    }
    
    public Exponencial(float media) {
        this.media = media;
        this.lambda = 1/this.media;
    }
    
    public ArrayList generarNumeros(int cantidad) {
        ArrayList numeros = new ArrayList();
        Random r = new Random();
        double numeroGenerado;
        
        for (int i = 0; i < cantidad; i++) {
            numeroGenerado = (-1/this.lambda) * Math.log((1-r.nextFloat()));
            numeros.add(numeroGenerado);
        }
        
        return numeros;
    }
        
    
    
    
    
}
