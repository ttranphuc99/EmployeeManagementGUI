/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import phuctt.daos.EmployeeDAO;

/**
 *
 * @author Thien Phuc
 */
public class Frame extends javax.swing.JFrame {

    private Vector<String> header;
    private Vector data;
    private DefaultTableModel model;
    private boolean changed = false;
    private boolean isLoad = false;

    /**
     * Creates new form Frame
     */
    public Frame() {
        initComponents();
        model = (DefaultTableModel) tblEmployee.getModel();
        header = new Vector<>();
        EmployeeDAO dao = new EmployeeDAO();
        data = dao.loadData();

        header.add("Code");
        header.add("Name");
        header.add("Dept");
        header.add("Salary");

        model.setDataVector(data, header);
        tblEmployee.setModel(model);
        setEditorDept();

        tblEmployee.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (!isLoad) {
                    changed = true;
                    setStatus("Data has been modified!");
                    if (e.getColumn() == 0) {
                        int col = e.getColumn();
                        int row = e.getFirstRow();
                        String code = (String) tblEmployee.getValueAt(row, col);
                        String nameDup = isDuplicateID(code);
                        if (nameDup.equals(((Vector<String>) data.get(row)).get(1))) {
                            return;
                        }
                        String newCode;
                        do {
                            newCode = JOptionPane.showInputDialog("This code is used for " + nameDup + ". Enter new code");
                            if (newCode != null) {
                                while (newCode.equals("") || !newCode.matches("E\\d\\d\\d$")) {
                                    newCode = JOptionPane.showInputDialog("This code is not match with format Exxx");
                                    if (newCode == null) {
                                        tblEmployee.setValueAt("", e.getFirstRow(), e.getColumn());
                                        return;
                                    }
                                }

                                nameDup = isDuplicateID(newCode);
                            } else {
                                tblEmployee.setValueAt("", e.getFirstRow(), e.getColumn());
                                return;
                            }
                        } while (nameDup != null);
                        tblEmployee.setValueAt(newCode, row, col);
                    }
                }

            }
        });
    }

    private void setEditorDept() {
        JComboBox cb = new JComboBox();
        cb.addItem("IT");
        cb.addItem("IC");
        cb.addItem("Language");
        cb.addItem("Library");
        cb.setSelectedIndex(0);
        DefaultCellEditor cbEditor = new DefaultCellEditor(cb);
        tblEmployee.getColumnModel().getColumn(2).setCellEditor(cbEditor);
    }

    private boolean checkValidate() {
        boolean check = true;
        String regexCode = "E\\d\\d\\d$";
        String regexSalary = "^[0-9]*$";
        for (int i = 0; i < tblEmployee.getRowCount(); i++) {
            String code = String.valueOf(tblEmployee.getValueAt(i, 0));
            String salary = String.valueOf(tblEmployee.getValueAt(i, 3));
            String name = String.valueOf(tblEmployee.getValueAt(i, 1));

            if (!code.matches(regexCode)) {
                JOptionPane.showMessageDialog(this, "Code: " + code + " is not match with format Exxx");
                check = false;
                tblEmployee.setEditingColumn(0);
                tblEmployee.setEditingRow(i);
                break;
            }
            if (!salary.matches(regexSalary)) {
                JOptionPane.showMessageDialog(this, "Salary: " + salary + " is not valid");
                check = false;
                tblEmployee.setEditingColumn(3);
                tblEmployee.setEditingRow(i);
                break;
            }
            if (name == null || name.trim().equals("")) {
                JOptionPane.showMessageDialog(this, "At row number: " + (i + 1) + "Name is not null!");
                check = false;
                tblEmployee.setEditingColumn(0);
                tblEmployee.setEditingRow(i);
                break;
            }
        }
        return check;
    }

    private void setStatus(String mess) {
        txtStatus.setText(mess);
    }

    private String isDuplicateID(String id) {
        String name = null;
        for (Object obj : data) {
            Vector<String> vec = (Vector<String>) obj;
            if (vec.get(0).equalsIgnoreCase(id)) {
                name = vec.get(1);
                break;
            }
        }
        return name;
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
        tblEmployee = new javax.swing.JTable();
        btnLoadData = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtStatus = new javax.swing.JTextPane();
        lbCode = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbDept = new javax.swing.JLabel();
        cbDept = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnViewAll = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuLoadData = new javax.swing.JMenuItem();
        menuSave = new javax.swing.JMenuItem();
        menuNew = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuEdit = new javax.swing.JMenuItem();
        menuDelete = new javax.swing.JMenuItem();
        menuViewDetail = new javax.swing.JMenuItem();
        Window = new javax.swing.JMenu();
        menuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblEmployee.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Dept", "Salary"
            }
        ));
        jScrollPane1.setViewportView(tblEmployee);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 96, 687, 443));

        btnLoadData.setText("Load Data");
        btnLoadData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadDataActionPerformed(evt);
            }
        });
        getContentPane().add(btnLoadData, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, -1, -1));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 570, -1, -1));

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 570, -1, -1));

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 570, -1, -1));

        jButton1.setText("Edit");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 570, -1, -1));

        jScrollPane2.setViewportView(txtStatus);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 614, 620, 30));

        lbCode.setText("Code:");
        getContentPane().add(lbCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(736, 130, -1, -1));
        getContentPane().add(txtCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 127, 240, -1));

        lbName.setText("Name:");
        getContentPane().add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(736, 176, -1, -1));
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 173, 240, -1));

        lbDept.setText("Dept:");
        getContentPane().add(lbDept, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 223, -1, -1));

        cbDept.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "IT", "IC", "Language", "Library" }));
        cbDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDeptActionPerformed(evt);
            }
        });
        getContentPane().add(cbDept, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 220, -1, -1));

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Employee Management - PhucTT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 34, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Status:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, -1, -1));

        btnViewAll.setText("View All");
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });
        getContentPane().add(btnViewAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 570, -1, -1));

        jMenu1.setText("File");

        menuLoadData.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menuLoadData.setText("Load Data");
        menuLoadData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoadDataActionPerformed(evt);
            }
        });
        jMenu1.add(menuLoadData);

        menuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSave.setText("Save");
        jMenu1.add(menuSave);

        menuNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuNew.setText("New");
        jMenu1.add(menuNew);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        menuEdit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuEdit.setText("Edit");
        jMenu2.add(menuEdit);

        menuDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        menuDelete.setText("Remove");
        jMenu2.add(menuDelete);

        menuViewDetail.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.SHIFT_MASK));
        menuViewDetail.setText("View Detail");
        menuViewDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuViewDetailActionPerformed(evt);
            }
        });
        jMenu2.add(menuViewDetail);

        jMenuBar1.add(jMenu2);

        Window.setText("Window");

        menuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menuExit.setText("Exit");
        Window.add(menuExit);

        jMenuBar1.add(Window);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuLoadDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoadDataActionPerformed
        btnLoadDataActionPerformed(evt);
    }//GEN-LAST:event_menuLoadDataActionPerformed

    private void menuViewDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuViewDetailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuViewDetailActionPerformed

    private void btnLoadDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadDataActionPerformed
        isLoad = true;
        if (changed) {
            int choice = JOptionPane.showConfirmDialog(this, "Data is modified. Do you want to save?");
            switch (choice) {
                case JOptionPane.OK_OPTION:
                    EmployeeDAO dao = new EmployeeDAO();
                    if (checkValidate()) {
                        dao.saveData(data);
                        changed = false;
                        setStatus("Data has been saved!");
                    } else {
                        isLoad = false;
                        return;
                    }
                    break;
                case JOptionPane.CANCEL_OPTION:
                    isLoad = false;
                    return;
                case JOptionPane.NO_OPTION:
                    setStatus(" ");
                default:
                    break;
            }
        }
        changed = false;
        EmployeeDAO dao = new EmployeeDAO();
        data = dao.loadData();
        model.setDataVector(data, header);
        tblEmployee.setModel(model);
        setEditorDept();
        isLoad = false;
    }//GEN-LAST:event_btnLoadDataActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        EmployeeDAO dao = new EmployeeDAO();
        if (checkValidate()) {
            dao.saveData(data);
            changed = false;
            setStatus("Data has been saved!");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        Vector<String> recentRecord = (Vector<String>) data.get(data.size() - 1);
        boolean isFilled = false;
        for (String string : recentRecord) {
            if (!(string == null || string.trim().equals(""))) {
                isFilled = true;
                break;
            }
        }
        if (isFilled) {
            Vector<String> newRecord = new Vector<>();
            newRecord.add("");
            newRecord.add("");
            newRecord.add("IT");

            data.add(newRecord);
            model.setDataVector(data, header);
            tblEmployee.setModel(model);
            setEditorDept();
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int rowSelect = tblEmployee.getSelectedRow();
        if (rowSelect >= 0) {
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to delete this row?");
            switch (choice) {
                case JOptionPane.OK_OPTION:
                    data.remove(rowSelect);
                    model.setDataVector(data, header);
                    tblEmployee.setModel(model);
                    setStatus("Data has been modified!");
                    changed = true;
                    break;
            }

        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String name = txtName.getText().trim();
        String code = String.valueOf(txtCode.getText()).trim();
        String dept = (String) cbDept.getSelectedItem();

        //check validate
        String regexCode = "E\\d\\d\\d$";
        if (!code.equals("") && !code.matches(regexCode)) {
            JOptionPane.showMessageDialog(this, "Code is not match with format Exxx");
            txtCode.requestFocus();
            return;
        }

        Vector searchResult = new Vector();
        for (Object obj : data) {
            Vector<String> vec = (Vector<String>) obj;
            if (!code.equals("")) {
                if (vec.get(0).equals(code)) {
                    if (vec.get(2).equals(dept) || dept.equals("All")) {
                        if (name.equals("") || vec.get(1).toLowerCase().contains(name)) {
                            searchResult.add(obj);
                            break;
                        }
                    }
                }
            } else if (!name.equals("")) {
                if (vec.get(1).toLowerCase().contains(name)) {
                    if (vec.get(2).equals(dept) || dept.equals("All")) {
                        searchResult.add(obj);
                    }
                }
            } else {
                if (vec.get(2).equals(dept) || dept.equals("All")) {
                    searchResult.add(obj);
                }
            }
        }

        if (searchResult.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No result found!");
        } else {
            isLoad = true;
            model.setDataVector(searchResult, header);
            setEditorDept();
            isLoad = false;
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cbDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDeptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDeptActionPerformed

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed
        isLoad = true;
        model.setDataVector(data, header);
        setEditorDept();
        isLoad = false;
    }//GEN-LAST:event_btnViewAllActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Frame().setVisible(true);
//            }
//        });
        Frame frame = new Frame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (frame.changed) {
                    int choice = JOptionPane.showConfirmDialog(frame, "Data is modified. Do you want to save?");
                    if (choice == JOptionPane.OK_OPTION) {
                        EmployeeDAO dao = new EmployeeDAO();
                        if (frame.checkValidate()) {
                            dao.saveData(frame.data);
                            System.exit(0);
                        }
                    } else if (choice == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                } else {
                    int choice = JOptionPane.showConfirmDialog(frame, "Do you want to exit?");
                    if (choice == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Window;
    private javax.swing.JButton btnLoadData;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewAll;
    private javax.swing.JComboBox<String> cbDept;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbCode;
    private javax.swing.JLabel lbDept;
    private javax.swing.JLabel lbName;
    private javax.swing.JMenuItem menuDelete;
    private javax.swing.JMenuItem menuEdit;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuLoadData;
    private javax.swing.JMenuItem menuNew;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JMenuItem menuViewDetail;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextPane txtStatus;
    // End of variables declaration//GEN-END:variables
}
