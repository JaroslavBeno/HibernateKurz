package sk.jaroslavbeno.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

    private static Logger logger = LoggerFactory.getLogger(Message.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Date CREATE_DATE;

    public Message() {
    }

    public Message(String message) {
        logger.debug("som v konštruktore Message debug");
        logger.warn("som v konštruktore Message warn");
        logger.error("som v konštruktore Message error");
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(Date CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }
}


//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "daco_SEQ")
//    @SequenceGenerator(name = "daco_SEQ", sequenceName = "daco_SEQ", allocationSize = 1)
//    private Long id;

