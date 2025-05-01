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
    public void setMonthlyReport(LocalDate selectedMonthAnyDay) {
        this.startDate = selectedMonthAnyDay.withDayOfMonth(1);
        this.endDate = selectedMonthAnyDay.withDayOfMonth(selectedMonthAnyDay.lengthOfMonth());
    }

    // set Yearly Report in one year
    public void setYearlyReport(LocalDate selectedYearAnyDay) {
        this.startDate = selectedYearAnyDay.withDayOfYear(1);
        this.endDate = selectedYearAnyDay.withDayOfYear(selectedYearAnyDay.lengthOfYear());
    }

    // set Custom Report date domain
    public void setCustomizeReport(LocalDate start, LocalDate end) {
         if (start.isAfter(end)) {
        // automatic change if user input up sidedown
        LocalDate temp = start;
        start = end;
        end = temp;
    }
        this.startDate = start;
        this.endDate = end;
    }

  //getter
    public LocalDate getStartDate() {
        return startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }
    

}
