package model;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import javax.management.Query;
import java.util.List;

@ApplicationScoped
public class NamespaceRepository implements PanacheRepository<Namespace> {

    public List<Conta> contaNsBySigla() {
        return find("SELECT n.sigla, COUNT(n) FROM Namespace n GROUP BY n.sigla").project(Conta.class).list();
    }

    public List<Conta> contaTudo() {
        return find("SELECT n.sigla, COUNT(n) FROM Namespace n").project(Conta.class).list();
    }
}

