package com.mycompany.iproductcalculations;

import javax.swing.*;
import java.io.*;

public class MattressSalesApp extends JFrame {
    JTextField txtCustomer, txtPrice;
    JComboBox<String> cmbProduct;

    public MattressSalesApp() {
        setTitle("Product App");
        setSize(480, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 

        // Menu 
        JMenuBar menuBar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenuItem mnuExit = new JMenuItem("Exit");
        JMenu mnuTools = new JMenu("Tools");
        JMenuItem mnuProcess = new JMenuItem("Process");
        JMenuItem mnuClear = new JMenuItem("Clear");
        mnuFile.add(mnuExit);
        mnuTools.add(mnuProcess);
        mnuTools.add(mnuClear);
        menuBar.add(mnuFile);
        menuBar.add(mnuTools);
        setJMenuBar(menuBar);

         //customer details and product selection
        JLabel lblCustomer = new JLabel("Customer Name:");
        JLabel lblProduct = new JLabel("Select Product:");
        JLabel lblPrice = new JLabel("Enter Price:");
        txtCustomer = new JTextField(" ");
        cmbProduct = new JComboBox<>(new String[]{"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
        txtPrice = new JTextField();
        JButton btnProcess = new JButton("PROCESS");

        lblCustomer.setBounds(20, 20, 110, 25);
        txtCustomer.setBounds(140, 20, 240, 25);
        lblProduct.setBounds(20, 65, 110, 25);
        cmbProduct.setBounds(140, 65, 240, 25);
        lblPrice.setBounds(20, 110, 110, 25);
        txtPrice.setBounds(140, 110, 240, 25);
        btnProcess.setBounds(140, 155, 240, 30);

        add(lblCustomer); add(txtCustomer);
        add(lblProduct); add(cmbProduct);
        add(lblPrice); add(txtPrice);
        add(btnProcess);

        Runnable doProcess = () -> {
            String customer = txtCustomer.getText();
            String product = (String) cmbProduct.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText());

            ProductsCalculations calc = new ProductsCalculations(price);
            double vat = calc.GetVat();
            double comm = calc.GetCommission();
            double total = calc.GetTotal();
            
            //pop-up message with entered details and calculate VAT + commission
            String msg = "Customer Name: " + customer +
                    "\nProduct: " + product +
                    "\nVAT: R " + vat +
                    "\nCOMMISSION: R " + comm +
                    "\nTOTAL: R " + total;

            JOptionPane.showMessageDialog(this, msg, "Message", JOptionPane.INFORMATION_MESSAGE);

            // Save to data.txt
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt", true))) {
                bw.write("Customer Name: " + customer + " | Product: " + product + " | VAT: R " + vat + " | COMMISSION: R " + comm + " | TOTAL: R " + total);
                bw.newLine();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file");
            }
        };

        Runnable doClear = () -> {
            txtCustomer.setText("");
            txtPrice.setText("");
            cmbProduct.setSelectedIndex(0);
        };

        btnProcess.addActionListener(e -> doProcess.run());
        mnuProcess.addActionListener(e -> doProcess.run());
        mnuClear.addActionListener(e -> doClear.run());
        mnuExit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new MattressSalesApp();
    }
}