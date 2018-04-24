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
public class ChiExponencial extends TestChiCuadrado{

    private double media;
    private double lambda;
    
   
    public ChiExponencial(int intervalos, ArrayList numeros, double media) {
        super(intervalos, numeros);
        this.media = media;
        this.lambda = 1 / media;
    }
    
    private ArrayList<Double> probabilidadesAcumuladas() {
        //calcula la probabilidad acumulada para cada intervalo que servira
        // para calcular las frecuencias esperadas
        
        ArrayList probabilidades = new ArrayList();
        double amplitudIntervalo = 1 / (double) super.getNumIntervalos();
        double acumulada;
        
        for (float i = 0; i < 1; i+=amplitudIntervalo)
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
    
    private ArrayList esperadasAgrupadas() {
        // calcula las frecuencias esperadas en caso de que 
        //deban ser agrupadas (si las fe < 5)
        
        ArrayList<Double> esperadasInagrupadas = this.frecuenciaEsperada();
        ArrayList<Double> esperadasAgrupadas = new ArrayList<>();
        
        int index = 0; //indice desde el cual se debería agrupar
        
        for(double aux: esperadasInagrupadas) {
            //encontramos el indice desde la cual ya las esperadas son menores a 5
            if (aux < 5) {
                break;
            }
            esperadasAgrupadas.add(aux);
            index += 1;
        }
        
        double nuevaEsperada = 0;
        
        
        for (;  index < esperadasInagrupadas.size(); index++) {
            if (nuevaEsperada < 5) {
                nuevaEsperada += esperadasInagrupadas.get(index);
            }
            else {
                esperadasAgrupadas.add(nuevaEsperada);
                nuevaEsperada = 0;
            }
        }
        
        if(nuevaEsperada < 5) {
             // en caso de que queden valores al final de la lista de esperadas
             //que no sumen 5, se suman al ultimo valor que sí los sunma
            int aux = esperadasAgrupadas.size() - 1; //indice del ultima esperada >= 5
            nuevaEsperada += esperadasAgrupadas.get(aux); // se las suma
            
            esperadasAgrupadas.add(aux, nuevaEsperada); // pisa el valor anterior
        }
        
        return esperadasAgrupadas;
    }
    
    @Override
    public ArrayList<Double> diferenciaYalCuadrado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Double> generarSumatoriaChi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
