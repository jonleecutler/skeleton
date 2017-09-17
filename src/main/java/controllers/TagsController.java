package controllers;

import api.ReceiptResponse;
import dao.ReceiptDao;
import dao.TagDao;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagsController {
    final ReceiptDao receipts;
    final TagDao tags;

    public TagsController(ReceiptDao receipts, TagDao tags) {
        this.receipts = receipts;
        this.tags = tags;
    }

    @GET
    @Path("/{tag}")
    public List<ReceiptResponse> getTags(@PathParam("tag") String tag) {
        List<TagsRecord> selectedTags = tags.get(tag);
        List<Integer> receiptIds = selectedTags.stream().map(TagsRecord::getReceiptId).collect(toList());

        List<ReceiptResponse> receiptResponses = new ArrayList<>();
        for (ReceiptsRecord receiptRecord : receipts.get(receiptIds)) {
            receiptResponses.add(new ReceiptResponse(receiptRecord, tags.get(receiptRecord.getId())));
        }

        return receiptResponses;
    }

    @PUT
    @Path("/{tag}")
    public void toggleTag(@PathParam("tag") String tag, @Valid @NotNull int receiptId) {
        ReceiptsRecord receiptRecord = receipts.get(receiptId);
        if (receiptRecord == null || receiptRecord.getId() == null) {
            throw new NotFoundException();
        }

        tags.toggle(receiptId, tag);
    }
}
