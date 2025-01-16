package org.example.billingservice.service;

import org.example.billingservice.dto.invoice.InvoiceRequestDTO;
import org.example.billingservice.dto.invoice.InvoiceResponseDTO;
import org.example.billingservice.entity.Invoice;
import org.example.billingservice.entity.Subscription;
import org.example.billingservice.repository.InvoiceRepository;
import org.example.billingservice.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.example.billingservice.util.ApiInformations.*;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO DTO) {
        Invoice createdInvoice = invoiceRepository.save(parseFromDTO(DTO));
        return new InvoiceResponseDTO(createdInvoice);
    }

    public InvoiceResponseDTO getInvoice(int id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice != null) {
            return new InvoiceResponseDTO(invoice);
        }
        return null;
    }

    private Invoice parseFromDTO(InvoiceRequestDTO DTO) {
        RestClient<Subscription> restClient = new RestClient<>(API_ROOT + SUBSCRIPTION_PORT + SUBSCRIPTION_SUFFIX + "/" + DTO.getSubscriptionId());
        Subscription subscription = restClient.getRequest(Subscription.class);

        Invoice invoice = new Invoice(DTO);
        invoice.setSubscription(subscription);

        return invoice;
    }
}
