import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Stylist list:");
  }


  @Test
  public void StylistIsDisplayedTest() {
    goTo("http://localhost:4567/");
    fill("#stylistName").with("Jimmy");
    submit(".btn");
    assertThat(pageSource()).contains("Jimmy");
  }

  // @Test
  // public void taskIsDisplayedTest() {
  //   goTo("http://localhost:4567/tasks/new");
  //   fill("#description").with("Mow the lawn");
  //   submit(".btn");
  //   click("a", withText("View tasks"));
  //   assertThat(pageSource()).contains("Mow the lawn");
  // }
  // @Test
  // public void taskIsDisplayedTest() {
  //   goTo("http://localhost:4567/tasks/new");
  //   fill("#description").with("Mow the lawn");
  //   submit(".btn");
  //   click("a", withText("View tasks"));
  //   assertThat(pageSource()).contains("Mow the lawn");
  // }
  //
  // @Test
  // public void multipleTasksAreDisplayedTest() {
  //   goTo("http://localhost:4567/tasks/new");
  //   fill("#description").with("Mow the lawn");
  //   submit(".btn");
  //   goTo("http://localhost:4567/tasks/new");
  //   fill("#description").with("Buy groceries");
  //   submit(".btn");
  //   click("a", withText("View tasks"));
  //   assertThat(pageSource()).contains("Mow the lawn");
  //   assertThat(pageSource()).contains("Buy groceries");
  // }
  //
  // @Test
  // public void taskShowPageDisplaysDescription() {
  //   goTo("http://localhost:4567/tasks/new");
  //   fill("#description").with("Do the dishes");
  //   submit(".btn");
  //   click("a", withText("View tasks"));
  //   click("a", withText("Do the dishes"));
  //   assertThat(pageSource()).contains("Do the dishes");
  // }
  //
  // @Test
  // public void taskNotFoundMessageShown() {
  //   goTo("http://localhost:4567/tasks/999");
  //   assertThat(pageSource()).contains("Task not found");
  // }
}
