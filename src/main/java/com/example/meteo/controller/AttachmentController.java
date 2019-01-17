package com.example.meteo.controller;

import com.example.meteo.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttachmentController {

    AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentController(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }
}
