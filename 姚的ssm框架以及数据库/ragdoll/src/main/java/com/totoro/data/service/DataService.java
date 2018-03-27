package com.totoro.data.service;

import java.util.List;

import com.totoro.core.utils.MyPage;
import com.totoro.data.model.Node;

public interface DataService {

	void gainLhbData();

	MyPage findDataPage(String selectname, Integer page, Integer rows);

	List<Node> getAllDirList();

}
