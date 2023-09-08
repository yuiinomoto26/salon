package com.example.salon.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hyouji {
	
	@Id
	private Integer id;
	//会員番号
	private String name;
	//名前
	private LocalDate visit;
	//最終来店日
	private String stylist;
	//担当者
	private String menu;
	//メニュー
	private String memo;
    //メモ
	
	
}
