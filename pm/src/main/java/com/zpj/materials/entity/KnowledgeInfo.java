package com.zpj.materials.entity;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/*
 * @ClassName: KnowledgeInfo
 * @Description: TODO(知识类实体对象)
 * @author zpj
 * @date 2019/4/9 16:50
*/
@Entity
@Table(name = "jl_material_knowledge_info")
public class KnowledgeInfo implements java.io.Serializable {
    @ApiModelProperty(value = "主键",name="id", required = true)
    private String id;//主键
    @ApiModelProperty(value = "登记时间",name="registerTime", required = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registertime;
    @ApiModelProperty(value = "内容",name="content", required = false)
    private String content;//备注
    @ApiModelProperty(value = "标题",name="title", required = false)
    private String title;
    @ApiModelProperty(value = "附件路径，多个用逗号分割",name="url", required = false)
    private String url;

    @Id
    @Column(name = "id",  nullable = false, length=36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
