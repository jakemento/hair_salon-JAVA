import java.util.Map;
import java.util.HashMap;
import static spark.Spark.*;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Stylist newStylist = new Stylist(name);
      newStylist.save();
      model.put ("stylists", Stylist.all());
      model.put("newStylist", newStylist);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    // get("/:id", (request, reponse) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("template", "templates/cuisine-form.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    //
    // post("/:id", (request, response) -> {
    //    HashMap<String, Object> model = new HashMap<String, Object>();
    //    Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
    //    model.put("cuisine", cuisine);
    //    model.put("template", "templates/cuisine.vtl");
    //    return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/restaurant-form/:id", (request, response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //     Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
    //     model.put("cuisine", cuisine);
    //     model.put("template", "templates/restaurant-form.vtl");
    //     return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/cuisines/:id", (request, response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //     String name = request.queryParams("name");
    //     String description = request.queryParams("description");
    //     int cuisine_Id = Integer.parseInt(request.params(":id"));
    //     Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
    //     model.put("cuisine", cuisine);
    //     Restaurant newRestaurant = new Restaurant(name,description,cuisine_Id);
    //     newRestaurant.save();
    //     model.put("template", "templates/cuisines.vtl");
    //     return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/cuisines/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //
    //   model.put("template", "templates/cuisines.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());


  }
}
