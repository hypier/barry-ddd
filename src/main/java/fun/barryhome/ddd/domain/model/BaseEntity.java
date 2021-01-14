package fun.barryhome.ddd.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author heyong
 * @date 2017/1/6
 * 基础实体
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 9160459103011557025L;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonIgnore
    @Setter(AccessLevel.PRIVATE)
    private Date createTime;

    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonIgnore
    @Setter(AccessLevel.PRIVATE)
    private Date updateTime;

    /**
     * 版本号
     */
    @Version
    @Setter(AccessLevel.PRIVATE)
    private Long version;


    @PrePersist
    public void preCreate() {
        createTime = new Date();
        updateTime = createTime;
    }

    @PreUpdate
    public void preUpdate() {
        updateTime = new Date();
    }



}
