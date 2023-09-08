package com.example.salon.service;

import java.util.Optional;

import com.example.salon.entity.Hyouji;
import com.example.salon.entity.Salon;

//Salonサービス処理
public interface SalonService {
	//Salon情報を全件取得します
	Iterable<Hyouji> selectAll();
	//Salon情報を、idをキーに1件取得します。
	Optional<Salon> selectOneById(Integer id);
	//登録します
	void insertSalon(Salon salon);
	//更新します
	void updateSalon(Salon salon);
	//削除します
	void deleteSalonById(Integer id);
 
}
