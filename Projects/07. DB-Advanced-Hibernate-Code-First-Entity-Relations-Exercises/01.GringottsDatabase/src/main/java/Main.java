import entities.WizardDeposit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory managerFactory =
                Persistence.createEntityManagerFactory("gringotts");
        EntityManager em = managerFactory.createEntityManager();
        em.getTransaction().begin();

//        WizardDeposit dumbledore = new WizardDeposit();
//        dumbledore.setFirst_name("Albus");
//        dumbledore.setLast_name("Dumbledore");
//        dumbledore.setAge((short) 150);
//        dumbledore.setMagic_wand_size(32768);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2016, 10, 20);
//        Date depositStart = calendar.getTime();
//        dumbledore.setDeposit_start_date(depositStart);
//        calendar.set(2020, 10, 20);
//        Date depositEnd = calendar.getTime();
//        dumbledore.setDeposit_expiration_date(depositEnd);
//        dumbledore.setDeposit_amount(2000.24);
//        dumbledore.setDeposit_charge(0.2);
//        dumbledore.setIs_deposit_expired(false);
//
//        em.persist(dumbledore);

        em.getTransaction().commit();
        em.close();
        managerFactory.close();
    }
}
