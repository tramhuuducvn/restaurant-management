package org.example.service;

import lombok.NoArgsConstructor;
import org.example.repository.BillOrderRepo;

@NoArgsConstructor
public class BillOrderService {
    private static BillOrderRepo billOrderRepo;
    static {
        billOrderRepo = BillOrderRepo.getInstance();
    }
}