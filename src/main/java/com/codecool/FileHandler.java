package com.codecool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

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

        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void append(String file, String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            if (text != null) {
                bw.append(text + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int countLines(String file) {
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                br.readLine();
                i++;
            }
            return i;

        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
            return 0;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int countCols(String file) {
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String countFieldString = br.readLine();
            i = countFieldString.length() - countFieldString.replace(",", "").length() + 1;
            return i;

        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
            return 0;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
