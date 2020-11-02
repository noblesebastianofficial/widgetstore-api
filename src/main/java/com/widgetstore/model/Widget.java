package com.widgetstore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
/**
 * @author Noble Sebastian
 * @version 1.0
 */
@Entity
@Table(name = "Widget")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Widget {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Integer id;

	@Version
	@Column(name = "VERSION_ID", nullable = false, columnDefinition = "int default 0")
	private Integer versionId;

	@Column(name = "X_COORD", nullable = false)
	private Integer xCoordinate;

	@Column(name = "Y_COORD", nullable = false)
	private Integer yCoordinate;

	@Column(name = "Z_COORD", nullable = false)
	private Integer zCoordinate;

	@Column(name = "WIDTH", nullable = false)
	private Integer width;

	@Column(name = "HEIGHT", nullable = false)
	private Integer height;

	@UpdateTimestamp
	@Column(name = "LAST_UPDATE_DATE", columnDefinition = "datetime default current_timestamp", nullable = false, insertable = false)
	private LocalDateTime lastUpdateTimestamp;

}
