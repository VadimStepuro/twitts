package com.stepuro.twitts.repositories;

import java.util.List;

public interface RepositoryInterface<T> {
    List<T> read(String path);
}
