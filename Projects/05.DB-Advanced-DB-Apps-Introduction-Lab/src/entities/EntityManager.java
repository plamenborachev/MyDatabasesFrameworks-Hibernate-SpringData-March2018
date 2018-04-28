package entities;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class EntityManager implements DbContext{

    private Connection connection;
    private Set<Object> persistedEntities;

    public EntityManager(Connection connection) {
        this.connection = connection;
        this.persistedEntities = new HashSet<>();
    }

    @Override
    public <E> boolean persist(E entity) throws IllegalAccessException {

        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);
        Object value = primary.get(entity);

        this.doCreate(entity, primary);

        if (value == null || (Long)value <= 0) {
            return this.doInsert(entity, primary);
        }

        return this.doUpdate(entity, primary);


//        String tableName = entity.getClass().getSimpleName().toLowerCase();
//        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
//                "id BIGINT AUTO_INCREMENT PRIMARY KEY";
//        Field[] fields = entity.getClass().getDeclaredFields();
//        String columns = "";
//        for (int i = 0; i < fields.length; i++) {
//            Field field = fields[i];
//            String fieldName = field.getName();
//            if (!fieldName.equals("id")){
//                columns += fieldName + type(field);
//            }
//            if (i < fields.length - 1){
//                columns += ",";
//            }
//        }
//
//        try(Statement )
//        return false;
    }

    private <E> boolean doInsert(E entity, Field primary) {
        String query = "INSERT INTO " +
    }

    private <E> boolean doUpdate(E entity, Field primary) {

    }

    @Override
    public <E> Iterable<E> find(Class<E> table) {
        return null;
    }

    @Override
    public <E> Iterable<E> find(Class<E> table, String where) {
        return null;
    }

    @Override
    public <E> E findFirst(Class<E> table) {
        return null;
    }

    @Override
    public <E> E findFirst(Class<E> table, String where) {
        return null;
    }

    @Override
    public void closeConnection() {

    }

    private <E> boolean doCreate(E entity, Field primary){
        String query = "CREATE TABLE IF NOT EXISTS ";
        // TODO: Make the implementation
        return true;
    }

    private String getDbType(Field field, Field primary){
        field.setAccessible(true);
        if (field.getName().equals(primary.getName())){
            return "BIGINT PRIMARY KEY AUTO_INCREMENT";
        }

        switch (field.getType().getSimpleName()){
            case "int":
                return "INT";
            case "String":
                return "VARCHAR(50)";
            case "LocalDate":
                return "DATE";
        }
        return null;
    }
}
