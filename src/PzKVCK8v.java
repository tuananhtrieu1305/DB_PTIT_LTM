import RMI.ByteService;

import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.net.*;
import java.rmi.*;

public class PzKVCK8v {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        String sCode = "B23DCCN053";
        String qCode = "PzKVCK8v";

        try {
            Registry r = LocateRegistry.getRegistry(host, 1099);
            ByteService byteService = (ByteService) r.lookup("RMIByteService");

            byte[] a = byteService.requestData(sCode, qCode);

            int[] cnt = new int[256];
            for(byte x : a) {
                cnt[x & 255]++;
            }
            byte max = a[0];
            for(byte x : a) {
                if(cnt[x & 255] > cnt[max]) {
                    max = x;
                }
            }
            byteService.submitData(sCode, qCode, new byte[]{max, (byte) cnt[max & 255]});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
