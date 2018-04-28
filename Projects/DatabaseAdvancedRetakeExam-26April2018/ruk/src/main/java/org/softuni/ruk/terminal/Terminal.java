package org.softuni.ruk.terminal;

import org.softuni.ruk.Config;
import org.softuni.ruk.controllers.*;
import org.softuni.ruk.io.interfaces.ConsoleIO;
import org.softuni.ruk.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final ConsoleIO consoleIO;
    private final FileIO fileIO;
    private final BranchController branchController;
    private final EmployeeController employeeController;
    private final ClientController clientController;
    private final BankAccountController bankAccountController;
    private final CardController cardController;

    @Autowired
    public Terminal(ConsoleIO consoleIO, FileIO fileIO, BranchController branchController, EmployeeController employeeController, ClientController clientController, BankAccountController bankAccountController, CardController cardController) {
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
        this.branchController = branchController;
        this.employeeController = employeeController;
        this.clientController = clientController;
        this.bankAccountController = bankAccountController;
        this.cardController = cardController;
    }

    @Override
    public void run(String... args) throws Exception {

        importBranchesJson();
        importEmployeesJson();
        importClientsJson();

        importBankAccountsXml();
        importCardsXml();

        exportJson();
        exportXml();
    }

    private void exportXml() throws IOException {
        this.fileIO.write(this.clientController.exportDataToXml(), Config.CLIENT_EXPORT_XML);
    }

    private void exportJson() throws IOException {
        this.fileIO.write(this.employeeController.exportDataToJson(), Config.EMPLOYEES_EXPORT_JSON);
    }

    private void importCardsXml() throws IOException {
        this.consoleIO.write(this.cardController.importDataFromXML(this.fileIO.read(Config.CARDS_IMPORT_XML)));
    }

    private void importBankAccountsXml() throws IOException {
        this.consoleIO.write(this.bankAccountController.importDataFromXML(this.fileIO.read(Config.BANK_ACCOUNTS_IMPORT_XML)));
    }

    private void importClientsJson() throws IOException {
        this.consoleIO.write(this.clientController.importDataFromJSON(this.fileIO.read(Config.CLIENTS_IMPORT_JSON)));
    }

    private void importEmployeesJson() throws IOException {
        this.consoleIO.write(this.employeeController.importDataFromJSON(this.fileIO.read(Config.EMPLOYEES_IMPORT_JSON)));
    }

    private void importBranchesJson() throws IOException {
        this.consoleIO.write(this.branchController.importDataFromJSON(this.fileIO.read(Config.BRANCHES_IMPORT_JSON)));
    }
}
