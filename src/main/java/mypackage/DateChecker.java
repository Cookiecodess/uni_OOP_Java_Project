/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.time.LocalDate;
/**
 *
 * @author songl
 */
public class DateChecker {
 private LocalDate startDate;
    private LocalDate endDate;

    public DateChecker() {
    }

    // set Daily Report in one day
    public void setDailyReport(LocalDate selectedDay) {
        this.startDate = selectedDay;
        this.endDate = selectedDay;
    }

    // set Monthly Report in one month
    public void setMonthlyReport(LocalDate selectedMonth) {
        this.startDate = selectedMonth.withDayOfMonth(1);
        this.endDate = selectedMonth.withDayOfMonth(selectedMonth.lengthOfMonth());
    }

    // set Yearly Report in one year
    public void setYearlyReport(LocalDate selectedYear) {
        this.startDate = selectedYear.withDayOfYear(1);
        this.endDate = selectedYear.withDayOfYear(selectedYear.lengthOfYear());
    }

    // set Custom Report date domain
    public void setCustomizeReport(LocalDate startD, LocalDate endD) {
         if (startD.isAfter(endD)) {
        // automatic change if user input up sidedown
        LocalDate temp = startD;
        startD = endD;
        endD = temp;
    }
        this.startDate = startD;
        this.endDate = endD;
    }

  //getter
    public LocalDate getStartDate() {
        return startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }
    

}
