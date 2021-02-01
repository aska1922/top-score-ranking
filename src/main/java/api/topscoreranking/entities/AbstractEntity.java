package api.topscoreranking.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@MappedSuperclass
@Getter
@Setter
public class AbstractEntity {

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@JsonIgnore
	@CreationTimestamp
	@Column(updatable = false)
	protected Instant created;
	
}
