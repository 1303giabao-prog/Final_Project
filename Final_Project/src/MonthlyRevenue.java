public class MonthlyRevenue {
    // Attributes
    private String month;      // e.g., "April 2026"
    private double amount;     // Total revenue for that month
    private int year; 

    // Constructor
 // MonthlyRevenue.java

    public MonthlyRevenue(String month, double amount, int year) {
        this.month = month;
        this.amount = amount;
        this.year = year;  
    }

    // Getters and Setters
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // toString Method
    @Override
    public String toString() {
        return String.format("Month: %-15s | Total Revenue: $%,.2f", 
                             month, amount);
    }
}