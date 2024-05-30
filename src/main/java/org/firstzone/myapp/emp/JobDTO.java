package org.firstzone.myapp.emp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class JobDTO {
	String job_id;
	String job_title;
	int min_salary;
	int max_salar;
}
