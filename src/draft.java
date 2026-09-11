import java.util.*;
import java.io.*;
import java.net.*;

public class draft {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2207;
        String sCode = "B23DCCN053";
        String qCode = "5uNbZIvU";

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ip = InetAddress.getByName(host);

            String req = ";" + sCode + ";" + qCode;
            byte[] data = req.getBytes();
            socket.send(new DatagramPacket(data, data.length, ip, port));

            byte[] buf = new byte[4069];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, port);
            socket.receive(packet);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
