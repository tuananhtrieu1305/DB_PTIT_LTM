import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class mz7z0MCH {
    static boolean isPrime(int n) {
        if(n < 2) {
            return false;
        }
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "mz7z0MCH";
        try {
            DataService s = new DataService();
            SoapDataService port = s.getSoapDataServicePort();

            List<Integer> list = port.getData(sCode, qCode);
            int primeCnt = 0, checksum = 0;
            for(int i = 0; i < list.size(); i++) {
                if(isPrime(list.get(i))) {
                    primeCnt++;
                }
                checksum += ((i + 1) * list.get(i)) % 100000;
            }
            String res = "primeCount=" + primeCnt + ";checksum=" + checksum;
            port.submitDataString(sCode, qCode, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
