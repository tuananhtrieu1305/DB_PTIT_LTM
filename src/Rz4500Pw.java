import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class Rz4500Pw {
    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "Rz4500Pw";
        try {
            ObjectService s = new ObjectService();
            SoapObjectService port = s.getSoapObjectServicePort();

            ProductY productY = port.requestProductY(sCode, qCode);
            productY.setFinalPrice(productY.getPrice() * (1 + productY.getTaxRate() / 100) * (1 - productY.getDiscount() / 100));
            port.submitProductY(sCode, qCode, productY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
