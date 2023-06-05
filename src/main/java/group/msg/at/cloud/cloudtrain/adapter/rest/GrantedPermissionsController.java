package group.msg.at.cloud.cloudtrain.adapter.rest;

import group.msg.at.cloud.cloudtrain.core.boundary.GrantedPermissionManager;
import group.msg.at.cloud.cloudtrain.core.entity.GrantedPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/grantedPermissions")
@CrossOrigin
@SecurityRequirement(name = "oidc", scopes = {"openid", "microprofile-jwt"})
public class GrantedPermissionsController {

    @Autowired
    GrantedPermissionManager boundary;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "returns all permissions granted to the currently authenticated user")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "body contains array of all granted permissions",
                    content = @Content(schema = @Schema(implementation = GrantedPermission.class))
            )
    })
    public ResponseEntity<List<GrantedPermission>> getPermissionsByCurrentUser() {
        return ResponseEntity.ok(this.boundary.getGrantedPermissionsByCurrentUser());
    }
}
