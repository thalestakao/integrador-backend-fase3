package br.com.icarros.icontas.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class AbstractEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Version
	protected Integer version;
	
	@CreatedDate
	protected LocalDateTime creationDateTime;
	
	@CreatedBy
	protected String createdBy;
	
	@LastModifiedDate
	protected LocalDateTime lastModifiedDate;
	
	@LastModifiedBy
	protected String lastModifiedBy;
	
	
	public AbstractEntity() {
	}

	public AbstractEntity(Long id, Integer version) {
		super();
		this.id = id;
		this.version = version;
	}




	@PrePersist
	public void setCreationDate() {
		if (this.id == null) {
			this.creationDateTime = LocalDateTime.now();
		}
	}

}
