package p09FindLatest10Projects;

import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Project> projects = em.createQuery("SELECT p FROM Project p", Project.class)
                .getResultList();

        StringBuilder sb = new StringBuilder();

        projects.stream()
                .sorted(Comparator.comparing(Project::getStartDate, Comparator.reverseOrder()))
                .limit(10L)
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> {
                    sb.append(String.format("Project name: %s", project.getName()))
                            .append(System.lineSeparator());
                    sb.append(String.format("\tProject Description: %s", project.getDescription()))
                            .append(System.lineSeparator());
                    sb.append(String.format("\tProject Start Date: %s", project.getStartDate()))
                            .append(System.lineSeparator());
                    sb.append(String.format("\tProject End Date: %s", project.getEndDate()))
                            .append(System.lineSeparator());
                });

        System.out.println(sb.toString().trim());

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
