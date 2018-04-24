package testChiCuadrado;


import testChiCuadrado.TestChiCuadrado;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleex
 */
public class ChiUniforme extends TestChiCuadrado{
    
    private double esperado;
    
    public ChiUniforme(int intervalos, ArrayList numeros) {
        super(intervalos, numeros);
        super.setGradosDeLibertad(intervalos - 1);
    }

    public double getEsperado() {
        return esperado;
    }
    
    
    public void calcularEsperado()
    {
        ArrayList NumerosAleatorios = this.getNumerosAleatorios();
        int numIntervalos = this.getNumIntervalos();
        this.esperado = (float) NumerosAleatorios.size() / (float) numIntervalos ;
        while(esperado < 5)
            {
                numIntervalos -= 1;
                this.esperado = (float) super.getNumerosAleatorios().size() / (float) this.getNumIntervalos();
            }
        this.esperado = Math.round(this.esperado*10000.0) / 10000.0;
        super.setNumIntervalos(numIntervalos);
        int intervalos[] = new int[numIntervalos];
        super.setIntervalos(intervalos);
    }

    @Override
    public ArrayList<Double> diferenciaYalCuadrado() 
    {
        ArrayList<Double> aux = new ArrayList<>();
        int intervalos[] = super.getIntervalos();
        for ( int i = 0 ; i < intervalos.length ; i++)
        {
            double aux1 = Math.pow(intervalos[i] - this.esperado, 2);
            aux1 /= this.esperado;
            aux.add(aux1);
        }
        return aux; 
    }

    @Override
    public boolean ejecutarTest() 
    {
        this.calcularEsperado();
        return this.esAprobado();
    }

    
    
}
    