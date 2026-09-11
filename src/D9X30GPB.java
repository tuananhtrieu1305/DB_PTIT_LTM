import java.util.*;
import java.io.*;
import java.net.*;

public class D9X30GPB {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2207;
        String studentCode = "B23DCCN053";
        String qCode = "D9X30GPB";

        try {
            Socket socket = new Socket(host, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String req = studentCode + ";" + qCode;
            out.writeUTF(req);
            out.flush();
            int a = in.readInt();
            int b = in.readInt();

            int res1 = a + b;
            int res2 = a * b;
            out.writeInt(res1);
            out.writeInt(res2);
            out.flush();

            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
