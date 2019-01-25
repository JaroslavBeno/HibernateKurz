package sk.jaroslavbeno.filters;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class VlastnyFIlter extends Filter<ILoggingEvent> {
    String levelElement;
    @Override
    public FilterReply decide(ILoggingEvent event) {
        if(event.getLevel().levelStr.equalsIgnoreCase(levelElement)
        && event.getMessage().contains("jaroslavbeno.sk")){
            return FilterReply.NEUTRAL;
        }
        return FilterReply.DENY;
    }

    public String getLevelElement() {
        return levelElement;
    }

    public void setLevelElement(String levelElement) {
        this.levelElement = levelElement;
    }
}
