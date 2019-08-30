package it.polito.dp2.RNS.sol3.service.api;

import io.swagger.annotations.*;
import it.polito.dp2.RNS.sol3.service.model.GateType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/gates")
@Api(description = "the gates API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2019-08-28T10:57:56.578+02:00[Europe/Rome]")
public class GatesApi {

    @GET
    @Path("/{id}/type")
    @Produces({"application/xml", "application/json"})
    @ApiOperation(value = "retrieves one gate's type", notes = "Retrieves one gate in the RNS system", response = GateType.class, tags = {"places (admin)"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gate's type found", response = GateType.class),
            @ApiResponse(code = 404, message = "Place not found", response = Void.class)
    })
    public Response getGateType(@PathParam("id") @ApiParam("The place ID of interest") String id) {
        return Response.ok().entity("magic!").build();
    }
}
