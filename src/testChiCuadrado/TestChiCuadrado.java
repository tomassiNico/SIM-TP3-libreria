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
    
    private int numAGenerar;
    private int numIntervalos;
    private ArrayList<Double> numerosAleatorios;//Numeros aleatorios a testear
    private int gradosDeLibertad;
    private int intervalos[];
    
    public TestChiCuadrado(int intervalos, ArrayList numeros, int valoresEmpiricos)
    {
        this.numIntervalos = intervalos;
        this.numerosAleatorios = numeros;
        this.gradosDeLibertad = intervalos - 1 - valoresEmpiricos;
    }
    
    public ArrayList<Double> getNumerosAleatorios() 
    {
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
    
     public double getNumeroTabla(int numero)
    {
        ArrayList<Double> tablaChi = new ArrayList<>();
        /*switch (numero){
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
            case 1:
                                
        }*/
        
        tablaChi.add(3.84);
        tablaChi.add(5.99);
        tablaChi.add(7.81);
        tablaChi.add(9.49);
        tablaChi.add(11.1);
        tablaChi.add(12.6);
        tablaChi.add(14.1);
        tablaChi.add(15.5);
        tablaChi.add(16.9);
        tablaChi.add(18.3);
        tablaChi.add(19.7);
        tablaChi.add(21.0);
        tablaChi.add(22.4);
        tablaChi.add(23.7);
        tablaChi.add(25.0);
        tablaChi.add(26.3);
        tablaChi.add(27.6);
        tablaChi.add(28.9);
        tablaChi.add(30.1);
        tablaChi.add(31.4);
        tablaChi.add(32.7);
        tablaChi.add(33.9);
        tablaChi.add(35.2);
        tablaChi.add(36.4);
        tablaChi.add(37.7);
        tablaChi.add(38.9);
        tablaChi.add(40.1);
        tablaChi.add(41.3);
        tablaChi.add(42.6);
        tablaChi.add(43.8);
        tablaChi.add(55.8);
        tablaChi.add(67.5);
        tablaChi.add(79.1);
        tablaChi.add(90.5);
        tablaChi.add(101.9);
        tablaChi.add(113.1);
        tablaChi.add(124.3);
        if(this.gradosDeLibertad >30)
        {
            this.gradosDeLibertad -= 30;
            this.gradosDeLibertad /= 10;
            this.gradosDeLibertad += 29;
        }
        return tablaChi.get(numero-1);
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

    public void setNumAGenerar(int numAGenerar) {
        this.numAGenerar = numAGenerar;
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
