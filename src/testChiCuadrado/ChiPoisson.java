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
    private ArrayList<Double> probabilidades;
    
    public ChiPoisson(int cantidad, ArrayList numeros, double media) {
        super(cantidad, numeros);
        this.media = media;
        this.probabilidades = new ArrayList<>();
        this.intervalosAgrupados = new ArrayList();
        this.esperadas = this.frecuenciaEsperada();
    }
    
    private ArrayList frecuenciaEsperada() {
        //calcula las frecuencias esperadas para cada intervalo
        
        this.probabilidades = this.probabilidadesAcumuladas();
        ArrayList<Double> esperadas = new ArrayList<>();
        double fe ;
        
        for (double aux: probabilidades) {
            fe = aux * this.getNumerosAleatorios().size();
            
            esperadas.add(fe);
        }
        return esperadas;
    }

    public double getMedia() {
        return media;
    }

    public ArrayList<Double> getProbabilidades() {
        return probabilidades;
    }

    private ArrayList<Double> probabilidadesAcumuladas() {
        //calcula la probabilidad acumulada para cada intervalo que servira
        // para calcular las frecuencias esperadas
        
        ArrayList probabilidades = new ArrayList();
                
        double acumulada;
        
        for (Object i: this.getIntervalosGenerados())
        {
                double []intervalo = (double[]) i;
                
                //=(LambdaPoisson^D4*EXP(-LambdaPoisson))/FACT(D4)+(LambdaPoisson^E4*EXP(-LambdaPoisson))/FACT(E4)
                
                acumulada = this.acumulada(intervalo[1]) + this.acumulada(intervalo[0]);
                
                probabilidades.add(acumulada);
        }
        
        return probabilidades;
    }
    
    private double acumulada(double x) {
        return (Math.pow(this.media, x) * Math.pow(Math.E, (-this.media)) / this.factorial(x));
    }
    
    private double factorial(double n) {
        double factorial = 1;
        
        for(double i = n; i >= 1; i--)
	{
            factorial *= i;
	}
        return factorial;
    }
    
    /*public void generarIntervalosAgrupados()
    {
        double acuEsperada = 0;
        double acuFrecuencia = 0;
        double limInf = this.getMin();//Desde - Nuevo intervalo
        ArrayList intViejos = this.getIntervalosGenerados();
        int []frecuenciaVieja = this.getContadorFrecuencia();
        
        
        for (int i = 0; i < this.frecuenciaEsperada().size(); i++)
        {
            acuEsperada += (double) this.frecuenciaEsperada().get(i);
            acuFrecuencia += frecuenciaVieja[i];
            if (acuEsperada >= 5) 
            {   
                 this.esperadasAgrupadas.add(acuEsperada);
                 this.observadasAgrupadas.add(acuFrecuencia);
                 double []limites = new double[2];
                 limites[0] = limInf;
                 double limitesViejos[] = (double[]) intViejos.get(i);
                 limites[1] = limitesViejos[1];
                 this.intervalosAgrupados.add(limites);
                 limInf = limites[1];
                 acuEsperada = 0;
                 acuFrecuencia = 0;
            }
        }
    }*/
    
    public void generarIntervalosAgrupados()
    {
        double acuEsperada = 0;
        double acuFrecuencia = 0;
        double limInf = this.getMin();//Desde - Nuevo intervalo
        ArrayList intViejos = this.getIntervalosGenerados();
        int []frecuenciaVieja = this.getContadorFrecuencia();
        
        double limitesViejos[];
        
        for (int i = 0; i < this.frecuenciaEsperada().size(); i++)
        {
            acuEsperada += (double) this.frecuenciaEsperada().get(i);
            acuFrecuencia += frecuenciaVieja[i];
            if (acuEsperada >= 5) 
            {   
                 double []limites = new double[2];
                 this.esperadasAgrupadas.add(acuEsperada);
                 this.observadasAgrupadas.add(acuFrecuencia);
                 limites[0] = limInf;
                 limitesViejos = (double[]) intViejos.get(i);
                 limites[1] = limitesViejos[1];               
                 this.intervalosAgrupados.add(limites);
                 limInf = limites[1];
                 acuEsperada = 0;
                 acuFrecuencia = 0;
            }
            if (i == this.frecuenciaEsperada().size()-1) {
                double []limites = new double[2];
                int tamaño = this.intervalosAgrupados.size();
                double[] ultimoIntervalo = (double[]) this.intervalosAgrupados.get(tamaño - 1);
                limitesViejos = (double[]) intViejos.get(i);
                limites[1] = limitesViejos[1];
                limites[0] = ultimoIntervalo[0];
                this.intervalosAgrupados.set(tamaño - 1,limites);
            }
        }
        
        if(acuEsperada <= 5) {
            int ultimo = this.esperadasAgrupadas.size() -1;
            acuEsperada += this.esperadasAgrupadas.get(ultimo);
            this.esperadasAgrupadas.set(ultimo, acuEsperada);
            acuFrecuencia += this.observadasAgrupadas.get(ultimo);
            this.observadasAgrupadas.set(ultimo, acuFrecuencia);
        }
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
        this.generarIntervalosAgrupados();
        this.gradosDeLibertad = this.intervalosAgrupados.size() - 2 ;
        return this.esAprobado();
    }
    
}
