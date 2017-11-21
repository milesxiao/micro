package me.tdcarefor.tdcloud.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by xxg on 16/8/20.
 */

@MappedSuperclass
public class IdModel implements Serializable {

    @ApiModelProperty(value ="主键")
    @Id
    @Column
    @TargetAggregateIdentifier
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
