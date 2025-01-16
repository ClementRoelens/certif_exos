package org.example.billingservice.controller;

import org.example.billingservice.dto.invoice.InvoiceRequestDTO;
import org.example.billingservice.dto.invoice.InvoiceResponseDTO;
import org.example.billingservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> addInvoice(@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        InvoiceResponseDTO invoice = invoiceService.createInvoice(invoiceRequestDTO);

        if (invoice != null){
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoice(@PathVariable int id) {
        InvoiceResponseDTO invoice = invoiceService.getInvoice(id);

        if (invoice != null){
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
