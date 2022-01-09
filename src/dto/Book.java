/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Scanner;
import manager.AuthorList;
import validation.MyValidation;

/**
 *
 * @author Peter
 */
public class Book {

    private String ISBN;
    private String title;
    private double price;
    private String authorID;
//=============Construtor=======================================================

    public Book(String ISBN, String title, double price, String authorID) {
        this.ISBN = ISBN;
        this.title = title;
        this.price = price;
        this.authorID = authorID;
    }

    public Book() {
        ISBN = "blank";
        title = "blank";
        price = 0;
        authorID = "blank";
    }
//=============Getter Setter====================================================   

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

//=============End==============================================================   
    public void inputISBN() {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        do {
            try {
                do {
                    sc = new Scanner(System.in);
                    System.out.print("Book ISBN [num-num-...]: ");
                    ISBN = sc.nextLine();
                    MyValidation.checkString(ISBN, "^[0-9][\\d-](.*[0-9])?$");
                    if (ISBN.length() > 13) {
                        System.out.println("\tYour lenght now is " + ISBN.length() + ". Must be under 13 characters!\n");
                    }
                    loop = false;
                } while (ISBN.length() > 13);
            } catch (Exception e) {
                if (e.getMessage() != null) {
                    System.out.println(e.getMessage());
                }
                loop = true;
            }
        } while (loop);
    }

    public void inputTitle() {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
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
    }

    public void inputPrice() {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
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
    }

    public void inputAuthorID() {
        AuthorList authorlist = new AuthorList();
        authorlist.importAuthorList("author.dat");
        Scanner sc = new Scanner(System.in);
        boolean liloop = true;
        int subchoice;
        System.out.println(".............................................................");
        System.out.println(":           Author list in author.dat                       :");
        System.out.println(".............................................................");
        System.out.printf("%-5s %-12s %-36s \n",
                ": No."," : Author ID", "   : Author Name                         :");
        System.out.println(".............................................................");
        authorlist.outputAuthorList();
        do {
            try {
                do {
                    sc = new Scanner(System.in);
                    System.out.print("Your Author choice is: ");
                    subchoice = sc.nextInt();
                    if (subchoice < 1 || subchoice > authorlist.sizeAuthorList()) {
                        System.out.println("\tMust be from 1-" + authorlist.sizeAuthorList() + "!\n");
                    }
                    liloop = false;
                } while (subchoice < 1 || subchoice > authorlist.sizeAuthorList());
                authorID = authorlist.outputAuthorID(subchoice - 1);
            } catch (Exception e) {
                System.out.println("\tInvalid, Must be number!\n");
                liloop = true;
            }
        } while (liloop);
    }

    @Override
    public String toString() {
        return ISBN + "/" + title + "/" + price + '/' + authorID;
    }

    public void outputBooktData() {

        System.out.printf("║ %-15s║ %-34s║ $%-12s║ %-12s\n",
                ISBN, title, price, authorID);
    }

}
