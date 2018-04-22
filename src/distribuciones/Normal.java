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
public class Normal implements Distribucion{
    
    private double media;
    private double desviacionEstandar;
    
    public Normal()
    {
        
    }
    
    public Normal(double media, double de)
    {
        this.media = media;
        this.desviacionEstandar = de;
    }
    
    @Override
    public ArrayList generarNumeros(int cantidad)
    {
        Random r = new Random();
        ArrayList numerosGenerados = new ArrayList(); //lista donde se guardaran los numeros generados;
        double numGenerado; //numero que se genera
        double rnd1;
        double rnd2;
        for (int i = 0; i < cantidad; i++) {
            rnd1 = r.nextFloat();
            rnd2 = r.nextFloat();
            if (i%2 == 0) 
            { 
                numGenerado = ((Math.sqrt(-2 * Math.log(rnd1)) * Math.cos(2 * Math.PI * rnd2)) * this.desviacionEstandar + this.media);
            } 
            else
            {
                numGenerado = ((Math.sqrt(-2 * Math.log(rnd1)) * Math.sin(2 * Math.PI * rnd2)) * this.desviacionEstandar + this.media);
            }
            numerosGenerados.add(numGenerado);
        }
        return numerosGenerados;
    }
    
    public ArrayList generarNumeros(int cantidad, double media, double de)
    {
        //sobrecarga de generarNumero por si se quiere con otra media y desviación o nunca se setearon estos valores
        this.desviacionEstandar = de;
        this.media = media;
        
        return this.generarNumeros(cantidad);
    }
}
