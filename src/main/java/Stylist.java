import org.sql2o.*;
import java.util.List;

public class Cuisine {
  private int cuisine_id;
  private String type;

  public Cuisine (String type) {
    this.type = type;
    //this.cuisine_id = cuisine_id;
  }

  public int getId() {
    return cuisine_id;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object otherCuisine){
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()) &&
        this.getId() == newCuisine.getId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cuisine(type) VALUES (:type)";
      this.cuisine_id = (int) con.createQuery(sql, true)
         .addParameter("type", this.type)
         .executeUpdate()
         .getKey();
     }
  }


  public static List<Cuisine> all() {
    String sql = "SELECT cuisine_id, type FROM cuisine";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);

    }
  }

  public void update(String type) {
    this.type = type;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE cuisine SET type = :type WHERE cuisine_id = :cuisine_id";
      con.createQuery(sql)
      .addParameter("cuisine_id", cuisine_id)
      .addParameter("type", type)
      .executeUpdate();
      }

  }
    public void deleteCuisine(int cuisine_id) {
      this.cuisine_id = cuisine_id;
      try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM cuisine WHERE cuisine_id = :cuisine_id";
        con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .executeUpdate();
      }
    }

    public static Cuisine find(int cuisine_id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM Cuisine where cuisine_id=:cuisine_id";
        return con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .executeAndFetchFirst(Cuisine.class);
      }
    }

    public List<Restaurant> getRestaurants() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM restaurants where cuisine_id=:cuisine_id";
        return con.createQuery(sql)
          .addParameter("cuisine_id", this.cuisine_id)
          .executeAndFetch(Restaurant.class);
        }
      }
}
