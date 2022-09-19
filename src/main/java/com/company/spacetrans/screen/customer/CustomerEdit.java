package com.company.spacetrans.screen.customer;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Customer;

@UiController("st_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
public class CustomerEdit extends StandardEditor<Customer> {
}