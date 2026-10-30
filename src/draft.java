import RMI.ByteService;
import RMI.DataService;
import RMI.ObjectService;
import RMI.TicketSla;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.net.*;
import java.io.*;

public class draft {
    public static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        String sCode = "B23DCCN053";
        String qCode = "Ydh8mQVE";
        try {
            Registry r = LocateRegistry.getRegistry(host, 1099);
            ObjectService objectService = (ObjectService) r.lookup("RMIObjectService");

            TicketSla ticketSla = (TicketSla) objectService.requestObject(sCode, qCode);
            int openH = ticketSla.getOpenedHoursAgo();
            String priority = ticketSla.getPriority();
            if((priority.equals("CRITICAL") && openH > 2)
                || (priority.equals("HIGH") && openH > 8)
                    || (priority.equals("MEDIUM") && openH > 24)
                    || (priority.equals("LOW") && openH > 72)
            ) {
                ticketSla.setBreached(true);
            }
            if(!ticketSla.isBreached()) {
                ticketSla.setAction("MONITOR");
            } else {
                if(priority.equals("CRITICAL") || openH > 96) {
                    ticketSla.setAction("ESCALATE_L2");
                } else {
                    ticketSla.setAction("ESCALATE_L1");
                }
            }

            objectService.submitObject(sCode, qCode, ticketSla);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}