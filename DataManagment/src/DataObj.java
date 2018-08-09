import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class DataObj {
    private Date        initialDate;
    private Date        endingDate;
    private double      initialPressure;
    private double      endingPressure;
    SimpleDateFormat    simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
    File                file1 = new File("res/Environmental_Data_Deep_Moor_2012.txt");
    File                file2 = new File("res/Environmental_Data_Deep_Moor_2013.txt");
    File                file3 = new File("res/Environmental_Data_Deep_Moor_2014.txt");
    File                file4 = new File("res/Environmental_Data_Deep_Moor_2015.txt");
    private long        timeInterval;

    public DataObj() {
    }

    public void getData() throws ParseException, IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList <BufferedReader> bufarr = new ArrayList<>();
        bufarr.add(new BufferedReader(new FileReader(this.file1)));
        bufarr.add(new BufferedReader(new FileReader(this.file2)));
        bufarr.add(new BufferedReader(new FileReader(this.file3)));
        bufarr.add(new BufferedReader(new FileReader(this.file4)));
        String info1;
        String info2;
        String[] dataSet;
        int i;

        i = 0;
        info1 = null;
        info2 = null;
        System.out.print("Please input the initial date: (yyyy_MM_dd HH:mm:ss) ");
        this.initialDate =  simpleDateFormat.parse(sc.nextLine());
        System.out.print("Please input the ending date: (yyyy_MM_dd HH:mm:ss) ");
        this.endingDate = simpleDateFormat.parse(sc.nextLine());
        while (i < bufarr.size() && (info1 == null || info2 == null)) {
            if (info1 == null)
                info1 = getInfoLine(bufarr.get(i), initialDate, this.simpleDateFormat);
            if (info2 == null)
                info2 = getInfoLine(bufarr.get(i), endingDate, this.simpleDateFormat);
            i++;
        }
        if (info1 == null || info2 == null) {
            System.out.println("Dates inputed where not found!");
            System.exit(-1);
        }
        this.timeInterval = (this.endingDate.getTime() - this.initialDate.getTime()) / 3600000;
        if (this.timeInterval == 0) {
            System.out.println("The interval of time between initial date and ending date is not even 1 hour!");
            System.exit(-1);
        }
        if (this.timeInterval < 0) {
            System.out.println("Initial date is before ending date!");
            System.exit(-1);
        }
        dataSet = info1.split("\t");
        this.initialPressure = Double.parseDouble(dataSet[2]);
        dataSet = info2.split("\t");
        this.endingPressure = Double.parseDouble(dataSet[2]);
    }

    public static String getInfoLine(BufferedReader bf, Date date, SimpleDateFormat dateFormat) throws IOException {
        String res;
        while ((res = bf.readLine()) != null) {
            if (res.contains(dateFormat.format(date))) {
                return (res);
            }
        }
        return (null);
    }

    public double calculateSlope() {
        double slope;
        slope = this.endingPressure - this.initialPressure;
        slope = slope / this.timeInterval;
        return slope;
    }
}
