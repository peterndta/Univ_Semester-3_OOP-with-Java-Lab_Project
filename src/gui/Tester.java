/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Scanner;
import dto.Book;
import manager.AuthorList;
import manager.BookList;

/**
 *
 * @author Peter
 */
public class Tester {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookList booklist = new BookList();
        AuthorList authorlist = new AuthorList();
        authorlist.importAuthorList("author.dat");
        booklist.importBookList("book.dat");
        boolean loop = true;
        int choice = 0;
        do {
            System.out.println("╔══════════════════════╗");
            System.out.println("║        __  ________  ____  __        ║\n"
                    + "║       /  |/  / __/ |/ / / / /        ║\n"
                    + "║      / /|_/ / _//    / /_/ /         ║\n"
                    + "║     /_/  /_/___/_/|_/\\____/          ║");
            System.out.println("╠══════════════════════╣");
            System.out.println("║ 1. Show the book list                ║");
            System.out.println("║ 2. Add new book                      ║");
            System.out.println("║ 3. Update book                       ║");
            System.out.println("║ 4. Delete book                       ║");
            System.out.println("║ 5. Search book                       ║");
            System.out.println("║ 6. Store data to file                ║");
            System.out.println("║ 7. Delete Author                       ║");
            System.out.println("║--------------------------------------║");
            System.out.println("║ 8. Quit                              ║");
            System.out.println("╚══════════════════════╝");
            boolean liLoop = true;
            do {
                try {
                    sc = new Scanner(System.in);
                    System.out.print(" >> Choice: ");
                    choice = sc.nextInt();
                    if (choice < 1 || choice > 8) {
                        System.out.println("\tPlease Input from 1-8!\n");
                    }
                    liLoop = false;
                } catch (Exception e) {
                    System.out.println("\tYour choice is inValid! Please Input Again!\n");
                    liLoop = true;
                }
            } while (liLoop);
            System.out.print("\n");
            switch (choice) {
                case 1:
                    if (booklist.checkEmpty() == true) {
                        System.out.println("\tThe list is empty!\n");
                        break;
                    } else {
                        System.out.println("╔═══════════════════════"
                                + "══════════════════════════════════════╗");
                        System.out.printf("%-15s %-34s %-12s %-12s %-36s \n",
                                "║ BookISBN", " ║ Title", "  ║ Price", "    ║ Author ID", " ");
                        System.out.println("╠════════════════════════"
                                + "═════════════════════════════════════╝");
                        booklist.outputBookList();
                    }
                    break;

                case 2:
                    String confirm;
                    do {
                        Book book = new Book();
                        sc = new Scanner(System.in);
                        book.inputISBN();
                        //Kiem tra trung va them Book vao List
                        if (booklist.duplicateISBN(book.getISBN()) == true) {
                            System.out.println("\tFailed to add! Book already has in list!\n");
                        } else {
                            book.inputTitle();
                            book.inputPrice();
                            book.inputAuthorID();
                            System.out.println("\tBook successfully added!\n");
                            booklist.addBook(book);
                        }

                        do {
                            sc = new Scanner(System.in);
                            //Kiem tra User co tiep tuc hay khong    
                            System.out.print("Do you want to continue?(yes/no): ");
                            confirm = sc.nextLine();
                            if (!confirm.equals("yes") && !confirm.equals("no")) {
                                System.out.println("\tMust choose yes/no!\n");
                            }
                            if (confirm.equals("yes")) {
                                System.out.print("\n");
                            }
                        } while (!confirm.equals("yes") && !confirm.equals("no"));
                    } while (!confirm.equals("no"));
                    System.out.println("\tReturning Menu!\n");

                    break;

                case 3:
                    do {
                        sc = new Scanner(System.in);
                        if (booklist.checkEmpty() == true) {
                            System.out.println("\tThe list is empty!");
                            break;
                        } else {
                            do {
                                System.out.println("\tNOTE: This will help narrow down ISBN to update by input keyword!");
                                System.out.print("Do you want to find ISBN keyword? (yes/no): ");
                                confirm = sc.nextLine();

                                if (!confirm.equals("yes") && !confirm.equals("no")) {
                                    System.out.println("\tMust choose yes/no!\n");

                                } else if (confirm.equals("yes")) {
                                    do {
                                        System.out.print("ISBNs you look for [Press Enter to list all]: ");
                                        String ISBNs = sc.nextLine();
                                        if (booklist.searchISBNs(ISBNs) == false) {
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

                            //Nhap ISBN cần update
                            System.out.print("Book's ISBN to update: ");
                            String updateISBN = sc.nextLine();
                            if (booklist.updateBook(updateISBN) == true) {
                                System.out.println("\tFinished Update!\n");
                            }
                        }

                        do {
                            //Kiem tra User co tiep tuc hay khong                            
                            System.out.print("Do you want to continue?(yes/no): ");
                            confirm = sc.nextLine();
                            if (!confirm.equals("yes") && !confirm.equals("no")) {
                                System.out.println("\tMust choose yes/no!\n");
                            }
                            if (confirm.equals("yes")) {
                                System.out.println("");
                            }
                        } while (!confirm.equals("yes") && !confirm.equals("no"));
                    } while (!confirm.equals("no"));
                    System.out.println("\tReturning Menu!\n");
                    break;

                case 4:
                    do {
                        sc = new Scanner(System.in);
                        if (booklist.checkEmpty() == true) {
                            System.out.println("\tThe list is empty!");
                            break;
                        } else {
                            //Xoa phan tu
                            booklist.removeBook();
                        }

                        do {
                            //Kiem tra User co tiep tuc hay khong                            
                            System.out.print("Do you want to continue?(yes/no): ");
                            confirm = sc.nextLine();
                            if (!confirm.equals("yes") && !confirm.equals("no")) {
                                System.out.println("\tMust choose yes/no!\n");
                            }
                            if (confirm.equals("yes")) {
                                System.out.println("");
                            }
                        } while (!confirm.equals("yes") && !confirm.equals("no"));
                    } while (!confirm.equals("no"));
                    booklist.exportBookList("book.dat");
                    System.out.println("\tReturning Menu!\n");
                    break;

                case 5:
                    do {
                        sc = new Scanner(System.in);
                        boolean submenu = true;
                        do {
                            System.out.println("┌─────────────────┐\n");
                            System.out.println("   1. Search by book name          ");
                            System.out.println("   2. Search by author name");
                            System.out.println("   3. Back to Menu   \n");
                            System.out.println("└─────────────────┘ ");
                            do {
                                try {
                                    sc = new Scanner(System.in);
                                    System.out.print(" >> Choice: ");
                                    choice = sc.nextInt();
                                    if (choice < 1 || choice > 3) {
                                        System.out.println("\tPlease Input from 1-3!\n");
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
                                    sc = new Scanner(System.in);
                                    if (booklist.checkEmpty() == true) {
                                        System.out.println("\tThe list is empty!");
                                        break;
                                    } else {
                                        //Nhap title can tim
                                        System.out.print("Book's title: ");
                                        String findName = sc.nextLine();
                                        if (booklist.searchBookName(findName) == false) {
                                            System.out.println("\tNo matching results were found!\n");
                                        }
                                    }
                                    break;
                                case 2:
                                    System.out.println(".............................................................");
                                    System.out.println(":           Author list in author.dat                       :");
                                    System.out.println(".............................................................");
                                    System.out.printf("%-5s %-12s %-36s \n",
                                            ": No.", " : Author ID", "   : Author Name                         :");
                                    System.out.println(".............................................................");
                                    authorlist.outputAuthorList();
                                    sc = new Scanner(System.in);
                                    System.out.print("\nAuthor's name: ");
                                    String authorname = sc.nextLine();
                                    if (booklist.searchAuthorName(authorlist.searchAuthorName(authorname)) == false) {
                                        System.out.println("\tNo book has this Author name!\n");
                                    }
                                    break;
                                case 3:
                                    submenu = false;
                                    System.out.println("\tFinished Searching!\n");
                                    break;
                            }
                        } while (submenu);

                        sc = new Scanner(System.in);
                        do {
                            //Kiem tra User co tiep tuc hay khong                            
                            System.out.print("Do you want to continue?(yes/no): ");
                            confirm = sc.nextLine();
                            if (!confirm.equals("yes") && !confirm.equals("no")) {
                                System.out.println("\tMust choose yes/no!\n");
                            }
                        } while (!confirm.equals("yes") && !confirm.equals("no"));

                    } while (!confirm.equals("no"));
                    System.out.println("\tReturning Menu!\n");
                    break;

                case 6:
                    //Kiem tra list trống 
                    if (booklist.checkEmpty() == true) {
                        System.out.println("\tThe list is empty!\n");
                        break;
                    } else {
                        // Xuat File
                        booklist.exportBookList("book.dat");
                        System.out.println("\tBook successfully stored!\n");
                    }
                    break;
                case 7:
                    do {
                        authorlist.narrowID();
                        sc = new Scanner(System.in);
                        System.out.print("Author's ID to delete: ");
                        String deleteAuthorID = sc.nextLine();
                        if (authorlist.checkAuthorID(deleteAuthorID) == false) {
                            System.out.println("\tThere is no such ID!\n");
                        }
                        if (booklist.checkAuthorID(deleteAuthorID) == true) {
                            System.out.println("\tThis author has a book in the store, cannot delete this author!\n");
                        }
                        if (booklist.checkAuthorID(deleteAuthorID) == false && authorlist.checkAuthorID(deleteAuthorID) == true) {
                            authorlist.deleteAuthor(deleteAuthorID);
                        }
                        do {
                            //Kiem tra User co tiep tuc hay khong                            
                            System.out.print("Do you want to continue?(yes/no): ");
                            confirm = sc.nextLine();
                            if (!confirm.equals("yes") && !confirm.equals("no")) {
                                System.out.println("\tMust choose yes/no!\n");
                            }
                            if (confirm.equals("yes")) {
                                System.out.println("");
                            }
                        } while (!confirm.equals("yes") && !confirm.equals("no"));
                    } while (!confirm.equals("no"));
                    authorlist.exportAuthorList("author.dat");
                    System.out.println("\tReturning Menu!\n");
                    break;
                case 8:
                    System.out.println("\tSEE YOU LATER!");
                    loop = false;
                    break;
            }
        } while (loop);
    }
}
