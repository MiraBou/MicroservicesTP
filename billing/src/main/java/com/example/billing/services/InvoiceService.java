package com.example.billing.services;


import com.example.billing.dto.InvoiceRequestDTO;
import com.example.billing.dto.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO getInvoice(String invoiceId);
    List<InvoiceResponseDTO> getAllInvoices();
    List<InvoiceResponseDTO> invoicesByCustomer(String customerId);
}
