package test.unit;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.ws.rs.core.MediaType.TEXT_HTML_TYPE;
import static org.assertj.core.api.BDDAssertions.then;
import static test.TestData.ORDER;

import java.io.ByteArrayOutputStream;
import java.lang.annotation.Annotation;

import javax.ws.rs.core.MultivaluedHashMap;

import org.junit.jupiter.api.Test;

import com.example.frontend.boundary.OrderHtmlMessageBodyWriter;
import com.example.frontend.domain.Order;

class OrderHtmlMessageBodyWriterTest {
    private final OrderHtmlMessageBodyWriter writer = new OrderHtmlMessageBodyWriter();

    @Test void shouldPrintOrder() {
        String html = whenWrite();

        then(html).isEqualTo(""
                + "<html>\n"
                + "<head>\n"
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
                + "    <title>Order #1</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Order #1</h1>\n"
                + "Customer: Jane Doe</td>\n"
                + "<table><tr>\n"
                + "  <td>Id</td>\n"
                + "  <td>Count</td>\n"
                + "  <td>Product</td>\n"
                + "  <td>Price</td>\n"
                + "</tr>\n"
                + "\n"
                + "<tr>\n"
                + "  <td>1</td>\n"
                + "  <td>3</td>\n"
                + "  <td>Foobar</td>\n"
                + "  <td>EUR 12.34</td>\n"
                + "</tr>\n"
                + "\n"
                + "<tr>\n"
                + "  <td>2</td>\n"
                + "  <td>2</td>\n"
                + "  <td>Bazzing</td>\n"
                + "  <td>EUR 56.78</td>\n"
                + "</tr>\n"
                + "</table>\n"
                + "</body>\n"
                + "</html>\n");
    }

    private String whenWrite() {
        ByteArrayOutputStream entityStream = new ByteArrayOutputStream();
        writer.writeTo(ORDER, Order.class, Order.class, new Annotation[0], TEXT_HTML_TYPE, new MultivaluedHashMap<>(), entityStream);
        return entityStream.toString(UTF_8);
    }
}
