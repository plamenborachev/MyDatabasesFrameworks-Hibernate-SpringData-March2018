package p02RemoveObjects;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RemoveObjects {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Town> towns = em.createQuery("SELECT t FROM Town t", Town.class)
                .getResultList();
        for (Town town : towns) {
            if (town.getName().length() > 5){
                em.detach(town);
                continue;
            }
            town.setName(town.getName().toLowerCase());
            em.merge(town);
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
