package user;

import java.util.ArrayList;
import java.util.List;

public class Bank extends User {

    private double currentRateTRYtoEUR;
    private double currentRateTRYtoUSD;
    private double currentRateTRYtoXAU;
    private double currentRateEURtoUSD;
    private double currentRateEURtoXAU;
    private double currentRateUSDtoXAU;
    private List<Client> clients;

    public Bank(){
        this.currentRateTRYtoEUR = 0.04;
        this.currentRateTRYtoUSD = 0.05;
        this.currentRateTRYtoXAU = 0.03;
        this.currentRateEURtoUSD = 0.95;
        this.currentRateEURtoXAU = 0.07;
        this.currentRateUSDtoXAU = 0.36;
        this.clients = new ArrayList<>();
    }
    public double getCurrentRateTRYtoEUR() {
        return currentRateTRYtoEUR;
    }

    public void setCurrentRateTRYtoEUR(double currentRateTRYtoEUR) {
        this.currentRateTRYtoEUR = currentRateTRYtoEUR;
    }

    public double getCurrentRateTRYtoUSD() {
        return currentRateTRYtoUSD;
    }

    public void setCurrentRateTRYtoUSD(double currentRateTRYtoUSD) {
        this.currentRateTRYtoUSD = currentRateTRYtoUSD;
    }

    public double getCurrentRateTRYtoXAU() {
        return currentRateTRYtoXAU;
    }

    public void setCurrentRateTRYtoXAU(double currentRateTRYtoXAU) {
        this.currentRateTRYtoXAU = currentRateTRYtoXAU;
    }

    public double getCurrentRateEURtoUSD() {
        return currentRateEURtoUSD;
    }

    public void setCurrentRateEURtoUSD(double currentRateEURtoUSD) {
        this.currentRateEURtoUSD = currentRateEURtoUSD;
    }

    public double getCurrentRateEURtoXAU() {
        return currentRateEURtoXAU;
    }

    public void setCurrentRateEURtoXAU(double currentRateEURtoXAU) {
        this.currentRateEURtoXAU = currentRateEURtoXAU;
    }

    public double getCurrentRateUSDtoXAU() {
        return currentRateUSDtoXAU;
    }

    public void setCurrentRateUSDtoXAU(double currentRateUSDtoXAU) {
        this.currentRateUSDtoXAU = currentRateUSDtoXAU;
    }

    public List<Client> getClients(){
        return clients;
    }
    public boolean checkClient(String name){
        for (Client client : clients){
            if (client.getClientName().equals(name)){
                return false;
            }
        }
        return true;
    }
    public void addClient(Client client){
        clients.add(client);
    }
    public Client getClient(String name){
        for (Client client : clients){
            if (client.getClientName().equals(name)){
                return client;
            }
        }
        return null;
    }
    public void displayClients(){
        for (Client client: clients){
            System.out.println("name: " + client.getClientName());
        }
    }
}
