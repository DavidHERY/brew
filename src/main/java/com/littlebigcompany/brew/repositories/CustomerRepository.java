package com.littlebigcompany.brew.repositories;

import com.littlebigcompany.brew.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
