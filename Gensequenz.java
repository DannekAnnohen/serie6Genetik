package serie6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Gensequenz {
	
	private StringBuilder gensequenz = new StringBuilder();
	
	
	
	
	
	public HashMap<Integer,List<Integer>> knuthSuche(String motiv){
		
		
		HashMap<Integer, List<Integer>> result = new HashMap<Integer, List<Integer>>();
		
		 int i = 0;     
		 int j = -1;    
		 int[] resultA = new int[motiv.length() +1];
		 List<Integer> positionen = new ArrayList<Integer>();
		 
		
				 resultA[0] = j;  

				 while (i < motiv.length()){     
				 
				    while (j >= 0 && motiv.charAt(j) != motiv.charAt(i)){   
				                                  
				      j = resultA[j];                   
				    
				    }			 
				           
				 
				    i = i+1;               
				    j = j+1;          
				    resultA[i] = j;              
				 
				 }
				 
				 // erste Phase vorbei
				 
				 

				 i = 0;  
				 j = 0;  

				 while (i < gensequenz.length()){  
				 
				    while (j >= 0 && gensequenz.charAt(i) != motiv.charAt(j)){     
				                                      
				       j = resultA[j];                 
				    }
				 
				    i = i + 1;          
				    j = j + 1;            
				 
				    if (j == motiv.length()){         
				       positionen.add(i - motiv.length());        
				    
				       j = resultA[j];           
				    
				    }
				 
				 }
				 
				 
				 Integer anzahl = new Integer(positionen.size());
				 result.put(anzahl, positionen);
				 return result;
	}
	
	
	
	public HashMap<Integer,ArrayList<Integer>> suche1(String motiv){
		
		ArrayList<Integer> positionen = new ArrayList<Integer>();
		
	
			int a = 0;
			
			while (a != -1){
				a = gensequenz.indexOf(motiv, a);
				if ( a == -1){
					break;
				}
				positionen.add(a);
				++a;
			}			
		
		
		Integer anzahl = new Integer(positionen.size());
		
		HashMap<Integer, ArrayList<Integer>> result = new HashMap<Integer, ArrayList<Integer>>();
		result.put(anzahl, positionen);
		
		return result;
	}
	
	
	
	public Gensequenz(String[] args){ 
		
		for (int i = 0; i < args.length; ++i){
			
			String temp = args[i].toUpperCase();
			this.gensequenz.append(temp.toCharArray());
		}
		
	}	
	
	public StringBuilder getGensequenz(){
		return this.gensequenz;
	}
	
	public String toString(){
		return gensequenz.toString();
	}
}
