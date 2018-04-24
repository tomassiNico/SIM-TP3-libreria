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
    
    
    public ChiNormal(int intervalos, ArrayList numeros) {
        super(intervalos, numeros);
        this.cantidadNumeros = numeros.size();
        this.intervalosGenerados = new ArrayList();
    }

    
    public ArrayList calcularEsperadas()
    {
        ArrayList aux = new ArrayList();
        for (int i = 0; i < this.getNumIntervalos() ; i++)
        {
            double aux1 = Math.pow(Math.E, (-1/2* ((this.x - this.u))))
            aux.add(aux1); 
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
