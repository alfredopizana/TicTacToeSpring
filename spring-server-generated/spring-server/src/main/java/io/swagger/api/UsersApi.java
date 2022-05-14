/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.27).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.User;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-13T04:30:20.933Z")

@Validated
@Api(value = "users", description = "the users API")
@RequestMapping(value = "/v1")
public interface UsersApi {

    @ApiOperation(value = "", nickname = "usersGet", notes = "", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Users found") })
    @RequestMapping(value = "/users",
        method = RequestMethod.GET)
    ResponseEntity<Void> usersGet();


    @ApiOperation(value = "", nickname = "usersIdDelete", notes = "", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Not Allowed") })
    @RequestMapping(value = "/users/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> usersIdDelete(@ApiParam(value = "",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "", nickname = "usersIdGet", notes = "", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "User found") })
    @RequestMapping(value = "/users/{id}",
        method = RequestMethod.GET)
    ResponseEntity<Void> usersIdGet(@ApiParam(value = "",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "", nickname = "usersIdPatch", notes = "", response = User.class, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "User updated", response = User.class) })
    @RequestMapping(value = "/users/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PATCH)
    ResponseEntity<User> usersIdPatch(@ApiParam(value = "",required=true) @PathVariable("id") Integer id,@ApiParam(value = "User object" ,required=true )  @Valid @RequestBody User user);


    @ApiOperation(value = "", nickname = "usersPost", notes = "", response = User.class, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "User created", response = User.class),
        @ApiResponse(code = 400, message = "Bad Request") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<User> usersPost(@ApiParam(value = "User object" ,required=true )  @Valid @RequestBody User user);

}
