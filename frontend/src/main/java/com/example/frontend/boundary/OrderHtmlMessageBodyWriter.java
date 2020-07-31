package com.example.frontend.boundary;

import static javax.ws.rs.core.MediaType.TEXT_HTML;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.example.frontend.domain.Order;
import com.example.frontend.domain.OrderItem;
import lombok.RequiredArgsConstructor;

@Provider
@Produces(TEXT_HTML)
public class OrderHtmlMessageBodyWriter implements MessageBodyWriter<Order> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Order.class.equals(type);
    }

    @SuppressWarnings("resource") @Override
    public void writeTo(Order order, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws WebApplicationException {
        PrintWriter out = new PrintWriter(entityStream);
        new HtmlWriter(out, order).print();
    }

    @RequiredArgsConstructor
    private static final class HtmlWriter {
        private final PrintWriter out;
        private final Order order;

        public void print() {
            out.println("<html>");
            printHead();
            printBody();
            out.println("</html>");
            out.flush();
        }

        private void printHead() {
            out.println("<head>\n"
                    + "    <style>\n"
                    + "        body {\n"
                    + "            font-family: \"Fira Code\", \"Courier New\", Courier, monospace;\n"
                    + "            font-size: 14px;\n"
                    + "        }\n"
                    + "\n"
                    + "        table {\n"
                    + "            margin-top: 24pt;\n"
                    + "            border: 1px solid rgb(221, 221, 221);\n"
                    + "            border-collapse: collapse;\n"
                    + "            box-sizing: border-box;\n"
                    + "            color: rgb(51, 51, 51);\n"
                    + "        }\n"
                    + "\n"
                    + "        tr {\n"
                    + "            height: 37px;\n"
                    + "        }\n"
                    + "\n"
                    + "        td {\n"
                    + "            border: 1px solid rgb(221, 221, 221);\n"
                    + "            border-collapse: collapse;\n"
                    + "            padding: 8px 8px 0;\n"
                    + "            vertical-align: top;\n"
                    + "        }\n"
                    + "    </style>\n"
                    + "    <title>Order #" + order.getId() + "</title>\n"
                    + "</head>");
        }

        private void printBody() {
            out.println("<body>\n"
                    + "<h1>Order #" + order.getId() + "</h1>\n"
                    + "Customer: " + order.getCustomer().getName() + "</td>\n"
                    + "<table>"
                    + "<tr>\n"
                    + "  <td>Id</td>\n"
                    + "  <td>Count</td>\n"
                    + "  <td>Product</td>\n"
                    + "  <td>Price</td>\n"
                    + "</tr>");
            for (OrderItem item : order.getItems())
                print(item);
            out.println("</table>\n"
                    + "</body>");
        }

        private void print(OrderItem item) {
            out.println("\n"
                    + "<tr>\n"
                    + "  <td>" + item.getId() + "</td>\n"
                    + "  <td>" + item.getCount() + "</td>\n"
                    + "  <td>" + item.getProduct() + "</td>\n"
                    + "  <td>" + toPrice(item.getPieceCostInCent()) + "</td>\n"
                    + "</tr>");
        }

        private String toPrice(int priceInCent) {
            return "EUR " + priceInCent / 100 + "." + priceInCent % 100;
        }
    }
}

