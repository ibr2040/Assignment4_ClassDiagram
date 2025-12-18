import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;
public class ModeratorInheritanceTest {
    @Test
    public void testSupportRegular() {
        Support s = new Support(
                "Pidor","pidor@gmail.com","+32142134344","Pidor","Pidor123456","PidorStreet","PidorGrad",
                "PidorSlav","929292922",null
        );
        assertNotEquals(null,s);
        assertEquals(null,s.getAdmin());
    }
    @Test
    public void testSupportAdmin() {
        Admin admin = new Admin();
        Support s = new Support(
                "Pidor","pidor@gmail.com","+32142134344","Pidor","Pidor123456","PidorStreet","PidorGrad",
                "PidorSlav","929292922",admin
        );
        assertEquals(admin,s.getAdmin());
    }
    @Test
    public void testMarketModRegular() {
        MarketModerator s = new MarketModerator(
                "Pidor","pidor@gmail.com","+32142134344","Pidor","Pidor123456","PidorStreet","PidorGrad",
                "PidorSlav","929292922",null
        );
        assertNotEquals(null,s);
        assertEquals(null,s.getAdmin());
    }
    @Test
    public void testMarketModAdmin() {
        Admin admin = new Admin();
        MarketModerator s = new MarketModerator(
                "Pidor","pidor@gmail.com","+32142134344","Pidor","Pidor123456","PidorStreet","PidorGrad",
                "PidorSlav","929292922",admin
        );
        assertEquals(admin,s.getAdmin());
    }
    @Test
    public void testAdminFunctions() {
        MarketModerator s = new MarketModerator(
                "Pidor", "pidor@gmail.com", "+32142134344", "Pidor", "Pidor123456", "PidorStreet", "PidorGrad",
                "PidorSlav", "929292922", new Admin()
        );
        MarketModerator s2 = new MarketModerator(
                "Pidor1", "pidor1@gmail.com", "+321421342344", "Pido3r", "Pidor1234564", "PidorStreet2", "1PidorGrad",
                "Pido2rSlav", "9292932922", null
        );
        s.suspendModer(s2);
        s.addArchiveLog("Ja Gandon");
        assertThrows(UnsupportedOperationException.class, () -> {s2.suspendModer(s);});
        assertThrows(UnsupportedOperationException.class,()->{s2.addArchiveLog("Pido3r");});
        assertEquals("Moderator suspended: " + s2.getEmployeeNumber()+" By: "+s.getFullName(),s.getAdmin().getArchiveLogs().get(1));
        assertEquals("Ja Gandon"+" By: "+s.getFullName(),s.getAdmin().getArchiveLogs().get(2));
    }
    @Test
    public void testAdminEdit(){
        MarketModerator s2 = new MarketModerator(
                "Pidor1", "pidor1@gmail.com", "+321421342344", "Pido3r", "Pidor1234564", "PidorStreet2", "1PidorGrad",
                "Pido2rSlav", "9292932922", null
        );
        MarketModerator s = new MarketModerator(
                "Pidor4444", "pidor@gmail.com", "+32142134344", "Pidor", "Pidor123456", "PidorStreet", "PidorGrad",
                "PidorSlav", "929292922", new Admin()
        );
        s.addArchiveLog("Ja Gandon");
        s.editArchiveLogs(0, "Ja Gandon1234");
        assertEquals("Ja Gandon1234"+" By: "+s.getFullName(),s.getAdmin().getArchiveLogs().get(0).toString());
        assertThrows(UnsupportedOperationException.class,()->{s2.editArchiveLogs(0, "Pido3r");});
    }
}
