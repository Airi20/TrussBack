package com.airi.trussforce.demo.controller;

import com.airi.trussforce.demo.dto.NodeDto;
import com.airi.trussforce.demo.dto.MemberDto;
import com.airi.trussforce.demo.dto.TrussInput;
import com.airi.trussforce.demo.dto.TrussResult;
import com.airi.trussforce.demo.converter.NodeConverter;
import com.airi.trussforce.demo.converter.MemberConverter;
import com.airi.trussforce.demo.converter.ResultConverter;
import com.airi.trussforce.demo.service.TrussForceSolver;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrussForceController {

}