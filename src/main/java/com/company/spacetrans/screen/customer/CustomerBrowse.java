package com.company.spacetrans.screen.customer;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Customer;

@UiController("st_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
public class CustomerBrowse extends StandardLookup<Customer> {
}