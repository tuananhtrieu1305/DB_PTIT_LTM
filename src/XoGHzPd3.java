import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class XoGHzPd3 {
    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "XoGHzPd3";
        try {
            CharacterService s = new CharacterService();
            SoapCharacterService port = s.getSoapCharacterServicePort();

            List<String> list = port.requestStringArray(sCode, qCode);
            TreeMap<Integer, List<String>> m = new TreeMap<>();
            for(String x : list) {
                int cnt = 0;
                for(char c : x.toCharArray()) {
                    c = Character.toLowerCase(c);
                    if(c == 'a' || c == 'e' || c == 'u' || c == 'i' || c == 'o') {
                        ++cnt;
                    }
                }
                if(m.get(cnt) == null) {
                    List<String> tmp = new ArrayList<>();
                    tmp.add(x);
                    m.put(cnt, tmp);
                } else {
                    m.get(cnt).add(x);
                }
            }
            List<String> res = new ArrayList<>();
            for(Map.Entry<Integer, List<String>> x : m.entrySet()) {
                Collections.sort(x.getValue());
                res.addAll(x.getValue());
            }

            port.submitCharacterStringArray(sCode, qCode, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
