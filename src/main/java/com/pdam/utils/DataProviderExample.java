package com.pdam.utils;


import com.thoughtworks.selenium.*;

import org.junit.AfterClass;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.*;

import java.io.File;
import jxl.*; 


public class DataProviderExample extends SeleneseTestCase{
    
    @BeforeClass
    public void setUp() throws Exception {
        SeleniumServer seleniumserver=new SeleniumServer();
        seleniumserver.boot();
        seleniumserver.start();
        setUp("http://www.imdb.com/", "*firefox");
        selenium.open("/");
        selenium.windowMaximize();
        selenium.windowFocus();
    }

    
    @DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("test\\Resources\\Data\\data1.xls",
                "DataPool", "imdbTestData1");
        return(retObjArr);
    }
    
    @Test (dataProvider = "DP1")
    public void testDataProviderExample(String movieTitle, 
            String directorName, String moviePlot, String actorName) throws Exception {    
        //enter the movie title 
        selenium.type("q", movieTitle);
        //they keep switching the go button to keep the bots away
        if (selenium.isElementPresent("nb15go_image"))
            selenium.click("nb15go_image");
        else
        selenium.click("xpath=/descendant::button[@type='submit']");
        
        selenium.waitForPageToLoad("30000");
        //click on the movie title in the search result page
        selenium.click("xpath=/descendant::a[text()='"+movieTitle+"']");
        selenium.waitForPageToLoad("30000");
        //verify director name is present in the movie details page 
        verifyTrue(selenium.isTextPresent(directorName));
        //verify movie plot is present in the movie details page
        verifyTrue(selenium.isTextPresent(moviePlot));
        //verify movie actor name is present in the movie details page
        verifyTrue(selenium.isTextPresent(actorName));
    }
    
    @AfterClass
    public void tearDown(){
        selenium.close();
        selenium.stop();
    } 
    
    @DataProvider(name = "DP1")
    public Object[][] createData() {
        Object[][] retObjArr={{"001","Jack","London"},
                            {"002","John","New York"},
                            {"003","Mary","Miami"},
{"004","George","california"}};
        return(retObjArr);
    }



@Test (dataProvider = "DP1")
    public void testEmployeeData(String empid, String empName, String city){
selenium.type("id", empid);
selenium.type("name", empName);
selenium.click("submit_button");
selenium.waitForPageToLoad("30000");
assertTrue(selenium.isTextPresent(city));

}


    public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception{
        String[][] tabArray=null;
        
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            Sheet sheet = workbook.getSheet(sheetName); 
            int startRow,startCol, endRow, endCol,ci,cj;
            Cell tableStart=sheet.findCell(tableName);
            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();

            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
            ci=0;

            for (int i=startRow+1;i<endRow;i++,ci++){
                cj=0;
                for (int j=startCol+1;j<endCol;j++,cj++){
                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
                }
            }
        

        return(tabArray);
    }
    
    
}//