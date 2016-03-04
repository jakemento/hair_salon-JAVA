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
      String stylistName = request.queryParams("stylistName");
      Stylist newStylist = new Stylist(stylistName);
      newStylist.save();
      List<Stylist> stylistList = newStylist.all();
      model.put("stylists", stylistList);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      List<Client> clients = stylist.getClients();
      model.put("stylist", stylist);
      model.put("clients", clients);
      model.put("template", "templates/client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id", (request, response) -> {
     HashMap<String, Object> model = new HashMap<String, Object>();
     Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylist_id")));
     int id = Integer.parseInt(request.queryParams("stylist_id"));
     String name = request.queryParams("name");
     Client newClient = new Client(name, id);
     newClient.save();
     List<Client> clients = stylist.getClients();
     model.put("stylist", stylist);
     model.put("clients", clients);
     model.put("template", "templates/client-form.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());


   post("/:id/deleteClient", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      Client client = Client.find(id);
      client.delete();
      response.redirect("/");
      return null;
    });

    post("/:id/deleteStylist", (request, response) -> {
       int id = Integer.parseInt(request.params("id"));
       Stylist stylist = Stylist.find(id);
       stylist.delete();
       response.redirect("/");
       return null;
     });

  }
}
