/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Peter
 */
public class Author {

    private String authorID;
    private String name;
//=============Construtor=======================================================

    public Author(String authorIDFile, String nameFile) {
        this.authorID = authorIDFile;
        this.name = nameFile;
    }

    public Author() {
        authorID = "blank";
        name = "blank";
    }

//=============Getter===========================================================
    public String getAuthorID() {
        return authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//=============End==============================================================
    public void outputAuthorData(int i) {
        System.out.printf(": %-5d: %-13s: %-36s:\n", i + 1, authorID, name);
    }

    public void outputAuthorData() {
        System.out.printf("| %-13s| %-36s|\n", authorID, name);
    }

    @Override
    public String toString() {
        return authorID + "/" + name;
    }

}
