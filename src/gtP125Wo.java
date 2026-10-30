import RMI.CharacterService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.io.*;
import java.net.*;
import java.rmi.*;

public class gtP125Wo {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        String sCode = "B23DCCN053";
        String qCode = "gtP125Wo";

        try {
            Registry r = LocateRegistry.getRegistry(host, 1099);
            CharacterService characterService = (CharacterService) r.lookup("RMICharacterService");

            String text = characterService.requestCharacter(sCode, qCode);
            int n = Integer.parseInt(text.trim());
            int[] a = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] b = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < a.length; i++) {
                while(n >= a[i]) {
                    sb.append(b[i]);
                    n -= a[i];
                }
            }

            characterService.submitCharacter(sCode, qCode, sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
