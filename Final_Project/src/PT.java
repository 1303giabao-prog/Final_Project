
public class PT extends Person {
    private String certificate;
    private int yearsOfExperience; // Professional coaching years
    private int yearsOfWorkingOut; // Personal training years
    private String achievement;

    // Updated Constructor
    public PT(String name, String email, String phoneNum, String certificate, 
                 int yearsOfExperience, int yearsOfWorkingOut, String achievement) {
        super(name, email, phoneNum);
        this.certificate = certificate;
        this.yearsOfExperience = yearsOfExperience;
        this.yearsOfWorkingOut = yearsOfWorkingOut;
        this.achievement = achievement;
    }

    // Getter and Setter
    public int getYearsOfWorkingOut() { return yearsOfWorkingOut; }
    public void setYearsOfWorkingOut(int yearsOfWorkingOut) { this.yearsOfWorkingOut = yearsOfWorkingOut; }

   
    public String getCertificate() { return certificate; }
    public void setCertificate(String certificate) { this.certificate = certificate; }

    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public String getAchievement() { return achievement; }
    public void setAchievement(String achievement) { this.achievement = achievement; }

    @Override
    public String toString() {
        return String.format("--- Staff Profile ---\n" +
                             "Name: %s\n" +
                             "Cert: %s\n" +
                             "Pro Experience: %d years\n" +
                             "Personal Training: %d years\n" +
                             "Top Achievement: %s\n" +
                             "---------------------", 
                             getName(), certificate, yearsOfExperience, yearsOfWorkingOut, achievement);
    }
}