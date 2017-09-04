package api;

import controllers.ReceiptController;
import dao.ReceiptDao;
import generated.tables.records.ReceiptsRecord;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

public class ReceiptControllerTest {

    @Test
    public void testGetReceiptsEmpty() {
        ReceiptDao receiptDaoMock = mock(ReceiptDao.class);

        ReceiptController receiptController = new ReceiptController(receiptDaoMock);

        Assert.assertEquals(new ArrayList<String>(), receiptController.getReceipts());
    }

    @Test
    public void testGetReceiptsValid() {
        ReceiptDao receiptDaoMock = mock(ReceiptDao.class);
        when(receiptDaoMock.get()).thenReturn(asList(new ReceiptsRecord()));

        ReceiptController receiptController = new ReceiptController(receiptDaoMock);

        Assert.assertEquals(1, receiptController.getReceipts().size());
    }
}
