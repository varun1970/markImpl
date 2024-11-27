package com.mark.Controller;

import com.mark.EmployeeDto.EmployeeDto;
import com.mark.Entity.Employe;
import com.mark.Service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/employe")
public class EmployeeController {


    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addemp(@Valid  @RequestBody EmployeeDto dto , BindingResult result){
        if (result.hasErrors())
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
       EmployeeDto saveddto= employeeService.addemp(dto);
        System.out.println(saveddto.getName());
        System.out.println("1000");
        System.out.println("1000");
        System.out.println("1000");
        System.out.println("1000");
        System.out.println("1000");
        return new ResponseEntity<>(saveddto, HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<EmployeeDto> deleteemp(@RequestParam Long id){
        EmployeeDto deletedDto= employeeService.deleteemp(id);
        return new ResponseEntity<>(deletedDto, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateemp( @RequestParam Long id  ,@Valid @RequestBody EmployeeDto dto, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
        EmployeeDto updatedDto= employeeService.updateemp(dto,id)  ;
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);


    }


    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getall(
            @RequestParam(name="PageSize",required = false,defaultValue = "5") int pageSize,
            @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,
            @RequestParam(name="sortBy",required = false,defaultValue = "name") String sortBy,
            @RequestParam(name="sortDir",required = false,defaultValue = "asc") String sortDir
    ){
        List<EmployeeDto> allDto= employeeService.getall(pageSize,pageNo,sortBy,sortDir);
        return new ResponseEntity<>(allDto, HttpStatus.OK);
    }

    @GetMapping("/employ/{id}")
    public ResponseEntity<EmployeeDto> getEmployById(@PathVariable Long id){
        EmployeeDto empDto= employeeService.getEmployById(id);
        return new ResponseEntity<>(empDto, HttpStatus.OK);
    }
}
