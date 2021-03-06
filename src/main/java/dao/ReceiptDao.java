package dao;

import generated.tables.records.ReceiptsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;

public class ReceiptDao {
    DSLContext dsl;

    public ReceiptDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public List<ReceiptsRecord> get() {
        return dsl.selectFrom(RECEIPTS).fetch();
    }

    public ReceiptsRecord get(int id) {
        return dsl
                .selectFrom(RECEIPTS)
                .where(RECEIPTS.ID.eq(id))
                .fetchOne();
    }

    public List<ReceiptsRecord> get(List<Integer> ids) {
        return dsl
                .selectFrom(RECEIPTS)
                .where(RECEIPTS.ID.in(ids))
                .fetch();
    }

    public int insert(String merchantName, BigDecimal amount) {
        ReceiptsRecord receiptsRecord = dsl
                .insertInto(RECEIPTS, RECEIPTS.MERCHANT, RECEIPTS.AMOUNT)
                .values(merchantName, amount)
                .returning(RECEIPTS.ID)
                .fetchOne();

        checkState(receiptsRecord != null && receiptsRecord.getId() != null, "Insert failed");

        return receiptsRecord.getId();
    }
}
