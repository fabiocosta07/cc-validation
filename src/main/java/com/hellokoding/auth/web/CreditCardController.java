package com.hellokoding.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hellokoding.auth.model.CreditCard;
import com.hellokoding.auth.service.CreditCardService;
import com.hellokoding.auth.validator.CreditCardValidator;

@Controller
public class CreditCardController {
	@Autowired
	private CreditCardValidator creditCardValidator;
	@Autowired
	private CreditCardService creditCardService;
    @GetMapping("/ccs")
    public String listCreditCards(Model model) {
        model.addAttribute("ccSearchForm", new CreditCard());
        model.addAttribute("ccs", creditCardService.findAll());
        return "cclist";
    }
    @GetMapping("/ccs/add")
    public String addCreditCard(Model model) {
        model.addAttribute("ccForm", new CreditCard());
        return "ccadd";
    }	
    
    @PostMapping("/ccs")
    public String saveOrUpdate(@ModelAttribute("ccForm") CreditCard ccForm, BindingResult bindingResult) {
        creditCardValidator.validate(ccForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "ccadd";
        }
        creditCardService.save(ccForm);
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
        model.addAttribute("ccs", creditCardService.findByNumber(ccSearchForm.getNumber()));
        return "cclist";       
    }

}
