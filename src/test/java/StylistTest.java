import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist myStylist = new Stylist("Bob");
    myStylist.save();
    assertEquals(Stylist.all().get(0).getName(), "Bob");
  }

  @Test
  public void find_findsStylistsInDatabase_true() {
    Stylist myStylist = new Stylist ("Bob");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }


  @Test
  public void update_Stylist_true() {
    Stylist testStylist = new Stylist("Bob");
    testStylist.save();
    testStylist.update("Jim");
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(savedStylist.getName(), "Jim");
  }


  @Test
 public void getClients_retrievesALlClientsFromDatabase_clientsList() {
   Stylist myStylist = new Stylist("James");
   myStylist.save();
   Client firstClient = new Client("John", myStylist.getId());
   firstClient.save();
   Client secondClient = new Client("Pablo", myStylist.getId());
   secondClient.save();
   Client[] clients = new Client[] { firstClient, secondClient };
   assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }
}
