import ptit.dblab.judge.network.server.soap.*;
import java.util.*;

public class I6ZJWQYb {
    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "I6ZJWQYb";

        try {
            DataService s = new DataService();
            SoapDataService port = s.getSoapDataServicePort();

            List<Integer> list = port.getData(sCode, qCode);
            int sum = 0;
            for(int x : list) {
                sum += x;
            }
            port.submitDataInt(sCode, qCode, sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
