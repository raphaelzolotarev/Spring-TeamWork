package com.example.springteamwork.controller;

import com.example.springteamwork.model.Comment;
import com.example.springteamwork.model.Post;
import com.example.springteamwork.model.Role;
import com.example.springteamwork.model.User;
import com.example.springteamwork.service.CommentServiceImpl;
import com.example.springteamwork.service.LikeServiceImpl;
import com.example.springteamwork.service.PostServiceImpl;
import com.example.springteamwork.service.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.jws.soap.SOAPBinding;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOError;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {

    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LikeServiceImpl likeService;


    private final int postPerPage = 6;
    private int currentPage = 1;
    private Long currentAuthorId = 0L;
    private String currentFilter = "new";
    private List<Post> listsOfPosts;
    private int numberOfPages = 1;
    private String currentKeyword = "";


    //SHOW ALL POSTS INITIALIZER
    public String postsInitializer(Model model, int pageId, String filter, String keyword, Long authorId) {

        currentAuthorId = authorId;
        currentKeyword = keyword;

        List<Post> myPostList;

        //NEED TO KNOW NUMBER OF PAGES FOR PAGINATION
        if (currentAuthorId==0) {
            if (!currentKeyword.isEmpty() && currentKeyword.charAt(0)=='#')
                myPostList = postService.getAllPosts()
                        .stream()
                        .filter(post -> post.getTag().toLowerCase().contains(currentKeyword.substring(1).toLowerCase()) )
                        .collect(Collectors.toList());
            else
                myPostList = postService.getAllPosts()
                        .stream()
                        .filter(post ->
                                post.getTitle().toLowerCase().contains(currentKeyword.toLowerCase()) ||
                                        post.getTag().toLowerCase().contains(currentKeyword.toLowerCase()) ||
                                        post.getAuthor().getFirstName().toLowerCase().contains(currentKeyword.toLowerCase()) ||
                                        post.getAuthor().getUsername().toLowerCase().contains(currentKeyword.toLowerCase()) ||
                                        post.getAuthor().getLastName().toLowerCase().contains(currentKeyword.toLowerCase()))
                        .collect(Collectors.toList());
        }
        else {
            if (!currentKeyword.isEmpty() && currentKeyword.charAt(0)=='#')
                myPostList = postService.getAllPosts()
                        .stream()
                        .filter(p -> p.getAuthor().getId() == currentAuthorId)
                        .filter(post -> post.getTag().toLowerCase().contains(currentKeyword.substring(1).toLowerCase()) )
                        .collect(Collectors.toList());
            else
                myPostList = postService.getAllPosts()
                        .stream()
                        .filter(p -> p.getAuthor().getId() == currentAuthorId)
                        .filter(post ->
                                post.getTitle().toLowerCase().contains(currentKeyword.toLowerCase()) ||
                                        post.getTag().toLowerCase().contains(currentKeyword.toLowerCase()) ||
                                        post.getAuthor().getFirstName().toLowerCase().contains(currentKeyword.toLowerCase()) ||
                                        post.getAuthor().getUsername().toLowerCase().contains(currentKeyword.toLowerCase()) ||
                                        post.getAuthor().getLastName().toLowerCase().contains(currentKeyword.toLowerCase()))
                        .collect(Collectors.toList());
        }
        numberOfPages = (int) Math.ceil((double) myPostList.size() / postPerPage);
        numberOfPages = numberOfPages==0 ? 1 : numberOfPages;


        //ONE HOMEPAGE ALWAYS FIRST PAGE
        currentPage = pageId == 0 ? 1 : pageId > numberOfPages ? numberOfPages : pageId;

        //WHAT IS THE CURRENT FILTER
        currentFilter = filter;

        //HOW MANY POSTS TO SKIP
        int numberOfPagesToSkip = currentPage==1 ? 0 : postPerPage*(currentPage-1);

        try {
            //LIST OF POSTS DEFAULT SORTED BY NEW
            listsOfPosts = myPostList
                    .stream()
                    .sorted(Comparator.comparing(Post::getId).reversed())
                    .skip(numberOfPagesToSkip)
                    .limit(postPerPage)
                    .collect(Collectors.toList());

            //IF FILTER -> LIST OF POSTS DEFAULT SORTED BY FILTER VALUE
            if (currentFilter.equals("Oldest")){
                listsOfPosts = myPostList
                        .stream()
                        .sorted(Comparator.comparing(Post::getId))
                        .skip(numberOfPagesToSkip)
                        .limit(postPerPage)
                        .collect(Collectors.toList());
            }

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("current page: "+currentPage);
            System.out.println("numberOfPages: "+numberOfPages);
            System.out.println("numberOfPagesToSkip: "+numberOfPagesToSkip);

        }

        //SEND TO FRONTEND
        model.addAttribute("authors", userService.getAllUsers().stream().filter(u->u.getRole()== Role.AUTHOR));
        model.addAttribute("selectedAuthorId", currentAuthorId);
        model.addAttribute("posts", listsOfPosts);
        model.addAttribute("filter", currentFilter);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("currentKeyword", currentKeyword);

        //RETURN HOMEPAGE
        return "index";
    }

    /*SHOW ALL POSTS IN HOMEPAGE*/
    @GetMapping("/")
    public String showAllPost(@RequestParam(value = "filter", required = false, defaultValue = "new") String filter,
                              @RequestParam(value = "authorId", required = false, defaultValue = "0") String authorId, Model model) {
        return postsInitializer(model, 1, filter, "", Long.parseLong(authorId));
    }
    @GetMapping("/filterBy")
    public String showAllPostWithFilter(@RequestParam(value = "filter", required = false, defaultValue = "new") String filter,
                                        @RequestParam(value = "authorId", required = false, defaultValue = "0") String authorId, Model model) {
        return postsInitializer(model, 1, filter, currentKeyword, Long.parseLong(authorId));
    }

    /*SHOW ALL POSTS OF AUTHOR*/
    @GetMapping("/filterByAuthor")
    public String showAllPostOfAuthor(@RequestParam(value = "filter", required = false, defaultValue = "new") String filter,
                                      @RequestParam(value = "authorId", required = false, defaultValue = "0") String authorId, Model model) {
        return postsInitializer(model, 1, filter, "", Long.parseLong(authorId));
    }

    /*SHOW ALL POSTS WITH PAGINATION*/
    @GetMapping("/pageNumber/{pageId}")
    public String pagination(Model model, @PathVariable(value="pageId") int pageId) {
        return postsInitializer(model, pageId, currentFilter, currentKeyword, currentAuthorId);
    }

    /*SHOW ALL POSTS WITH SEARCH*/
    @PostMapping("/")
    public String searchPost(@RequestParam(value = "search", required = false, defaultValue = "") String search, Model model) {
        return postsInitializer(model, 1, currentFilter, search, currentAuthorId);
    }

    /*SHOW ALL POSTS WITH TAG*/
    @PostMapping("/searchByTag")
    public String searchTag(@RequestParam(value = "search", required = false, defaultValue = "") String search, Model model) {
        return postsInitializer(model, 1, "new", search, 0L);
    }





    /*SHOW ONE POST*/
    @GetMapping("/showPost/{id}")
    public String showOnePost(Model model, @PathVariable(value="id") Long id) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);

        List<Comment> comments = commentService.getAllCommentsByPostId(id);
        model.addAttribute("comments", comments);

        int numberOfLike = (int) likeService.getAllLikes().stream().filter(like -> like.getPost().getId() == id).count();
        model.addAttribute("numberOfLike", numberOfLike);

        return "blogpage";
    }






    /*CREATE POST*/
    @GetMapping("/newPost")
    public String showPostForm(Model model) {
        Post newpost = new Post();
        model.addAttribute("newpost", newpost);
        return "createpost";
    }
    @PostMapping("/newPost")
    public String createPost(@ModelAttribute Post newpost, Model model) {
        try {
            postService.savePost(newpost);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("newpost", new Post());
            return "createpost";
        }
    }



    /*EDIT POST*/
    @GetMapping("/editPost/{id}")
    public String showPostEditForm(@PathVariable Long id, Model model) {
        Post updatePost = postService.getPostById(id);
        model.addAttribute("updatePost", updatePost);
        model.addAttribute("postid", id);
        return "editpost";
    }
    @PostMapping("/editPost")
    public String updatePost(
            @RequestParam("postid") Long postID,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file,
            Model model) {
        try {
            Post updatedPost = postService.getPostById(postID);
            postService.updatePost(updatedPost, title, description, tag, file);
            return "redirect:/showPost/"+postID;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return showPostEditForm(postID, model);
        }
    }




    /*DELETE POST*/
    @GetMapping("/deletePost/{id}")
    public String deleteUser(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/";
    }

}
