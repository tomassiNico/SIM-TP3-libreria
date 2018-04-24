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
    
    //Metodo que genera los intervalos agrupados. frecuencia observada agrupada y las esperadas agrupadas
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

    //Metodo que crea la frecuencia Esperada en el atributo esperada de la clase
    public void calcularEsperadas()
    {
        this.esperadas = new ArrayList();
        //ArrayList que tiene los intervalos sin agrupar
        ArrayList intervalos = this.getIntervalosGenerados();
        for (int i = 0; i < this.getNumIntervalos() ; i++)
        {
            //Variable para guardar el intervalo necesario
            double aux[] =  (double[]) intervalos.get(i);
            //Calculo la marca de clase
            double marcaClase = (aux[0] + aux[1]) / 2;
            //Formula de p() de LA distribucion normal
            double aux3 = Math.pow(2,(marcaClase - this.media)/this.desviacion);
            double aux2 = this.desviacion * Math.sqrt(2 * Math.PI);
            double fmc = (Math.pow(Math.E, (-1/2* aux3 ))) / aux2;
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
            int frec = (int) this.frecuenciaAgrupada.get(i);
            Double esp = this.esperadasAgrupadas.get(i);
            double aux1 = Math.pow(frec - esp, 2) / esp;
            aux.add(aux1);
        }
        return aux; 
    }

  
    @Override
    public boolean ejecutarTest() {
        this.calcularEsperadas();
        return this.esAprobado();
    }
    
    
    
}
