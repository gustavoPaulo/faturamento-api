package br.com.billing.faturamento.resources;

import br.com.billing.faturamento.model.DashboardModel;
import br.com.billing.faturamento.services.DashboardService;
import br.com.billing.faturamento.useful.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Utility.RESOURCE_REQUEST_MAPPING_DASH)
public class DashboardResource {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping(params = "currentMonth")
    public DashboardModel fieldDash(String currentMonth) {
        return dashboardService.fieldDash(currentMonth);
    }
}
