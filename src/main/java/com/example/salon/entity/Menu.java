package com.example.salon.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
	@Id
	private String menuId;
	//メニュー番号
	private String menuNum;
	//メニュー名
}
