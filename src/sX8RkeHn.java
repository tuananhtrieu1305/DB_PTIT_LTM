import TCP.Customer;

import java.util.*;
import java.io.*;
import java.net.*;

public class sX8RkeHn {
    public static String normalizeName (String name) {
        String[] s = name.split("\\s+");
        StringBuilder sb = new StringBuilder();
        sb.append(s[s.length - 1].toUpperCase());
        sb.append(", ");
        for(int i = 0; i < s.length - 1; i++) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Character.toUpperCase(s[i].charAt(0)));
            sb2.append(s[i].substring(1, s[i].length()).toLowerCase());
            if(i != s.length - 2) {
                sb.append(sb2.toString() + " ");
            } else {
                sb.append(sb2.toString());
            }
        }
        return sb.toString();
    }
    public static String normalizeDate (String date) {
        String[] s = date.split("-");
        return s[1] + "/" + s[0] + "/" + s[2];
    }
    public static String normalizeUsername (String name) {
        String[] s = name.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if(i == s.length - 1) {
                sb.append(s[i].toLowerCase());
            } else {
                sb.append(Character.toLowerCase(s[i].charAt(0)));
            }
        }
        return sb.toString();
    }

    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2209;
        String studentCode = "B23DCCN053";
        String qCode = "sX8RkeHn";

        try {
            Socket socket = new Socket(host, port);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            String req = studentCode + ";" + qCode;
            out.writeObject(req);
            out.flush();

            Customer customer = (Customer) in.readObject();
            String tmp = customer.getName();
            customer.setName(normalizeName(tmp));
            customer.setDayOfBirth(normalizeDate(customer.getDayOfBirth()));
            customer.setUserName(normalizeUsername(tmp));

            out.writeObject(customer);
            out.flush();

            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
