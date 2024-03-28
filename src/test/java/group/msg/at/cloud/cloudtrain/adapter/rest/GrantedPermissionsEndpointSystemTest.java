package group.msg.at.cloud.cloudtrain.adapter.rest;

import group.msg.at.cloud.cloudtrain.core.entity.GrantedPermission;
import group.msg.at.cloud.common.test.rest.RestAssuredSystemTestFixture;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

/**
 * System test that verifies that the REST endpoint works as expected.
 * <p>
 * Assumes that a remote server hosting the REST endpoint is up and running.
 * </p>
 */
public class GrantedPermissionsEndpointSystemTest {

    private static final RestAssuredSystemTestFixture fixture = new RestAssuredSystemTestFixture();

    @BeforeAll
    public static void onBeforeClass() {
        fixture.onBefore();
    }

    @AfterAll
    public static void onAfterClass() {
        fixture.onAfter();
    }

    @AfterEach
    public void onAfter() {
    }

    @Test
    void getWithProjectNameReturnsExpectedPermissions() {
        GrantedPermission[] permissions = given().log().body(true).auth().oauth2(fixture.getAccessToken())
                .accept(ContentType.JSON)
                .get("api/v1/grantedPermissions")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(GrantedPermission[].class);
        assertNotNull(permissions);
        assertNotEquals(0, permissions.length);
        assertNotNull(permissions[0].getPermission());
        assertFalse(permissions[0].getPermission().isEmpty());
    }
}
