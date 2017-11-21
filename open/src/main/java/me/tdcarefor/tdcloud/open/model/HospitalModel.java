package me.tdcarefor.tdcloud.open.model;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.MappedSuperclass;
import me.tdcarefor.tdcloud.model.IdModel;

/**
 * Created by xsx on 17/3/2.
 */

@MappedSuperclass
public class HospitalModel extends IdModel {

    @ApiModelProperty(value ="医院名称")
    private String name;

    @ApiModelProperty(value ="所在省份编码")
    private String provinceCode;

    @ApiModelProperty(value ="所在城市编码")
    private String cityCode;

    @ApiModelProperty(value ="所在地区编码")
    private String areaCode;

    @ApiModelProperty(value ="所在省份名称")
    private String provinceName;

    @ApiModelProperty(value ="所在城市名称")
    private String cityName;

    @ApiModelProperty(value ="所在地区名称")
    private String areaName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
