package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * This is an API Object.  Its purpose is to model the JSON API that we expose.
 * This class is NOT used for storing in the Database.
 *
 * This ReceiptResponse in particular is the model of a Receipt that we expose to users of our API
 *
 * Any properties that you want exposed when this class is translated to JSON must be
 * annotated with {@link JsonProperty}
 */
public class ReceiptResponse {
    @JsonProperty
    Integer id;

    @JsonProperty
    Time created;

    @JsonProperty
    String merchant;

    @JsonProperty
    BigDecimal amount;

    @JsonProperty
    List<String> tags;

    public ReceiptResponse(ReceiptsRecord dbReceipt, List<TagsRecord> dbTags) {
        this.id = dbReceipt.getId();
        this.created = dbReceipt.getUploaded();
        this.merchant = dbReceipt.getMerchant();
        this.amount = dbReceipt.getAmount();
        this.tags = dbTags.stream().map(TagsRecord::getTag).collect(toList());
    }
}
