import RMI.DataService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.io.*;
import java.net.*;
import java.rmi.*;

public class G9yEDzv5 {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        String sCode = "B23DCCN053";
        String qCode = "G9yEDzv5";

        try {
            Registry r = LocateRegistry.getRegistry(host, 1099);
            DataService dataService = (DataService) r.lookup("RMIDataService");

            String text = (String) dataService.requestData(sCode, qCode);
            String[] s = text.split(",");
            ArrayList<Double> a = new ArrayList<>();
            for(String x : s) {
                a.add(Double.parseDouble(x.trim()));
            }

            double avg = a.stream().mapToDouble(x -> x).average().getAsDouble();
            double std = Math.sqrt(a.stream().mapToDouble(x -> (x - avg) * (x - avg)).average().getAsDouble());

            Collections.sort(a);
            double pvi = a.get((int)Math.ceil(a.size() * 0.95) - 1);

            StringBuilder sb = new StringBuilder();
            sb.append("average=").append(String.format(Locale.US, "%.2f", avg)).append(";stddev=").append(String.format(Locale.US, "%.2f", std)).append(";p95=").append(String.format(Locale.US, "%.2f", pvi));
            dataService.submitData(sCode, qCode, sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
