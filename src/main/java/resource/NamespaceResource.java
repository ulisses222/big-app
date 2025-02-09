package resource;

import model.NamespaceRepository;
import model.Namespace;
import model.Conta;
import service.NamespaceService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.transaction.*;
import jakarta.transaction.NotSupportedException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.List;

@Path("/api/namespace")
@Produces(MediaType.APPLICATION_JSON)
public class NamespaceResource {

    @Inject
    NamespaceRepository namespaceRepository;

    @Inject
    TransactionManager transactionManager;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createNamespace(Namespace namespace){
        namespaceRepository.persist(namespace);
        return Response.ok(namespace).build();
    }
    @Inject
    @RestClient
    NamespaceService namespaceService;

    @GET
    public List<Namespace> listar() {
        return namespaceService.listar(); }

    @GET
    @Path("/listar-memoria")

    public List<Namespace> listarH2() {
        return namespaceRepository.listAll();
    }

    @PostConstruct
    public void init() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        transactionManager.begin();
        List<Namespace> namespaces = namespaceService.listar();
        namespaces.forEach((Namespace ns) -> {
            namespaceRepository.persist(ns);
        });
        transactionManager.commit();
    }

    @GET
    @Path("/stats")

    public List<Conta> conta() {
        return namespaceRepository.contaNsBySigla();
    }

}
