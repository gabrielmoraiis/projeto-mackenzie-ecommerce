package com.mackenzie.e_commerce.controller;

import com.mackenzie.e_commerce.dto.DashboardDTO;
import com.mackenzie.e_commerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDTO> getDashboard() {
        DashboardDTO dashboardData = adminService.getDashboardData();
        return ResponseEntity.ok(dashboardData);
    }
}
