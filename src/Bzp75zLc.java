import UDP.Customer;

import java.util.*;
import java.io.*;
import java.net.*;

public class Bzp75zLc {
    public static String normalizeSubString(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(name.charAt(0)));
        for(int i = 1; i < name.length(); i++) {
            sb.append(Character.toLowerCase(name.charAt(i)));
        }
        return sb.toString();
    }

    public static String normalizeName(String name) {
        String[] s = name.split("\\s+");
        StringBuilder sb = new StringBuilder();
        sb.append(s[s.length - 1].toUpperCase()).append(", ");
        for(int i = 0; i < s.length - 1; i++) {
            sb.append(normalizeSubString(s[i]));
            if(i != s.length - 2) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static String normalizeDate(String date) {
        String[] s = date.split("-");
        StringBuilder sb = new StringBuilder();
        sb.append(s[1]).append("/").append(s[0]).append("/").append(s[2]);
        return sb.toString();
    }

    public static String createUsername(String name) {
        String[] s = name.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length - 1; i++) {
            sb.append(Character.toLowerCase(s[i].charAt(0)));
        }
        sb.append(s[s.length - 1].toLowerCase());
        return sb.toString();
    }

    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2209;
        String sCode = "B23DCCN053";
        String qCode = "Bzp75zLc";

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ip = InetAddress.getByName(host);

            String req = ";" + sCode + ";" + qCode;
            byte[] data = req.getBytes();
            socket.send(new DatagramPacket(data, data.length, ip, port));

            byte[] buf = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String reqId = new String(packet.getData(), 0, 8);
            ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData(), 8, packet.getLength() - 8);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Customer customer = (Customer) ois.readObject();
            String tmp = customer.getName();
            customer.setName(normalizeName(tmp));
            customer.setDayOfBirth(normalizeDate(customer.getDayOfBirth()));
            customer.setUserName(createUsername(tmp));

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(customer);
            oos.flush();
            byte[] reqIdData = reqId.getBytes();
            byte[] objectData = bos.toByteArray();
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
