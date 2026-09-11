import java.io.*;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class NdeLkzEo {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2208;
        String studentCode = "B23DCCN053";
        String qCode = "NdeLkzEo";
        try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String request = studentCode + ";" + qCode;
            out.write(request);
            out.newLine();
            out.flush();

            String text = in.readLine();
            LinkedHashMap<Character, Integer> m = new LinkedHashMap<>();
            for(int i = 0; i < text.length(); i++) {
                if(Character.isLetterOrDigit(text.charAt(i))) {
                    m.put(text.charAt(i), m.getOrDefault(text.charAt(i), 0) + 1);
                }
            }
            StringBuilder sb = new StringBuilder("");
            for(Map.Entry<Character, Integer> x : m.entrySet()) {
                if(x.getValue() > 1) {
                    sb.append(x.getKey());
                    sb.append(":");
                    sb.append(x.getValue());
                    sb.append(",");
                }
            }

            out.write(sb.toString());
            out.newLine();
            out.flush();

            in.close();
            out.close();
            socket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
