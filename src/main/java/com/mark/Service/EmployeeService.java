package com.mark.Service;

import com.mark.EmployeeDto.EmployeeDto;
import com.mark.Entity.Employe;
import com.mark.Exception.ResourceNotFound;
import com.mark.Repository.EmployeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    ModelMapper modelMapper;

    EmployeRepository employeRepository;

    public EmployeeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public EmployeeDto addemp(EmployeeDto dto) {

        Employe emp = mapToEntity(dto);
        Employe savedEmp = employeRepository.save(emp);
        return mapToEmployeeDto(savedEmp);
    }

    private Employe mapToEntity(EmployeeDto dto) {
        return modelMapper.map(dto, Employe.class);

    }
    private EmployeeDto mapToEmployeeDto(Employe emp) {
        return modelMapper.map(emp, EmployeeDto.class);
    }


    public EmployeeDto deleteemp(Long id) {

        Employe emp = employeRepository.findById(id).get();
        employeRepository.deleteById(id);
        return mapToEmployeeDto(emp);
    }

    public List<EmployeeDto> getall(int pageSize, int pageNo, String sortBy, String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page= PageRequest.of(pageNo, pageSize, sort);
        List<Employe> pageEmployees = employeRepository.findAll(page).getContent();
        List<EmployeeDto> dto=pageEmployees.stream().map(this::mapToEmployeeDto).collect(Collectors.toList());
        return dto;
    }

    public EmployeeDto updateemp( EmployeeDto dto, Long id) {
        Optional<Employe> emp= employeRepository.findById(id);
        if(emp.isPresent()){
           Employe emp1=mapToEntity(dto);
           emp1.setId(id);
            Employe savedEmp=employeRepository.save(emp1);
            return mapToEmployeeDto(savedEmp);
        }
        return null;
    }


    public EmployeeDto getEmployById(Long id) {
        Optional<Employe> emp= Optional.ofNullable(employeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Resource Not Found with id: " + id)));
        return emp.map(this::mapToEmployeeDto).orElse(null);
    }
}
