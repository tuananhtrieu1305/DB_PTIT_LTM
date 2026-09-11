import java.util.*;
import java.net.*;
import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class xxOjm1Ns {
    static void sendLine(GZIPOutputStream out, String s) throws Exception {
        out.write((s + "\n").getBytes());
        out.flush();
    }
    static String readLine(GZIPInputStream in) throws Exception {
        StringBuilder sb = new StringBuilder();
        int c;
        while((c = in.read()) != - 1 && c != '\n') {
            sb.append((char) c);
        }
        return sb.toString();
    }

    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2210;
        String sCode = "B23DCCN053";
        String qCode = "xxOjm1Ns";

        try {
            Socket socket = new Socket(host, port);
            GZIPInputStream in = new GZIPInputStream(socket.getInputStream());
            GZIPOutputStream out = new GZIPOutputStream(socket.getOutputStream(), true);

            String req = sCode + ";" + qCode;
            sendLine(out, req);

            String text = readLine(in);
            char[] s = text.toCharArray();
            Arrays.sort(s);
            String res = new String(s);

            sendLine(out, res);
            out.finish();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
