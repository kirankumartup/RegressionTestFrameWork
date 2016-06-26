package com.company.testsuite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.company.utils.FilePathManager;
import com.company.utils.TestDataSheetName;
import com.helper.utils.ExcelFileManager;


public class TestNGProgrammaticallySuite {
	static ExcelFileManager excelFileManager = new ExcelFileManager(FilePathManager.PATH_RUN_CONFIGURATION);
	static Map<String, String> testNGSuite ;
	static Map<String, String> environmentDetails;
	public static void main(String args[]) {
		runTestNGSuite("RunConf01");
//		Map<String, String> testngParams = new HashMap<String, String>();
//		runTestNGTest(testngParams);
		
//		ExcelFileManager excel = new ExcelFileManager(Constants.PATH_RUN_CONFIGURATION);
//		excel.getRowValue(TestDataSheetName.SHEET_RunConfiguration,"RunConf01");
		
//		System.out.println(OrangeHRMLoginPageCallScript.getTxtUsername());
	}

    private XmlSuite getXmlSuite() {
        XmlSuite suite = new XmlSuite();
        suite.setName("Test Suite");
        return suite;
    }

    private XmlTest getXmlTest(XmlSuite suite) {
        XmlTest test = new XmlTest(suite);
        test.setName("test_with_firefox");
        return test;
    }

    private List<XmlClass> getXmlClasses() {
        List<XmlClass> classez = new ArrayList<XmlClass>();
        classez.add(new XmlClass("com.sayem.pom.scripts.LoginTest"));
        return classez;
    }

  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void runTestNGSuite(String strRunConfigReference) {
		Map<String, String> runConfiguration = excelFileManager.getRowValue(TestDataSheetName.SHEET_RunConfiguration, strRunConfigReference);
		String strEnvironmentDetailsRef = runConfiguration.get(TestDataSheetName.SHEET_RunConfiguration.toUpperCase()+"_ENVIRONMENTDETAILSREF");
		String strTestNGSuiteRef = runConfiguration.get(TestDataSheetName.SHEET_RunConfiguration.toUpperCase()+"_TESTNGSUITEREF");
//		String strTestLinkDetailsRef = runConfiguration.get(TestDataSheetName.SHEET_RunConfiguration+"_TESTLINKDETAILSREF");
//		String strUpdateTestLink = runConfiguration.get(TestDataSheetName.SHEET_RunConfiguration+"_UPDATETESTLINK");
//		String strReportLog = runConfiguration.get(TestDataSheetName.SHEET_RunConfiguration+"_REPORTLOG");
//		String strReportXML = runConfiguration.get(TestDataSheetName.SHEET_RunConfiguration+"_REPORTXML");
//		String strEmailStatus = runConfiguration.get(TestDataSheetName.SHEET_RunConfiguration+"_EMAILSTATUS");
		
		environmentDetails = excelFileManager.getRowValue(TestDataSheetName.SHEET_EnvironmentDetails, strEnvironmentDetailsRef);

		testNGSuite = excelFileManager.getRowValue(TestDataSheetName.SHEET_TestNGSuite, strTestNGSuiteRef);
		String strSuiteName = testNGSuite.get(TestDataSheetName.SHEET_TestNGSuite.toUpperCase() +"_SUITENAME").trim();
		String strSuiteParallel = testNGSuite.get(TestDataSheetName.SHEET_TestNGSuite.toUpperCase() +"_PARALLEL").trim();
		String strSuiteThreadCount = testNGSuite.get(TestDataSheetName.SHEET_TestNGSuite.toUpperCase() +"_THREADCOUNT").trim();
		String strTestNGTestRef = testNGSuite.get(TestDataSheetName.SHEET_TestNGSuite.toUpperCase() +"_TESTNGTEST").trim();
		
		
		Map<String, String> testngSuiteParams = new HashMap<String, String>();
		testngSuiteParams.put("parallel", strSuiteParallel);
		testngSuiteParams.put("thread-count", strSuiteThreadCount);
		
		// Create an instance on TestNG
		TestNG myTestNG = new TestNG();

		// Create an instance of XML Suite and assign a name for it.
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName(strSuiteName);
		mySuite.setParameters(testngSuiteParams);
		
		// Create a list of XmlTests and add the Xmltest you created earlier to
		// it.
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		myTests.addAll(getXmlTest(mySuite, strTestNGTestRef));

		// add the list of tests to your Suite.
		mySuite.setTests(myTests);

		// Add the suite to the list of suites.
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);

		// Set the list of Suites to the testNG object you created earlier.
		myTestNG.setXmlSuites(mySuites);

		// invoke run() - this will run your class.
		myTestNG.run();

	}

	    private static List<XmlTest> getXmlTest(XmlSuite suite, String strTestNGTestRef) {
	    	List<XmlTest> test = new ArrayList<XmlTest>();
	    	String aryTestNGTestRef[] = strTestNGTestRef.split(",");
			for(int i=0;i<aryTestNGTestRef.length;i++){
				Map<String, String> testNGSuite = excelFileManager.getRowValue(TestDataSheetName.SHEET_TestNGTest, aryTestNGTestRef[i]);
				String strTestName = testNGSuite.get(TestDataSheetName.SHEET_TestNGTest.toUpperCase() +"_TESTNAME").trim();
				String strTestParallel = testNGSuite.get(TestDataSheetName.SHEET_TestNGTest.toUpperCase() +"_PARALLEL").trim();
				String strTestThreadCount = testNGSuite.get(TestDataSheetName.SHEET_TestNGTest.toUpperCase() +"_THREADCOUNT").trim();
				String strTestNGClassRef = testNGSuite.get(TestDataSheetName.SHEET_TestNGTest.toUpperCase() +"_TESTNGCLASS").trim();
				
		    	// Create an instance of XmlTest and assign a name for it.
				XmlTest myTest = new XmlTest(suite);
				myTest.setName(strTestName);

				Map<String, String> testngTestParams = new HashMap<String, String>();
				testngTestParams.put("parallel", strTestParallel);
				testngTestParams.put("thread-count", strTestThreadCount);
				myTest.setParameters(testngTestParams);
				myTest.setXmlClasses(getXmlClasses(strTestNGClassRef));
				
			}
	        return test;
	    }

	    private static List<XmlClass> getXmlClasses(String strTestNGClassRef) {
	    	List<XmlClass> classez = new ArrayList<XmlClass>();
	    	String aryTestNGClassRef[] = strTestNGClassRef.split(",");
			for(int i=0;i<aryTestNGClassRef.length;i++){
				Map<String, String> testNGClass = excelFileManager.getRowValue(TestDataSheetName.SHEET_TestNGClass, aryTestNGClassRef[i]);
				String strClassName = testNGClass.get(TestDataSheetName.SHEET_TestNGClass.toUpperCase() +"_CLASSNAME").trim();
				classez.add(new XmlClass(strClassName));
			}
	        return classez;
	    }

}
