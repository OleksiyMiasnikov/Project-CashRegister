package com.myProject.dto;

import com.myProject.entitie.Unit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Report {
    private final Date startDate;
    private final Date endDate;
    private String seniorCashier;
    private final List<ReportItem> list;
    private final double kgTotal;
    private final int pcsTotal;
    private final double amountTotal;

    public Report(Date startDate, Date endDate, String seniorCashier, List<ReportItem> list) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.seniorCashier = seniorCashier;
        this.list = list;
        kgTotal = list.stream()
                .mapToDouble(r -> (r.getUnit().equals(Unit.KG)) ? r.getQuantity():0)
                .sum();
        pcsTotal = (int) list.stream()
                .mapToDouble(r -> (r.getUnit().equals(Unit.PCS)) ? r.getQuantity():0)
                .sum();
        amountTotal = list.stream()
                .mapToDouble(ReportItem::getAmount)
                .sum();
    }

    public static ReportBuilder builder() {
        return new Report.ReportBuilder();
    }

    public static class ReportBuilder {
        private Date startDate;
        private Date endDate;
        private String seniorCashier;
        private List<ReportItem> list;

        public ReportBuilder() {
        }

        public ReportBuilder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }
        public ReportBuilder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }
        public ReportBuilder seniorCashier(String seniorCashier) {
            this.seniorCashier = seniorCashier;
            return this;
        }
        public ReportBuilder list(List<ReportItem> list) {
            this.list = new ArrayList<>(list);
            return this;
        }

        public Report build() {
            return new Report(startDate,
                    endDate,
                    seniorCashier,
                    list);
        }
    }
    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getSeniorCashier() {
        return seniorCashier;
    }

    public void setSeniorCashier(String seniorCashier) {
        this.seniorCashier = seniorCashier;
    }

    public List<ReportItem> getList() {
        return list;
    }

    public double getKgTotal() {
        return kgTotal;
    }

    public int getPcsTotal() {
        return pcsTotal;
    }

    public double getAmountTotal() {
        return amountTotal;
    }
}
