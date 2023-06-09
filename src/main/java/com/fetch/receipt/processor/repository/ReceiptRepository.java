package com.fetch.receipt.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fetch.receipt.processor.model.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, String> {

}
