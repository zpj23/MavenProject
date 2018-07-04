package com.zpj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpj.common.BaseDao;
import com.zpj.sys.entity.DictionaryType;
import com.zpj.sys.service.DictionaryService;
@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	@Autowired
	private BaseDao<DictionaryType> dtDao;
	
	public String findJson(Map<String, String> params) {
		StringBuffer s=new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from sys_dictionary_type a where  parentTypeid ='"+params.get("id")+"' order by orderNum asc   ");
		List<Map> list=dtDao.findMapObjBySql(sql.toString(), null, 1, 100);
		if(list!=null&&list.size()>0){
			s.append("[");
			for(int i=0;i<list.size();i++){
				if(i>0){
					s.append(",");
				}
				s.append("{\"id\":\""+list.get(i).get("id")+"\",\"text\":\""+list.get(i).get("typeName")+"\",\"attributes\":{\"pk\":\""+list.get(i).get("id")+"\"},");
				String id=String.valueOf((list.get(i).get("id")));
				Map temp= new HashMap();
				temp.put("id", id);
				List tlist=findChildMenuByPId(temp);
				if(tlist.size()>0){
					s.append("\"state\":\"closed\"}");
				}else{
					s.append("\"state\":\"open\"}");
				}
			}
			s.append("]");
			return s.toString();
		}else{
		  return "[]";
		}
	}

	@Override
	public String findTopJson() {
		StringBuffer s=new StringBuffer();
		//s.append("[{\"id\":1,\"text\":\"My Documents\",\"children\":[{\"id\":11,\"text\":\"Photos\",\"state\":\"closed\",\"children\":[{\"id\":111,\"text\":\"Friend\"},{\"id\":112,\"text\":\"Wife\"},{\"id\":113,\"text\":\"Company\"}]},{\"id\":12,\"text\":\"Program Files\",\"children\":[{\"id\":121,\"text\":\"Intel\"},{\"id\":122,\"text\":\"Java\",\"attributes\":{\"p1\":\"Custom Attribute1\",\"p2\":\"Custom Attribute2\"}},{\"id\":123,\"text\":\"Microsoft Office\"},{\"id\":124,\"text\":\"Games\",\"checked\":true}]},{\"id\":13,\"text\":\"index.html\"},{\"id\":14,\"text\":\"about.html\"},{\"id\":15,\"text\":\"welcome.html\"}]}]");
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from sys_dictionary_type a where  parentTypeid =0 order by orderNum asc   ");
		List<Map> list=dtDao.findMapObjBySql(sql.toString(), null, 1, 100);
		s.append("[");
		for(int i=0;i<list.size();i++){
			if(i>0){
				s.append(",");
			}
			s.append("{\"id\":\""+list.get(i).get("id")+"\",\"text\":\""+list.get(i).get("typeName")+"\",\"attributes\":{\"pk\":\""+list.get(i).get("id")+"\"},\"state\":\"closed\"}");
		}
		s.append("]");
		return s.toString();
	}
	
	public List<Map> findChildMenuByPId(Map params) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from sys_dictionary_type a where  parentTypeid ='"+params.get("id")+"' order by orderNum asc   ");
		List<Map> list=dtDao.findMapObjBySql(sql.toString(), null, 1, 100);
		return list;
	}
	
	public DictionaryType findDicTypeId(String id){
		if(null!=id&&!id.equalsIgnoreCase("")){
			return dtDao.get(Integer.parseInt(id),DictionaryType.class);
		}else{
			return null;
		}
	}
	public void saveDictionaryType(DictionaryType dt){
		if(null==dt.getId()){
			dtDao.add(dt);
		}else{
			DictionaryType temp=findDicTypeId(String.valueOf(dt.getId()));
			if(null!=temp){
				dtDao.merge(dt, String.valueOf(dt.getId()));
			}else{
				dtDao.add(dt);
			}
		}
	}
	
	public void delDictionaryType(String id){
		if(null!=id){
			DictionaryType temp=findDicTypeId(String.valueOf(id));
			dtDao.delete(temp);
			dtDao.executeSql(" delete from sys_dictionary_type where parentTypeid='"+id+"'");
			
		}
	}
	
}
