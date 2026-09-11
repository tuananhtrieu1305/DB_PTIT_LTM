import java.util.*;
import java.net.*;
import java.io.*;

public class hx2drKBR {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2207;
        String studentCode = "B23DCCN053";
        String qCode = "hx2drKBR";

        try {
            Socket socket = new Socket(host, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String req = studentCode + ";" + qCode;
            out.writeUTF(req);
            out.flush();

            String text = in.readUTF();
            int s = in.readInt();

            StringBuilder sb = new StringBuilder();
            for(char c : text.toCharArray()) {
                if(c >= 'A' && c <= 'Z') {
                    sb.append((char) ('A' + (c - 'A' - s + 26) % 26));
                } else if (c >= 'a' && c <= 'z') {
                    sb.append((char) ('a' + (c - 'a' - s + 26) % 26));
                } else {
                    sb.append(c);
                }
            }
            out.writeUTF(sb.toString());
            out.flush();

            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
