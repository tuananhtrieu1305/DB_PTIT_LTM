import RMI.ObjectService;
import RMI.TicketSla;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.io.*;
import java.net.*;
import java.rmi.*;

public class Ydh8mQVE {
    static boolean calcBreached(String priority, int openedHoursAgo) {
        return priority.equals("CRITICAL") && openedHoursAgo > 2 ||
                priority.equals("HIGH") && openedHoursAgo > 8 ||
                priority.equals("MEDIUM") && openedHoursAgo > 24 ||
                priority.equals("LOW") && openedHoursAgo > 72;
    }
    static String calcAction(String priority, boolean breached, int openedHoursAgo) {
        if(!breached) {
            return "MONITOR";
        }
        if(priority.equals("CRITICAL") || openedHoursAgo > 96) {
            return "ESCALATE_L2";
        }
        return "ESCALATE_L1";
    }

    static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        String sCode = "B23DCCN053";
        String qCode = "Ydh8mQVE";

        try {
            Registry r = LocateRegistry.getRegistry(host, 1099);
            ObjectService objectService = (ObjectService) r.lookup("RMIObjectService");

            TicketSla ticketSla = (TicketSla) objectService.requestObject(sCode, qCode);
            ticketSla.setBreached(calcBreached(ticketSla.getPriority(), ticketSla.getOpenedHoursAgo()));
            ticketSla.setAction(calcAction(ticketSla.getPriority(), ticketSla.isBreached(), ticketSla.getOpenedHoursAgo()));

            objectService.submitObject(sCode, qCode, ticketSla);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
