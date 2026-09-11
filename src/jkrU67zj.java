import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.net.*;
import java.io.*;

public class jkrU67zj {
    static void readFully (SocketChannel channel, ByteBuffer buffer) throws Exception {
        while (buffer.hasRemaining()) {
            int n = channel.read(buffer);
            if (n == -1) {
                return;
            }
        }
    }
    static String readFrame (SocketChannel channel) throws Exception {
        ByteBuffer lengthBuffer = ByteBuffer.allocate(4);
        readFully(channel, lengthBuffer);
        lengthBuffer.flip();

        int length = lengthBuffer.getInt();
        ByteBuffer payloadBuffer = ByteBuffer.allocate(length);
        readFully(channel, payloadBuffer);
        payloadBuffer.flip();

        byte[] data = new byte[length];
        payloadBuffer.get(data);

        return new String (data, StandardCharsets.UTF_8);
    }
    static void sendFrame (SocketChannel channel, String message) throws Exception {
        byte[] data = message.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.allocate(4 + data.length);

        buffer.putInt(data.length);
        buffer.put(data);
        buffer.flip();

        while(buffer.hasRemaining()) {
            channel.write(buffer);
        }
    }

    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2211;
        String studentCode = "B23DCCN053";
        String qCode = "jkrU67zj";

        try (SocketChannel channel = SocketChannel.open()) {
            channel.connect(new InetSocketAddress(host, port));

            String req = studentCode + ";" + qCode;
            sendFrame(channel, req);

            StringBuilder httpReq = new StringBuilder();
            for(int i = 0; i < 2; i++) {
                String part = readFrame(channel);
                httpReq.append(part);
            }
            String http = httpReq.toString();
            String[] lines = http.substring(1, http.length() - 1).split(",");
            StringBuilder res = new StringBuilder();
            for(String line : lines) {
                String tmp = line.substring(1, line.length() - 1);
                if (tmp.startsWith("event")) {
                    res.append("event=").append(tmp.substring(8)).append(";");
                } else if (tmp.startsWith("user")) {
                    res.append("user=").append(tmp.substring(7)).append(";");
                } else if (tmp.startsWith("ok")) {
                    tmp += line.substring(line.length() - 1);
                    int cur = tmp.substring(4).equals("true") ? 1 : 0;
                    res.append("ok=").append(cur);
                }
            }

            sendFrame(channel, res.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
