package com.codecool;

import java.io.*;

public class FileHandler {

    public String[][] read(String file) {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String[][] data = new String[0][0];
            while (br.ready()) {
                int numOfLines = countLines(file);
                int numOfFields = countCols(file);

                data = new String[numOfLines][numOfFields];
                for (int i = 0; i < numOfLines; i++) {
                    data[i] = br.readLine().split(",");
                }
            }
            return data;

        } catch (IOException f) {
            System.out.println(f.getMessage());
            return null;
        }
    }

    public void write(String file, String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            if (text != null) {
                bw.write(text + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int countLines(String file) {
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                br.readLine();
                i++;
            }
            return i;

        } catch (IOException f) {
            System.out.println(f.getMessage());
            return 0;
        }
    }

    private int countCols(String file) {
        int i;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String countFieldString = br.readLine();
            i = countFieldString.length() - countFieldString.replace(",", "").length() + 1;
            return i;

        } catch (IOException f) {
            System.out.println(f.getMessage());
            return 0;
        }
    }
}
