package br.com.billing.faturamento.services;

import br.com.billing.faturamento.model.DashboardModel;
import br.com.billing.faturamento.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;

@Service
public class DashboardService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public DashboardModel fieldDash(String currentMonth) {
        LocalDate startDate = stringDateToDataBasePattern("01-".concat(currentMonth));
        LocalDate endDate = stringDateToDataBasePattern(getLastDayOfMonth().concat("-").concat(currentMonth));
        String maiorDespesa = null != invoiceRepository.findExpenseMaxPrice(startDate, endDate)
                ? String.valueOf(invoiceRepository.findExpenseMaxPrice(startDate, endDate)) : "0.00";
        String menorDespesa = null != invoiceRepository.findExpenseMinPrice(startDate, endDate)
                ? String.valueOf(invoiceRepository.findExpenseMinPrice(startDate, endDate)) : "0.00";
        String maiorReceita = null != invoiceRepository.findRecipeMaxPrice(startDate, endDate)
                ? String.valueOf(invoiceRepository.findRecipeMaxPrice(startDate, endDate)) : "0.00";
        String menorReceita = null != invoiceRepository.findRecipeMinPrice(startDate, endDate)
                ? String.valueOf(invoiceRepository.findRecipeMinPrice(startDate, endDate)) : "0.00";

        DashboardModel dashboardModel = new DashboardModel();
        dashboardModel.setMesAtual(currentMonth);
        dashboardModel.setMaiorDespesa(maiorDespesa);
        dashboardModel.setMenorDespesa(menorDespesa);
        dashboardModel.setMaiorReceita(maiorReceita);
        dashboardModel.setMenorReceita(menorReceita);

        return dashboardModel;
    }

    private LocalDate stringDateToDataBasePattern(String date) {
        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year = date.substring(6, 10);

        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    private String getLastDayOfMonth() {
        Calendar c = Calendar.getInstance();
        int lastDayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return String.valueOf(lastDayOfMonth);
    }
}
