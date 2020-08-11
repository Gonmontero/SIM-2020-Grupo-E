/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator {

    // Calcula Xi, Xi+1 y Calcula el Random
    public static String[][] calculate(Datos datos, int loops) {
        String[][] seeds = new String[loops][3];
        for (int i = 0; i < loops; i++) 
        {
            //0 : Xi
            seeds[i][0] = String.valueOf(datos.getSeed());
            //Guardo el Registro Anterior.
            datos.setSeed(((datos.getA() * datos.getSeed()) + datos.getC()) % datos.getM());
            //1 : Xi+1
            seeds[i][1] = String.valueOf(datos.getSeed());
            //2 : Num Aleat (entre 0 y 1)
            BigDecimal div = BigDecimal.valueOf(datos.getSeed()).divide(BigDecimal.valueOf(datos.getM()), MathContext.DECIMAL64);
            seeds[i][2] = String.valueOf(formatRandomNumber(div.doubleValue()));
        }
        return seeds;
    }
    
    public static String formatRandomNumber(double nbr)
    {
        String rv = String.valueOf(nbr);
        if (rv != null && rv.indexOf('.') > -1)
        {
            int pos = rv.indexOf('.');
            if (rv.length() > pos + 5)
            {
                rv = rv.substring(0, pos+5);
            }
        }
        return rv;
    }
}
