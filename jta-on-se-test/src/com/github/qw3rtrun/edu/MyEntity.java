package com.github.qw3rtrun.edu;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class MyEntity {
	
	@Id
	Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
