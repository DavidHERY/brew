package com.littlebigcompany.brew.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.littlebigcompany.brew.domain.Customer;
//import com.littlebigcompany.brew.form.MessageForm;
import com.littlebigcompany.brew.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CustomerRepository customerRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
        model.addAttribute("customer", new Customer());
		return "redirect:/customers";
	}

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String listCustomers(@ModelAttribute("customer") Customer customer, Model model) {
        List<Customer> customers = customerRepository.findAll();
        logger.info("{} customers found", !customers.isEmpty() ? customers.size() : 0);
        model.addAttribute("customers", customers);
        return "index";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String addCustormer(@ModelAttribute("customer") Customer customer) {
        logger.info("add new Customer with name: {}", customer.getName());
        customerRepository.saveAndFlush(customer);
        return "redirect:/customers";
    }
}
