package it.polito.dp2.RNS.sol3.service.api;

import java.util.Date;
import it.polito.dp2.RNS.sol3.service.model.Entrance;
import it.polito.dp2.RNS.sol3.service.model.GenericPlace;
import it.polito.dp2.RNS.sol3.service.model.NewVehicle;
import java.net.URI;
import it.polito.dp2.RNS.sol3.service.model.Vehicle;
import it.polito.dp2.RNS.sol3.service.model.VehicleStatus;
import it.polito.dp2.RNS.sol3.service.model.VehicleType;
import it.polito.dp2.RNS.sol3.service.model.Vehicles;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/vehicles")
@Api(description = "the vehicles API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2019-08-28T10:57:56.578+02:00[Europe/Rome]")
public class VehiclesApi {

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "add a new vehicle", notes = "", response = NewVehicle.class, tags={ "vehicles (user)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "New vehicle created, return suggest path", response = NewVehicle.class),
        @ApiResponse(code = 400, message = "Bad request: the vehicle object was malformed", response = Void.class),
        @ApiResponse(code = 403, message = "Permission not granted: reason in response body", response = String.class),
        @ApiResponse(code = 404, message = "At least a place has not been found", response = Void.class),
        @ApiResponse(code = 409, message = "Conflict: vehicle already existed", response = Void.class)
    })
    public Response addVehicle(@Valid Entrance entrance) {
        return Response.ok().entity("magic!").build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "deletes a vehicle", notes = "Deletes a vehicle from the RNS system", response = Void.class, tags={ "vehicles (user)", "vehicles (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Vehicle successfully deleted", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden: vehicle is currently not in an OUT or INOUT gate", response = Void.class),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response deleteVehicle(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/destination")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves a vehicle's destination", notes = "Retrieves a vehicle's destination in the RNS system", response = GenericPlace.class, tags={ "vehicles (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicle's destination found", response = GenericPlace.class),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response getDestination(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/entrytime")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves a vehicle's entry time", notes = "Retrieves a vehicle's entry time in the RNS system", response = Date.class, tags={ "vehicles (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicle's entry time found", response = Date.class),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response getEntryTime(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/origin")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves a vehicle's origin", notes = "Retrieves a vehicle's origin in the RNS system", response = GenericPlace.class, tags={ "vehicles (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicle's origin found", response = GenericPlace.class),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response getOrigin(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/path")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves a vehicle's type", notes = "Retrieves a vehicle's suggested path in the RNS system", response = URI.class, responseContainer = "List", tags={ "vehicles (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicle's suggested path found", response = URI.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response getPath(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/position")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves a vehicle's position", notes = "Retrieves a vehicle's position in the RNS system", response = GenericPlace.class, tags={ "vehicles (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicle's position found", response = GenericPlace.class),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response getPosition(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/state")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves a vehicle's state", notes = "Retrieves a vehicle's state in the RNS system", response = VehicleStatus.class, tags={ "vehicles (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicle's state found", response = VehicleStatus.class),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response getState(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves a vehicle", notes = "Retrieves a vehicle in the RNS system", response = Vehicle.class, tags={ "vehicles (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicle found", response = Vehicle.class),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response getVehicle(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/type")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves a vehicle's type", notes = "Retrieves a vehicle's type in the RNS system", response = VehicleType.class, tags={ "vehicles (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicle's type found", response = VehicleType.class),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response getVehicleType(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves all vehicles", notes = "Retrieves a set containing all vehicles in the RNS system", response = Vehicles.class, tags={ "vehicles (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicles returned", response = Vehicles.class),
        @ApiResponse(code = 400, message = "Bad request: at least one invalid parameter", response = Void.class)
    })
    public Response getVehicles(@QueryParam("since")   @ApiParam("The entrance date/time since when vehicles have to be selected")  Date since,@QueryParam("types") @Size(min=0)   @ApiParam("The set of types of vehicles that have to be selected")  List<VehicleType> types,@QueryParam("state")   @ApiParam("The state of vehicles to be selected")  VehicleStatus state,@QueryParam("page")  @DefaultValue("0")  @ApiParam("The desired page result")  Integer page) {
        return Response.ok().entity("magic!").build();
    }

    @PUT
    @Path("/{id}/position")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "updates a vehicle's position", notes = "Updates a vehicle's position in the RNS system", response = URI.class, responseContainer = "List", tags={ "vehicles (user)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicle's position updated. Replying with suggested path towards destination", response = URI.class, responseContainer = "List"),
        @ApiResponse(code = 204, message = "Vehicle's position updated. Destination is not reachable anymore", response = Void.class),
        @ApiResponse(code = 400, message = "Bad request: destination is unknown or not reachable from the previous position", response = Void.class),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response setPosition(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id,@Valid URI body) {
        return Response.ok().entity("magic!").build();
    }

    @PUT
    @Path("/{id}/state")
    @Consumes({ "application/xml", "application/json" })
    @ApiOperation(value = "updates a vehicle's state", notes = "Updates a vehicle's state in the RNS system", response = Void.class, tags={ "vehicles (user)" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Vehicle's state updated", response = Void.class),
        @ApiResponse(code = 400, message = "New state is invalid", response = Void.class),
        @ApiResponse(code = 404, message = "Vehicle not found", response = Void.class)
    })
    public Response setState(@PathParam("id") @ApiParam("The vehicle&#39;s plate ID of interest") String id,@Valid String body) {
        return Response.ok().entity("magic!").build();
    }
}
