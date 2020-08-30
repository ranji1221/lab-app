package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.ResourcePdf;

import java.util.Map;

public interface IResourcePdfService {
    int insertResourcePdf(ResourcePdf resourcePdf);

    int updateResourcePdf(ResourcePdf resourcePdf);

    Map<Object,Object> ResourcePdfInTen();

    Map<Object,Object> ResourcePdfPaging(int pageNum,int pageSize);

    ResourcePdf findResourcePdfById(int id);
}
