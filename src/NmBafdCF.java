import java.util.*;
import java.io.*;
import java.net.*;

public class NmBafdCF {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2207;
        String studentCode = "B23DCCN053";
        String qCode = "NmBafdCF";

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
            String[] list = parts[1].split(",");
            TreeSet<Integer> set = new TreeSet<>();
            for (String s : list) {
                set.add(Integer.parseInt(s));
            }
            StringBuilder res = new StringBuilder();
            res.append(reqId).append(";").append(set.last()).append(",").append(set.first());
            data = res.toString().getBytes();
            socket.send(new DatagramPacket(data, data.length, ip, port));

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
