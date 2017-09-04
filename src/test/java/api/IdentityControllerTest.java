package api;

import controllers.IdentityController;
import org.junit.Assert;
import org.junit.Test;

public class IdentityControllerTest {

    @Test
    public void testValid() {
        IdentityController identityController = new IdentityController();

        Assert.assertEquals("jlc553", identityController.getNetId());
    }
}
