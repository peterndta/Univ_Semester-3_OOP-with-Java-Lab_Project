/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dao.TextFileDAO;
import dto.Book;
import java.util.Scanner;
import validation.MyValidation;

/**
 *
 * @author Peter
 */
public class BookList {

    private Book[] list;
    private int count;
    public static final int MAX = 100000;

    public BookList(Book[] list, int count) {
        this.list = list;
        this.count = count;
    }

    public BookList() {
        list = new Book[MAX];
        count = 0;
    }
//=============Methods in List==================================================

    //----------Output Methods--------------------------------------------------
    public void outputBookList() {
        for (int i = 0; i < count; i++) {
            list[i].outputBooktData();
            System.out.println("╚══════════════════════════"
                    + "═══════════════════════════════════╝");
        }
    }

    public void outputInfo(String updateISBN) {
        for (int i = 0; i < count; i++) {
            if (updateISBN.equals(list[i].getISBN())) {
                list[i].outputBooktData();
            }
        }
    }

    //----------Adding Methods--------------------------------------------------
    public boolean addBook(Book temp) {
        if (count > 100) {
            return false;
        }
        list[count] = temp;
        count++;
        return true;
    }

    //----------Update Methods--------------------------------------------------
    public void updateTitle(int location) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        String title = null;
        do {
            try {
                do {
                    sc = new Scanner(System.in);
                    System.out.print("Book Title [begin with char/num]: ");
                    title = sc.nextLine();
                    MyValidation.checkString(title, "^[a-zA-Z0-9]+(?!\\\\).*$");
                    if (title.length() > 32) {
                        System.out.println("\tYour lenght now is " + title.length() + ". Must be under 32 characters!\n");
                    }
                    loop = false;
                } while (title.length() > 32);
            } catch (Exception e) {
                if (e.getMessage() != null) {
                    System.out.println(e.getMessage());
                }
                loop = true;
            }
        } while (loop);
        list[location].setTitle(title);
    }

    public void updatePrice(int location) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        double price = 0;
        do {
            try {
                do {
                    sc = new Scanner(System.in);
                    System.out.print("Price [number only]: ");
                    price = sc.nextDouble();
                    if (price < 1) {
                        System.out.println("\tPrice must > 0!\n");
                    }
                    if (price > 9999999) {
                        System.out.println("\tYour length must be under 7 digits!\n");
                    }
                    loop = false;
                } while (price < 1 || price > 9999999);
            } catch (Exception e) {
                System.out.println("\tInvalid, Try again!\n");
                loop = true;
            }
        } while (loop);
        list[location].setPrice(price);
    }

    public boolean updateBook(String updateISBN) {
        Scanner sc = new Scanner(System.in);
        int location = locateBook(updateISBN);
        // Kiem tra tim thay book can update hay khong
        if (location == -1) {
            System.out.println("\tCannot find book to update!\n");
            return false;
        } else {
            // Xuất ra thông tin Book trước khi update
            outputInfo(updateISBN);
            // Khi tim duoc book, thuc hien update          
            sc = new Scanner(System.in);
            int choice = 0;
            boolean loop = true;
            boolean liLoop = true;
            do {
                System.out.println("┌───────────┐");
                System.out.println("   1. Title          ");
                System.out.println("   2. Price          ");
                System.out.println("   3. Update All");
                System.out.println("   4. Back to Menu   ");
                System.out.println("└───────────┘ ");
                do {
                    try {
                        sc = new Scanner(System.in);
                        System.out.print(" >> Choice: ");
                        choice = sc.nextInt();
                        if (choice < 1 || choice > 4) {
                            System.out.println("\tPlease Input from 1-4!\n");
                            liLoop = true;
                        }
                        liLoop = false;
                    } catch (Exception e) {
                        System.out.println("\tYour choice is inValid! Please Input Again!\n");
                        liLoop = true;
                    }
                } while (liLoop);
                switch (choice) {
                    case 1:
                        updateTitle(location);
                        System.out.println("\tTitle successfully updated!\n");
                        break;
                    case 2:
                        updatePrice(location);
                        System.out.println("\tPrice successfully updated!\n");
                        break;
                    case 3:
                        updateTitle(location);
                        updatePrice(location);
                        System.out.println("\tBook successfully updated!\n");
                        break;
                    case 4:
                        loop = false;
                        break;
                }
            } while (loop);
        }
        return true;
    }

    //----------Remove Methods--------------------------------------------------
    public void removeBook() {
        Scanner sc = new Scanner(System.in);
        String removeBook;
        String confirm;
        int check = 1;
        do {
            System.out.println("\tNOTE: This will help narrow down ISBN to remove by input keyword!");
            System.out.print("Do you want to find ISBN keyword? (yes/no): ");
            confirm = sc.nextLine();

            if (!confirm.equals("yes") && !confirm.equals("no")) {
                System.out.println("\tMust choose yes/no!\n");

            } else if (confirm.equals("yes")) {
                do {
                    System.out.print("ISBNs you look for [Press Enter to list all]: ");
                    String ISBNs = sc.nextLine();
                    if (searchISBNs(ISBNs) == false) {
                        System.out.println("\tThere are none ISBNs contain [" + ISBNs + "] in it!\n");
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

        System.out.print("Book's ISBN to remove: ");
        removeBook = sc.nextLine();
        outputInfo(removeBook);
        do {
            //Kiem tra User co tiep tuc hay khong                            
            System.out.print("Do you want to remove [" + removeBook + "] (yes/no): ");
            confirm = sc.nextLine();
            if (!confirm.equals("yes") && !confirm.equals("no")) {
                System.out.println("\tMust choose yes/no!\n");
            }
        } while (!confirm.equals("yes") && !confirm.equals("no"));

        //Xóa phần tử
        for (int i = 0; i < count; i++) {
            if (list[i].getISBN().equals(removeBook) && confirm.equals("yes")) {
                count--;
                //Đẩy ISBN ra khỏi mảng
                for (int j = i; j < count; j++) {
                    list[j] = list[j + 1];
                }
                System.out.println("\tBook successfully removed!\n");
                check = 0;
                break;
            } else {
                check = 1;
            }
        }
        if (check == 1) {
            System.out.println("\tCannot remove this Book!\n");
        }
    }

    //-------File Managing Methods---------------------------------------------- 
    // Luu danh sach vao File
    public void exportBookList(String filename) {
        TextFileDAO.writeFileBook(list, count, filename);
    }

    // Lay danh sach tu File
    public void importBookList(String filename) {
        int i;
        Book[] loadedList = TextFileDAO.readFileBook(filename);
        for (i = 0; i < loadedList.length; i++) {
            list[i] = loadedList[i];
        }
        count = i;
    }

    //----------Check Methods--------------------------------------------------
    // Kiem tra List trống
    public boolean checkEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    // Kiem tra trùng
    public boolean duplicateISBN(String ISBN) {
        for (int i = 0; i < count; i++) {
            if (list[i].getISBN().equals(ISBN)) {
                return true;
            }
        }
        return false;
    }
    //----------Search Methods--------------------------------------------------

    //Tim Vi Tri sách trong List để thực hiện Update
    public int locateBook(String ISBN) {
        for (int i = 0; i < count; i++) {
            if (list[i].getISBN().equals(ISBN)) {
                return i;
            }
        }
        return -1;
    }

    //Tìm trùng các ISBNs khi xuất ra danh sách ISBNs cho Update va Remove
    public boolean searchISBNs(String ISBNs) {
        int check = 0;
        for (int i = 0; i < count; i++) {
            if (list[i].getISBN().contains(ISBNs)) {
                list[i].outputBooktData();
                check++;
            }
        }
        if (check == 0) {
            return false;
        }
        return true;
    }

    // Tìm kiem Name của Book
    public boolean searchBookName(String bookname) {
        int check = 0;
        if (bookname.isEmpty()) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (list[i].getTitle().contains(bookname)) {
                list[i].outputBooktData();
                check++;
            }
        }
        if (check == 0) {
            return false;
        }
        return true;
    }

    public boolean searchAuthorName(String authorID) {
        int check = 0;
        for (int i = 0; i < count; i++) {
            if (list[i].getAuthorID().equals(authorID)) {
                list[i].outputBooktData();
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
    
//==============END=============================================================
}
