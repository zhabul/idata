package idata;


enum MainWork {
	  Paper,
	  Digital
}

interface IIData {
	  public MainWork getMainWorgk(); 
	  public String getDepartment(); 
	}

abstract class BaseIdata implements IIData {
	 protected String dep;
	 protected MainWork work; 
	 
	  public String getDepartment() {
		return dep;
	  }
	  public MainWork getMainWorgk() {
		return work;
	  }
	}

class ProductionIdata extends BaseIdata {
	/** konstruktor */
	ProductionIdata()
	{
		dep="Production";
		work=MainWork.Paper;
	}

}
class DevIdata extends BaseIdata {
	/** konstruktor */
	DevIdata()
	{
		dep= "Development";
		work = MainWork.Digital;
	}
}
class test {
	  public static void main(String[] args) {
		  // Inget att göra
	  }
	}
