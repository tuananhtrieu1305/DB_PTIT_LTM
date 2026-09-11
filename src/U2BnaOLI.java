import java.util.*;
import java.io.*;
import java.net.*;

public class U2BnaOLI {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2206;
        String studentCode = "B23DCCN053";
        String qCode = "U2BnaOLI";

        try {
            Socket socket = new Socket(host, port);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            String req = studentCode + ";" + qCode;
            out.write(req.getBytes());
            out.flush();

            byte[] buffer = new byte[1024];
            int byteRead = in.read(buffer);
            String text = new String(buffer, 0, byteRead);

            ArrayList<Integer> a = new ArrayList<>();
            String[] intList = text.split(",");
            for(String x : intList) {
                a.add(Integer.parseInt(x));
            }
            LinkedHashMap<Integer, Integer> m = new LinkedHashMap<>();
            for(int i = 0; i < a.size(); i++) {
                m.put(a.get(i), i);
            }
            Collections.sort(a);
            String res = a.get(a.size() - 2) + "," + m.get(a.get(a.size() - 2));

            out.write(res.getBytes());
            out.flush();

            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
