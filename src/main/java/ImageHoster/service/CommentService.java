package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    /**
     * This method calls createComment method in the Repository and passes the comment object to be persisted in the database
     *
     * @param comment
     * @return newly created comment object
     */
    public Comment createComment(Comment comment) {
        return commentRepository.createComment(comment);
    }
}
