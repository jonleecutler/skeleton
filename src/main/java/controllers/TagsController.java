package controllers;

import api.ReceiptResponse;
import dao.ReceiptDao;
import generated.tables.records.ReceiptsRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagsController {

    public TagsController() { }

    @GET
    @Path("/{tag}")
    public void getTags(@PathParam("tag") String tagName) {
    }

    @PUT
    @Path("/{tag}")
    public List<ReceiptResponse> toggleTag(@PathParam("tag") String tagName, @Valid @NotNull Integer receiptId) {
        return null;
    }
}
