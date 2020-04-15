/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automclctalgorithm;

import static automclctalgorithm.SensorUtility.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Hieu
 */
public class AutoMCLCTAlgorithm {

    /**
     * @param args the command line arguments
     */
     /**
     * @param args the command line arguments
     */
   public static long timeRuning;
    public static double timeLifeOn;
    public static String mPath = "E:\\HIEU\\HOC TAP\\CAO HOC\\Testcase\\NewTestCase\\";
    
    public static void main(String[] args) {
        // TODO code application logic here
        MCLCTAlgorithm3 algorithm = new MCLCTAlgorithm3();
        //MCLCTAlgorithm3 algorithm = new MCLCTAlgorithm3();
        //initData();
        //Chay test case tu 6 den 10
        for (int i = 11; i <= 11; i++) {
            try {
                //Cai dat ten File
                String filename = "test"+i+".INP";
                
                readFile(mPath+filename); //Add URL sensor file with format (
                long begin = System.currentTimeMillis();
                algorithm.run();
                long end = System.currentTimeMillis();
                timeRuning = end - begin;
                timeLifeOn = calculateTotalTime();

            } catch (IOException ex) {
                Logger.getLogger(AutoMCLCTAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                writeResultFile(mPath+"CompareMCLCTResult11.txt", i, timeRuning, timeLifeOn); //Url luu file input duoc sinh ra
                resetData();
            } catch (IOException ex) {
                Logger.getLogger(AutoMCLCTAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Running Finish ");
        JOptionPane.showMessageDialog(null, "Compare MCLCT Algorithm run finished !");
    }
    
    static double calculateTotalTime() {
        double totalTime =0;
        for (int i = 0; i < mListofListTime.size(); i++) {
            Double next = mListofListTime.get(i);
            totalTime+=next;
        }
        return totalTime;
    }
    
    public static void writeResultFile(String filename, int postion, double timeRuning, double timLife) throws IOException {
        FileWriter fw = new FileWriter(filename, true); //the true will append the new data
        fw.write("Test case : "+ postion+"\n");
        fw.write("Sensor="+mListSensorNodes.size() + "  Target="+mListTargetNodes.size()+ "  Sink="+mListSinkNodes.size()+ "  Rs="+mRsValue +"  Rc="+mRcValue +"  MaxHop="+mMaxHopper+ " TimeUnit ="+mUnitTime+"\n");
        fw.write("Time Run = "+ timeRuning+" , Time Life = "+ timLife+"\n");//appends the string to the file
        fw.write("\n");
        writeCoverSetDataToFile(fw);
        fw.write("\n--------------------------------------------\n");
        fw.close();

    }
    
	/*
	 * public static void writeCoverSetDataToFile(FileWriter fw) throws IOException{
	 * //Data mListofListCMLCT and mListofListTime in SensorUtility if (fw == null)
	 * return; for (int i =0; i< mListofListCMLCT.size(); i++) { List<List<Integer>>
	 * coverSet = mListofListCMLCT.get(i); fw.write("CoverSet "+ i+ ": "); for (int
	 * j =0; j< coverSet.size(); j++) { List<Integer> path = coverSet.get(j);
	 * fw.write(path.get(0)+ "-"+path.get(path.size()-1) +"; "); } fw.write("\n"); }
	 * //Clear Data mListofListCMLCT.clear(); mListofListPathTime.clear(); }
	 */
    
    public static void writeCoverSetDataToFile(FileWriter fw) throws IOException{
    	//Data mListofListCMLCT and mListofListTime in SensorUtility
    	if (fw == null) return;
    	System.out.println("writeCoverSetDataToFileMCLCT---------------------------");
		fw.write(""+mListofListCMLCT.size()+"\n");
    	for (int i =0; i< mListofListCMLCT.size(); i++) {
    		List<List<Integer>> coverSet = mListofListCMLCT.get(i);
    		System.out.println("CoverSet ="+i+ " SizeCCP="+coverSet.size()+ " TimeCoverSet="+mListofListTime.get(i));
    		fw.write(""+coverSet.size()+" "+mListofListTime.get(i)+"\n");
    		for (int j =0; j< coverSet.size(); j++) {
    			List<Integer> path = coverSet.get(j);
    			fw.write(""+path.size());
    			for (int k =0; k< path.size(); k++) {
    			   fw.write(" "+path.get(k));
    			}
    			fw.write("\n");
    		}
    	}
    	//Clear Data
    	mListofListCMLCT.clear();
    	mListofListTime.clear();
    }
 
    static void resetData() {
        mListofListSensor.clear();
        mListofListTime.clear();
        timeLifeOn = 0;
    }
}
