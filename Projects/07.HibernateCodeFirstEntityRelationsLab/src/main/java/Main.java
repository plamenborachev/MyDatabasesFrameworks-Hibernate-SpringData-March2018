import models.ingredient.AmmoniumChloride;
import models.ingredient.BasicIngredient;
import models.ingredient.Mint;
import models.ingredient.Nettle;
import models.label.BasicLabel;
import models.shampoo.BasicShampoo;
import models.shampoo.FreshNuke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory managerFactory =
                Persistence.createEntityManagerFactory("shampoo_company");
        EntityManager em = managerFactory.createEntityManager();
        em.getTransaction().begin();

        BasicIngredient am = new AmmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        BasicLabel label = new BasicLabel("Fresh Nuke Shampoo", "Contains mint and nettle");

        BasicShampoo shampoo = new FreshNuke(label);

        em.persist(shampoo);

        shampoo.getIngredients().add(mint);
        shampoo.getIngredients().add(nettle);
        shampoo.getIngredients().add(am);

        em.getTransaction().commit();
        em.close();
        managerFactory.close();
    }
}
