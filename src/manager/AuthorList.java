/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dao.TextFileDAO;
import dto.Author;
import java.util.Scanner;

/**
 *
 * @author Peter
 */
public class AuthorList {

    private Author[] list;
    private int count;
    public static final int MAX = 100000;

    public AuthorList(Author[] list, int count) {
        this.list = list;
        this.count = count;
    }

    public AuthorList() {
        list = new Author[MAX];
        count = 0;
    }
//=============Methods in List==================================================
    // Lay danh sach tu File

    public void importAuthorList(String filename) {
        int i;
        Author[] loadedList = TextFileDAO.readFileAuthor(filename);
        for (i = 0; i < loadedList.length; i++) {
            list[i] = loadedList[i];
        }
        count = i;
    }

    // Luu danh sach vao File
    public void exportAuthorList(String filename) {
        TextFileDAO.writeFileAuthor(list, count, filename);
    }

    // Lấy authorID để add vào book
    public String outputAuthorID(int order) {
        return list[order].getAuthorID();
    }

    // Trả về số phần tử có trong Author List
    public int sizeAuthorList() {
        return count;
    }

    public void outputAuthorList() {
        for (int i = 0; i < count; i++) {
            list[i].outputAuthorData(i);
            System.out.println(".............................................................");
        }
    }

    public void outputInfo(String deleteID) {
        for (int i = 0; i < count; i++) {
            if (deleteID.equals(list[i].getAuthorID())) {
                list[i].outputAuthorData();
            }
        }
    }

    public String searchAuthorName(String authorname) {
        String temp = null;
        for (int i = 0; i < count; i++) {
            if (list[i].getName().equals(authorname)) {
                return list[i].getAuthorID();
            }
        }
        return temp;
    }

    //Tìm trùng các Author ID khi xuất ra danh sách ID cho Delete
    public boolean searchAuthorID(String authorID) {
        int check = 0;
        for (int i = 0; i < count; i++) {
            if (list[i].getAuthorID().contains(authorID)) {
                list[i].outputAuthorData(i);
                check++;
            }
        }
        if (check == 0) {
            return false;
        }
        return true;
    }

    public boolean checkAuthorID(String authorID) {
        for (int i = 0; i < count; i++) {
            if (list[i].getAuthorID().equals(authorID)) {
                return true;
            }
        }
        return false;
    }

    public void narrowID() {
        Scanner sc = new Scanner(System.in);
        String confirm;
        do {
            System.out.println("\tNOTE: This will help narrow down Author ID to remove by input keyword!");
            System.out.print("Do you want to find Author ID keyword? (yes/no): ");
            confirm = sc.nextLine();

            if (!confirm.equals("yes") && !confirm.equals("no")) {
                System.out.println("\tMust choose yes/no!\n");

            } else if (confirm.equals("yes")) {
                do {
                    System.out.print("IDs you look for [Press Enter to list all]: ");
                    String ISBNs = sc.nextLine();
                    if (searchAuthorID(ISBNs) == false) {
                        System.out.println("\tThere are none IDs contain [" + ISBNs + "] in it!\n");
                    }
                    do {
                        System.out.print("  Try Again?(yes/no): ");
                        confirm = sc.nextLine();
                        if (!confirm.equals("yes") && !confirm.equals("no")) {
                            System.out.println("\tMust choose yes/no!\n");
                        }
                        if (confirm.equals("yes")) {
                            System.out.println("");
                        }
                    } while (!confirm.equals("yes") && !confirm.equals("no"));
                } while (!confirm.equals("no"));
            }
        } while (!confirm.equals("yes") && !confirm.equals("no"));
    }

    public void deleteAuthor(String deleteAuthorID) {
        Scanner sc = new Scanner(System.in);
        String confirm;
        int check = 1;
        outputInfo(deleteAuthorID);
        do {
            //Kiem tra User co tiep tuc hay khong                            
            System.out.print("Do you want to delete [" + deleteAuthorID + "] (yes/no): ");
            confirm = sc.nextLine();
            if (!confirm.equals("yes") && !confirm.equals("no")) {
                System.out.println("\tMust choose yes/no!\n");
            }
        } while (!confirm.equals("yes") && !confirm.equals("no"));

        //Xóa phần tử
        for (int i = 0; i < count; i++) {
            if (list[i].getAuthorID().equals(deleteAuthorID) && confirm.equals("yes")) {
                count--;
                for (int j = i; j < count; j++) {
                    list[j] = list[j + 1];
                }
                System.out.println("\tAuthor successfully deleted!\n");
                check = 0;
                break;
            } else {
                check = 1;
            }
        }
        if (check == 1) {
            System.out.println("\tCannot remove this Author!\n");
        }
    }

//==============END=============================================================
}
