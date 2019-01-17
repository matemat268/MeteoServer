package com.example.meteo.controller;

import com.example.meteo.entity.Attachment;
import com.example.meteo.entity.Post;
import com.example.meteo.entity.Thread;
import com.example.meteo.repository.AttachmentRepository;
import com.example.meteo.repository.PostRepository;
import com.example.meteo.repository.ThreadRepository;
import com.example.meteo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ThreadController {

    ThreadRepository threadRepository;
    UserRepository userRepository;
    PostRepository postRepository;
    AttachmentRepository attachmentRepository;

    @Autowired
    public ThreadController(ThreadRepository threadRepository, UserRepository userRepository, PostRepository postRepository, AttachmentRepository attachmentRepository) {
        this.threadRepository = threadRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/threads")
    public List<Thread> getThreads(){
        List<Thread> l = threadRepository.findAll();
        for (Thread t : l) {
            for (Post p : t.getPosty()) {
                p.setWatek(null);
            }
        }
        return l;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/threads/{threadId}")
    public Thread getThread(@PathVariable Long threadId){
        Thread toReturn = threadRepository.findById(threadId).get();
        return toReturn;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addThread")
    public Thread addThread(@RequestBody Thread thread) {
        ArrayList<Post> l  = new ArrayList<>(thread.getPosty());
        Post post = l.get(0);
        thread.setPosty(null);
        thread.setData(new Date());
        thread.setUzyt(userRepository.findById(new Long(1)).get());
        thread = threadRepository.save(thread);
        post.setWatek(thread);
        post.setNr_postu(1);
        post.setData(new Date());
        post.setUzyt(userRepository.findById(new Long(1)).get());
        if(post.getZalaczniki() != null) {
            ArrayList<Attachment> a = new ArrayList<>(post.getZalaczniki());
            post.setZalaczniki(null);
            post = postRepository.save(post);
            for (Attachment at : a) {
                at.setPost(post);
                attachmentRepository.save(at);
            }
        }
        postRepository.save(post);
        return thread;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addPost")
    public Post addPost(@RequestBody Post post) {
        post.setNr_postu(postRepository.getMaxNrPostu(post.getWatek().getId_watku())+1);
        post.setData(new Date());
        post.setUzyt(userRepository.findById(post.getUzyt().getId_uzyt()).get());
        postRepository.save(post);
        return post;
    }
}
