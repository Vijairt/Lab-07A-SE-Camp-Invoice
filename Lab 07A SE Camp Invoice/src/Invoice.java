import java.util.ArrayList;

public class Invoice {
    private Customer customer;
    private ArrayList<LineItem> items = new ArrayList<>();

    public Invoice(Customer customer) {
        this.customer = customer;
    }

    public void addItem(LineItem item) {
        items.add(item);
    }

    public double getTotal() {
        double total = 0.0;
        for (LineItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public String generateInvoiceText() {
        StringBuilder sb = new StringBuilder();
        sb.append("INVOICE\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append(customer.getAddress()).append("\n\n");

        sb.append(String.format("%-20s %-10s %-10s\n", "Product", "Qty", "Total"));
        sb.append("----------------------------------------\n");
        for (LineItem item : items) {
            sb.append(String.format("%-20s %-10d $%.2f\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotal()));
        }

        sb.append("\nTotal Amount Due: $").append(String.format("%.2f", getTotal()));
        return sb.toString();
    }
}
