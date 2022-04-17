import java.io.File;
import java.io.FileNotFoundException;
//import java.io.Reader;
import java.util.Scanner;

class StopContagion {
    public static void disconnect(int[][] a, int remove) {
        for (int x = 0; x < a.length; x++) {
            a[remove][x] = 0;
        }
        for (int y = 0; y < a.length; y++) {
            a[y][remove] = 0;
        }

    }

    public static int findHighDegree(int[][] a) {
        int highRow = 0;
        int highCount = 0;
        for (int x = 0; x < a.length; x++) {
            int currentCount = 0;
            for (int y = 0; y < a.length; y++) {
                if (a[x][y] == 1)
                    currentCount++;
            }
            if (currentCount > highCount) {
                highRow = x;
                highCount = currentCount;
            }
        }
        System.out.println((highRow) + " " + highCount);
        return highRow;
    }

    public static int arr2DSize(String Filename) {
        int max = 0;
        try {
            File input = new File(Filename);
            Scanner myReader = new Scanner(input);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splited = data.split("\\s+");
                if (Integer.parseInt(splited[0]) > max)
                    max = Integer.parseInt(splited[0]);
                if (Integer.parseInt(splited[1]) > max)
                    max = Integer.parseInt(splited[1]);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return max;
    }

    public static void main(String args[]) {
        int rad = 2;
        String arg1 = args[0];

        if (arg1.equals("-d")) {

            // program will use degree of each node
            // not collective influence
            int numNodes = Integer.parseInt(args[1]);

            // Number of nodes to be removed

            String inputFile = args[2];
            // name of file describing graph
            int size = arr2DSize(inputFile) + 1;
            int[][] arr2D = new int[size][size];
            // initialize array with correct size
            // now re read input to fill array

            try {
                File input = new File(inputFile);
                Scanner myReader = new Scanner(input);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] splited = data.split("\\s+");
                    int a = Integer.parseInt(splited[0]);
                    int b = Integer.parseInt(splited[1]);

                    arr2D[a][b] = 1;
                    arr2D[b][a] = 1;

                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            // print2D(arr2D, size);
            for (int x = 0; x < numNodes; x++) {
                int highestNode = findHighDegree(arr2D);
                disconnect(arr2D, highestNode);
            }
            // System.out.println("/////////////////////////////////////////////");
            // print2D(arr2D, size);
        } else if (args[3] != null) {
            // Run program by influence of each node
            // but using specific radius
            rad = Integer.parseInt(args[1]);

            String numNodes = args[2];
            // Number of nodes to be removed

            String inputFile = args[3];
            // name of file describing graph

            // *****SAME PROGRAM HERE */

        } else {

            String numNodes = args[1];
            // Number of nodes to be removed

            String inputFile = args[2];
            // name of file describing graph

            // Runs program by influence of each node
            // with default radius rad = 2

            // *****SAME PROGRAM HERE */

        }
    }

    public static void print2D(int[][] a, int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                System.out.print(a[x][y] + " ");
            }
            System.out.println("");
        }
    }

}