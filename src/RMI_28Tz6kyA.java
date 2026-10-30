import RMI.DataService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.io.*;
import java.net.*;
import java.rmi.*;

public class RMI_28Tz6kyA {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        String sCode = "B23DCCN053";
        String qCode = "28Tz6kyA";

        try {
            Registry r = LocateRegistry.getRegistry(host, 1099);
            DataService dataService = (DataService) r.lookup("RMIDataService");

            int[] a = (int[]) dataService.requestData(sCode, qCode);
            List<Integer> res = new ArrayList<>();
            for(int i = 0; i < a.length; i++) {
                if(i == 0) {
                    if(a[i] > a[i + 1]) {
                        res.add(i + 1);
                    }
                } else if(i == a.length - 1) {
                    if(a[i] > a[i - 1]) {
                        res.add(i + 1);
                    }
                } else {
                    if(a[i] > a[i + 1] && a[i] > a[i - 1]) {
                        res.add(i + 1);
                    }
                }
            }

            dataService.submitData(sCode, qCode, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
