package p11RemoveTowns;

import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RemoveTowns {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String townName = reader.readLine();

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Address> addresses = em.createQuery("FROM Address a WHERE a.town.name = ?", Address.class)
                .setParameter(0, townName)
                .getResultList();

        if (addresses.isEmpty()){
            System.out.println(String.format("Town %s does not exist", townName));
            em.getTransaction().commit();
            em.close();
            factory.close();
            return;
        }

        Town town = addresses.get(0).getTown();
        addresses.forEach(address -> {
            address.getEmployees().forEach(employee -> employee.setAddress(null));
            em.remove(address);
        });
        em.remove(town);

        System.out.println(String.format("%d address in %s deleted", addresses.size(), town.getName()));

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
