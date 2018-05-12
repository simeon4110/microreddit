package com.microreddit.app.controllers.rest;

import com.microreddit.app.amqp.messages.VoteMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Josh Harkema
 */
@RestController
public class KarmaController {
    private final RabbitTemplate template;

    @Autowired
    public KarmaController(RabbitTemplate template) {
        this.template = template;
    }

    @PutMapping(value = "/vote/post")
    public ResponseEntity<Void> postVote(@RequestParam("postID") String postID, @RequestParam("userID") String userID,
                                         @RequestParam("vote") String vote) {
        try {
            VoteMessage message = new VoteMessage(postID, userID, vote, "post");
            template.convertAndSend("ureddit", "command", message);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/vote/comment")
    public void commentVote(@RequestBody String commentID, @RequestBody String userID, @RequestBody String vote) {


    }


}
