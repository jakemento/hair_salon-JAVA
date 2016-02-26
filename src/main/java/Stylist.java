import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String name;

  public Stylist (String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
//
  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
        this.getId() == newStylist.getId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
         .addParameter("name", this.name)
         .executeUpdate()
         .getKey();
     }
  }


  public static List<Stylist> all() {
    String sql = "SELECT id, name FROM stylists";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);

    }
  }


  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id=:id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
    }
  }

//
  public void update(String name) {
    this.name = name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .addParameter("name", name)
      .executeUpdate();
      }
//
//   }
//     public void deleteCuisine(int cuisine_id) {
//       this.cuisine_id = cuisine_id;
//       try(Connection con = DB.sql2o.open()) {
//         String sql = "DELETE FROM cuisine WHERE cuisine_id = :cuisine_id";
//         con.createQuery(sql)
//         .addParameter("cuisine_id", cuisine_id)
//         .executeUpdate();
//       }
//     }
//

//     public List<Restaurant> getRestaurants() {
//       try(Connection con = DB.sql2o.open()) {
//         String sql = "SELECT * FROM restaurants where cuisine_id=:cuisine_id";
//         return con.createQuery(sql)
//           .addParameter("cuisine_id", this.cuisine_id)
//           .executeAndFetch(Restaurant.class);
//         }
      }
}
