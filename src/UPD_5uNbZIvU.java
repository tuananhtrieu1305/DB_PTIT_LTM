import java.util.*;
import java.io.*;
import java.net.*;

public class UPD_5uNbZIvU {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2207;
        String studentCode = "B23DCCN053";
        String qCode = "5uNbZIvU";

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ip = InetAddress.getByName(host);

            String req = ";" + studentCode + ";" + qCode;
            byte[] data = req.getBytes();
            socket.send(new DatagramPacket(data, data.length, ip, port));

            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String text = new String(packet.getData(), 0, packet.getLength());

            String[] parts = text.split(";");
            String reqId = parts[0];
            int n = Integer.parseInt(parts[1]);
            HashSet<Integer> set = new HashSet<>();
            for (String x : parts[2].split(",")) {
                set.add(Integer.parseInt(x));
            }
            StringBuilder res = new StringBuilder();
            res.append(reqId).append(";");
            for (int i = 1; i <= n; i++) {
                if(!set.contains(i)) {
                    res.append(i);
                    if(i != n) {
                        res.append(",");
                    }
                }
            }

            data = res.toString().getBytes();
            socket.send(new DatagramPacket(data, data.length, ip, port));

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
