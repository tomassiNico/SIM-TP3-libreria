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
public class Uniforme {
    
    private float valorInf; //valor inferior minimo a generar
    private float valorSup; //valor superior máximo a generar
    
    public Uniforme()
    {
        
    }
    
    public Uniforme(float inf, float sup) {
        this.valorInf = inf;
        this.valorSup = inf;
    }
    
    public ArrayList generarNumeros(int cantidad) {
        ArrayList numeros = new ArrayList(); //lista de numeros generados
        Random r = new Random();
        float numeroGenerado;  // numero generado
        float amplitud = this.valorSup - this.valorInf; //amplitud o recorrido = valor entre los que toma el random
        
        for (int i = 0; i < cantidad; i++) {
            numeroGenerado = this.valorInf + (r.nextFloat()*amplitud);
            numeros.add(numeroGenerado);
        }
        
        return numeros;
    }
    
    public ArrayList generarNumeros(int cantidad, float inf, float sup){
        //para generar numero entre dos valores, en caso de nunca inicializados los valores o querer cambiar los actuales
        
        this.valorInf = inf;
        this.valorSup = sup;
        
        return this.generarNumeros(cantidad);
    }
}
