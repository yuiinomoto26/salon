package com.example.salon.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalonForm {

	private Integer id;        //会員No.
	@NotBlank
	private String name;       //名前
	//@NotBlank
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate visit;        //最終来店日
	@NotBlank
	private String stylist;    //担当者
	@NotBlank
	private String menu;       //メニュー
	private String memo;       //メモ
	
	private Boolean newMember; //登録or更新
}
