package api;

import controllers.ReceiptController;
import dao.ReceiptDao;
import dao.TagDao;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

public class ReceiptControllerTest {

    @Test
    public void testGetReceiptsEmpty() {
        ReceiptDao receiptDaoMock = mock(ReceiptDao.class);
        TagDao tagDaoMock = mock(TagDao.class);

        ReceiptController receiptController = new ReceiptController(receiptDaoMock, tagDaoMock);

        Assert.assertEquals(new ArrayList<String>(), receiptController.getReceipts());
    }

    @Test
    public void testGetReceiptsValid() {
        ReceiptDao receiptDaoMock = mock(ReceiptDao.class);
        TagDao tagDaoMock = mock(TagDao.class);

        ReceiptsRecord record = new ReceiptsRecord();
        record.setId(1);
        when(receiptDaoMock.get()).thenReturn(asList(record));
        when(tagDaoMock.get(any())).thenReturn(new ArrayList<>());

        ReceiptController receiptController = new ReceiptController(receiptDaoMock, tagDaoMock);

        Assert.assertEquals(1, receiptController.getReceipts().size());
    }
}
