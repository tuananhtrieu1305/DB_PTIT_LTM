import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class tjHNOBOS {
    public static void main(String[] args) throws Exception {
        String sCode = "B23DCCN053";
        String qCode = "tjHNOBOS";
        try {
            ObjectService s = new ObjectService();
            SoapObjectService port = s.getSoapObjectServicePort();

            List<EmployeeY> employeeY = port.requestListEmployeeY(sCode, qCode);
            employeeY.sort((a, b) -> a.getStartDate().toGregorianCalendar().compareTo(b.getStartDate().toGregorianCalendar()));
            port.submitListEmployeeY(sCode, qCode, employeeY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
