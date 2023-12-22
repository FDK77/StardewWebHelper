package com.example.stardewweb.other;

import java.io.File;

public class FileWatcher {
    private final String filePath;
    private volatile boolean running;

    public FileWatcher(String filePath) {
        this.filePath = filePath;
        this.running = true;
    }

    public boolean startWatching() {
        Thread fileWatcherThread = new Thread(this::watchFileChanges);
        fileWatcherThread.start();

        // Ждем, пока поток отслеживания изменений не завершится (не обязательно)
        try {
            fileWatcherThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true; // Метод вернет true после завершения отслеживания изменений
    }

    public void stopWatching() {
        running = false;
    }

    private void watchFileChanges() {
        try {
            File file = new File(filePath);
            long previousLastModified = file.lastModified();

            while (running) {
                // Проверяем, изменилась ли временная метка последнего изменения файла
                if (file.lastModified() != previousLastModified) {
                    previousLastModified = file.lastModified();
                    return;
                }

                // Ждем некоторое время перед следующей проверкой
                Thread.sleep(5000); // Например, подождать 5 секунд
            }

            System.out.println("Поток отслеживания изменений завершен.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
