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
    private int gradosDeLibertad;
    private int intervalos[];
    
    public TestChiCuadrado(int intervalos, ArrayList numeros)
    {
        this.numIntervalos = intervalos;
        this.numerosAleatorios = numeros;
    }

    public ArrayList<Double> getNumerosAleatorios() {
        return numerosAleatorios;
    }
    
    public boolean esAprobado()
    {
        double ChiTabulado = this.getNumeroTabla(this.gradosDeLibertad);
        if (ChiTabulado >= this.generarChiCuadradoCalculado())
        {
            return true;
        }
        return false;
    }
    
    public int getGradosDeLibertad()
    {
        return this.gradosDeLibertad;
    }
    
    
    private void intervalos()
    {
        intervalos = new int[numIntervalos];
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
     
    private void contarFrecuencia()
    {

        double amplitudIntervalo = 1 / (float) numIntervalos;
        amplitudIntervalo = Math.round(amplitudIntervalo*10000.0) / 10000.0;
        for (double aux : numerosAleatorios)
        {
            int count = 0;
            for (double i = 0; i< 1 ; i += amplitudIntervalo)
            {
                if(aux >= 1) 
                    {
                        break;
                    
                    }
                if (i <= aux && aux < i + amplitudIntervalo)
                {
                    
                    intervalos[count] += 1;
                    break;
                }
                else { count += 1;  }
            }
        }

    }
    
    //
    public ArrayList listaIntervalos()
    {
        ArrayList<String> listaIntervalos = new ArrayList<>();
        double amplitudIntervalo = 1 / (float) numIntervalos;
        amplitudIntervalo = Math.round(amplitudIntervalo*10000.0) / 10000.0;
        String aux;
        for (float i = 0; i < 1; i+=amplitudIntervalo)
        {
                double intM = Math.round((i+amplitudIntervalo) * 10000.0) / 10000.0;
                double intm = Math.round(i * 10000.0) / 10000.0;
                aux = String.valueOf(intm)  + " - " + String.valueOf(intM);
                if (i+amplitudIntervalo > 1) 
                {
                    aux = String.valueOf(intm)  + " - 1";
                }
                listaIntervalos.add(aux);
        }
        return listaIntervalos;
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
        this.intervalos = intervalos;
    }

    public int getNumIntervalos() {
        return numIntervalos;
    }

    public int[] getIntervalos() 
    {
        return intervalos;
    }

    
    public abstract ArrayList<Double> diferenciaYalCuadrado();
    
    public abstract ArrayList<Double> generarSumatoriaChi();
    
//Suma todos los valores obtenidos en generarSumatoriaChi    
    public double generarChiCuadradoCalculado()
    {
        ArrayList<Double> lista = this.generarSumatoriaChi();
        double aux = 0;
        for (double i : lista)
        {
            aux += i;
        }
        return aux;
    }
}
