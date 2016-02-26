import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String name;
  private int stylist_id;

  public Client (String name, int stylist_id) {
    this.name = name;
    this.stylist_id = stylist_id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }



  public int getStylistId() {
    return stylist_id;
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
        this.getId() == newClient.getId() && this.getStylistId() == (newClient.getStylistId());
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, stylist_id) VALUES (:name, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate()
        .getKey();
    }
  }



  public static List<Client> all() {
    String sql = "SELECT id, name, stylist_id FROM clients";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }
//
//   public void update(String name, String description, int cuisine_id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "UPDATE restaurants SET id = :id, name = :name, description = :description, cuisine_id = :cuisine_id WHERE id = :id";
//       con.createQuery(sql)
//       .addParameter("id", id)
//       .addParameter("name", name)
//       .addParameter("description", description)
//       .addParameter("cuisine_id", cuisine_id)
//       .executeUpdate();
//       }
//   }
//
//   public static void deleteRestaurant(int id) {
//     String sql = "DELETE FROM restaurants WHERE id=:id";
//     try(Connection con = DB.sql2o.open()) {
//       con.createQuery(sql)
//       .addParameter("id", id)
//       .executeUpdate();
//     }
//   }
//
//   public static Restaurant find(int id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM restaurants where id=:id";
//       Restaurant restaurant = con.createQuery(sql)
//       .addParameter("id", id)
//       .executeAndFetchFirst(Restaurant.class);
//       return restaurant;
//     }
//   }
//
//   public List<Cuisine> getCuisines() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM cuisine where cuisine_id=:cuisine_id";
//       return con.createQuery(sql)
//         .addParameter("cuisine_id", cuisine_id)
//         .executeAndFetch(Cuisine.class);
//       }
//     }
}
