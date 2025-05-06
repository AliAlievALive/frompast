package org.frompast.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PaginationUtil {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 15;

    public static Pageable getPageable(PageParam pageParam) {
        return PageRequest.of(
                pageParam.getPage() != null && pageParam.getPage() != 0
                        ? pageParam.getPage() - 1
                        : DEFAULT_PAGE,
                pageParam.getSize() != null
                        ? pageParam.getSize()
                        : DEFAULT_PAGE_SIZE);
    }

    public static Pageable getPageable(PageParam pageParam, Sort sort) {
        return PageRequest.of(
                pageParam.getPage() != null && pageParam.getPage() != 0
                        ? pageParam.getPage() - 1
                        : DEFAULT_PAGE,
                pageParam.getSize() != null
                        ? pageParam.getSize()
                        : DEFAULT_PAGE_SIZE,
                sort);
    }
}
