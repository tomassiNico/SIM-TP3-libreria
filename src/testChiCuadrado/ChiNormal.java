/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testChiCuadrado;

import java.util.ArrayList;

/**s
 *
 * @author aleex
 */
public class ChiNormal extends TestChiCuadrado{
    private int cantidadNumeros;
    private double media;
    private double desviacion;
    
    public ChiNormal(int intervalos, ArrayList numeros, double media, double desviacion) {
        super(intervalos, numeros);
        this.media = media;
        this.cantidadNumeros = numeros.size();
        this.desviacion = desviacion;
        this.calcularEsperadas();
        
    }

    public int getCantidadNumeros() {
        return cantidadNumeros;
    }

    public double getMedia() {
        return media;
    }

    public double getDesviacion() {
        return desviacion;
    }

   
    //Metodo que genera los intervalos agrupados. frecuencia observada agrupada y las esperadas agrupadas
    public void generarIntervalosAgrupados()
    {
        double acuEsperada = 0;
        double acuFrecuencia = 0;
        double limInf = this.getMin();//Desde - Nuevo intervalo
        ArrayList intViejos = this.getIntervalosGenerados();
        int []frecuenciaVieja = this.getContadorFrecuencia();
        for (int i = 0; i < this.esperadas.size(); i++)
        {
            acuEsperada += this.esperadas.get(i);
            acuFrecuencia += frecuenciaVieja[i];
            if (acuEsperada > 5) 
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
    }

    //Metodo que crea la frecuencia Esperada en el atributo esperada de la clase
    public void calcularEsperadas()
    {
        this.esperadas = new ArrayList();
        //ArrayList que tiene los intervalos sin agrupar
        ArrayList intervalos = this.getIntervalosGenerados();
        double marcaClase;
        double aux[];
        for (Object i: intervalos)
        //for (int i = 0; i < this.getNumIntervalos() ; i++)
        {
            //Variable para guardar el intervalo necesario
            aux =  (double[]) i;
            //Calculo la marca de clase
            marcaClase = (aux[0] + aux[1]) / 2;
            //Formula de p() de LA distribucion normal
            // f(x) = 1/desv * raiz(2*PI)
            double aux3 = Math.pow((marcaClase - this.media)/this.desviacion, 2);
            double aux2 = this.desviacion * Math.sqrt(2 * Math.PI);
            double exponente = (-0.5 * aux3 );
            double fmc = (Math.pow(Math.E , exponente)) / aux2;
            //calculo de la probabilidad
            double px = fmc * (aux[1] - aux[0]);
            double esperada = px * this.cantidadNumeros;
            this.esperadas.add(esperada); 
        }
       
    }
    
    @Override
    public ArrayList<Double> diferenciaYalCuadrado() {
        ArrayList<Double> aux = new ArrayList<>();
        for ( int i = 0 ; i < this.intervalosAgrupados.size() ; i++)
        {
            double frec =  this.observadasAgrupadas.get(i);
            Double esp = this.esperadasAgrupadas.get(i);
            double aux1 = Math.pow(frec - esp, 2) / esp;
            aux.add(aux1);
        }
        return aux; 
    }

  
    @Override
    public boolean ejecutarTest() {
        this.generarIntervalosAgrupados();
        this.gradosDeLibertad = this.intervalosAgrupados.size() - 3;
        return this.esAprobado();
    }
    
    
    
}
