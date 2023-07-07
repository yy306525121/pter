package cn.codeyang.pter.common.core.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author yangzy
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDate createTime;

    @CreatedBy
    private String createBy;

    @LastModifiedDate
    private LocalDate updateTime;

    @LastModifiedBy
    private String updateBy;

    /**
     * 版本号，用于乐观锁
     * 默认：1
     */
    @Basic
    private Integer version;
}
