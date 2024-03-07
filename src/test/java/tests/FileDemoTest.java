package tests;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.GenApiSpec.loggingRequestSpec;
import static specs.GenApiSpec.loggingResponseSpec;

public class FileDemoTest {

    ClassLoader cl = FileDemoTest.class.getClassLoader();

    @Test
    void demoRestAssuredStringTest() throws IOException {

        InputStream requestFileIS = cl.getResourceAsStream("payloads/registerUserRequest.json");

        File responseFile = new File(cl.getResource("payloads/registerUserResponse.json").getFile());

        String expectedString = FileUtils.readFileToString(responseFile, "UTF-8").replaceAll("\\s", "");

        String responseString =
                given(loggingRequestSpec)
                        .body(requestFileIS)
                        .when()
                        .post("https://reqres.in/api/register")
                        .then()
                        .spec(loggingResponseSpec)
                        .extract()
                        .asString();

        assertThat(responseString).isEqualTo(expectedString);

    }


}
