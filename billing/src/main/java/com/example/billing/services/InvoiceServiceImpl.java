package com.example.billing.services;

import com.example.billing.dto.InvoiceRequestDTO;
import com.example.billing.dto.InvoiceResponseDTO;
import com.example.billing.entities.Customer;
import com.example.billing.entities.Invoice;
import com.example.billing.exceptions.CustomerNotFoundException;
import com.example.billing.mappers.InvoiceMapper;
import com.example.billing.openfeign.CustomerRestClient;
import com.example.billing.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        Customer customer = null;
        try{
            customer = customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
            System.out.println(customer);
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer Not Found");
        }

        Invoice invoice = invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice savedInvoice = invoiceRepository.save(invoice);
        savedInvoice.setCustomer(customer);

        return invoiceMapper.fromInvoice(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> getAllInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        // map customers
        for(Invoice invoice: invoiceList){
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        List<InvoiceResponseDTO> invoiceResponseDTOS = invoiceList.stream().map(invoice -> invoiceMapper.fromInvoice(invoice)).collect(Collectors.toList());
        return invoiceResponseDTOS;
    }


    @Override
    public List<InvoiceResponseDTO> invoicesByCustomer(String customerId) {
        List<Invoice> invoiceList = invoiceRepository.findByCustomerId(customerId);
        for(Invoice invoice: invoiceList){
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        List<InvoiceResponseDTO> invoiceResponseDTOS = invoiceList.stream().map(invoice -> invoiceMapper.fromInvoice(invoice)).collect(Collectors.toList());
        return invoiceResponseDTOS;
    }
}
