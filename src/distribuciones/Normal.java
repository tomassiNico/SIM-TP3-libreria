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
public class Normal {
    
    private float media;
    private float desviacionEstandar;
    
    public Normal()
    {
        
    }
    
    public Normal(float media, float de)
    {
        this.media = media;
        this.desviacionEstandar = de;
    }
    
    
    public ArrayList generarNumeros(int cantidad)
    {
        Random r = new Random();
        ArrayList numeros = new ArrayList(); //lista donde se guardaran los numeros generados;
        boolean positivo; //para saber si sumar o restar la desviación estándar a la media; true= se suma; false= se resta
        float numGenerado; //numero que se genera
        float de;
        for (int i = 0; i < cantidad; i++) {
            de = r.nextFloat() * this.desviacionEstandar; //obtenemos la desviacion
            
            positivo = r.nextBoolean(); //obtenemos si será positiva o negativa
            if (!positivo) {
                de *= -1;
            }
            
            numGenerado = this.media + de; //generamos el número
            
            numeros.add(numGenerado);
        }
        return numeros;
    }
    
    public ArrayList generarNumeros(int cantidad, float media, float de)
    {
        //sobrecarga de generarNumero por si se quiere con otra media y desviación o nunca se setearon estos valores
        this.desviacionEstandar = de;
        this.media = media;
        
        return this.generarNumeros(cantidad);
    }
}
