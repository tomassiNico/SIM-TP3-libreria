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
        
        this.probabilidades = this.probabilidadesAcumuladas();
        ArrayList<Integer> esperadas = new ArrayList<>();
        int fe ;
        
        for (double aux: probabilidades) {
            fe = (int) aux * this.getNumerosAleatorios().size();
            
            esperadas.add(fe);
        }
        return esperadas;
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
    
    private int factorial(double n) {
        int factorial = (int) n;
        for(int i =(factorial - 1); i > 1; i--)
	{
            factorial = factorial * i;
	}
        return factorial;
    }
    
    public void generarIntervalosAgrupados()
    {
        double acuEsperada = 0;
        double acuFrecuencia = 0;
        double limInf = this.getMin();//Desde - Nuevo intervalo
        ArrayList intViejos = this.getIntervalosGenerados();
        int []frecuenciaVieja = this.getContadorFrecuencia();
        for (int i = 0; i < this.frecuenciaEsperada().size(); i++)
        {
            acuEsperada += (int) this.frecuenciaEsperada().get(i);
            acuFrecuencia += frecuenciaVieja[i];
            if (acuEsperada > 5) 
            {
                 this.esperadasAgrupadas.add(acuEsperada);
                 this.observadasAgrupadas.add(acuFrecuencia);
                 double []limites = new double[2];
                 limites[0] = limInf;
                 double limitesViejos[] = (double[]) this.intervalosAgrupados.get(i);
                 limites[1] = limitesViejos[1];
                 this.intervalosAgrupados.add(limites);
                 limInf = limites[1];
                 acuEsperada = 0;
                 acuFrecuencia = 0;
            }
        }
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
