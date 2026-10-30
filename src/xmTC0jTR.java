import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class xmTC0jTR {
    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "xmTC0jTR";
        try {
            CharacterService s = new CharacterService();
            SoapCharacterService port = s.getSoapCharacterServicePort();

            String text = port.requestString(sCode, qCode);
            String res = (new StringBuilder(text).reverse()).toString();
            port.submitString(sCode, qCode, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
