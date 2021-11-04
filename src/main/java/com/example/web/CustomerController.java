/**
 * 画面遷移用 コントローラー
 */

package com.example.web;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("customers")
public class CustomerController {
  @Autowired
  CustomerService customerService;

  @GetMapping
  //

  /**
   * Create list.html path :pass the value to window By Model
   *
   * @param model
   * @return
   */
  String list(Model model) {
    List<Customer> customers = customerService.findAll();
    model.addAttribute("customers", customers);
    return "customers/list";
  }
}
