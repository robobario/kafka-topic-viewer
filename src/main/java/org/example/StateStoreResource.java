package org.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")
public class StateStoreResource {
    @Inject
    SynchronizedStateStore synchronizedStateStore;

    @GET
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(String key) {
        return String.join(",", synchronizedStateStore.get(key));
    }
}