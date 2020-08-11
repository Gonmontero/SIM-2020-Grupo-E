/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.*;
import Graphs.Histograma;
import Entities.Controller;
import java.math.BigDecimal;
import java.util.ArrayList;

public class TestCongruencial extends javax.swing.JFrame {

    Controller controller;
    int contador;
    ArrayList<BigDecimal> valoresGenerados;
    int cantIntervalos;
    double frecEsp;

    public TestCongruencial(Controller cont, int cantIntervalos, double rango, JTable finaltable) {
        controller = cont;

        //Iniciizo valores
        DecimalFormat in = new DecimalFormat("0.00");
        DecimalFormat c = new DecimalFormat("0.000");
        DecimalFormat aleat = new DecimalFormat("0.0000");
        initComponents();
        double[][] matrizIntervalos = new double[cantIntervalos][2];
        contador = 0;

        // Tomo los valores de la tabla que le pase, y genero un Vector con todos los Valores a recorrer.
        ArrayList<BigDecimal> vec = obtenerValoresGeneradosEnTabla(finaltable);

        //Calculo la Frecuencia Esperada (Total de Valores / Intervalos)
        frecEsp = (double) vec.size() / cantIntervalos;

        //
        int[][] response = controller.matrizFrecuencia(vec, cantIntervalos);
        double rangoM;
        DefaultTableModel tm = (DefaultTableModel) tableC.getModel();
        for (int i = 0; i < response.length; i++) {
            if (i == 0) {
                rangoM = rango;
                tm.addRow(new Object[]{"0.00 - " + in.format(rangoM), i, response[i][1], frecEsp});
                matrizIntervalos[0][0] = 0;
                matrizIntervalos[0][1] = (double) rangoM;
                contador++;
            } else {
                if (contador == 1) {
                    tm.addRow(new Object[]{in.format(rango) + " - " + in.format(rango + rango), i, response[i][1], frecEsp});
                    matrizIntervalos[i][0] = rango;
                    matrizIntervalos[i][1] = (double) rango + rango;
                    contador++;
                } else {
                    tm.addRow(new Object[]{in.format(rango * contador) + " - " + in.format((rango * contador) + rango), i, response[i][1], frecEsp});
                    matrizIntervalos[i][0] = rango * contador;
                    matrizIntervalos[i][1] = (rango * contador) + rango;
                    contador++;
                }

            }
        }

        String acum = "";
        for (int i = 0; i < vec.size(); i++) {
            acum += "Valor " + (i + 1) + ": " + vec.get(i) + ".\n";
        }
        txt_valoresGenerados.setText(acum);

        valoresGenerados = vec;
        this.cantIntervalos = cantIntervalos;
        agregarHistograma();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public ArrayList<BigDecimal> obtenerValoresGeneradosEnTabla(JTable t) {
        ArrayList<BigDecimal> s = new ArrayList<>();
        
        for (int i = 0; i < t.getRowCount(); i++) {
            s.add(BigDecimal.valueOf(Double.parseDouble((String) t.getValueAt(i, 3))));
        }
        return s;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableC = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_valoresGenerados = new javax.swing.JTextArea();
        _pnlHistograma = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Test de chi cuadrado para el metodo congruencial");

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 402));

        tableC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numeros del intervalo", "Intervalo", "Frecuencia", "Frecuencia esperada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableC);

        jLabel1.setText("Valores generados");

        txt_valoresGenerados.setColumns(20);
        txt_valoresGenerados.setRows(5);
        jScrollPane2.setViewportView(txt_valoresGenerados);

        _pnlHistograma.setPreferredSize(new java.awt.Dimension(600, 200));
        _pnlHistograma.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(_pnlHistograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addComponent(_pnlHistograma, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel _pnlHistograma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableC;
    private javax.swing.JTextArea txt_valoresGenerados;
    // End of variables declaration//GEN-END:variables

    private void agregarHistograma() {
        // Tenemos que convertir los numeros generados a un vector de double.
        double[] valoresGeneradosEnDouble = obtenerValoresEnDouble();
        Histograma histograma = new Histograma("Frecuencias del generador con el Metodo Congruencial",
                valoresGeneradosEnDouble, cantIntervalos);
        JPanel histoPanel = histograma.obtenerPanel();
        histoPanel.setVisible(true);
        _pnlHistograma.add(histoPanel);
        _pnlHistograma.validate();
    }

    private double[] obtenerValoresEnDouble() {
        double[] ret = new double[valoresGenerados.size()];
        for (int i = 0; i < valoresGenerados.size(); i++) {
            ret[i] = valoresGenerados.get(i).doubleValue();
        }
        return ret;
    }
}
