import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class InvoiceTest {
    @Test
    public void testLineItemTotal() {
        Product p = new Product("Pen", 1.5);
        LineItem item = new LineItem(p, 3);
        assertEquals(4.5, item.getTotal(), 0.001);
    }

    @Test
    public void testInvoiceTotal() {
        Product p1 = new Product("Notebook", 2.0);
        Product p2 = new Product("Pencil", 0.5);
        LineItem l1 = new LineItem(p1, 2);
        LineItem l2 = new LineItem(p2, 4);
        Customer c = new Customer("Jane", new Address("123 Main", "Town", "OH", "45220"));
        Invoice invoice = new Invoice(c);
        invoice.addItem(l1);
        invoice.addItem(l2);
        assertEquals(5.0, invoice.getTotal(), 0.001);
    }
}
