package org.softuni.ruk;

public final class Config {
    public static final String BRANCHES_IMPORT_JSON = "/files/json/input/branches.json";
    public static final String EMPLOYEES_IMPORT_JSON = "/files/json/input/employees.json";
    public static final String CLIENTS_IMPORT_JSON = "/files/json/input/clients.json";
    public static final String BANK_ACCOUNTS_IMPORT_XML = "/files/xml/input/bank-accounts.xml";
    public static final String CARDS_IMPORT_XML = "/files/xml/input/cards.xml";

    public static final String  EMPLOYEES_EXPORT_JSON
            = "src/main/resources/files/json/output/topEmployees.json";
    public static final String  CLIENT_EXPORT_XML
            = "src/main/resources/files/xml/output/family-guy.xml";

    public static final String SUCCESS_MESSAGE = "Succesfully imported %s – %s.";
    public static final String ERROR_MESSAGE = "Error: Incorrect Data!";
}
