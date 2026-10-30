import ptit.dblab.judge.network.server.soap.*;
import java.util.*;

public class MdtafDuN {
    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "MdtafDuN";

        try {
            CharacterService s = new CharacterService();
            SoapCharacterService port = s.getSoapCharacterServicePort();

            List<Integer> list = port.requestCharacter(sCode, qCode);
            int n1 = list.get(0);
            int n2 = list.size();
            List<Integer> res = new ArrayList<>();
            for(int x : list) {
                res.add(x + (n1 % n2));
            }

            port.submitCharacterCharArray(sCode, qCode, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
