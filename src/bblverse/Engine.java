package bblverse;

import java.util.*;
import java.io.*;

class Engine {
    static Timer timer;
    
    static Random random = new Random();

    static void setup() {
        timer = new Timer();
        if (!loadVerses()) {
            System.err.println("Could not load verses.");
            System.exit(2);
        }
    }

    static void shutdown() {
        timer.cancel();
    }

    static final int VERSE_COUNT = 3200; // 3096 actually
    static List<String> verses = new ArrayList<String>();

    private static boolean loadVerses() {
        InputStream verseStream = Main.class.getClassLoader().getResourceAsStream("bblverse/text/verses_hu.txt");
        if (verseStream == null) {
            System.err.println("Could not find verse file.");
            return false;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(verseStream));
        String s;
        try {
            while ((s=in.readLine()) != null) {
                verses.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (verses.size() == 0) {
            return false;
        }
        return true;
    }

    static void fetchNewVerse() {
        saveOldVerse();
        index = random.nextInt(verses.size());
        String line = verses.get(index);
        int p1 = line.indexOf(':'),
            p2 = line.indexOf(':', p1+1);
        cite = line.substring(0, p2); // so that second colon is not included
        verse = line.substring(p2+1); // so that space after colon is included
    }


    static ArrayList<Integer> history = new ArrayList<Integer>();

    private static void saveOldVerse() {
        if (index != -1) {
            history.add(index);
        }
    }

    static int index = -1;
    static String verse;
    static String cite;

    static int readingSpeed = 100; // words per min
    static int thinkingTime = 5; // seconds
    static final int AVG_WORD_LENGTH = 5;

    static long readingTime() {
        return verse.length() * 1000L * 60 / (readingSpeed * AVG_WORD_LENGTH) + 1000L * thinkingTime;
    }

    static int minWaitingTime = 20;
    static int maxWaitingTime = 6*60;

    static long waitingTime() {
        return 1000L * (long)(minWaitingTime + random.nextDouble() * (maxWaitingTime - minWaitingTime));
    }


}
