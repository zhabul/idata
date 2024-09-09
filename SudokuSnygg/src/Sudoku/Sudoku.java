package Sudoku;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
* klasen anvönds för att returnera flera värden
*/
class structFreeSlot
{

	Integer  row =-1;
    int collumn=-1;
    boolean foundSlotFlag=false;
    
 };

public class Sudoku {
	
	private Integer[][] tempMatrix;
	
	Sudoku(Integer[][] tempMatrix)
	{
		/*bara för att använda konstruktor*/
		this.tempMatrix=tempMatrix;
	}
	
	private void run()
	{
			
		/* convert to List*/
		List<List<Integer>> listMatrix = Arrays.stream(tempMatrix).map(Arrays::asList).collect(Collectors.toList());
		
		/*randomioze*/
		Collections.shuffle(listMatrix);

		/* do console printouts*/
		if (compleateSudoku(listMatrix)) {
			for (List<Integer> row0List : listMatrix) {
				System.out.println(Arrays.toString(row0List.toArray()));
			}

		}
		
	}
	
	/**
	* Returnera true om det INTE finns nummer i den rad vi kollar
	* annars returnera false 
	* @param  number  nummer vi testar med
	* @param  rowToCheck nummer av rad vi kollar mot
	* @param  sudokuArray Sudoku matris vi har hitils
	* @return true or false, true om det INTE finns i raden
	*/
	private boolean checkIfNumberIsNotInRow(Integer number, int rowToCheck, List<List<Integer>> sudokuArray)	
	{
		
		if (sudokuArray.get(rowToCheck).contains(number)){
			return false;
		}
		return true;

			
	}
	
	/**
	* Returnera true om det finns inte nummer i den kolumen vi kollar
	* annars returnera false 
	* @param  number är nummer vi testar med
	* @param  collumnToCheck nummer av kolumn i matrisen vi kollar mot
	* @param  sudokuArray Sudoku matris vi har hitils
	* @return true or false, true om det INTE finns i kolumn
	*/
	private boolean checkIfNumberIsNotInCollumn(int number, int collumnToCheck, List<List<Integer>> sudokuArray)
	{
		for (int collumn = 0; collumn < sudokuArray.size(); collumn++)  
	    {  
	 
	    	if (sudokuArray.get(collumn).get(collumnToCheck) == number)   
			{  
			    return false;  
			}  
	    }
			
		return true;
			
	}
	/**
	* Returnera true om det finns inte nummer i den 2x2 nät vi kollar
	* annars returnera false 
	* Att få 2x2 matris from definerad kolumn och rad först vi måste ta fram antal element
	* Sen med modus tar vi fram start positioner för x och y, slutposition är startposition+anatal elemnt
	* Sen loopar vi fram och kollar om angivna nummer finns i 2x2 matrisen. 
	* @param  number  nummer vi testar med
	* @param  rowToCheck nummer av rad vi kollar mot och bygger 2x2 matris
	* @param  collumnToCheck nummer av kolumn i matrisen vi kollar mot mot och bygger 2x2 matris
	* @param  sudokuArray Sudoku matris vi har hitils
	* @return true or false, true om det INTE finns i kolumn
	*/
	private boolean checkIfNumberIsNotInSubGrid(int number, int rowToCheck, int collumnToCheck, List<List<Integer>> sudokuArray)
	{

		int maxElementInOneDimension = sudokuArray.size();
		int maxElementOfSubGrid = (int)Math.sqrt(maxElementInOneDimension);  
	    int startPositionOfsubGridRow = rowToCheck - rowToCheck % maxElementOfSubGrid;  
	    int startPositionOfsubGridCollumn = collumnToCheck - collumnToCheck % maxElementOfSubGrid;  
    
	    for (int i = startPositionOfsubGridRow; i < (startPositionOfsubGridRow + maxElementOfSubGrid); i++)  
	    {  
		    for (int j = startPositionOfsubGridCollumn; j < startPositionOfsubGridCollumn + maxElementOfSubGrid; j++)  
		    {  
		
		    	if (sudokuArray.get(i).get(j)== number)  
		    	{  
		    		return false;  
		    	}  
		    }  
	    }  
       
    return true;  
			
	}
	/**
	* Hitta lediga platser i matris, dvs det där finns 0
	* returnera vilken position i matrisen är ledig, dvs x, y
	* eller om det finns inte lediga platser returnera false flagga
	* klasen structFreeSlot används för att returnera flera värden
	* @param  sudokuArray Sudoku 2d matris som vi ska loopa- genom
	* @return klasen structFreeSlot, två värden och true/false flagga
	*/
	
	private structFreeSlot getFreeSlot(List<List<Integer>> sudokuArray) {

		structFreeSlot xy = new structFreeSlot();
		for (List<Integer> rowItem : sudokuArray) {
			
			for (Integer collumItem : rowItem) {
				
				if (collumItem==0) {
					xy.row = sudokuArray.indexOf(rowItem);  
    				xy.collumn = rowItem.indexOf(collumItem);  
    				xy.foundSlotFlag = true;  
    				break;  
				}
			}
		    if (xy.foundSlotFlag)   
		    {  
		    	break;  
		    }

		}

		return xy;	
	}
	
	/**
	* Huvud funktion som startar alla andra, lopa genom kanddater 1,2,3,4
	* random funktioner kunde inte anvöndas eftersom den ger samma resulat många gånger
	* annars, kör om funktionen recrusiv, med en ny kandidat tills vi når slutet
	* @param  sudokuArray Sudoku 2d matris som vi ska loopa- genom
	* @return true om vi alla 0-or är omskrivna i matrisen och
	* vi hittade plats åt alla annars false, det var inte möjligt
	*/
	private  boolean compleateSudoku(List<List<Integer>> sudokuArray)  
    {  

		int maxElementInOneDimension = sudokuArray.size();
		boolean check = false;
		structFreeSlot xy = getFreeSlot(sudokuArray);
	    
		
		if (!xy.foundSlotFlag)  
	    {  
			/* vi är färdiga gå ut från recusive funktion*/
			return true;  
	    } 
	    
	    
		
		for (int candidate = 1; candidate <= maxElementInOneDimension; candidate++)  
		{  
			check = checkIfNumberIsNotInRow(candidate, xy.row, sudokuArray);
			check &= checkIfNumberIsNotInCollumn(candidate, xy.collumn, sudokuArray);
			check &= checkIfNumberIsNotInSubGrid(candidate, xy.row,xy.collumn,sudokuArray);
			
			/* check ==true = hittade inga duplicerade värde*/
			if (check)  {
				
				/*tilldela ny kandidat 1-4*/
				sudokuArray.get(xy.row).set(xy.collumn, candidate);
			    if (compleateSudoku(sudokuArray)){  
			    	return true;  
			    } 
			    else {
			    	sudokuArray.get(xy.row).set(xy.collumn, 0); 
			    }
			}  
		}  
		return false;  
    }  
	
	public static void main(String[] args)
	{
		/*orginal exampel from uppgiften*/
		Integer[][] tempMatrix = new Integer[][] { 
			{ 0, 0, 0, 2},
			{ 3, 0, 0, 1},
			{ 1, 0, 0, 3},
			{ 2, 0, 0, 0}} ;
			
		Sudoku sdk = new Sudoku(tempMatrix);
		sdk.run();
	}
}


