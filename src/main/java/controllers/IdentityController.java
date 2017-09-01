package controllers;

import javax.ws.rs.*;

@Path("/netid")
public class IdentityController {

    public IdentityController() { }

    @GET
    public String getNetId() {
        return "jlc553";
    }
}
