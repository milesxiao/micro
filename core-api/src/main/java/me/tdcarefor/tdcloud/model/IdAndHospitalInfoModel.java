package me.tdcarefor.tdcloud.model;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.MappedSuperclass;

/**
 * Created by xxg on 16/8/20.
 */

@MappedSuperclass
public class IdAndHospitalInfoModel extends IdModel{

    @ApiModelProperty(value ="医院主键")
    private String hospitalId;

    @ApiModelProperty(value ="医院名称")
    private String hospitalName;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
