import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class soap_9aZwsI4e {
    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "9aZwsI4e";
        try {
            DataService s = new DataService();
            SoapDataService port = s.getSoapDataServicePort();

            List<Integer> list = port.getData(sCode, qCode);
            int cnt1 = 0, cnt2 = 0;
            for(int x : list) {
                if(x % 2 == 0) {
                    cnt1++;
                } else {
                    cnt2++;
                }
            }
            List<String> res = new ArrayList<>();
            res.add("EVEN=" + cnt1);
            res.add("ODD=" + cnt2);
            port.submitDataStringArray(sCode, qCode, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
