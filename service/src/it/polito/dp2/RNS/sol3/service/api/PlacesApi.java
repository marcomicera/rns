package it.polito.dp2.RNS.sol3.service.api;

import it.polito.dp2.RNS.sol3.service.model.GateType;
import it.polito.dp2.RNS.sol3.service.model.Gates;
import it.polito.dp2.RNS.sol3.service.model.GenericPlace;
import it.polito.dp2.RNS.sol3.service.model.GenericPlaces;
import it.polito.dp2.RNS.sol3.service.model.ParkingAreas;
import it.polito.dp2.RNS.sol3.service.model.RoadSegments;
import java.net.URI;
import it.polito.dp2.RNS.sol3.service.model.Vehicles;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/places")
@Api(description = "the places API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2019-08-28T10:57:56.578+02:00[Europe/Rome]")
public class PlacesApi {

    @GET
    @Path("/{id}/capacity")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves the capacity of a place", notes = "Retrieves the capacity of a place in the RNS system", response = Integer.class, tags={ "places (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Place capacity found", response = Integer.class),
        @ApiResponse(code = 404, message = "Place not found", response = Void.class)
    })
    public Response getCapacity(@PathParam("id") @ApiParam("The place ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/gates")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves all gates", notes = "Retrieves a set containing all gates in the RNS system", response = Gates.class, tags={ "places (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Gates returned", response = Gates.class),
        @ApiResponse(code = 400, message = "Bad request: at least one invalid parameter", response = Void.class)
    })
    public Response getGates(@QueryParam("type")   @ApiParam("The ID prefix for selecting places")  GateType type,@QueryParam("page")  @DefaultValue("0")  @ApiParam("The desired page result")  Integer page) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/nextPlaces")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves all places to which this place is connected", notes = "Retrieves the set of all places to which this place is connected", response = URI.class, responseContainer = "List", tags={ "places (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Next places found", response = URI.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Place not found", response = Void.class)
    })
    public Response getNextPlaces(@PathParam("id") @ApiParam("The place ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/parkingAreas")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves all parking areas", notes = "Retrieves a set containing all parking areas in the RNS system", response = ParkingAreas.class, tags={ "places (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Parking areas returned", response = ParkingAreas.class),
        @ApiResponse(code = 400, message = "Bad request: at least one invalid parameter", response = Void.class)
    })
    public Response getParkingAreas(@QueryParam("services") @Size(min=0)   @ApiParam("The set of services that parking areas must have")  List<String> services,@QueryParam("page")  @DefaultValue("0")  @ApiParam("The desired page result")  Integer page) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves a place", notes = "Retrieves a place in the RNS system", response = GenericPlace.class, tags={ "places (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Place found", response = GenericPlace.class),
        @ApiResponse(code = 404, message = "Place not found", response = Void.class)
    })
    public Response getPlace(@PathParam("id") @ApiParam("The place ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves all palces", notes = "Retrieves a set containing all places in the RNS system", response = GenericPlaces.class, tags={ "places (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Places returned", response = GenericPlaces.class),
        @ApiResponse(code = 400, message = "Bad request: at least one invalid parameter", response = Void.class)
    })
    public Response getPlaces(@QueryParam("idPrefix")   @ApiParam("The ID prefix for selecting places")  String idPrefix,@QueryParam("page")  @DefaultValue("0")  @ApiParam("The desired page result")  Integer page) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/roadSegments/{id}/name")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves one road segment's name", notes = "Retrieves one road segment's name in the RNS system", response = String.class, tags={ "places (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Road segment's name found", response = String.class),
        @ApiResponse(code = 404, message = "Place not found", response = Void.class)
    })
    public Response getRoadSegmentName(@PathParam("id") @ApiParam("The place ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/roadSegments/{id}/road")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves one road segment's road name", notes = "Retrieves one road segment's road name in the RNS system", response = String.class, tags={ "places (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Road segment's road name found", response = String.class),
        @ApiResponse(code = 404, message = "Place not found", response = Void.class)
    })
    public Response getRoadSegmentRoadName(@PathParam("id") @ApiParam("The place ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/roadSegments")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves all road segments", notes = "Retrieves a set containing all road segments in the RNS system", response = RoadSegments.class, tags={ "places (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Road segments returned", response = RoadSegments.class),
        @ApiResponse(code = 400, message = "Bad request: at least one invalid parameter", response = Void.class)
    })
    public Response getRoadSegments(@QueryParam("roadName")   @ApiParam("The name of the road to which the segment must belong to")  String roadName,@QueryParam("page")  @DefaultValue("0")  @ApiParam("The desired page result")  Integer page) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/parkingAreas/{id}/services")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves one parking area's services", notes = "Retrieves one parking area's services in the RNS system", response = String.class, responseContainer = "List", tags={ "places (admin)",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Parking area's services found", response = String.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Place not found", response = Void.class)
    })
    public Response getServices(@PathParam("id") @ApiParam("The place ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/vehicles")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "retrieves all vehicles in a place", notes = "Retrieves the vehicles in a place of the RNS system", response = Vehicles.class, tags={ "places (admin)" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Vehicles found", response = Vehicles.class),
        @ApiResponse(code = 404, message = "Place not found", response = Void.class)
    })
    public Response getVehiclesInPlace(@PathParam("id") @ApiParam("The place ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }
}
