package simulation;

import java.util.ArrayList;
import java.util.List;

public class TimeManagement {
    private int currentDay;
    private List<Integer> lockedInterestAccountIds;

    public TimeManagement() {
        this.currentDay = 0;
        this.lockedInterestAccountIds = new ArrayList<>();
    }

    public void addLockedInterestAccount(int id){
        this.lockedInterestAccountIds.add(id);
    }

    public boolean checkAccountId(int id){
        return this.lockedInterestAccountIds.contains(id);
    }

    public void passOneDay(){
        this.currentDay++;
        this.lockedInterestAccountIds.clear();
    }

    public int getCurrentDay() {
        return currentDay;
    }
}
