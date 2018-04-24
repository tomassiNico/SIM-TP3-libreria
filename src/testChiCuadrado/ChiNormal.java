/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testChiCuadrado;

import java.util.ArrayList;

/**
 *
 * @author aleex
 */
public class ChiNormal extends TestChiCuadrado{
    int cantidadNumeros;
    double media;
    double desviacion;
    ArrayList<Double> esperadas;
    //En la agrupacion
    ArrayList<Double> esperadasAgrupadas;
    ArrayList intervalosAgrupados;
    ArrayList frecuenciaAgrupada;
    
    public ChiNormal(int intervalos, ArrayList numeros, double media, double desviacion) {
        super(intervalos, numeros);
        this.media = media;
        this.cantidadNumeros = numeros.size();
        this.desviacion = desviacion;
        this.calcularEsperadas();
        this.esperadasAgrupadas = new ArrayList();
        this.frecuenciaAgrupada = new ArrayList();
        this.intervalosAgrupados = new ArrayList();
    }
    
    public void generarIntervalosAgrupados()
    {
        double acuEsperada = 0;
        double acuFrecuencia = 0;
        double limInf = 0;//Desde - Nuevo intervalo
        ArrayList intViejos = this.getIntervalosGenerados();
        int []frecuenciaVieja = this.getContadorFrecuencia();
        for (int i = 0; i < this.esperadas.size(); i++)
        {
            acuEsperada += this.esperadas.get(i);
            acuFrecuencia += frecuenciaVieja[i];
            if (acuEsperada > 5) 
            {
                 this.esperadasAgrupadas.add(acuEsperada);
                 this.frecuenciaAgrupada.add(acuFrecuencia);
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

    
    public void calcularEsperadas()
    {
        this.esperadas = new ArrayList();
        ArrayList intervalos = this.getIntervalosGenerados();
        for (int i = 0; i < this.getNumIntervalos() ; i++)
        {
            double aux[] =  (double[]) intervalos.get(i);
            double marcaClase = (aux[0] + aux[1]) / 2;
            double aux3 = Math.pow(2,(marcaClase - this.media)/this.desviacion);
            double aux2 = this.desviacion * Math.sqrt(2 * Math.PI);
            double fmc = (Math.pow(Math.E, (-1/2* aux3 ))) / aux2;
            double px = fmc * (aux[1] - aux[0]);
            double esperada = px * this.cantidadNumeros;
            this.esperadas.add(esperada); 
        }
    }
    
    @Override
    public ArrayList<Double> diferenciaYalCuadrado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Double> generarSumatoriaChi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ejecutarTest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
