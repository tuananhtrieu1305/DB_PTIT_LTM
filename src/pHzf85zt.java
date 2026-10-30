import ptit.dblab.judge.network.server.soap.*;
import java.util.*;

public class pHzf85zt {
    public static void main(String[] args) {
        String sCode = "B23DCCN053";
        String qCode = "pHzf85zt";

        try {
            DataService s = new DataService();
            SoapDataService port = s.getSoapDataServicePort();

            List<Integer> a = port.getData(sCode, qCode);
            List<String> list = new ArrayList<>();
            for (int x : a)
                list.add("" + x);

            list.sort((x, y) -> (y + x).compareTo(x + y));
            String result = String.join("", list);

            port.submitDataString(sCode, qCode, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
