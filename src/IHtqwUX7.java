import java.util.*;
import java.net.*;
import java.io.*;

public class IHtqwUX7 {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2208;
        String studentCode = "B23DCCN053";
        String qCode = "IHtqwUX7";

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
            String hashText = parts[1];

            LinkedHashMap<Character, Integer> m = new LinkedHashMap<>();
            for (char c : hashText.toCharArray()) {
                m.put(c, m.getOrDefault(c, 0) + 1);
            }
            int maxx = 0;
            char charMax = 'a';
            for (char c : m.keySet()) {
                if (maxx < m.get(c)) {
                    maxx = m.get(c);
                    charMax = c;
                }
            }
            StringBuilder res = new StringBuilder();
            res.append(reqId).append(";").append(charMax).append(":");
            for (int i = 0; i < hashText.length(); i++) {
                if (hashText.charAt(i) == charMax) {
                    res.append(i + 1).append(",");
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
