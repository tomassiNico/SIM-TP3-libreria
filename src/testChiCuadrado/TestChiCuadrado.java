package testChiCuadrado;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author nicolastomassi
 */
public abstract class TestChiCuadrado {
    
    private int numIntervalos;
    private ArrayList<Double> numerosAleatorios;//Numeros aleatorios a testear
    protected int gradosDeLibertad;
    private int[] contadorFrecuencia;//Contador de frecuencias observadas en cada intervalo
    private ArrayList intervalosGenerados; 
    private double min; //valor minimo de los numeros aleatorios
    private double max; //valor maximo de los numeros aleatorios
    protected ArrayList<Double> observadasAgrupadas;
    protected ArrayList<Double> esperadasAgrupadas;
    protected ArrayList intervalosAgrupados;
    protected ArrayList<Double> esperadas;

    public ArrayList<Double> getEsperadas() {
        return esperadas;
    }
    
    public ArrayList<Double> getObservadasAgrupadas() {
        return observadasAgrupadas;
    }

    public ArrayList<Double> getEsperadasAgrupadas() {
        return esperadasAgrupadas;
    }

    public ArrayList getIntervalosAgrupados() {
        return intervalosAgrupados;
    }
    
    public TestChiCuadrado(int intervalos, ArrayList numeros)
    {
        this.esperadasAgrupadas = new ArrayList();
        this.observadasAgrupadas = new ArrayList();
        this.intervalosAgrupados = new ArrayList();
        this.numIntervalos = intervalos;
        this.numerosAleatorios = numeros;
        this.contadorFrecuencia = new int[numIntervalos];
        this.calcularMinMax();
        this.generarIntervalosNoAgrupados();
        this.contarFrecuencia();
    }
    
    public ArrayList getIntervalosGenerados() {
        return this.intervalosGenerados;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
    
    public ArrayList<Double> getNumerosAleatorios() {
        return numerosAleatorios;
    }
    
    private void calcularMinMax() {
        this.min = this.getNumerosAleatorios().get(0).intValue();
        this.max = 0;
        int ayuda = 0;
        //for(Object aux: this.getNumerosAleatorios()) 
        for(double aux: this.getNumerosAleatorios()) { 
            //obtenemos el menor y mayor número dentro de los generados
            //para poder saber el rango
            if (aux < this.min) {
                this.min = (int)aux;
            }
            if(aux > this.max) {
                this.max = ((int) aux) + 1;
            }
        }  
    }
    
    public boolean esAprobado()
    { 
        double ChiTabulado = this.getNumeroTabla(this.gradosDeLibertad);
        if (ChiTabulado >= this.generarSumatoriaChi())
        {
            return true;
        }
        return false;
    }
    
    public int getGradosDeLibertad()
    {
        return this.gradosDeLibertad;
    }
    
    //Metodo que devuelve el valor de la tabla, si no encuentra devuelve 0WS
     public double getNumeroTabla(int gradosDeLibertad)
    {
        double aux;
        switch (gradosDeLibertad){
            case 1: aux = 3.84; break;
            case 2: aux = 5.99; break;
            case 3: aux = 7.81; break;
            case 4: aux = 9.49; break;
            case 5: aux = 11.1; break;
            case 6: aux = 12.6; break;
            case 7: aux = 14.1; break;
            case 8: aux = 15.5; break;
            case 9: aux = 16.9; break;
            case 10: aux = 18.3; break;
            case 11: aux = 19.7; break;
            case 12: aux = 21.0; break;
            case 13: aux = 22.4; break;
            case 14: aux = 23.7; break;
            case 15: aux = 25.0; break;
            case 16: aux = 26.3; break;
            case 17: aux = 27.6; break;
            case 18: aux = 28.9; break;
            case 19: aux = 30.1; break;
            case 20: aux = 31.4; break;
            case 21: aux = 32.7; break;
            case 22: aux = 33.9; break;
            case 23: aux = 35.2; break;
            case 24: aux = 36.4; break;
            case 25: aux = 37.7; break;
            case 26: aux = 38.9; break;
            case 27: aux = 40.1; break;
            case 28: aux = 41.3; break;
            case 29: aux = 42.6; break;
            case 30: aux = 43.8; break;
            case 40: aux = 55.8; break;
            case 50: aux = 67.5; break;
            case 60: aux = 79.1; break; 
            case 70: aux = 90.5; break;
            case 80: aux = 101.9; break;
            case 90: aux = 113.1; break;
            case 100: aux = 124.3; break;
            default: aux = 0; break;
        }
        return aux;
    }
     
    protected void contarFrecuencia()
    {
        for (double aux : numerosAleatorios)
        {
            int count = 0;
            for (Object i:  this.intervalosGenerados)
            {
                double intervalo[] = (double[]) i;
                if(aux >= this.max) 
                    {
                        break;
                    }
                
                if (intervalo[0] <= aux && aux < intervalo[1])
                {
                    contadorFrecuencia[count] += 1;
                    break;
                }
                else { count += 1;  }
            }
        }

    }
    
    private void generarIntervalosNoAgrupados()
    {
        double rango = this.max - this.min;
        double amplitudIntervalo = rango / (float) this.getNumIntervalos();
        amplitudIntervalo = Math.round(amplitudIntervalo*100.0) / 100.0;
        double aux = this.min;
        ArrayList intervalosGenerados = new ArrayList();
        System.out.println("Maximo: " + this.max);
        while (aux < this.max)
        {
            double intervalo[] = new double[2];
            intervalo[0] = Math.round(aux*100.0) / 100.0;
            intervalo[1] = Math.round((aux + amplitudIntervalo)*100.0) / 100.0;
            System.out.println("Izq: " + intervalo[0]);
            System.out.println("Der: " + intervalo[1]);
            if (intervalo[1] >= this.max) 
            {
                intervalo[1] = this.max;
                intervalosGenerados.add(intervalo);
                break;
            }
            intervalosGenerados.add(intervalo);
            aux += amplitudIntervalo;
            aux = Math.round(aux*100.0) / 100.0;
        }
        this.intervalosGenerados = intervalosGenerados;
      
    }

    public int[] getContadorFrecuencia() {
        return contadorFrecuencia;
    }

    public void setNumIntervalos(int numIntervalos) {
        this.numIntervalos = numIntervalos;
    }

    public void setNumerosAleatorios(ArrayList<Double> numerosAleatorios) {
        this.numerosAleatorios = numerosAleatorios;
    }

    public void setGradosDeLibertad(int gradosDeLibertad) {
        this.gradosDeLibertad = gradosDeLibertad;
    }

    public void setIntervalos(int[] intervalos) {
        this.contadorFrecuencia = intervalos;
    }

    public int getNumIntervalos() {
        return numIntervalos;
    }

    public int[] getIntervalos() 
    {
        return contadorFrecuencia;
    }
    
    public abstract ArrayList<Double> diferenciaYalCuadrado();
    
    public double generarSumatoriaChi() {
        ArrayList<Double> diferencias = this.diferenciaYalCuadrado();
        double sumatoria = 0;
        for(double aux: diferencias) {
            sumatoria += aux;
        }
        return sumatoria;
    }
    
    public abstract boolean ejecutarTest();
    
    public String listarIntervalos()
    {
        String pepe = "";
        for (Object i: this.intervalosAgrupados)
        {
            double []aux = (double[]) i;
            pepe += String.valueOf(aux[0]) + " - " + String.valueOf(aux[1]) + "\n";
        }
        return pepe;
    }
    
    public String listarFrecuenciasObservadas()
    {
        String pepe = "";
        for (Object i: this.contadorFrecuencia)
        {
            pepe += String.valueOf(i) + "\n";
        }
        return pepe;
    }
 
}
