/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.27).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Game;
import io.swagger.model.Movement;
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
@Api(value = "movements", description = "the movements API")
@RequestMapping(value = "/v1")
public interface MovementsApi {

    @ApiOperation(value = "", nickname = "movementsGet", notes = "get all movements", tags={ "movements", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Not Allowed") })
    @RequestMapping(value = "/movements",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Void> movementsGet();


    @ApiOperation(value = "", nickname = "movementsIdDelete", notes = "", tags={ "movements", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Not Allowed") })
    @RequestMapping(value = "/movements/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> movementsIdDelete(@ApiParam(value = "",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "", nickname = "movementsIdGet", notes = "get a movement by Id", response = Movement.class, tags={ "movements", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Movement Found", response = Movement.class),
        @ApiResponse(code = 405, message = "Not Allowed") })
    @RequestMapping(value = "/movements/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Movement> movementsIdGet(@ApiParam(value = "",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "", nickname = "movementsPost", notes = "Add a new movement", response = Game.class, tags={ "movements", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Game Created", response = Game.class),
        @ApiResponse(code = 400, message = "Bad Request") })
    @RequestMapping(value = "/movements",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Game> movementsPost();

}
