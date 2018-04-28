package p04Telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {

    List<String> phoneNumbers;
    List<String> sites;

    public Smartphone(List<String> phoneNumbers, List<String> sites) {
        this.phoneNumbers = phoneNumbers;
        this.sites = sites;
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.phoneNumbers.size(); i++) {
            String currentPhoneNumber = this.phoneNumbers.get(i);
            boolean isCorrectPhoneNumber = true;
            for (int j = 0; j < currentPhoneNumber.length(); j++) {
                if (!Character.isDigit(currentPhoneNumber.charAt(j))){
                    isCorrectPhoneNumber = false;
                    break;
                }
            }
            if (isCorrectPhoneNumber){
                sb.append(String.format("Calling... %s", currentPhoneNumber)).append(System.lineSeparator());
            } else {
                sb.append("Invalid number!").append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.sites.size(); i++) {
            String currentSite = this.sites.get(i);
            boolean isCorrectSite = true;
            for (int j = 0; j < currentSite.length(); j++) {
                if (Character.isDigit(currentSite.charAt(j))){
                    isCorrectSite = false;
                    break;
                }
            }
            if (isCorrectSite){
                sb.append(String.format("Browsing: %s!", currentSite)).append(System.lineSeparator());
            } else {
                sb.append("Invalid URL!").append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
