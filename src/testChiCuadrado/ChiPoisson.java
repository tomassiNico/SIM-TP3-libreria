/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testChiCuadrado;

import java.util.ArrayList;

/**
 *
 * @author nicolastomassi
 */
public class ChiPoisson extends TestChiCuadrado {

    private double media;
    private ArrayList<Integer> observadasAgrupadas;
    private ArrayList<Double> esperadasAgrupadas;
    private ArrayList<Double> probabilidades;
    
    public ChiPoisson(int cantidad, ArrayList numeros, double media) {
        super(cantidad, numeros);
        this.media = media;
        this.esperadasAgrupadas = new ArrayList<>();
        this.observadasAgrupadas = new ArrayList<>();
        this.probabilidades = new ArrayList<>();
    }
    
    private ArrayList frecuenciaEsperada() {
        //calcula las frecuencias esperadas para cada intervalo
        
        ArrayList<Double> probabilidades = this.probabilidadesAcumuladas();
        ArrayList<Double> esperadas = new ArrayList<>();
        double fe ;
        
        for (double aux: probabilidades) {
            fe = aux * this.getNumerosAleatorios().size();
            esperadas.add(fe);
        }
        return esperadas;
    }
    
    private ArrayList<Double> probabilidadesAcumuladas() {
        //calcula la probabilidad acumulada para cada intervalo que servira
        // para calcular las frecuencias esperadas
        
        ArrayList probabilidades = new ArrayList();
        
        
        
        
        
        
        
        double amplitudIntervalo = (max - min) / (double) super.getNumIntervalos(); //obtenemos la amplitud del intervalo
        double acumulada;
        
        for (double i = min; i <= max; i+=amplitudIntervalo)
        {
                double intM = Math.round((i+amplitudIntervalo) * 10000.0) / 10000.0;
                double intm = Math.round(i * 10000.0) / 10000.0;
                if (intM > 1) 
                {
                    intM = 1;
                }
                
                //=(1-EXP(-Lambda*intM))-(1-EXP(-Lambda*intm))
                
                acumulada = (1 - Math.pow(Math.E, intM)) - (1 - Math.pow(Math.E, intm));
                
                probabilidades.add(acumulada);
        }
        return probabilidades;
    }
    
    @Override
    public ArrayList<Double> diferenciaYalCuadrado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ejecutarTest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
