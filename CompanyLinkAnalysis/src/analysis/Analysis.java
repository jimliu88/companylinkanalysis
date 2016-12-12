package analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.LinkedTransferQueue;

import model.Company;
import util.FileUtil;
import util.ObjectSort;

public class Analysis {

	private static final String TAG=" ";
	private static HashMap<String, HashMap<String, Integer>> linkToCompanies;
	private static HashMap<String, HashMap<String, Integer>> linkFromCompanies;

	private void fillHashMap(HashMap<String, HashMap<String, Integer>> result,String key, String value){
		if(key.isEmpty() || value.isEmpty()){
			return ;
		}
		
		if(!result.containsKey(key)){
			HashMap<String, Integer> valuesMap=new HashMap<String, Integer>();
			valuesMap.put(value, 1);
			result.put(key,valuesMap);
		}else{
			if(result.get(key).containsKey(value)){
				result.get(key).put(value, result.get(key).get(value)+1);
			}else{
				result.get(key).put(value, 1);
			}
			result.put(key,result.get(key));
			
		}

	}
	
	public void analysis(String data, String result){		
		FileUtil.read(data);
		ArrayList<String> companyLinkList=FileUtil.getFileContent();
		
		System.out.println(Arrays.toString(FileUtil.getFileContentArray()));
		 linkToCompanies=new HashMap<String, HashMap<String, Integer>>();
		//linkFromCompanies=new HashMap<String, HashMap<String, Integer>>();
		
		// fillHashMap(linkToCompanies, "网易", "百度");
		if(companyLinkList!=null && !companyLinkList.isEmpty()){
			for(String link:FileUtil.getFileContentArray()){
				String[] companys=link.split(TAG);
				System.out.println(companys[0]+companys[0].charAt(0));
				fillHashMap(linkToCompanies,companys[0], companys[1]);		
				
				
				//fillHashMap(linkFromCompanies,companys[1], companys[0]);				
			}
		}
		System.out.println(linkToCompanies.toString());
		
		ArrayList<Company> companys=new ArrayList<Company>();
		while(true){
			Iterator  iterator=null;		
			
			iterator=linkToCompanies.entrySet().iterator();
			
			while(iterator.hasNext()){
				Company company=new Company();
				Entry<String, HashMap<String, Integer>> entry=(Entry<String, HashMap<String, Integer>>) iterator.next();				
				company.setName(entry.getKey());
				company.setLinkToCompanySet(entry.getValue());
				company.setLinkToCompanyCount();
				companys.add(company);
			}
			
			/*iterator=linkFromCompanies.entrySet().iterator();
			while(iterator.hasNext()){
				Company company=new Company();
				Entry<String, HashMap<String, Integer>> entry=(Entry<String, HashMap<String, Integer>>) iterator.next();
				company.setName(entry.getKey());
				company.setLinkFromCompanySet(entry.getValue());
				companys.add(company);
			}*/
			
			break;
		}
		
		ObjectSort.sort(companys, "linkToCompanyCount");
		StringBuffer outputResult=new StringBuffer();
		
		{
			for(Company company: companys){
				System.out.println(company.getName()+"\t"+company.getLinkToCompanyCount()+"\t");
				outputResult.append(company.getName()+"\t"+company.getLinkToCompanyCount()+"\t");
				Iterator iterator=company.getLinkToCompanySet().entrySet().iterator();
				while(iterator.hasNext()){
					Entry<String, Integer> entry=(Entry<String, Integer>) iterator.next();
					outputResult.append(entry.getKey());
				}
				outputResult.append("\n");
			}
		}
		
		FileUtil.write(result, outputResult);
	}
	
	public void companyQuery(String name, HashMap<String, HashMap<String, Integer>> targetMap){
		if(name==null || name.isEmpty())
			return;
		if(targetMap==null){
			System.out.println("相关公司列表未初始化!");
			return;
		}
		if(targetMap.containsKey(name)){
			HashMap<String, Integer> relatedCompany=targetMap.get(name);
			Iterator iterator=relatedCompany.entrySet().iterator();
			while(iterator.hasNext()){
				
				System.out.println(iterator.next());
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String data="./data/data.txt";
        String result="./data/result.txt";
        Analysis analysis=new Analysis();
        analysis.analysis(data, result);
        analysis.companyQuery("百度",linkToCompanies);
	}

}
