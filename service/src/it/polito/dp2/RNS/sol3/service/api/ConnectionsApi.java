package it.polito.dp2.RNS.sol3.service.api;

import io.swagger.annotations.*;
import it.polito.dp2.RNS.sol3.service.model.Connections;
import it.polito.dp2.RNS.sol3.service.model.GenericPlace;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/connections")
@Api(description = "the connections API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2019-08-28T10:57:56.578+02:00[Europe/Rome]")
public class ConnectionsApi {

    @GET
    @Produces({"application/xml", "application/json"})
    @ApiOperation(value = "retrieves all connections", notes = "Retrieves a set containing all connections in the RNS system", response = Connections.class, tags = {"connections (admin)",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Connections returned", response = Connections.class)
    })
    public Response getConnections(@QueryParam("page") @DefaultValue("0") @ApiParam("The desired page result") Integer page) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/from")
    @Produces({"application/xml", "application/json"})
    @ApiOperation(value = "retrieves one connection's origin", notes = "Retrieves one connection's origin in the RNS system", response = GenericPlace.class, tags = {"connections (admin)",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Connection's origin found", response = GenericPlace.class),
            @ApiResponse(code = 404, message = "Connection not found", response = Void.class)
    })
    public Response getFrom(@PathParam("id") @ApiParam("The connection ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{id}/to")
    @Produces({"application/xml", "application/json"})
    @ApiOperation(value = "retrieves one connection's endpoint", notes = "Retrieves one connection's endpoint in the RNS system", response = GenericPlace.class, tags = {"connections (admin)"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Connection's endpoint found", response = GenericPlace.class),
            @ApiResponse(code = 404, message = "Connection not found", response = Void.class)
    })
    public Response getto(@PathParam("id") @ApiParam("The connection ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }
}
