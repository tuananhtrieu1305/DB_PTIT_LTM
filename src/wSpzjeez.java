import java.util.*;
import java.net.*;
import java.io.*;

public class wSpzjeez {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2206;

        String studentCode = "B23DCCN053";
        String qCode = "wSpzjeez";

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
            Collections.sort(a);
            int res = Integer.MAX_VALUE, s1 = 0, s2 = 0;
            for(int i = 1; i < a.size(); i++) {
                int tmp = a.get(i) - a.get(i - 1);
                if(tmp <= res) {
                    res = tmp;
                    s1 = a.get(i - 1);
                    s2 = a.get(i);
                }
            }
            String result = res + "," + s1 + "," + s2;

            out.write(result.getBytes());
            out.flush();

            in.close();
            out.close();
            socket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
