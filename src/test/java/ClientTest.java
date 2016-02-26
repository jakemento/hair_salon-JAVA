import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Client.all().size(), 0);
  }

  @Test
  public void all_savesIntoDatabase_true() {
    Client myClient = new Client("John", 1);
    myClient.save();
    assertEquals(Client.all().get(0).getName(), "John");
  }

  @Test
  public void find_findsClientsInDatabase_true() {
    Client myClient = new Client ("John", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

  @Test
  public void update_client_true() {
    Client testClient = new Client("John", 1);
    testClient.save();
    testClient.update("James", 1);
    Client savedClient = Client.all().get(0);
    assertEquals(savedClient.getName(), "James");
  }

}
