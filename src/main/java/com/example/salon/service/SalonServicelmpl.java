package com.example.salon.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.salon.entity.Hyouji;
import com.example.salon.entity.Menu;
import com.example.salon.entity.Salon;
import com.example.salon.repository.MenuRepository;
import com.example.salon.repository.SalonRepository;

@Service
@Transactional
public class SalonServicelmpl implements SalonService {
	@Autowired
	SalonRepository repository;
	@Autowired
	MenuRepository menuRepository;
	@Override
	public Iterable<Hyouji> selectAll() {
		Iterable<Salon> salon= repository.findAll();
		Iterable<Menu> menu = menuRepository.findAll();
		ArrayList<Hyouji> hyouji = new ArrayList<>();
		for (Salon s : salon) {
			Hyouji h = new Hyouji();
			h.setId(s.getId());
			h.setName(s.getName());
			h.setVisit(s.getVisit());
			h.setStylist(s.getStylist());
			h.setMemo(s.getMemo());
			String sa = s.getMenu();
			for (Menu m : menu) {
				if (m.getMenuId().equals(sa)) {
					h.setMenu(m.getMenuNum());
				}
			}
			hyouji.add(h);
		}
		return () -> hyouji.iterator();
	}

	@Override
	public Optional<Salon> selectOneById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void insertSalon(Salon salon) {
		repository.save(salon);
	}

	@Override
	public void updateSalon(Salon salon) {
		repository.save(salon);
	}

	@Override
	public void deleteSalonById(Integer id) {
		repository.deleteById(id);
	}

}
