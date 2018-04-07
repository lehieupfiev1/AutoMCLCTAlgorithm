/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automclctalgorithm;

import static automclctalgorithm.SensorUtility.*;
import java.io.FileWriter;
import java.io.IOException;
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
    public static String mPath = "E:\\HIEU\\CAO HOC\\SensorSimulationProject\\SensorDemo\\";
    
    public static void main(String[] args) {
        // TODO code application logic here
        MCLCTAlgorithm2 algorithm = new MCLCTAlgorithm2();
        //initData();
        //Chay test case tu 6 den 10
        for (int i = 13; i <= 13; i++) {
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
                writeResultFile(mPath+"CompareMCLCTResult2.txt", i, timeRuning, timeLifeOn); //Url luu file input duoc sinh ra
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
        fw.close();

    }
    
    static void resetData() {
        mListofListSensor.clear();
        mListofListTime.clear();
        timeLifeOn = 0;
    }
}
