package com.example.salon.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menber {
	
	@Id
	private Integer staffId;
	//社員番号
	private String staffName;
	//名前
}
