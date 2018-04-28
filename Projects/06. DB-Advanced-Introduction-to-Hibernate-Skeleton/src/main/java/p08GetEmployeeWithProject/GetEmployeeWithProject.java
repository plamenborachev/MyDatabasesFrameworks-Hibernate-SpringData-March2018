package p08GetEmployeeWithProject;

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GetEmployeeWithProject {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int employeeId = Integer.parseInt(reader.readLine());

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        try{
            Employee employee = em.createQuery("SELECT e FROM Employee e WHERE e.id = ?",
                        Employee.class)
                    .setParameter(0, employeeId)
                    .getSingleResult();

            StringBuilder sb = new StringBuilder();

            sb.append(String.format("%s %s - %s",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getJobTitle()))
                    .append(System.lineSeparator());

            for (Project project : employee.getProjects().stream()
                    .sorted(Comparator.comparing(Project::getName))
                    .collect(Collectors.toList())) {
                sb.append("\t").append(project.getName()).append(System.lineSeparator());
            }

            System.out.println(sb.toString().trim());
        } catch (NoResultException nre){
            System.out.println("Employee does not exist!");
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
