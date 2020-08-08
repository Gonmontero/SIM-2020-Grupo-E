/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import javax.swing.table.DefaultTableModel;

public class MetodoTableModel extends DefaultTableModel
{
    public static final int COL_NUM = 0;
    public static final int COL_ACTUAL = 1;
    public static final int COL_SIGUIENTE = 2;
    public static final int COL_NUM_ALEATORIO = 3;
    
    public MetodoTableModel (Object[] objects, int rowcount)
    {
        super(objects, rowcount);
    }
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }
}
