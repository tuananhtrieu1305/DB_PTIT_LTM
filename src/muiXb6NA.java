import java.io.*;
import java.net.*;
import java.util.*;

public class muiXb6NA {
    public static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2208;
        String studentCode = "B23DCCN053";
        String qCode = "muiXb6NA";

        try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String request = studentCode + ";" + qCode;
            out.write(request);
            out.newLine();
            out.flush();

            String domains = in.readLine();
            StringBuilder sb = new StringBuilder();
            String[] input = domains.split(",");
            for(String x : input) {
                x = x.trim();
                if(x.endsWith(".edu")) {
                    if(!sb.isEmpty()) {
                        sb.append(", ");
                    }
                    sb.append(x);
                }
            }

            out.write(sb.toString());
            out.newLine();
            out.flush();

            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
