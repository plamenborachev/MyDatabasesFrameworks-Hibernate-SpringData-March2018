package p07AddressesWithEmployeeCount;

import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Address> addresses = em
                .createQuery("SELECT a FROM Address a ORDER BY a.employees.size DESC, a.town.id",
                        Address.class)
                .setMaxResults(10)
                .getResultList();

        addresses.forEach(a -> System.out.println(String.format("%s, %s - %d employees",
                a.getText(), a.getTown().getName(), a.getEmployees().size())));

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
