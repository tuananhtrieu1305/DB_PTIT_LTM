import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;
import java.net.*;

public class Cgbh358N {
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

        return new String(data, StandardCharsets.UTF_8);
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
        String stutdentCode = "B23DCCN053";
        String qCode = "Cgbh358N";

        try (SocketChannel channel = SocketChannel.open()) {
            channel.connect(new InetSocketAddress(host, port));

            String req = stutdentCode + ";" + qCode;
            sendFrame(channel, req);

            StringBuilder httpReq = new StringBuilder();
            for(int i = 0; i < 3; i++) {
                String part = readFrame(channel);
                httpReq.append(part);
            }
            String http = httpReq.toString();

            String[] lines = http.split("\r\n");
            String[] firstLine = lines[0].split(" ");
            String method = firstLine[0];
            String path = firstLine[1];
            String reqHost = "";
            for(String line : lines) {
                if(line.toLowerCase().startsWith("host:")) {
                    reqHost = line.substring(5).trim();
                    break;
                }
            }
            String res = method + ";" + path + ";" + reqHost;
            sendFrame(channel, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
