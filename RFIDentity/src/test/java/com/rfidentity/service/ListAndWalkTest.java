package com.rfidentity.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class ListAndWalkTest {

    public static void main(String args[]) {

        final String startDir = "c:/Network_file";  // change this first!

        System.out.println("Starting Directory = " + startDir);
        final Path path = Paths.get(startDir);

        // maximum directory depth
        // if maxDepth is 0 only the starting file is visited
        final int maxDepth = 100;

        // ------------------------------------------------------------

        System.out.println();
        System.out.println("Files.list");  // list() is not recursive
        try {
            Files.list(path)
                    .forEach(p -> doSomething("list", p));
        } catch (IOException e) {
            log("list", e);
        }

        // ------------------------------------------------------------

        System.out.println();
        System.out.println("Files.walk");
        try {
            Files.walk(path, maxDepth)
                    .forEach(p -> doSomething("walk", p));
        } catch (IOException e) {
            log("walk", e);
        }

    } // end of main

    private static void doSomething(String label, Path path) {
        System.out.println(label + "\t: " + path);
    }

    private static void log(String label, IOException ex) {
        System.out.println(label + "\texception: " + ex);
    }

} // end of class