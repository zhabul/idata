package idata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class jUnitTest {
	DevIdata devIdata = new DevIdata();
	ProductionIdata productionIdata = new ProductionIdata();
	
	@Test
	void test_development() {
		/** Testa om DevelopmentData sträng ör korekt") */
		assertTrue(devIdata.dep == "Development");
		
		/** Testa om DevelopmentData enum ör korekt*/
		assertTrue(devIdata.work == MainWork.Digital);
		
	}
	
	@Test
	void test_production(){
		
		/** Testa om ProductonData sträng ör korekt */
		assertTrue(productionIdata.dep == "Production");
		
		/** Testa om ProductonData enum ör korekt*/
		assertTrue(productionIdata.work == MainWork.Paper);
	}

}
