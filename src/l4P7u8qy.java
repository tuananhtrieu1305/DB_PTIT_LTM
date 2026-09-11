import TCP.Laptop;

import java.util.*;
import java.net.*;
import java.io.*;

public class l4P7u8qy {
    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2209;
        String studentCode = "B23DCCN053";
        String qCode = "l4P7u8qy";

        try {
            Socket socket = new Socket(host, port);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            String req = studentCode + ";" + qCode;
            out.writeObject(req);
            out.flush();

            Laptop laptop = (Laptop) in.readObject();
            String[] words = laptop.getName().split("\\s+");
            String stmp = words[0];
            words[0] = words[words.length - 1];
            words[words.length - 1] = stmp;
            laptop.setName(String.join(" ", words));

            int quantiy = laptop.getQuantity();
            int tmp = 0;
            while(quantiy > 0) {
                tmp = tmp * 10 + quantiy % 10;
                quantiy /= 10;
            }
            laptop.setQuantity(tmp);

            out.writeObject(laptop);
            out.flush();

            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
