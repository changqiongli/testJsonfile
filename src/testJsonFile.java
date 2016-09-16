import java.awt.RenderingHints.Key;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
 
public class testJsonFile {
 
	 
    
	@SuppressWarnings("boxing")
	public static void main(String[] args) {   	  	   
 
        try {
 
        	String JsonContext = new Util().
        			ReadFile("E:\\EclipseWorkspace\\Ecobee\\src\\test_results.json");
        	JSONObject jsonObject = new JSONObject(JsonContext);
        	JSONArray  test_suites = jsonObject.getJSONArray("test_suites");
             
            
            for (int j = 0; j < test_suites.length(); j++) {   
            	JSONObject  test_suite =  (JSONObject) test_suites.get(j);
          	    	 
            	String suite_name=test_suite .getString("suite_name");
            	JSONArray results =  test_suite.getJSONArray("results");  
            	
            	List<JSONObject> pass_results = new ArrayList<JSONObject>();
            	List<JSONObject> fail_results=  new ArrayList<JSONObject>();
            	int number_pass=0;
                int number_fail=0;
                int number_blocked=0;
                int number_moreThan10seconds=0;     
             
            for (int i = 0; i < results.length(); i++) {
            	JSONObject result = (JSONObject) results.get(i);
            	//String test_name=result.optString("test_name");if test_name has prefix or suffix
            	String test_name=result.getString("test_name"); 
            	String time=result.getString("time");
            	String status=result.getString("status");            	
            	
            	if (status.equals("pass")){
            		number_pass ++;                 
                 pass_results.add(results.getJSONObject(i));
                                  
            	}else if (status.equals("fail")){
            		number_fail ++;            		
            	 fail_results.add( results.getJSONObject(i)); 
            	}else if (status.equals("blocked")){
            		number_blocked ++;
                }
            	if (!time.isEmpty()&&Double.valueOf(time) >10){
               		number_moreThan10seconds ++;
           	    }
              }
            //Test suite name 
            System.out.println("Test suite name : " + suite_name);  
            //Print out the total number of tests that passed and their details             
            System.out.println("the total number of tests that passed:"+number_pass);
        Collections.sort( pass_results, new Comparator<JSONObject>() {
                
                private static final String KEY_NAME = "test_name";
                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String valA = new String();
                    String valB = new String();

                    try {
                        valA = (String) a.get(KEY_NAME);
                        valB = (String) b.get(KEY_NAME);
                    } 
                    catch (JSONException e) {
                    	e.printStackTrace(); 
                    }

                    return valA.compareTo(valB);
                    
                }
            });              
			 				 
            for(int i=0;i<pass_results.size();i++){
            System.out.println(pass_results.get(i));
            System.out.println("********************");
            }
            //Print out the total number of tests that failed and their details            
            System.out.println("the total number of tests that failed :"+number_fail);
            Collections.sort( fail_results, new Comparator<JSONObject>() {
                
                private static final String KEY_NAME = "test_name";
                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String valA = new String();
                    String valB = new String();

                    try {
                        valA = (String) a.get(KEY_NAME);
                        valB = (String) b.get(KEY_NAME);
                    } 
                    catch (JSONException e) {
                    	e.printStackTrace(); 
                    }

                    return valA.compareTo(valB);
                    
                }
            });              
            for(int i=0;i<fail_results.size();i++){
                System.out.println(fail_results.get(i));
                System.out.println("********************");
                }
            
            //Print out the total number of test that are block;
            System.out.println("the total number of test that are blocked :"+number_blocked);
            
            //Print out the total number of test that took more than 10 seconds to execute   
                      
            System.out.println("the total number of test that took more than 10 seconds to execute :"+number_moreThan10seconds);
                  
            }  
                       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Util {

	  public String ReadFile(String Path){
	  BufferedReader reader = null;
	  String laststr = "";
	   try{
	    FileInputStream fileInputStream = new FileInputStream(Path);
	    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
	     reader = new BufferedReader(inputStreamReader);
	     String tempString = null;
	     while((tempString = reader.readLine()) != null){
	          laststr += tempString;
	      }
	      reader.close();
	       }catch(IOException e){
	         e.printStackTrace();
	       }finally{
	        if(reader != null){
	     try {
	         reader.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	     }
	  }
	    return laststr;
	   }

	}