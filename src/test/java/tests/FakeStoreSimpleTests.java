package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static specs.GenApiSpec.genRequestSpec;

public class FakeStoreSimpleTests {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    void getProduct1() {

        given(genRequestSpec)
                .when()
                .get("https://fakestoreapi.com/products/1")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("title", is("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"));

    }

    @Test
    void deleteProduct6() {

        given(genRequestSpec)
                .when()
                .delete("https://fakestoreapi.com/products/6")
                .then()
                .statusCode(200)
                .body("id", is(6))
                .body("title", is("Solid Gold Petite Micropave "));

    }


}
