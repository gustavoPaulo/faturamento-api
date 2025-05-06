package br.com.billing.faturamento.services;

import br.com.billing.faturamento.model.DashboardModel;
import br.com.billing.faturamento.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public DashboardModel fieldDash(String currentMonth) {
        LocalDate startDate = convertToDatePattern("01-".concat(currentMonth));
        LocalDate endDate = convertToDatePattern("31-".concat(currentMonth));

        DashboardModel dashboardModel = new DashboardModel();
        dashboardModel.setMesAtual(currentMonth);
        dashboardModel.setMaiorDespesa(String.valueOf(invoiceRepository.findExpenseMaxPrice(startDate, endDate)));
        dashboardModel.setMenorDespesa(String.valueOf(invoiceRepository.findExpenseMinPrice(startDate, endDate)));
        dashboardModel.setMaiorReceita(String.valueOf(invoiceRepository.findRecipeMaxPrice(startDate, endDate)));
        dashboardModel.setMenorReceita(String.valueOf(invoiceRepository.findRecipeMinPrice(startDate, endDate)));

        return dashboardModel;
    }

    private LocalDate convertToDatePattern(String date) {
        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year = date.substring(6, 10);

        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }
}
