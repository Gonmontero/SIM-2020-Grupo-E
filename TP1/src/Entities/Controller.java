/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;
import tp1.GeneradorCongruencial;
import tp1.Main;
import tp1.Metodo;
import tp1.Test;

/**
 *
 * @author gonzalomontero
 */
public class Controller {

    private static Controller controller;
    static Main main;
    static Metodo metodo;
    static Test test;
    static Datos datos;
    GeneradorCongruencial generadorCongruencial;

    protected Controller(Main menu) {
        main = menu;
        metodo = new Metodo(this);
        test = new Test(this);
    }

    public static Controller getInstance(Main menu) {
        if (controller == null) {
            controller = new Controller(menu);
        } else {
            controller.setMain(menu);
        }

        return controller;
    }

    public void showMenu() {
        metodo.setVisible(false);
        main.setVisible(true);
    }

    public void setMain(Main menu) {
        main = menu;
    }

    public void selectedMethod(String metodo) {
        main.setVisible(false);
        this.metodo.setVisible(true);
        this.metodo.setMethodType(metodo);
    }

    // Dado los parametros a, c, m, y semilla generar Numeros Aleatorios
    public void calculate(int a, int c, int m, int seed, int size) {
        datos = new Datos();
        datos.setValues(a, c, m, seed);
        String[][] response = Calculator.calculate(datos, size);

        String[] datosDelMetodo = {"" + a, "" + c, "" + m};
        generadorCongruencial = new GeneradorCongruencial(this, response, "Mixto", datosDelMetodo);
        generadorCongruencial.setVisible(true);
        metodo.setVisible(false);
    }

    public String[][] calculateOneMore() {
        return Calculator.calculate(datos, 1);
    }

    public Datos getDatos() {
        return datos;
    }

    //Genera un vector de los numeros aleatorios del generador de Java
    public ArrayList<BigDecimal> randomDouble(int size) {
        Random random = new Random();

        ArrayList<BigDecimal> result = new ArrayList<>();
        double[] vecValores = new double[size];
        double randomValue = 0;

        for (int i = 0; i < vecValores.length; i++) {
            randomValue = random.nextDouble();
            result.add(BigDecimal.valueOf(randomValue));
        }

        return result;
    }

    /**
     *
     * @param randomVec
     * @param intervalo 5, 10 o 20
     * @return
     */
    public int[][] matrizFrecuencia(ArrayList<BigDecimal> randomVec, int intervalo) {
        int[][] matrizFrecuencia = new int[intervalo][2];

            BigDecimal rango = BigDecimal.ONE.divide(BigDecimal.valueOf(intervalo));
        BigDecimal comparador;

        for (int i = 0; i < randomVec.size(); i++) {
            comparador = rango;
            for (int j = 0; j < intervalo; j++) {
                if (randomVec.get(i).doubleValue() < comparador.doubleValue()) {
                    matrizFrecuencia[j][1]++;
                    break;
                } else {
                    comparador = comparador.add(rango);
                }
            }
        }
        return matrizFrecuencia;
    }

    public double getRango(int intervalo) {
        double rango = 1f / intervalo;
        return rango;
    }

    public void ChiTestSelected() {
        main.setVisible(false);
        test.setVisible(true);
    }

    public void volverDeTestRandomJava() {
        main.setVisible(true);
    }

    public void volverDeGeneradorCongruencial() {
        generadorCongruencial.dispose();
        metodo.setVisible(true);
    }
}
