/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Author;
import dto.Book;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import manager.BookList;

/**
 *
 * @author Peter
 */
public class TextFileDAO {

    // Ham ghi BookList vao File
    public static void writeFileBook(Book[] list, int count, String filename) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(filename);
            for (int i = 0; i < count; i++) {
                String temp = list[i].toString();
                printWriter.println(temp);
            }
        } catch (Exception e) {
            System.out.println("\tSomething wrong!\n");
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (Exception e) {
                System.out.println("\tCannot close File!\n");
            }
        }
    }

    // Ham nay de mo file doc noi dung va convert thanh array Book
    public static Book[] readFileBook(String filename) {
        Book[] arrayBook = null;
        ArrayList<Book> result = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null; // dung de doc 1 hang       
        try {
            fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                String temp = bufferedReader.readLine(); //Đọc từng dòng trong file 
                if (temp != null && !temp.isEmpty()) {
                    String[] array = temp.split("/");
                    if (array.length == 4) {
                        Book fileData = new Book(array[0], array[1], parseDouble(array[2]), array[3]);
                        result.add(fileData);
                    }
                }
            }
            arrayBook = new Book[result.size()];
            arrayBook = result.toArray(arrayBook);
        } catch (Exception e) {
            BookList book = new BookList();
            book.exportBookList("book.dat");
            System.out.println("\tPlease restart again!\n");
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

            } catch (Exception e) {
                System.out.println("\tCannot close File!\n");

            }
        }
        return arrayBook;
    }

    // Ham nay de mo file doc noi dung va convert thanh array Author
    public static Author[] readFileAuthor(String filename) {
        Author[] arrayAuthor = null;
        ArrayList<Author> result = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null; // dung de doc 1 hang       
        try {
            fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                String temp = bufferedReader.readLine(); //Đọc từng dòng trong file 
                if (temp != null && !temp.isEmpty()) {
                    String[] array = temp.split("/");
                    if (array.length == 2) {
                        Author fileData = new Author(array[0], array[1]);
                        result.add(fileData);
                    }
                }
            }
            arrayAuthor = new Author[result.size()];
            arrayAuthor = result.toArray(arrayAuthor);
        } catch (Exception e) {
            System.out.println("\tCannot find author.dat!\n");
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

            } catch (Exception e) {
                System.out.println("\tCannot close File!\n");

            }
        }
        return arrayAuthor;
    }

    // Ham ghi BookList vao File
    public static void writeFileAuthor(Author[] list, int count, String filename) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(filename);
            for (int i = 0; i < count; i++) {
                String temp = list[i].toString();
                printWriter.println(temp);
            }
        } catch (Exception e) {
            System.out.println("\tSomething wrong!\n");
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (Exception e) {
                System.out.println("\tCannot close File!\n");
            }
        }
    }
}
