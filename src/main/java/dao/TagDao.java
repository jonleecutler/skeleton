package dao;

import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public List<TagsRecord> get(String tag) {
        return dsl
                .selectFrom(TAGS)
                .where(TAGS.TAG.eq(tag))
                .fetch();
    }

    public List<TagsRecord> get(int receiptId) {
        return dsl
                .selectFrom(TAGS)
                .where(TAGS.RECEIPT_ID.eq(receiptId))
                .fetch();
    }

    public void toggle(int receiptId, String tag) {
        TagsRecord tagsRecord = dsl
                .selectFrom(TAGS)
                .where(TAGS.RECEIPT_ID.eq(receiptId))
                .and(TAGS.TAG.eq(tag))
                .fetchOne();

        if (tagsRecord != null && tagsRecord.getId() != null) {
            tagsRecord.delete();
            return;
        }

        tagsRecord = dsl
                .insertInto(TAGS, TAGS.RECEIPT_ID, TAGS.TAG)
                .values(receiptId, tag)
                .returning(TAGS.ID)
                .fetchOne();

        checkState(tagsRecord != null && tagsRecord.getId() != null, "Insert failed");
    }
}
