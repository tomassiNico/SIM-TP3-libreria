/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuciones;

import java.util.ArrayList;

/**
 *
 * @author nicolastomassi
 */
public interface Distribucion {
    
    public ArrayList generarNumeros(int cantidad);
    public ArrayList getNumeros();
}
