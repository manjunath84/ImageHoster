package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    /**
     * This method creates the given comment for the given image and user.
     *
     * @param imageId     The id of the Image as Path variable
     * @param imageTitle  The title of the Image as Path variable
     * @param commentText The comment text as Request Parameter
     * @param session     The HTTP session object which contains loggeduser data
     * @return the view string
     * @throws IOException
     */
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle,
                                @RequestParam("comment") String commentText, HttpSession session) throws IOException {
        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");
        Comment comment = new Comment(commentText, LocalDate.now(), user, image);
        commentService.createComment(comment);
        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}
