package org.firstzone.myapp.emp;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//VO(Value Object)
//DTO(Data Transfer Object)
//JavaBeansê¸°ìˆ ?—?„œ ?´?š©(Jsp, Spring, Mybatis?Š” javabeansê¸°ìˆ ?„ ?´?š©?•œ?‹¤.)
@Getter@Setter //?•„?ˆ˜
@ToString
@AllArgsConstructor
@NoArgsConstructor// ?•„?ˆ˜ (AllArgsConstructorê°? ?—†?œ¼ë©? ì»´íŒŒ?¼?Ÿ¬ê°? NoArgsConstructorë¥? defaultë¡? ?ƒ?„±?•´ì£¼ê¸° ?•Œë¬¸ì— ?—†?–´?„ ê´œì°®ì§?ë§?, ì¶”ê??•œ ê²½ìš° ?•„?ˆ˜)
public class EmpDTO {

	private Integer employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private Integer salary;
	private Double commission_pct;
	private Integer manager_id;
	private Integer department_id;
}
