import RMI.CharacterService;

import java.io.*;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.rmi.*;

public class vuBx9RWV {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        String sCode = "B23DCCN053";
        String qCode = "vuBx9RWV";

        try {
            Registry r = LocateRegistry.getRegistry(host, 1099);
            CharacterService characterService = (CharacterService) r.lookup("RMICharacterService");

            String s = characterService.requestCharacter(sCode, qCode);
            String res = new URI(null, null, s, null).toASCIIString();

            characterService.submitCharacter(sCode, qCode, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
