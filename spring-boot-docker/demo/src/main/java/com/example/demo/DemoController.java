package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Locale;

@Controller
public class DemoController {

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    String root() {

        StringBuilder sb = new StringBuilder();

        /* Total number of processors or cores available to the JVM */
        sb.append("Available processors (cores): ").append(Runtime.getRuntime().availableProcessors());
        sb.append("\n");

        /* Total amount of free memory available to the JVM */
        sb.append("Free memory: ").append(humanReadableByteCount(Runtime.getRuntime().freeMemory()));
        sb.append("\n");

        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory();
        /* Maximum amount of memory the JVM will attempt to use */
        sb.append("Maximum memory: ").append((maxMemory == Long.MAX_VALUE ? "no limit" : humanReadableByteCount(maxMemory)));
        sb.append("\n");

        /* Total memory currently in use by the JVM */
        sb.append("Total memory: ").append(humanReadableByteCount(Runtime.getRuntime().totalMemory()));
        sb.append("\n");

        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();

        /* For each filesystem root, print some info */
        for (File root : roots) {
            sb.append("File system root: ").append(root.getAbsolutePath());
            sb.append("\n");

            sb.append("Total space: ").append(humanReadableByteCount(root.getTotalSpace()));
            sb.append("\n");

            sb.append("Free space: ").append(humanReadableByteCount(root.getFreeSpace()));
            sb.append("\n");

            sb.append("Usable space: ").append(humanReadableByteCount(root.getUsableSpace()));
            sb.append("\n");

            sb.append("Locale: ").append(Locale.getDefault());
        }

        return sb.toString();
    }

    private String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = ("KMGTPE").charAt(exp - 1) + "i";
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
