import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class soap_9ay21X2Y {
    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "9ay21X2Y";
        try {
            ObjectService s = new ObjectService();
            SoapObjectService port = s.getSoapObjectServicePort();

            List<Customer> customers = port.requestListCustomer(sCode, qCode);
            List<Customer> res = new ArrayList<>();
            for(Customer x : customers) {
                if(x.getPurchaseCount() >= 6 && x.getTotalSpent() >= 4000) {
                    res.add(x);
                }
            }
            Collections.sort(res, (a, b) -> {
                if(a.getTotalSpent() == b.getTotalSpent()) {
                    return a.getCustomerId().compareTo(b.getCustomerId());
                }
                return (int) (b.getTotalSpent() - a.getTotalSpent());
            });
            port.submitListCustomer(sCode, qCode, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
