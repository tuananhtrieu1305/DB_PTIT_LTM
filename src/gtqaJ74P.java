import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class gtqaJ74P {
    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "gtqaJ74P";
        try {
            ObjectService s = new ObjectService();
            SoapObjectService port = s.getSoapObjectServicePort();

            List<Customer> customers =
                    port.requestListCustomer(sCode, qCode);
            List<Customer> res = new ArrayList<>();
            for(Customer x : customers) {
                if(x.getTotalSpent() > 500
                        && x.getPurchaseCount() >= 3) {
                    res.add(x);
                }
            }
            port.submitListCustomer(sCode, qCode, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
