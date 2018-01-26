package PSM.DAO;


import java.util.*;

import hibernate.Monitor;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MapDAO extends HibernateDaoSupport{
	
	public void addMonitor(Monitor mot){
		this.getHibernateTemplate().save(mot);
	}
	
	public List<Monitor> monitorList(){
		String hql = "from Monitor mto";
		List<Monitor> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	public void updateMonitor(Monitor mot){
		this.getHibernateTemplate().update(mot);
	}
	
	public List<Monitor> monitorList(int start,int limit){
		String hql = "from Monitor mto";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List<Monitor> list = query.list();
		getSession().flush();
		getSession().close();
		return list;
	}
	
	public int totalMonitor(){
		int count = 0;
		Query q = getSession().createQuery("select count(mot.id) from Monitor mot");
		count = (new Integer(q.uniqueResult().toString()))  
                    .intValue();  
		getSession().close();
		return count;
	}
	public List<Monitor> getCenterPoint(){
		String hql = "from Monitor mto where mto.defaultpos = 1";
		List<Monitor> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	public int searchAllMonitor(String findStr){
		int count = 0;
		String hql = "select count(mot.id) from Monitor mot where id is not null";
		if(findStr != null || findStr.length()>0)
		{
    		String[] strKey = findStr.split(",");
        	for(int i=0; i<strKey.length; i++)
        	{
        		if(strKey[i].length()>0)
        		{
        			hql += " and ( monitorName like '%" + strKey[i] +"%' or longitude like '%" + strKey[i] +"%' or latitude like '%" + strKey[i] +"%' or userName like '%" + strKey[i]
        					+"%' or userPwd like '%" + strKey[i] +"%' or ipaddress like '%" + strKey[i] +"%' or port like '%" + strKey[i] + "%' or channel like '%" + strKey[i] + "%' or remarks like '%" + strKey[i] + "%' )";
        		}
        	}
    	}
		Query q = getSession().createQuery(hql);
		count = (new Integer(q.uniqueResult().toString()))  
                    .intValue();  
		getSession().close();
		return count;
	}
	
	public List<Monitor> monitorSearch(String findStr,int start,int limit){
		String hql = "from Monitor mto where id is not null";
		if(findStr != null || findStr.length()>0)
		{
    		String[] strKey = findStr.split(",");
        	for(int i=0; i<strKey.length; i++)
        	{
        		if(strKey[i].length()>0)
        		{
        			hql += " and ( monitorName like '%" + strKey[i] +"%' or longitude like '%" + strKey[i] +"%' or latitude like '%" + strKey[i] +"%' or userName like '%" + strKey[i]
        					+"%' or userPwd like '%" + strKey[i] +"%' or ipaddress like '%" + strKey[i] +"%' or port like '%" + strKey[i] + "%' or channel like '%" + strKey[i] + "%' or remarks like '%" + strKey[i] + "%' )";
        		}
        	}
    	}
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List<Monitor> list = query.list();
		getSession().flush();
		getSession().close();
		return list;
	}
	
	public void deleteMonitor(Monitor mot){
		this.getHibernateTemplate().delete(mot);
	}
	public void setCenterPoint(int ID){
		Query q = getSession().createQuery("update Monitor mot set mot.defaultpos = 0 where mot.defaultpos = 1");
		q.executeUpdate();
		q = getSession().createQuery("update Monitor mot set mot.defaultpos = 1 where mot.id = "+ID);
		q.executeUpdate();
		getSession().close();
	}
	
	public List getMonitorNameList() {
		//String xmjl = "项目经理";
		String hql = "SELECT DISTINCT(monitorName) from Monitor";
		// String hql = "SELECT DISTINCT(name) from Persondb";
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
}
