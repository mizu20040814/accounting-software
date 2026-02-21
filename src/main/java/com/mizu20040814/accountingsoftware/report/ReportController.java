package com.mizu20040814.accountingsoftware.report;

import com.mizu20040814.accountingsoftware.report.dto.HourlySalesResponse;
import com.mizu20040814.accountingsoftware.report.dto.ProductSalesResponse;
import com.mizu20040814.accountingsoftware.report.dto.SummaryResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/summary")
    public SummaryResponse getSummary(
            @RequestParam(required = false) LocalDate date) {
        LocalDate targetDate = (date != null) ? date : LocalDate.now();
        return reportService.getSummary(targetDate);
    }

    @GetMapping("/by-product")
    public List<ProductSalesResponse> getByProduct(
            @RequestParam(required = false) LocalDate date) {
        LocalDate targetDate = (date != null) ? date : LocalDate.now();
        return reportService.getByProduct(targetDate);
    }

    @GetMapping("/by-hour")
    public List<HourlySalesResponse> getByHour(
            @RequestParam(required = false) LocalDate date) {
        LocalDate targetDate = (date != null) ? date : LocalDate.now();
        return reportService.getByHour(targetDate);
    }
}
