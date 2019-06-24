package com.company.creditcard.web;

import com.company.creditcard.model.CreditCard;
import com.company.creditcard.model.User;
import com.company.creditcard.service.CreditCardService;
import com.company.creditcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.creditcard.validator.CreditCardValidator;

@Controller
public class CreditCardController {
	@Autowired
	private UserService userService;
	@Autowired
	private CreditCardValidator creditCardValidator;
	@Autowired
	private CreditCardService creditCardService;
	
    @GetMapping("/ccs")
    public String listCreditCards(Model model, Authentication authentication) {
        model.addAttribute("ccSearchForm", new CreditCard());
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
        	model.addAttribute("ccs", creditCardService.findAll());
        else
        	model.addAttribute("ccs", creditCardService.findByUserName(authentication.getName()));
        	
        return "cclist";
    }
    @GetMapping("/ccs/add")
    public String addCreditCard(Model model) {
        model.addAttribute("ccForm", new CreditCard());
        return "ccadd";
    }	
    
    @PostMapping("/ccs")
    public String saveOrUpdate(@ModelAttribute("ccForm") CreditCard ccForm, BindingResult bindingResult, Authentication authentication) {
        creditCardValidator.validate(ccForm, bindingResult); 

        if (bindingResult.hasErrors()) {
            return "ccadd";
        }
        
        final User user = userService.findByUsername(authentication.getName());
        ccForm.setUser(user);
        creditCardService.saveOrUpdate(ccForm);
        return "redirect:/ccs";
    }

	@GetMapping(value = "/ccs/{id}/delete")
	public String deleteUser(@PathVariable("id") long id, 
		final RedirectAttributes redirectAttributes) {

		creditCardService.deleteById(id);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Credit Card is deleted!");
		
		return "redirect:/ccs";

	}
    @PostMapping("/ccsearch")
    public String search(@ModelAttribute("ccSearchForm") CreditCard ccSearchForm, BindingResult bindingResult, Model model) {
        creditCardValidator.validate(ccSearchForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/ccs";
        }
        model.addAttribute("ccs", creditCardService.findByNumberContaining(ccSearchForm.getNumber()));
        return "cclist";       
    }

}
