import RMI.ByteService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.io.*;
import java.net.*;

public class Tx4frRA4 {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        String sCode = "B23DCCN053";
        String qCode = "Tx4frRA4";

        try {
            Registry r = LocateRegistry.getRegistry(host, 1099);
            ByteService byteService = (ByteService) r.lookup("RMIByteService");

            byte[] a = byteService.requestData(sCode, qCode);
            int[] cnt = new int[256];
            for(byte x : a) {
                cnt[x & 255]++;
            }
            byte min = a[0];
            for(byte x : a) {
                if(cnt[x & 255] < cnt[min & 255]) {
                    min = x;
                }
            }

            byteService.submitData(sCode, qCode, new byte[]{min, (byte) cnt[min & 255]});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
