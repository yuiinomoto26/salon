package com.example.salon.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.salon.entity.Hyouji;
import com.example.salon.entity.Salon;
import com.example.salon.form.SalonForm;
import com.example.salon.service.SalonService;

@Controller
@RequestMapping("/salon")
public class SalonController {
	
	@GetMapping("/touroku")
	public String set() {
		return "touroku";
	}
	
	@Autowired
	SalonService service;
	
	@ModelAttribute
	public SalonForm setUpForm() {
		SalonForm form = new SalonForm();
		form.setNewMember(true);
		return form;
	}
	
	@GetMapping 
	public String showList(SalonForm salonForm, Model model) {
		Iterable<Hyouji> list = service.selectAll();
		
		model.addAttribute("list", list);
		model.addAttribute("title", "登録用フォーム");
		return "crud";
	}
	
	@PostMapping("/insert")
	public String insert(@Validated SalonForm salonForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		
		Salon salon = new Salon();
		salon.setName(salonForm.getName());
		salon.setVisit(salonForm.getVisit());
		salon.setStylist(salonForm.getStylist());
		salon.setMenu(salonForm.getMenu());
		salon.setMemo(salonForm.getMemo());
		
		if (!bindingResult.hasErrors()){
			service.insertSalon(salon);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			return "redirect:/salon";
		} else {
			//エラーがある場合は、一覧表示処理を呼びます。
			return showList(salonForm, model);
		}
	}
	
	@GetMapping("/{id}")
	public String showUpdate(SalonForm salonForm, @PathVariable Integer id, Model model) {
		//Salonを取得
		Optional<Salon> salonOpt = service.selectOneById(id);
		Optional<SalonForm> salonFormOpt = salonOpt.map(t -> makeSalonForm(t));
		if (salonFormOpt.isPresent()) {
			salonForm = salonFormOpt.get();
		}
		
		makeUpdateModel(salonForm, model);
		return "touroku";
		
	}
	
	private void makeUpdateModel(SalonForm salonForm, Model model) {
		
		model.addAttribute("id", salonForm.getId());
		salonForm.setNewMember(false);
		model.addAttribute("salonForm", salonForm);
		model.addAttribute("title", "更新用フォーム");
	}
	
	@PostMapping("/update")
	public String update(@Validated SalonForm salonForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		Salon salon = makeSalon(salonForm);
		
		if (!result.hasErrors()) {
			service.updateSalon(salon);
			redirectAttributes.addFlashAttribute("complete", "更新が完了しました");
			
			return "redirect:/salon";
		} else {
			makeUpdateModel(salonForm, model);
			return "crud";
		}
	}
	
	private Salon makeSalon(SalonForm salonForm) {
		Salon salon = new Salon();
		salon.setId(salonForm.getId());
		salon.setName(salonForm.getName());
		salon.setVisit(salonForm.getVisit());
		salon.setStylist(salonForm.getStylist());
		salon.setMenu(salonForm.getMenu());
		salon.setMemo(salonForm.getMemo());
		return salon;
	}
	
	private SalonForm makeSalonForm(Salon salon) {
		SalonForm form = new SalonForm();
		form.setId(salon.getId());
		form.setName(salon.getName());
		form.setVisit(salon.getVisit());
		form.setStylist(salon.getStylist());
		form.setMenu(salon.getMenu());
		form.setMemo(salon.getMemo());
		form.setNewMember(false);
		return form;

	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("id") String id,
			Model model,
			RedirectAttributes redirectAttributes) {
		service.deleteSalonById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました");
		return "redirect:/salon";
		
	}
}
