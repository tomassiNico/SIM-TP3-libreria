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
public class Uniforme implements Distribucion{
    
    private double valorInf; //valor inferior minimo a generar
    private double valorSup; //valor superior máximo a generar
    private ArrayList numeros;
    
    public Uniforme()
    {
        
    }
    
    public Uniforme(double inf, double sup) {
        this.valorInf = inf;
        this.valorSup = inf;
        this.numeros = new ArrayList();
    }
    
    @Override
    public ArrayList generarNumeros(int cantidad) {
        ArrayList numeros = new ArrayList(); //lista de numeros generados
        Random r = new Random();
        double numeroGenerado;  // numero generado
        double amplitud = this.valorSup - this.valorInf; //amplitud o recorrido = valor entre los que toma el random
        
        for (int i = 0; i < cantidad; i++) {
            numeroGenerado = this.valorInf + (r.nextFloat()*amplitud);
            numeroGenerado = Math.round(numeroGenerado*10000.0) / 10000.0;
            numeros.add(numeroGenerado);
        }
        
        this.numeros = numeros;
        
        return numeros;
    }
    
    public ArrayList generarNumeros(int cantidad, double inf, double sup){
        //para generar numero entre dos valores, en caso de nunca inicializados los valores o querer cambiar los actuales
        
        this.valorInf = inf;
        this.valorSup = sup;
        
        return this.generarNumeros(cantidad);
    }
    
    @Override
    public ArrayList getNumeros() {
        return this.numeros;
    }
}
