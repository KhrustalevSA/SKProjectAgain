package com.simplekitchen.project.dao.entity.common;

import com.simplekitchen.project.dao.entity.common.api.LongList;
import lombok.*;

import java.util.List;

/**
 * Класс списка целых чисел
 * @author KhrustalevSA
 * @since 08.02.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LongListImpl implements LongList {

    /**
     * список целых чисел
     */
    private List<Long> longList;
}
