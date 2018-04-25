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
    private ArrayList<Double> esperadasInagrupadas ;

    public double getMedia() {
        return media;
    }

    public double getLambda() {
        return lambda;
    }

    public ArrayList<Double> getEsperadasInagrupadas() {
        return esperadasInagrupadas;
    }

    public ChiExponencial(int intervalos, ArrayList numeros, double media) {
        super(intervalos, numeros);
        this.media = media;
        this.lambda = 1 / media;
        this.esperadasInagrupadas = this.frecuenciaEsperada();
    }
    
    private ArrayList<Double> probabilidadesAcumuladas() {
        //calcula la probabilidad acumulada para cada intervalo que servira
        // para calcular las frecuencias esperadas
        
        ArrayList probabilidades = new ArrayList();
        double amplitudIntervalo = 1 / (double) super.getNumIntervalos(); 
        double acumulada;
        
        for (Object i: this.getIntervalosGenerados())
        {
                double intervalo[] = (double[]) i;
                double intM = intervalo[1];
                double intm = intervalo[0];
                //=(1-EXP(-Lambda*intM))-(1-EXP(-Lambda*intm))

                acumulada = (1 - Math.pow(Math.E, (-this.lambda*intM))) - (1 - Math.pow(Math.E, (-this.lambda*intm)));

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
    
    private void esperadasAgrupadas() {
        // calcula las frecuencias esperadas en caso de que 
        //deban ser agrupadas (si las fe < 5)
        //
        
        ArrayList intervalosNuevos = new ArrayList();
        ArrayList<Integer> viejasObservadas = new ArrayList<>();
        
        for (int i = 0; i < this.getContadorFrecuencia().length; i++) {
            viejasObservadas.add(this.getContadorFrecuencia()[i]);
        }
        
        
        int index = 0; //indice desde el cual se debería agrupar
        double acuObservadas = 0; // acumulador de frecuencias acumuladas
        System.out.println("Esperadas ina: " + this.esperadasInagrupadas.size());
        for (double a: this.esperadasInagrupadas) {
            System.out.println("cooas: " + a);
        }
        for(double aux: this.esperadasInagrupadas) {
            //encontramos el indice desde la cual ya las esperadas son menores a 5
            System.out.println("aux: "  + aux);
            if (aux < 5) {
                break;
            }
            this.esperadasAgrupadas.add(aux);
            intervalosNuevos.add(this.getIntervalosGenerados().get(index)); //intervalos sin cambios
            acuObservadas = viejasObservadas.get(index);
            this.observadasAgrupadas.add(acuObservadas); //observada sin cambios
            index += 1;
        }
        
        double nuevaEsperada = 0;
        double limites[];
        limites = (double[]) this.getIntervalosGenerados().get(index);
        
        double nuevosLimites[] = new double[2];
        acuObservadas = 0;
        
        nuevosLimites[0] = limites[0];
        
        for (;  index < this.esperadasInagrupadas.size() ; index++) {
            
            limites = (double[]) this.getIntervalosGenerados().get(index);
            
            if (nuevaEsperada < 5) {
                nuevaEsperada += esperadasInagrupadas.get(index);
                nuevosLimites[1] = limites[1];
                acuObservadas += viejasObservadas.get(index);
            }
            else {
                this.esperadasAgrupadas.add(nuevaEsperada); //nueva esperada
                intervalosNuevos.add(nuevosLimites); //intervalo de la nueva esperada
                this.observadasAgrupadas.add(acuObservadas); //observada de los nuevos intervalos
                acuObservadas = 0;
                nuevosLimites[0] = limites[0];
                nuevaEsperada = 0;
                
                if (index == this.esperadasInagrupadas.size()-1) {
                    //última vuelta y queda el último valor solo
                    nuevaEsperada = this.esperadasInagrupadas.get(index);
                    acuObservadas = viejasObservadas.get(index);
                }
            }
        }
        
        if(nuevaEsperada < 5 && nuevaEsperada != 0) {
             // en caso de que queden valores al final de la lista de esperadas
             //que no sumen 5, se suman al ultimo valor que sí los sunma
             System.out.println(this.esperadasAgrupadas.size());
            int aux = this.esperadasAgrupadas.size() - 1; //indice del ultima esperada <= 5
            nuevaEsperada += this.esperadasAgrupadas.get(aux); // se las suma
            acuObservadas += this.observadasAgrupadas.get(aux);
            intervalosNuevos.add(nuevosLimites);
            this.esperadasAgrupadas.add(aux, nuevaEsperada); // pisa el valor anterior
            this.observadasAgrupadas.add(aux, acuObservadas);
        }
        
        this.intervalosAgrupados = intervalosNuevos;
    }
    
    @Override
    public ArrayList<Double> diferenciaYalCuadrado() {
        ArrayList fes = this.esperadasAgrupadas; //lista de frecuencias esperadas
        ArrayList fos = this.observadasAgrupadas; //lista de frecuencias observadas
        ArrayList<Double> diferencias = new ArrayList<>();
        
        double dif;
        for (int i = 0; i < fes.size(); i++) {
            double fe = (double) fes.get(i), fo = (double) fos.get(i);
            
            dif = Math.pow((fo-fe), 2) / fe;
            diferencias.add(dif);
        }
        return diferencias;
    }

    @Override
    public boolean ejecutarTest() {
        
        this.esperadasAgrupadas();
        this.gradosDeLibertad = this.intervalosAgrupados.size() - 2 ;
        return this.esAprobado();
    }
    
}
