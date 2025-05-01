import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class InvoiceApp {
    private static ArrayList<LineItem> items = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Invoice Generator");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Customer fields
        JTextField nameField = new JTextField(20);
        JTextField streetField = new JTextField(20);
        JTextField cityField = new JTextField(10);
        JTextField stateField = new JTextField(2);
        JTextField zipField = new JTextField(5);

        // Product fields
        JTextField productField = new JTextField(15);
        JTextField priceField = new JTextField(7);
        JTextField quantityField = new JTextField(5);

        // Invoice Display
        JTextArea invoiceArea = new JTextArea(20, 50);
        invoiceArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(invoiceArea);

        // Add Line Item Button
        JButton addItemButton = new JButton("Add Line Item");
        addItemButton.addActionListener((ActionEvent e) -> {
            try {
                String name = productField.getText();
                double price = Double.parseDouble(priceField.getText());
                int qty = Integer.parseInt(quantityField.getText());
                Product product = new Product(name, price);
                items.add(new LineItem(product, qty));
                JOptionPane.showMessageDialog(frame, "Item Added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        // Generate Invoice Button
        JButton generateButton = new JButton("Generate Invoice");
        generateButton.addActionListener((ActionEvent e) -> {
            Address addr = new Address(
                    streetField.getText(),
                    cityField.getText(),
                    stateField.getText(),
                    zipField.getText());
            Customer customer = new Customer(nameField.getText(), addr);
            Invoice invoice = new Invoice(customer);
            for (LineItem item : items) {
                invoice.addItem(item);
            }
            invoiceArea.setText(invoice.generateInvoiceText());
        });

        // Add all components
        frame.add(new JLabel("Customer Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Street:"));
        frame.add(streetField);
        frame.add(new JLabel("City:"));
        frame.add(cityField);
        frame.add(new JLabel("State:"));
        frame.add(stateField);
        frame.add(new JLabel("ZIP:"));
        frame.add(zipField);

        frame.add(new JLabel("Product:"));
        frame.add(productField);
        frame.add(new JLabel("Unit Price:"));
        frame.add(priceField);
        frame.add(new JLabel("Quantity:"));
        frame.add(quantityField);

        frame.add(addItemButton);
        frame.add(generateButton);
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}
