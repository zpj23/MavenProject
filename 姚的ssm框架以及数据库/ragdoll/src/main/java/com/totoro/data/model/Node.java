package com.totoro.data.model;

import java.util.List;


public class Node {

	
	private Integer id;
    private Integer pid;
    private String name;
    private String code;
    private List<Node> children;
    
    
    public Node() {
    }

    public Node(Integer id, Integer pid, String name,String code) {
        super();
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.code = code;
    }
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
}
