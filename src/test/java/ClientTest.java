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

  // @Test
  // public void update_restaurant_true() {
  //   Restaurant testRestaurant = new Restaurant("Voodoo Donuts", "best donuts around", 1);
  //   testRestaurant.save();
  //   testRestaurant.update("Sesame Donuts", "best donuts around", 1);
  //   Restaurant savedRestaurant = Restaurant.all().get(0);
  //   assertEquals(savedRestaurant.getName(), "Sesame Donuts");
  // }
  //
  // @Test
  // public void restaurant_deletes_true() {
  //   Restaurant myRestaurant = new Restaurant("Voodoo Donuts", "best donuts around", 1);
  //   myRestaurant.deleteRestaurant(0);
  //   assertTrue(Restaurant.find(0) == null);
  // }

}
