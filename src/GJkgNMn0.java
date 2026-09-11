import UDP.Student;

import java.util.*;
import java.io.*;
import java.net.*;

public class GJkgNMn0 {
    public static String normalizeName (String s) {
        String[] list = s.split("\\s+");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            String x = list[i];
            StringBuilder sb = new StringBuilder();
            sb.append(Character.toUpperCase(x.charAt(0)));
            for (int j = 1; j < x.length(); j++) {
                sb.append(Character.toLowerCase(x.charAt(j)));
            }
            res.append(sb);
            if(i != list.length - 1) {
                res.append(" ");
            }
        }
        return res.toString();
    }
    public static String createEmail (String name) {
        String[] s = name.split("\\s+");
        StringBuilder sb = new StringBuilder();
        sb.append(s[s.length - 1].toLowerCase());
        for(int i = 0; i < s.length - 1; i++) {
            sb.append(Character.toLowerCase(s[i].charAt(0)));
        }
        sb.append("@ptit.edu.vn");
        return sb.toString();
    }

    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2209;
        String studentCode = "B23DCCN053";
        String qCode = "GJkgNMn0";

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ip = InetAddress.getByName(host);

            String req = ";" + studentCode + ";" + qCode;
            byte[] data = req.getBytes();
            socket.send(new DatagramPacket(data, data.length, ip, port));

            byte[] buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String reqId = new String(packet.getData(), 0, 8);
            ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData(), 8, packet.getLength() - 8);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Student student = (Student) ois.readObject();

            student.setName(normalizeName(student.getName()));
            student.setEmail(createEmail(student.getName()));

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(student);
            oos.flush();

            byte[] objectData = bos.toByteArray();
            byte[] reqIdData = reqId.getBytes();
            byte[] sendData = new byte[8 + objectData.length];
            System.arraycopy(reqIdData, 0, sendData, 0, 8);
            System.arraycopy(objectData, 0, sendData, 8, objectData.length);

            socket.send(new DatagramPacket(sendData, sendData.length, ip, port));
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
