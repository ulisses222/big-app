package service;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import model.Namespace;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/namespace")
@RegisterRestClient
public interface NamespaceService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Namespace> listar();

}
