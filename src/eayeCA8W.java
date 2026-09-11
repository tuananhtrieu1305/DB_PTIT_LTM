import java.util.*;
import java.io.*;
import java.net.*;

public class eayeCA8W {
    public static String normalize (String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(s.charAt(0)));
        for (int i = 1; i < s.length(); i++) {
            sb.append(Character.toLowerCase(s.charAt(i)));
        }
        return sb.toString();
    }

    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2208;
        String studentCode = "B23DCCN053";
        String qCode = "eayeCA8W";

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
            String[] words = parts[1].split("\\s+");

            StringBuilder res = new StringBuilder();
            res.append(reqId).append(";");
            for (int i = 0; i < words.length; i++) {
                res.append(normalize(words[i]));
                if (i != words.length - 1) {
                    res.append(" ");
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
