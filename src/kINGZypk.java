import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class kINGZypk {
    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "kINGZypk";
        try {
            CharacterService s = new CharacterService();
            SoapCharacterService port = s.getSoapCharacterServicePort();

            List<String> list = port.requestStringArray(sCode, qCode);
            Map<String, List<String>> m = new HashMap<>();
            for(String x : list) {
                String[] tmp = x.split("\\s+");
                StringBuilder sb = new StringBuilder();
                sb.append(tmp[0]).append(" ").append(tmp[1]).append(" ").append("email=[EMAIL] phone=[PHONE] token=[TOKEN] ");
                for(int i = 5; i < tmp.length; i++) {
                    sb.append(tmp[i]).append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                if(m.get(tmp[0]) == null) {
                    List<String> newList = new ArrayList<>();
                    newList.add(sb.toString());
                    m.put(tmp[0], newList);
                } else {
                    m.get(tmp[0]).add(sb.toString());
                }
            }
            List<String> res = new ArrayList<>();
            for(Map.Entry<String, List<String>> x : m.entrySet()) {
                if(x.getKey().equals("ERROR")) {
                    res.addAll(x.getValue());
                }
            }
            for(Map.Entry<String, List<String>> x : m.entrySet()) {
                if(x.getKey().equals("WARN")) {
                    res.addAll(x.getValue());
                }
            }
            for(Map.Entry<String, List<String>> x : m.entrySet()) {
                if(x.getKey().equals("INFO")) {
                    res.addAll(x.getValue());
                }
            }
            port.submitStringArray(sCode, qCode, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
