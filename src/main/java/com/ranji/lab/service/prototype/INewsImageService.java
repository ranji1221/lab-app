package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.NewsImage;

public interface INewsImageService {
    int insertNewsImage(NewsImage newsImage);

    NewsImage findNewsImageById(int id);
}
