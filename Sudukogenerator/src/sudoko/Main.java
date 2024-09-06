package sudoko;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main   {
	   
	public static void main(String[] args)
	{
		int [] sudokuNr= new int[16];
		Main instance = new Main(); 
		while(true)
		{
			Integer[][] tempMatrix = new Integer[][] { { 1, 0, 0, 0},{ 0, 2, 0, 0},{ 0, 0, 3, 0},{ 0, 0, 0, 4}} ;
			int size = tempMatrix.length; 
			for (int i=0; i<size; i++) {
	      		List<Integer> intList = Arrays.asList(tempMatrix[i]);
	    		Collections.shuffle(intList);
	    		intList.toArray(tempMatrix[i]);
			}
	        
			  
		   	if (instance.generateSudoku(tempMatrix, size))  
		   	{  
		   		int x=0;
		   		for (int i=0; i<size; i++) {
		   			for (int j=0; j<size; j++) {
		   				sudokuNr[x]=tempMatrix[i][j];
		   				x++;
		   			}
		   		}
		   		drawWindow dw = new drawWindow(sudokuNr);
		   		break;
		   	}  else
		   	{
		   		System.out.println("Not found, try again");
		   	}
		}

   } 
		


    public boolean checkIfOk(int row, int collumn, Integer[][] sudokuMatrix, int current_nr)  
    {  
   
	    for (int i = 0; i < sudokuMatrix.length; i++)  
	    {  
	 
	    	if (sudokuMatrix[row][i] == current_nr)   
			{  
			    return false;  
			}  
	    }  
      
	    for (int j = 0; j < sudokuMatrix.length; j++)  
	    {  

		    if (sudokuMatrix[j][collumn] ==current_nr)  
		    {  
		    	return false;  
		    }  
	    }  
      
		int rutenur = (int)Math.sqrt(sudokuMatrix.length);  
	    int MatrixRow = row - row % rutenur;  
	    int MatrixCollum = collumn - collumn % rutenur;  
    
	    for (int i = MatrixRow; i < MatrixRow + rutenur; i++)  
	    {  
		    for (int j = MatrixCollum; j < MatrixCollum + rutenur; j++)  
		    {  
		
		    	if (sudokuMatrix[i][j] == current_nr)  
		    	{  
		    		return false;  
		    	}  
		    }  
	    }  
       
    return true;  
    } 
    public boolean generateSudoku(Integer[][] sudokuMatrix, int current_nr)  
    {  
	    int row=0;  
	    int collumn=0;
	    
	    int isfree = 1;  
	    for (int i = 0; i < current_nr; i++)  
	    {  
	    	for (int j = 0; j < current_nr; j++)  
	    	{  
	    			if (sudokuMatrix[i][j] == 0)  
	    			{  
	    				row = i;  
	    				collumn = j;  
	    				isfree = 0;  
	    				break;  
	    			}  
	    	}  
	      
		    if (isfree==0)   
		    {  
		    	break;  
		    }  
		}  
	    if (isfree==1)  
	    {  
	    	return true;  
	    }  

		for (int i = 1; i <= current_nr; i++)  
		{  
			if (checkIfOk(row, collumn, sudokuMatrix,i))  
			    {  
					sudokuMatrix[row][collumn] = i;  
			    	if (generateSudoku(sudokuMatrix, current_nr))  //recusive
			    	{  
			    		return true;  
			    	}	  
			    	else  
			    	{  
			      		sudokuMatrix[row][collumn] = 0;  
			    	}  
			    }  
			}  
	    return false;  
    }  
    
}
