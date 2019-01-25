package sk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("jaroslavbeno.sk log 1");
        logger.trace("bez linku log 2");
        logger.error("jaroslavbeno.sk log 3");
        logger.error("jaroslavbeno.sk log 4");

    }
}
