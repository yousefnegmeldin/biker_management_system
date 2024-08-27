package com.brightskies.biker_system.admin.service;

import com.brightskies.biker_system.admin.model.Admin;
import com.brightskies.biker_system.admin.repository.AdminRepository;
import com.brightskies.biker_system.store.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService
{
    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

}
