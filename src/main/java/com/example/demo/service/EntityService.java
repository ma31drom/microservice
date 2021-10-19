package com.example.demo.service;

import com.example.demo.dto.SomeEntityDTO;
import com.example.demo.dto.SubEntityDTO;
import com.example.demo.dto.SubEntityWithParentDataDTO;
import com.example.demo.model.SomeEntity;
import com.example.demo.model.SubEntity;
import com.example.demo.repo.SomeEntityRepo;
import com.example.demo.repo.SubEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntityService {

    @Autowired
    private SomeEntityRepo repo;
    @Autowired
    private SubEntityRepo subRepo;

    public SomeEntityDTO getEntity(Long id) {
        Optional<SomeEntity> byId = repo.findById(id);
        return byId.map(this::toDTO).orElse(null);
    }


    public void saveEntity(SomeEntityDTO dto) {
        SomeEntity se = new SomeEntity();
        se.setName(dto.getName());
        repo.save(se);
        if (!CollectionUtils.isEmpty(dto.getSubEntities())) {
            List<SubEntity> collect = dto.getSubEntities().stream().map(sseDto -> {
                SubEntity sse = new SubEntity();
                sse.setSubName(sseDto.getSubEntityName());
                sse.setEntity(se);
                return sse;
            }).collect(Collectors.toList());
            se.setSubEntities(collect);
            saveSubEntities(collect);
        }
    }

    private void saveSubEntities(List<SubEntity> collect) {
        subRepo.saveAll(collect);
    }

    public List<SubEntityWithParentDataDTO> getPage(Integer pagenum, Integer pagesize) {

        List<SubEntity> all = subRepo.findPage(PageRequest.of(pagenum, pagesize));
        return all.stream().map(sse -> {
            SubEntityWithParentDataDTO dto = new SubEntityWithParentDataDTO();
            dto.setId(sse.getId());
            dto.setSubEntityName(sse.getSubName());
            dto.setSomeEntityName(sse.getEntity().getName());
            return dto;
        }).collect(Collectors.toList());
    }

    private SomeEntityDTO toDTO(SomeEntity e) {
        SomeEntityDTO dto = new SomeEntityDTO();
        dto.setId(e.getId());
        dto.setName(e.getName());
        if (!CollectionUtils.isEmpty(e.getSubEntities())) {
            dto.setSubEntities(e.getSubEntities().stream().map(se -> {
                SubEntityDTO seDto = new SubEntityDTO();
                seDto.setId(se.getId());
                seDto.setSubEntityName(se.getSubName());
                return seDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }

    public void saveEntities(List<SomeEntityDTO> dto) {
        List<SomeEntity> collect = dto.stream().map(this::toEntity).collect(Collectors.toList());
        repo.saveAll(collect);
        collect.forEach(se -> saveSubEntities(se.getSubEntities()));
    }

    private SomeEntity toEntity(SomeEntityDTO e) {
        SomeEntity se = new SomeEntity();
        se.setName(e.getName());
        if (!CollectionUtils.isEmpty(e.getSubEntities())) {
            List<SubEntity> collect = e.getSubEntities().stream().map(sseDto -> {
                SubEntity sse = new SubEntity();
                sse.setSubName(sseDto.getSubEntityName());
                sse.setEntity(se);
                return sse;
            }).collect(Collectors.toList());
            se.setSubEntities(collect);
        }
        return se;
    }

}
