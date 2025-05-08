package zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class Futil {

    public static void processDir(String dirName, String resultFilename){

        Path resultFile = Paths.get(resultFilename);

        try (BufferedWriter w = Files.newBufferedWriter(resultFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {

            Files.walkFileTree(Paths.get(dirName), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return null;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.toFile()), Charset.forName("Cp1250")))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            w.write(line);
                            w.newLine();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return null;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return null;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
