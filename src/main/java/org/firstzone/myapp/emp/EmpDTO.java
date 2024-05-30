package org.firstzone.myapp.emp;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//VO(Value Object)
//DTO(Data Transfer Object)
//JavaBeans기술?��?�� ?��?��(Jsp, Spring, Mybatis?�� javabeans기술?�� ?��?��?��?��.)
@Getter@Setter //?��?��
@ToString
@AllArgsConstructor
@NoArgsConstructor// ?��?�� (AllArgsConstructor�? ?��?���? 컴파?��?���? NoArgsConstructor�? default�? ?��?��?��주기 ?��문에 ?��?��?�� 괜찮�?�?, 추�??�� 경우 ?��?��)
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
