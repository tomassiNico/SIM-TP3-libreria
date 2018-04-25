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
        this.calcularEsperado();
        this.generarIntervalosAgrupados(); 
        this.esperadas = this.generarEsperados();
    }

    public double getEsperado() {
        return esperado;
    }
    
    public ArrayList generarEsperados()
    {
        ArrayList aux = new ArrayList();
        ArrayList intervalos = this.getIntervalosGenerados();
        for (int i = 0 ; i < intervalos.size() ; i++)
        {
            aux.add(this.esperado);
        }
        return aux;
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
        this.contarFrecuencia();
    }
    public void generarIntervalosAgrupados()
    {
        this.intervalosAgrupados = this.getIntervalosGenerados();
        ArrayList<Double> observadas = new ArrayList<>();
        for (int i = 0; i < this.getContadorFrecuencia().length; i++) {
            observadas.add((double)this.getContadorFrecuencia()[i]);
            this.esperadasAgrupadas.add(this.esperado);
        }
        this.observadasAgrupadas = observadas;
        this.intervalosAgrupados = this.getIntervalosGenerados();
    }
    
    @Override
    public ArrayList<Double> diferenciaYalCuadrado() 
    {
        double fo, fe = this.esperado, diferencia;
        
        ArrayList<Double> diferencias = new ArrayList<>();
        for ( int i = 0 ; i < this.getIntervalosAgrupados().size() ; i++)
        {
            fo = this.getObservadasAgrupadas().get(i);
            diferencia = Math.pow((fo-fe), 2) / fe;
            diferencias.add(diferencia);
        }
        return diferencias; 
    }

    @Override
    public boolean ejecutarTest() 
    {
                    
        this.gradosDeLibertad = this.intervalosAgrupados.size() - 1;
        return this.esAprobado();
    }


    
    
}
    